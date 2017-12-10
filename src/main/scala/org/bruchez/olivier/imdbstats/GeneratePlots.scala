package org.bruchez.olivier.imdbstats

import java.nio.file.Paths

object GeneratePlots {
  def main(args: Array[String]): Unit = {
    dumpRatingFrequencies(filename = "rating-frequencies.tsv", ratingsFilter = ratings => ratings)

    dumpYearFrequencies(filename = "year-frequencies.2025.tsv", yearsFilter = _.filter(_ <= 2025))

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.tsv",
                                   countsFilter = _.map(_.toDouble))

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.95.tsv",
                                   countsFilter = counts => {
                                     counts.sorted.take(95 * counts.size / 100).map(_.toDouble)
                                   })

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.log10.tsv",
                                   countsFilter = _.map(count => math.log10(count)))
  }

  def dumpRatingFrequencies(filename: String, ratingsFilter: (Seq[Double]) => Seq[Double]): Unit = {
    val IntervalCount = 40

    val valuesAndStats = ValuesAndStats(ratingsFilter(ImdbStats.titleRatings.map(_.rating)))

    valuesAndStats.dumpFrequenciesToGnuplotFile(Paths.get(filename), intervalCount = IntervalCount)
  }

  def dumpYearFrequencies(filename: String, yearsFilter: (Seq[Double]) => Seq[Double]): Unit = {
    val IntervalCount = 50

    val valuesAndStats =
      ValuesAndStats(
        yearsFilter(
          ImdbStats.titleYearsFromTitleInfos(ImdbStats.titleInfos).toSeq.map(_.toDouble)))

    valuesAndStats.dumpFrequenciesToGnuplotFile(Paths.get(filename), intervalCount = IntervalCount)
  }

  def dumpRatingVoteCountFrequencies(filename: String,
                                     countsFilter: (Seq[Int]) => Seq[Double]): Unit = {
    val IntervalCount = 30

    val valuesAndStats = ValuesAndStats(countsFilter(ImdbStats.titleRatings.map(_.voteCount)))

    valuesAndStats.dumpFrequenciesToGnuplotFile(Paths.get(filename), intervalCount = IntervalCount)
  }
}
