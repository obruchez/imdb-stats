package org.bruchez.olivier.imdbstats

import java.nio.file.Paths

import scala.annotation.tailrec

object GeneratePlots {
  def main(args: Array[String]): Unit = {
    dumpRatingFrequencies(filename = "rating-frequencies.tsv")

    dumpYearFrequencies(filename = "year-frequencies.2025.tsv", yearsFilter = _.filter(_ <= 2025))

    dumpDurationFrequencies(filename = "duration-frequencies.300.tsv",
                            durationsFilter = _.filter(_ <= 300))

    dumpMovieDurationFrequencies(filename = "duration-frequencies.movies.tsv",
                                 durationsFilter = _.filter(_ <= 240))

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.tsv",
                                   countsFilter = _.map(_.toDouble))

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.95.tsv",
                                   countsFilter = counts => {
                                     counts.sorted.take(95 * counts.size / 100).map(_.toDouble)
                                   })

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.log10.tsv",
                                   countsFilter = _.map(count => math.log10(count)))

    for (voteCount <- Seq(5, 10, 100, 1000, 10000)) {
      dumpMinimumRatings(filename = s"minimum-ratings.$voteCount.tsv",
                         titleType = ImdbStats.MovieType,
                         voteCount = voteCount)
    }
  }

  def dumpFrequencies(filename: String, values: Seq[Double], intervalCount: Int): Unit =
    ValuesAndStats(values).dumpFrequenciesToGnuplotFile(Paths.get(filename), intervalCount)

  def dumpRatingFrequencies(filename: String): Unit =
    dumpFrequencies(filename, ImdbStats.titleRatings.map(_.rating), intervalCount = 40)

  def dumpYearFrequencies(filename: String, yearsFilter: (Seq[Double]) => Seq[Double]): Unit =
    dumpFrequencies(filename,
                    yearsFilter(ImdbStats.titleYears.map(_.toDouble)),
                    intervalCount = 50)

  def dumpDurationFrequencies(filename: String,
                              durationsFilter: (Seq[Double]) => Seq[Double]): Unit =
    dumpFrequencies(filename,
                    durationsFilter(ImdbStats.titleDurations.map(_.toDouble)),
                    intervalCount = 50)

  def dumpMovieDurationFrequencies(filename: String,
                                   durationsFilter: (Seq[Double]) => Seq[Double]): Unit =
    dumpFrequencies(filename,
                    durationsFilter(ImdbStats.movieDurations.map(_.toDouble)),
                    intervalCount = 50)

  def dumpRatingVoteCountFrequencies(filename: String,
                                     countsFilter: (Seq[Int]) => Seq[Double]): Unit =
    dumpFrequencies(filename,
                    countsFilter(ImdbStats.titleRatings.map(_.voteCount)),
                    intervalCount = 30)

  def dumpMinimumRatings(filename: String, titleType: String, voteCount: Int): Unit = {
    val sortedRatings = (for {
      titleInfo <- ImdbStats.titleInfos
      if titleInfo.titleType == titleType
      titleRating <- ImdbStats.titleRatingsById.get(titleInfo.id)
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

    FileUtils.writeStrings(Paths.get(filename),
                           titleCountsAndRatings(sortedRatings).map(kv => s"${kv._1}\t${kv._2}"))
  }
}
