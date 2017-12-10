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
      "titleWithDurationCount" -> titleWithDurationCount.asString,
      "titleWithDurationPercentage" -> titleWithDurationCount.asPercentage(
        ImdbStats.titleInfos.size),
      "durationStats" -> durationStats(),
      "movieDurationStats" -> movieDurationStats(),
      "shortDurationStats" -> shortDurationStats(),
      "titleWithRatingCount" -> titleWithRatingCount.asString,
      "titleWithRatingPercentage" -> titleWithRatingCount.asPercentage(ImdbStats.titleInfos.size),
      "ratingStats" -> ratingStats(),
      "voteCountStats" -> voteCountStats(),
      "voteCountPercentages" -> voteCountPercentages(),
      "titlesWithMostVotes" -> titlesWithMostVotes(),
      "minimumRatings" -> minimumRatings(ImdbStats.MovieType)
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
      ImdbStats.titleInfos
        .groupBy(_.titleType)
        .map(kv => kv._1 -> kv._2.size)
        .toSeq
        .sortBy(_._2)
        .reverse

    (for ((titleType, titleCounts) <- titleTypesAndCounts)
      yield f" - ${ImdbStats.titleTypeDescriptions(titleType)}: $titleCounts%,d").mkString("\n")
  }

  lazy val titleWithYearCount: Int = ImdbStats.titleInfos.count { titleInfo =>
    titleInfo.startYear.isDefined || titleInfo.endYear.isDefined
  }

  def yearStats(): String =
    ValuesAndStats(ImdbStats.titleYears.map(_.toDouble)).stats
      .asStrings(withCount = false)
      .mkString("\n")

  lazy val titleWithDurationCount: Int = ImdbStats.titleDurations.size

  def durationStats(): String =
    ValuesAndStats(ImdbStats.titleDurations.map(_.toDouble)).stats
      .asStrings(withCount = false)
      .mkString("\n")

  def movieDurationStats(): String =
    ValuesAndStats(ImdbStats.movieDurations.map(_.toDouble)).stats
      .asStrings(withCount = true)
      .mkString("\n")

  def shortDurationStats(): String =
    ValuesAndStats(ImdbStats.shortDurations.map(_.toDouble)).stats
      .asStrings(withCount = true)
      .mkString("\n")

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
    } yield
      f" ${index + 1}. ${titleRating.voteCount}%,d votes: [${titleInfo.primaryTitle}](${titleInfo.url})")
      .mkString("\n")

  def minimumRatings(titleType: String, titleCount: Int = 1500): String = {
    val minVoteCounts = Seq(5, 10, 100, 1000, 10000, 100000)

    (for {
      minVoteCount <- minVoteCounts
      minRating <- ImdbStats.minimumRatingsByTitleCount(titleType, minVoteCount).get(titleCount)
    } yield {
      f" - votes >= $minVoteCount%,d ⇒ rating >= $minRating%.2f"
    }).mkString("\n")
  }
}
