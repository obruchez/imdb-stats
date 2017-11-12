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

  def main(args: Array[String]): Unit = {
    val ratingsById = this.ratingsById()
    println(s"ratingsById -> ${ratingsById.size}")
  }

  def ratingsById(): Map[String, Double] = {
    val mutableRatingsById = collection.mutable.Map[String, Double]()

    fromTsvGz[Unit](RatingsFilename, row => {
      mutableRatingsById(row(0).toString) = row(1).toString.toDouble
      None
    })

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
