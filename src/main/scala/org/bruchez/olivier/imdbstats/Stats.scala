package org.bruchez.olivier.imdbstats

case class Stats(min: Double, max: Double, mean: Double, median: Double)

object Stats {
  def stats(values: Seq[Double]): Stats = {
    val ordered = values.sorted

    // See https://en.wikipedia.org/wiki/Median
    val middleIndex = (values.length.toDouble + 1) / 2 - 1
    val medianLeftIndex = middleIndex.toInt
    val mediaRightIndex = (middleIndex + 0.5).toInt

    Stats(min = ordered.head,
          max = ordered.last,
          mean = ordered.sum / ordered.length,
          median = (ordered(medianLeftIndex) + ordered(mediaRightIndex)) / 2)
  }
}
