package org.bruchez.olivier.imdbstats

import java.io.{BufferedReader, FileInputStream, InputStreamReader}
import java.nio.file.Path
import java.util.zip.GZIPInputStream

import com.univocity.parsers.common.ParsingContext
import com.univocity.parsers.common.processor.ObjectRowProcessor
import com.univocity.parsers.tsv.{TsvParser, TsvParserSettings}

object FileUtils {
  def fromTsvGz[T](path: Path, f: Array[AnyRef] => Option[T]): Seq[T] = {
    val fis = new FileInputStream(path.toFile)
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
