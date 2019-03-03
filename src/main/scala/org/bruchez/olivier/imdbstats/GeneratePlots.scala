package org.bruchez.olivier.imdbstats

import java.nio.file.Paths

object GeneratePlots {
  def main(args: Array[String]): Unit = {
    dumpRatingFrequencies(filename = "rating-frequencies.tsv")

    dumpYearFrequencies(filename = "year-frequencies.2025.tsv", yearsFilter = _.filter(_ <= 2025))

    dumpDurationFrequencies(filename = "duration-frequencies.300.tsv",
                            durationsFilter = _.filter(_ <= 300))

    dumpMovieDurationFrequencies(filename = "duration-frequencies.movies.tsv",
                                 durationsFilter = _.filter(_ <= 240))

    dumpShortDurationFrequencies(filename = "duration-frequencies.shorts.tsv",
                                 durationsFilter = _.filter(_ <= 60))

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.tsv",
                                   countsFilter = _.map(_.toDouble))

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.95.tsv",
                                   countsFilter = counts => {
                                     counts.sorted.take(95 * counts.size / 100).map(_.toDouble)
                                   })

    dumpRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.log10.tsv",
                                   countsFilter = _.map(count => math.log10(count)))

    dumpMovieRatingVoteCountFrequencies(filename = "rating-vote-count-frequencies.movies.tsv",
                                        countsFilter = counts => {
                                          counts.sorted.takeWhile(_ <= 1100).map(_.toDouble)
                                        })

    for (voteCount <- Seq(5, 10, 100, 1000, 10000)) {
      dumpMinimumRatingsForVoteCount(filename = s"minimum-ratings.${voteCount}votes.tsv",
                                     titleType = ImdbStats.MovieType,
                                     voteCount = voteCount)
    }

    for (titleCount <- Seq(250, 1500)) {
      dumpMinimumRatingsForTitleCount(filename = s"minimum-ratings.${titleCount}movies.tsv",
                                      titleType = ImdbStats.MovieType,
                                      titleCount = titleCount)
    }
  }

  def dumpFrequencies(filename: String, values: Seq[Double], intervalCount: Int): Unit =
    ValuesAndStats(values).dumpFrequenciesToGnuplotFile(Paths.get(filename), intervalCount)

  def dumpRatingFrequencies(filename: String): Unit =
    dumpFrequencies(filename, ImdbStats.titleRatings.map(_.rating), intervalCount = 40)

  def dumpYearFrequencies(filename: String, yearsFilter: (Seq[Double]) => Seq[Double]): Unit =
    dumpFrequencies(filename, yearsFilter(ImdbStats.titleYears.map(_.toDouble)), intervalCount = 50)

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

  def dumpShortDurationFrequencies(filename: String,
                                   durationsFilter: (Seq[Double]) => Seq[Double]): Unit =
    dumpFrequencies(filename,
                    durationsFilter(ImdbStats.shortDurations.map(_.toDouble)),
                    intervalCount = 50)

  def dumpRatingVoteCountFrequencies(filename: String,
                                     countsFilter: (Seq[Int]) => Seq[Double]): Unit =
    dumpFrequencies(filename,
                    countsFilter(ImdbStats.titleRatings.map(_.voteCount)),
                    intervalCount = 30)

  def dumpMovieRatingVoteCountFrequencies(filename: String,
                                          countsFilter: (Seq[Int]) => Seq[Double]): Unit =
    dumpFrequencies(filename,
                    countsFilter(ImdbStats.movieTitleRatings.map(_.voteCount)),
                    intervalCount = 30)

  def dumpMinimumRatingsForVoteCount(filename: String, titleType: String, voteCount: Int): Unit = {
    val titleCountsAndRatings =
      ImdbStats.minimumRatingsByTitleCount(titleType, voteCount).toSeq.sortBy(_._1)

    FileUtils.writeStrings(Paths.get(filename),
                           titleCountsAndRatings.map(kv => s"${kv._1}\t${kv._2}"))
  }

  def dumpMinimumRatingsForTitleCount(filename: String,
                                      titleType: String,
                                      titleCount: Int): Unit = {
    val voteCountsAndRatings =
      ImdbStats
        .minimumRatingsByVoteCount(titleType, titleCount, maxVoteCount = 30000, step = 500)
        .toSeq
        .sortBy(_._1)

    FileUtils.writeStrings(Paths.get(filename),
                           voteCountsAndRatings.map(kv => s"${kv._1}\t${kv._2}"))
  }
}
