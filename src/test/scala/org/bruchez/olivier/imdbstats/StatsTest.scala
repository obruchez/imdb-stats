package org.bruchez.olivier.imdbstats

import org.scalatest._

// scalastyle:off magic.number
class StatsTest extends FlatSpec with Matchers {
  "Median value" should "be correct for odd number of numbers" in {
    Stats.stats(Seq(1, 2, 6)) should be(
      Stats(min = 1, max = 6, mean = 3, median = 2))
  }

  "Median value" should "be correct for even number of numbers" in {
    Stats.stats(Seq(1, 2, 4, 9)) should be(
      Stats(min = 1, max = 9, mean = 4, median = 3))
  }
}
// scalastyle:on magic.number
