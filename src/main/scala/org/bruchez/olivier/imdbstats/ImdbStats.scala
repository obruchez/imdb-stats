package org.bruchez.olivier.imdbstats

import java.nio.file.Paths

import scala.annotation.tailrec

object ImdbStats {
  val BasicsFilename = "title.basics.tsv.gz"
  val RatingsFilename = "title.ratings.tsv.gz"

  val MovieType = "movie"
  val ShortType = "short"

  val titleTypeDescriptions = Map(
    "tvEpisode" -> "TV episode",
    "short" -> "Short film",
    "movie" -> "Feature film",
    "video" -> "Video",
    "tvSeries" -> "TV series",
    "tvMovie" -> "TV movie",
    "tvMiniSeries" -> "TV mini-series",
    "videoGame" -> "Video game",
    "tvSpecial" -> "TV special",
    "tvShort" -> "TV short"
  )

  lazy val titleRatings: Seq[TitleRating] =
    FileUtils.fromTsvGz[TitleRating](
      Paths.get(RatingsFilename),
      row => Some(TitleRating(row))
    )

  lazy val titleRatingsById: Map[String, TitleRating] =
    titleRatings.groupBy(_.id).map(kv => kv._1 -> kv._2.head)

  lazy val titleInfos: Seq[TitleInfo] =
    FileUtils.fromTsvGz[TitleInfo](
      Paths.get(BasicsFilename),
      row => Some(TitleInfo(row))
    )

  lazy val titleInfosById: Map[String, TitleInfo] =
    titleInfos.groupBy(_.id).map(kv => kv._1 -> kv._2.head)

  lazy val titleYears: Seq[Int] =
    titleYearsFromTitleInfos(titleInfos).toSeq

  lazy val titleDurations: Seq[Int] =
    titleInfos.flatMap(_.runtimeMinutes)

  lazy val movieDurations: Seq[Int] =
    titleInfos.filter(_.titleType == MovieType).flatMap(_.runtimeMinutes)

  lazy val shortDurations: Seq[Int] =
    titleInfos.filter(_.titleType == ShortType).flatMap(_.runtimeMinutes)

  private def titleYearsFromTitleInfos(titleInfos: Iterable[TitleInfo]): Iterable[Int] = {
    val yearCounts = collection.mutable.Map[Int, Double]()

    // The logic here is to distribute a weight of 1.0 over all the years in range [startYear, endYear]

    for {
      titleInfo <- titleInfos
      startYear <- titleInfo.startYear orElse titleInfo.endYear
      endYear <- titleInfo.endYear orElse titleInfo.startYear
      weight = 1.0 / (endYear - startYear + 1.0)
    } {
      for (yearToUpdate <- startYear to endYear) {
        val currentValue = yearCounts.getOrElse(yearToUpdate, 0.0)
        yearCounts.update(yearToUpdate, currentValue + weight)
      }
    }

    for {
      (year, count) <- yearCounts
      value <- Seq.fill(count.round.toInt)(year)
    } yield value
  }

  def minimumRatingsByTitleCount(titleType: String, voteCount: Int): Map[Int, Double] = {
    val sortedRatings = (for {
      titleInfo <- titleInfos
      if titleInfo.titleType == titleType
      titleRating <- titleRatingsById.get(titleInfo.id)
      if titleRating.voteCount >= voteCount
    } yield titleRating.rating).sorted.reverse

    // Example:
    //
    // 10    10    10    10       9     9       8    8    8      ...
    // 0->11 1->11 2->11 3->11    4->10 5->10   6->9 7->9 8->9   ...

    @tailrec
    def titleCountsAndRatings(remainingRatings: Seq[Double],
                              acc: Seq[(Int, Double)] = Seq(),
                              previousRatingOption: Option[Double] = None): Seq[(Int, Double)] =
      if (remainingRatings.isEmpty) {
        acc
      } else {
        val rating = remainingRatings.head
        val titleWithRatingCount = remainingRatings.takeWhile(_ == rating).size

        val previousRating = previousRatingOption.getOrElse(rating + 0.1)

        val newRemainingRatings = remainingRatings.drop(titleWithRatingCount)
        val newAcc = acc ++ (acc.size until acc.size + titleWithRatingCount).map(count =>
          count -> previousRating)
        titleCountsAndRatings(newRemainingRatings, newAcc, previousRatingOption = Some(rating))
      }

    titleCountsAndRatings(sortedRatings).toMap
  }
}
