package org.bruchez.olivier.imdbstats

case class Stats(sampleCount: Int,
                 min: Double,
                 max: Double,
                 mean: Double,
                 median: Double,
                 mode: Double,
                 standardDeviation: Double) {
  val range: Double = max - min

  def asStrings(withCount: Boolean): Seq[String] =
    (if (withCount) Seq(f"- count: $sampleCount%,d") else Seq()) ++ Seq(
      f"- minimum: $min%,.2f",
      f"- maximum: $max%,.2f",
      f"- mean: $mean%,.2f",
      f"- median: $median%,.2f",
      f"- mode: $mode%,.2f",
      f"- standard deviation: $standardDeviation%,.2f"
    )
}

object Stats {
  def apply(values: Seq[Double]): Stats = {
    val sampleCount = values.length

    val mean = values.sum / sampleCount

    val ordered = values.sorted

    // See https://en.wikipedia.org/wiki/Median
    val middleIndex = (sampleCount.toDouble + 1) / 2 - 1
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
        .sum / (sampleCount - 1))

    Stats(sampleCount,
          min = ordered.head,
          max = ordered.last,
          mean = mean,
          median = median,
          mode = mode,
          standardDeviation = standardDeviation)
  }
}
