package org.bruchez.olivier.imdbstats

import java.nio.file.Paths

object ImdbStats {
  val BasicsFilename = "title.basics.tsv.gz"
  val RatingsFilename = "title.ratings.tsv.gz"

  val MovieType = "movie"

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
}
