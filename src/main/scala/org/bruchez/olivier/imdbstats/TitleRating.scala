package org.bruchez.olivier.imdbstats

case class TitleRating(id: String, ratingMultipliedByTen: Int, voteCount: Int) {
  def rating: Double = ratingMultipliedByTen.toDouble / 10
}

object TitleRating {
  def apply(row: Array[AnyRef]): TitleRating = {
    val strings = row.map(_.toString)

    TitleRating(
      id = strings(0),
      ratingMultipliedByTen = (strings(1).toDouble * 10).round.toInt,
      voteCount = strings(2).toInt
    )
  }
}
