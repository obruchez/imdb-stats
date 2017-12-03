package org.bruchez.olivier.imdbstats

object PrintStats {
  def main(args: Array[String]): Unit = {
    println("Loading title infos...")
    val titleInfosById = ImdbStats.titleInfos().groupBy(_.id).map(kv => kv._1 -> kv._2.head)
    println(f"Title info count: ${titleInfosById.size}%,d\n")

    printRatingVoteCountStats(titleInfosById)
  }

  def printRatingVoteCountStats(titleInfosById: Map[String, TitleInfo]): Unit = {
    val titleRatings = ImdbStats.titleRatings()

    val valuesAndStats = ValuesAndStats(titleRatings.map(_.voteCount.toDouble))

    println("Number of votes stats:\n")
    valuesAndStats.stats.asStrings.foreach(println)

    val minCounts = Seq(100, 1000, 10000, 50000, 100000, 500000, 1000000)

    println("\nEntries with number of votes >= X:\n")
    for (minCount <- minCounts) {
      val counts = valuesAndStats.values.count(_ >= minCount)
      println(
        f"- vote counts >= $minCount%,d: $counts%,d (${counts * 100.0 / valuesAndStats.values.size}%.2f%%)")
    }

    println("\nTitles with most votes:\n")
    for {
      titleRating <- titleRatings.sortBy(_.voteCount).reverse.takeWhile(_.voteCount >= 1000000)
      titleInfo = titleInfosById(titleRating.id)
    } {
      println(f" - ${titleRating.voteCount}%,d votes: '${titleInfo.primaryTitle}'")
    }
  }
}
