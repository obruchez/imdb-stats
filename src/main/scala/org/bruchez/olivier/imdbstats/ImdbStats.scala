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

  // scalastyle:off
  def test(): Unit = {
    val ratingVoteCounts = this.titleRatings().map(_.voteCount).sorted

    val minVoteCount = ratingVoteCounts.head
    val maxVoteCount = ratingVoteCounts.last
    val medianVoteCount = ratingVoteCounts(ratingVoteCounts.size / 2)
    val averageVoteCount = ratingVoteCounts.sum.toDouble / ratingVoteCounts.size
    val topFivePercentilCount = ratingVoteCounts(ratingVoteCounts.size * 95 / 100)

    println(
      s"Rating vote counts: min = $minVoteCount, max = $maxVoteCount, median = $medianVoteCount, average = $averageVoteCount")
    println(s"topFivePercentilCount = $topFivePercentilCount")

    val MinimumVoteCount = 10000

    val ratingsById =
      this.ratingsById(voteCountThreshold = Some(MinimumVoteCount))

    println(s"Rating count ($MinimumVoteCount): ${ratingsById.size}")

    val MinimumRuntimeInMinutes = 60

    val filteredIds = this.filteredIds(titleInfo => {
      titleInfo.titleType == MovieType &&
      titleInfo.runtimeMinutes.exists(_ >= MinimumRuntimeInMinutes)
    })

    println(s"Filtered movie count: ${filteredIds.size}")

    val filteredRatings = ratingsById.toSeq
      .filter(kv => filteredIds.contains(kv._1))
      .map(_._2)
      .sorted

    println(s"Filtered rating count: ${filteredRatings.size}")

    val MovieThatICanWatchCount = 1290

    val minimumImdbRating =
      filteredRatings.takeRight(MovieThatICanWatchCount).head

    val minimumImdbRatingMovieCount =
      filteredRatings.count(_ >= minimumImdbRating)

    println(s"Minimum IMDb rating: $minimumImdbRating (=> $minimumImdbRatingMovieCount movies)")

    for (rating <- 70 to 100 by 1) {
      val count = filteredRatings.count(_ >= rating)
      println(s"Movie >= $rating count: $count")
    }
  }
  // scalastyle:on

  def filteredIds(f: TitleInfo => Boolean): Set[String] = {
    val ids = collection.mutable.Set[String]()

    FileUtils.fromTsvGz[Unit](Paths.get(BasicsFilename), row => {
      val titleInfo = TitleInfo(row)
      if (f(titleInfo)) {
        ids.add(titleInfo.id)
      }
      None
    })

    ids.toSet
  }

  def titleRatings(): Seq[TitleRating] =
    FileUtils.fromTsvGz[TitleRating](
      Paths.get(RatingsFilename),
      row => Some(TitleRating(row))
    )

  def titleInfos(): Seq[TitleInfo] =
    FileUtils.fromTsvGz[TitleInfo](
      Paths.get(BasicsFilename),
      row => Some(TitleInfo(row))
    )

  // Rating * 10
  def ratingsById(voteCountThreshold: Option[Int] = None): Map[String, Int] =
    this
      .titleRatings()
      .filter { titleRating =>
        voteCountThreshold.map(titleRating.voteCount >= _).getOrElse(true)
      }
      .map(kv => kv.id -> kv.ratingMultipliedByTen)
      .toMap
}
