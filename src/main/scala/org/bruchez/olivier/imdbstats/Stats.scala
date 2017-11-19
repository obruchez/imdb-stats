package org.bruchez.olivier.imdbstats

import java.nio.file.Path

case class Stats(min: Double,
                 max: Double,
                 mean: Double,
                 median: Double,
                 mode: Double,
                 standardDeviation: Double) {
  def asStrings: Seq[String] = Seq(
    s"Minimum: $min",
    s"Maximum: $max",
    s"Mean: $mean",
    s"Median: $median",
    s"Mode: $mode",
    s"Standard deviation: $standardDeviation"
  )
}

object Stats {
  def apply(values: Seq[Double]): Stats = {
    val ordered = values.sorted

    val mean = ordered.sum / ordered.length

    // See https://en.wikipedia.org/wiki/Median
    val middleIndex = (values.length.toDouble + 1) / 2 - 1
    val medianLeftIndex = middleIndex.toInt
    val mediaRightIndex = (middleIndex + 0.5).toInt
    val median = (ordered(medianLeftIndex) + ordered(mediaRightIndex)) / 2

    val mode = values
      .groupBy(value => value)
      .map(kv => kv._1 -> kv._2.size)
      .maxBy(_._2)
      ._1

    val standardDeviation = math.sqrt(
      values
        .map(value => (value - mean) * (value - mean))
        .sum / (ordered.length - 1))

    Stats(min = ordered.head,
          max = ordered.last,
          mean = mean,
          median = median,
          mode = mode,
          standardDeviation = standardDeviation)
  }

  def dumpToGnuplotFile(path: Path, values: Seq[Double]): Unit =
    FileUtils.writeStrings(path, values.map(_.toString))
}
