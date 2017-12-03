package org.bruchez.olivier.imdbstats

object PrintStats {
  def main(args: Array[String]): Unit = {
    println("Loading title infos...")
    val titleInfosById = ImdbStats.titleInfos().groupBy(_.id).map(kv => kv._1 -> kv._2.head)
    println(f"\nTitle info count: ${titleInfosById.size}%,d\n")

    printTitleTypeStats(titleInfosById)

    printRatingStats()

    printRatingVoteCountStats(titleInfosById)
  }

  def printTitleTypeStats(titleInfosById: Map[String, TitleInfo]): Unit = {
    val titleTypesAndCounts =
      titleInfosById.values.groupBy(_.titleType).map(kv => kv._1 -> kv._2.size).toSeq

    println("\nTitle types:\n")
    for ((titleType, titleCounts) <- titleTypesAndCounts.sortBy(_._2).reverse) {
      println(f" - ${ImdbStats.titleTypeDescriptions(titleType)}: $titleCounts%,d")
    }
  }

  def printRatingStats(): Unit = {
    val titleRatings = ImdbStats.titleRatings()

    val valuesAndStats = ValuesAndStats(titleRatings.map(_.rating))

    println("\nRatings stats:\n")
    valuesAndStats.stats.asStrings.foreach(println)
  }

  def printRatingVoteCountStats(titleInfosById: Map[String, TitleInfo]): Unit = {
    val titleRatings = ImdbStats.titleRatings()

    val valuesAndStats = ValuesAndStats(titleRatings.map(_.voteCount.toDouble))

    println("\nNumber of votes stats:\n")
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
