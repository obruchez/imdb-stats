package org.bruchez.olivier.imdbstats

import com.univocity.parsers.common.ParsingContext
import com.univocity.parsers.common.processor.ObjectRowProcessor
import com.univocity.parsers.tsv.TsvParser
import com.univocity.parsers.tsv.TsvParserSettings
import java.io._
import java.util.zip.GZIPInputStream

object ImdbStats {
  val BasicsFilename = "title.basics.tsv.gz"
  val RatingsFilename = "title.ratings.tsv.gz"

  val MovieType = "movie"

  val NoValue = "\\N"

  def main(args: Array[String]): Unit = {
    val MinimumVoteCount = 1000

    val ratingsById =
      this.ratingsById(voteCountThreshold = Some(MinimumVoteCount))

    println(s"Rating count ($MinimumVoteCount): ${ratingsById.size}")

    val MinimumRuntimeInMinutes = 60

    val filteredIds = this.filteredIds(titleInfo => {
      titleInfo.titleType == MovieType &&
      titleInfo.runtimeMinutes.exists(_ >= MinimumRuntimeInMinutes)
    })

    println(s"Filtered movie count: ${filteredIds.size}")

    val filteredRatings = ratingsById.toSeq
      .filter(kv => filteredIds.contains(kv._1))
      .map(_._2)
      .sorted

    println(s"Filtered rating count: ${filteredRatings.size}")

    val MovieThatICanWatchCount = 1290

    val minimumImdbRating =
      filteredRatings.takeRight(MovieThatICanWatchCount).head

    val minimumImdbRatingMovieCount =
      filteredRatings.count(_ >= minimumImdbRating)

    println(
      s"Minimum IMDb rating: $minimumImdbRating (=> $minimumImdbRatingMovieCount movies)")

    for (rating <- 70 to 100 by 1) {
      val count = filteredRatings.count(_ >= rating)
      println(s"Movie >= $rating count: $count")
    }
  }

  case class TitleInfo(id: String,
                       titleType: String,
                       primaryTitle: String,
                       originalTitle: String,
                       isAdult: Boolean,
                       startYear: Option[Int],
                       endYear: Option[Int],
                       runtimeMinutes: Option[Int],
                       genres: Seq[String])

  object TitleInfo {
    def apply(row: Array[AnyRef]): TitleInfo = {
      val strings = row.map(_.toString)

      // scalastyle:off magic.number
      TitleInfo(
        id = strings(0),
        titleType = strings(1),
        primaryTitle = strings(2),
        originalTitle = strings(3),
        isAdult = strings(4).toInt != 0,
        startYear = Some(strings(5)).filter(_ != NoValue).map(_.toInt),
        endYear = Some(strings(6)).filter(_ != NoValue).map(_.toInt),
        runtimeMinutes = Some(strings(7)).filter(_ != NoValue).map(_.toInt),
        genres = strings(8).split(',').map(_.trim)
      )
      // scalastyle:on magic.number
    }
  }

  def filteredIds(f: TitleInfo => Boolean): Set[String] = {
    val ids = collection.mutable.Set[String]()

    fromTsvGz[Unit](BasicsFilename, row => {
      val titleInfo = TitleInfo(row)
      if (f(titleInfo)) {
        ids.add(titleInfo.id)
      }
      None
    })

    ids.toSet
  }

  // Rating * 10
  def ratingsById(voteCountThreshold: Option[Int] = None): Map[String, Int] = {
    val mutableRatingsById = collection.mutable.Map[String, Int]()

    fromTsvGz[Unit](
      RatingsFilename,
      row => {
        val rating = (row(1).toString.toDouble * 10).round.toInt
        val voteCount = row(2).toString.toInt

        if (voteCountThreshold.map(_ <= voteCount).getOrElse(true)) {
          mutableRatingsById(row(0).toString) = rating
        }

        None
      }
    )

    mutableRatingsById.toMap
  }

  def fromTsvGz[T](path: String, f: Array[AnyRef] => Option[T]): Seq[T] = {
    val fis = new FileInputStream(path)
    val gzis = new GZIPInputStream(fis)
    val isr = new InputStreamReader(gzis)
    val in = new BufferedReader(isr)

    var first = true

    try {
      val settings = new TsvParserSettings

      val buffer = collection.mutable.Buffer[T]()

      val rowProcessor = new ObjectRowProcessor() {
        override def rowProcessed(row: Array[AnyRef],
                                  context: ParsingContext): Unit = {
          if (first) {
            first = false
          } else {
            f(row).foreach(buffer.append(_))
          }
        }
      }

      settings.setProcessor(rowProcessor)

      val parser = new TsvParser(settings)

      parser.parse(in)

      buffer
    } finally {
      in.close()
    }
  }
}
