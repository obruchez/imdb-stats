package org.bruchez.olivier.imdbstats

import java.nio.file.Paths
import java.text.SimpleDateFormat

object GenerateReadme {
  def main(args: Array[String]): Unit = {
    val templateContents = this.templateContents()

    val substitutions = Seq(
      "date" -> lastModifiedDateString(),
      "titleCount" -> ImdbStats.titleInfos.size.asString,
      "titleTypesWithCounts" -> titleTypesWithCounts(),
      "titleWithYearCount" -> titleWithYearCount.asString,
      "titleWithYearPercentage" -> titleWithYearCount.asPercentage(ImdbStats.titleInfos.size),
      "yearStats" -> yearStats(),
      "titleWithRatingCount" -> titleWithRatingCount.asString,
      "titleWithRatingPercentage" -> titleWithRatingCount.asPercentage(ImdbStats.titleInfos.size),
      "ratingStats" -> ratingStats(),
      "voteCountStats" -> voteCountStats(),
      "voteCountPercentages" -> voteCountPercentages(),
      "titlesWithMostVotes" -> titlesWithMostVotes()
    )
    val updatedContents = withSubstitutions(templateContents, substitutions)

    saveReadme(updatedContents)
  }

  implicit class IntOps(value: Int) {
    def asString: String = f"$value%,d"

    def asPercentage(total: Int): String = f"${value.toDouble / total * 100.0}%.2f%%"
  }

  def templateContents(): String =
    FileUtils.fileContents(Paths.get("README.template.md"))

  def saveReadme(contents: String): Unit =
    FileUtils.writeString(Paths.get("README.md"), contents)

  def withSubstitutions(contents: String, substitutions: Seq[(String, String)]): String =
    substitutions.foldLeft(contents) {
      case (string, (target, replacement)) => string.replace("$" + target, replacement)
    }

  def lastModifiedDateString(): String =
    new SimpleDateFormat("MMMM dd, yyyy")
      .format(FileUtils.lastModifiedDate(Paths.get(ImdbStats.BasicsFilename)))

  def titleTypesWithCounts(): String = {
    val titleTypesAndCounts =
      ImdbStats.titleInfosById.values
        .groupBy(_.titleType)
        .map(kv => kv._1 -> kv._2.size)
        .toSeq
        .sortBy(_._2)
        .reverse

    (for ((titleType, titleCounts) <- titleTypesAndCounts)
      yield f" - ${ImdbStats.titleTypeDescriptions(titleType)}: $titleCounts%,d").mkString("\n")
  }

  lazy val titleWithYearCount: Int = ImdbStats.titleInfosById.values.count { titleInfo =>
    titleInfo.startYear.isDefined || titleInfo.endYear.isDefined
  }

  def yearStats(): String =
    ValuesAndStats(
      ImdbStats
        .titleYearsFromTitleInfos(ImdbStats.titleInfosById.values)
        .toSeq
        .map(_.toDouble)).stats.asStrings(withCount = false).mkString("\n")

  lazy val titleWithRatingCount: Int = ImdbStats.titleRatings.size

  def ratingStats(): String =
    ValuesAndStats(ImdbStats.titleRatings.map(_.rating)).stats
      .asStrings(withCount = false)
      .mkString("\n")

  lazy val voteCountValuesAndStats: ValuesAndStats =
    ValuesAndStats(ImdbStats.titleRatings.map(_.voteCount.toDouble))

  def voteCountStats(): String =
    voteCountValuesAndStats.stats.asStrings(withCount = false).mkString("\n")

  def voteCountPercentages(): String = {
    val minCounts = Seq(10, 100, 1000, 10000, 100000, 1000000)

    (for (minCount <- minCounts) yield {
      val counts = voteCountValuesAndStats.values.count(_ >= minCount)
      f"- votes >= $minCount%,d: $counts%,d (${counts * 100.0 / voteCountValuesAndStats.values.size}%.2f%%)"
    }).mkString("\n")
  }

  def titlesWithMostVotes(threshold: Int = 1000000): String =
    (for {
      (titleRating, index) <- ImdbStats.titleRatings
        .sortBy(_.voteCount)
        .reverse
        .takeWhile(_.voteCount >= threshold)
        .zipWithIndex
      titleInfo = ImdbStats.titleInfosById(titleRating.id)
      url = s"http://www.imdb.com/title/${titleRating.id}/"
    } yield f" ${index + 1}. ${titleRating.voteCount}%,d votes: [${titleInfo.primaryTitle}]($url)")
      .mkString("\n")
}
