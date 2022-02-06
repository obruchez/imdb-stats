package org.bruchez.olivier.imdbstats

case class TitleInfo(
    id: String,
    titleType: String,
    primaryTitle: String,
    originalTitle: String,
    isAdult: Boolean,
    startYear: Option[Int],
    endYear: Option[Int],
    runtimeMinutes: Option[Int],
    genres: Seq[String]
) {
  def url: String = s"http://www.imdb.com/title/$id/"
}

object TitleInfo {
  val NoValue = "\\N"

  def apply(row: Array[AnyRef]): TitleInfo = {
    val strings = row.map(_.toString)

    TitleInfo(
      id = strings(0),
      titleType = strings(1),
      primaryTitle = strings(2),
      originalTitle = strings(3),
      isAdult = strings(4).toInt != 0,
      startYear = Some(strings(5)).filter(_ != NoValue).map(_.toInt),
      endYear = Some(strings(6)).filter(_ != NoValue).map(_.toInt),
      runtimeMinutes = Some(strings(7)).filter(_ != NoValue).map(_.toInt).filter(_ > 0),
      genres = strings(8).split(',').map(_.trim)
    )
  }
}
