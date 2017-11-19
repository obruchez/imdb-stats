package org.bruchez.olivier.imdbstats

import java.nio.file.Path

import scala.annotation.tailrec

case class ValuesAndStats(values: Seq[Double], stats: Stats) {
  def frequencies(intervalCount: Int = ValuesAndStats.DefaultIntervalCount)
    : Seq[(Double, Int)] = {
    val sorted = values.sorted

    // We will consider intervalCount intervals and count the values inside those intervals. The first interval will be
    // centered around the minimum value and the last interval will be centered around the maximum value.

    @tailrec
    def frequencies(interval: Int = 0,
                    startIndex: Int = 0,
                    acc: Seq[(Double, Int)] = Seq()): Seq[(Double, Int)] = {
      if (interval >= intervalCount || startIndex >= sorted.length) {
        acc
      } else {
        val middleValueForCurrentInterval = stats.min + stats.range * interval.toDouble / (intervalCount - 1)

        val maximumValueForCurrentInterval = stats.min + stats.range * (interval.toDouble + 0.5) / (intervalCount - 1)

        val countForCurrentInterval = sorted
          .drop(startIndex)
          .zipWithIndex
          .find(_._1 >= maximumValueForCurrentInterval)
          .map(_._2)
          .getOrElse(sorted.length - startIndex)

        val newAcc = acc :+ (middleValueForCurrentInterval, countForCurrentInterval)

        frequencies(interval + 1,
                    startIndex = startIndex + countForCurrentInterval,
                    newAcc)
      }
    }

    frequencies()
  }

  def dumpValuesToGnuplotFile(path: Path): Unit =
    FileUtils.writeStrings(path, values.map(_.toString))

  def dumpFrequenciesToGnuplotFile(
      path: Path,
      intervalCount: Int = ValuesAndStats.DefaultIntervalCount): Unit =
    FileUtils.writeStrings(
      path,
      frequencies(intervalCount).map(kv => s"${kv._1}\t${kv._2}"))
}

object ValuesAndStats {
  protected val DefaultIntervalCount = 100

  def apply(values: Seq[Double]): ValuesAndStats =
    ValuesAndStats(values, Stats(values))
}
