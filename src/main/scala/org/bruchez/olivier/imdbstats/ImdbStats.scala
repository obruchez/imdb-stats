package org.bruchez.olivier.imdbstats

import java.nio.file.Paths

object ImdbStats {
  val BasicsFilename = "title.basics.tsv.gz"
  val RatingsFilename = "title.ratings.tsv.gz"

  val MovieType = "movie"

  val NoValue = "\\N"

  def main(args: Array[String]): Unit = {
    dumpRatingVoteCountFrequencies(filename =
                                     "rating-vote-count-frequencies.tsv",
                                   countsFilter = _.map(_.toDouble))

    dumpRatingVoteCountFrequencies(
      filename = "rating-vote-count-frequencies.log10.tsv",
      countsFilter = _.map(count => math.log10(count)))

    dumpRatingVoteCountFrequencies(
      filename = "rating-vote-count-frequencies.95.tsv",
      countsFilter = counts => {
        counts.sorted.take(95 * counts.size / 100).map(_.toDouble)
      })
  }

  def dumpRatingVoteCountFrequencies(
      filename: String,
      countsFilter: (Seq[Int]) => Seq[Double]): Unit = {
    val IntervalCount = 30

    val valuesAndStats = ValuesAndStats(countsFilter(this.ratingVoteCounts()))

    valuesAndStats.stats.asStrings.foreach(println)

    valuesAndStats.dumpFrequenciesToGnuplotFile(Paths.get(filename),
                                                intervalCount = IntervalCount)
  }

  // scalastyle:off
  def test(): Unit = {
    val ratingVoteCounts = this.ratingVoteCounts().sorted

    val minVoteCount = ratingVoteCounts.head
    val maxVoteCount = ratingVoteCounts.last
    val medianVoteCount = ratingVoteCounts(ratingVoteCounts.size / 2)
    val averageVoteCount = ratingVoteCounts.sum.toDouble / ratingVoteCounts.size
    val topFivePercentilCount = ratingVoteCounts(
      ratingVoteCounts.size * 95 / 100)

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

    println(
      s"Minimum IMDb rating: $minimumImdbRating (=> $minimumImdbRatingMovieCount movies)")

    for (rating <- 70 to 100 by 1) {
      val count = filteredRatings.count(_ >= rating)
      println(s"Movie >= $rating count: $count")
    }
  }
  // scalastyle:on

  case class TitleInfo(id: String,
                       titleType: String,
                       primaryTitle: String,
                       originalTitle: String,
                       isAdult: Boolean,
                       startYear: Option[Int],
                       endYear: Option[Int],
                       runtimeMinutes: Option[Int],
                       genres: Seq[String])

  object TitleInfo {
    def apply(row: Array[AnyRef]): TitleInfo = {
      val strings = row.map(_.toString)

      // scalastyle:off magic.number
      TitleInfo(
        id = strings(0),
        titleType = strings(1),
        primaryTitle = strings(2),
        originalTitle = strings(3),
        isAdult = strings(4).toInt != 0,
        startYear = Some(strings(5)).filter(_ != NoValue).map(_.toInt),
        endYear = Some(strings(6)).filter(_ != NoValue).map(_.toInt),
        runtimeMinutes = Some(strings(7)).filter(_ != NoValue).map(_.toInt),
        genres = strings(8).split(',').map(_.trim)
      )
      // scalastyle:on magic.number
    }
  }

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

  // Rating * 10
  def ratingsById(voteCountThreshold: Option[Int] = None): Map[String, Int] = {
    val mutableRatingsById = collection.mutable.Map[String, Int]()

    FileUtils.fromTsvGz[Unit](
      Paths.get(RatingsFilename),
      row => {
        val rating = (row(1).toString.toDouble * 10).round.toInt
        val voteCount = row(2).toString.toInt

        if (voteCountThreshold.map(_ <= voteCount).getOrElse(true)) {
          mutableRatingsById(row(0).toString) = rating
        }

        None
      }
    )

    mutableRatingsById.toMap
  }

  // Rating * 10
  def ratingVoteCounts(): Seq[Int] = {
    val mutableCounts = collection.mutable.Buffer[Int]()

    FileUtils.fromTsvGz[Unit](
      Paths.get(RatingsFilename),
      row => {
        val voteCount = row(2).toString.toInt
        mutableCounts.append(voteCount)

        None
      }
    )

    mutableCounts
  }
}
