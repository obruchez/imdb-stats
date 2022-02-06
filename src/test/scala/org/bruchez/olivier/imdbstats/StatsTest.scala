package org.bruchez.olivier.imdbstats

import org.scalatest._

class StatsTest extends FlatSpec with Matchers {
  "Minimum value" should "be correct" in {
    Stats(Seq(1)).min should be(1)
    Stats(Seq(1, 2)).min should be(1)
    Stats(Seq(2, 1)).min should be(1)
    Stats(Seq(2, 1, 1)).min should be(1)
  }

  "Maximum value" should "be correct" in {
    Stats(Seq(1)).max should be(1)
    Stats(Seq(1, 2)).max should be(2)
    Stats(Seq(2, 1)).max should be(2)
    Stats(Seq(1, 2, 2)).max should be(2)
  }

  "Mean value" should "be correct" in {
    Stats(Seq(1)).mean should be(1)
    Stats(Seq(1, 2)).mean should be(1.5)
    Stats(Seq(2, 1)).mean should be(1.5)
    Stats(Seq(1, 2, 3)).mean should be(2)
    Stats(Seq(-1, 1)).mean should be(0)
  }

  "Median value" should "be correct for odd number of numbers" in {
    Stats(Seq(1, 2, 6)).median should be(2)
  }

  "Median value" should "be correct for even number of numbers" in {
    Stats(Seq(1, 2, 4, 9)).median should be(3)
  }

  "Mode value" should "be correct" in {
    Stats(Seq(1)).median should be(1)
    Stats(Seq(1, 1, 1)).median should be(1)
    Stats(Seq(2, 2, 2, 1)).median should be(2)
    Stats(Seq(1, 2, 2, 2)).median should be(2)
    Stats(Seq(1, 2, 2, 2, 1)).median should be(2)
    Stats(Seq(1, 1, 2, 2, 2, 1, 1)).median should be(1)
  }

  "Frequencies" should "work as expected" in {
    ValuesAndStats(Seq(1, 2, 3)).frequencies(intervalCount = 3) should be(
      Seq((1, 1), (2, 1), (3, 1))
    )

    ValuesAndStats(Seq(1, 3)).frequencies(intervalCount = 3) should be(Seq((1, 1), (2, 0), (3, 1)))
  }
}
