package org.bruchez.olivier.imdbstats

object PrintStats {
  def main(args: Array[String]): Unit = {
    printRatingVoteCountStats()

    /*println("Loading title infos...")
    val titleInfos = ImdbStats.titleInfos()
    println(s"titleInfos -> ${titleInfos.size}")*/
  }

  def printRatingVoteCountStats(): Unit = {
    val valuesAndStats =
      ValuesAndStats(ImdbStats.titleRatings().map(_.voteCount.toDouble))

    println("Rating vote count stats:\n")
    valuesAndStats.stats.asStrings.foreach(println)

    // scalastyle:off magic.number
    val minCounts = Seq(100, 1000, 10000, 50000, 100000, 500000, 1000000)
    // scalastyle:on magic.number

    println("\nNumber of vote counts >= X:\n")
    for (minCount <- minCounts) {
      val counts = valuesAndStats.values.count(_ >= minCount)
      println(
        f"- vote counts >= $minCount: $counts (${counts * 100.0 / valuesAndStats.values.size}%.2f%%)")
    }

    // @todo dump titles of movies with most votes
    //val x = this.
  }
}
