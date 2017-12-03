package org.bruchez.olivier.imdbstats

import java.nio.file.Paths

object GeneratePlots {
  def main(args: Array[String]): Unit = {
    dumpRatingRatingFrequencies(filename = "rating-frequencies.tsv",
                                ratingsFilter = ratings => ratings)

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.tsv",
                                   countsFilter = _.map(_.toDouble))

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.95.tsv",
                                   countsFilter = counts => {
                                     counts.sorted.take(95 * counts.size / 100).map(_.toDouble)
                                   })

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.log10.tsv",
                                   countsFilter = _.map(count => math.log10(count)))
  }

  def dumpRatingRatingFrequencies(filename: String,
                                  ratingsFilter: (Seq[Double]) => Seq[Double]): Unit = {
    val IntervalCount = 30

    val titleRatings = ImdbStats.titleRatings()

    val valuesAndStats = ValuesAndStats(ratingsFilter(titleRatings.map(_.rating)))

    valuesAndStats.dumpFrequenciesToGnuplotFile(Paths.get(filename), intervalCount = IntervalCount)
  }

  def dumpRatingVoteCountFrequencies(filename: String,
                                     countsFilter: (Seq[Int]) => Seq[Double]): Unit = {
    val IntervalCount = 30

    val titleRatings = ImdbStats.titleRatings()

    val valuesAndStats = ValuesAndStats(countsFilter(titleRatings.map(_.voteCount)))

    valuesAndStats.dumpFrequenciesToGnuplotFile(Paths.get(filename), intervalCount = IntervalCount)
  }
}
