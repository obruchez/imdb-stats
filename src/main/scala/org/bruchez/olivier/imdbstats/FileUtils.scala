package org.bruchez.olivier.imdbstats

import java.io.{BufferedReader, FileInputStream, InputStreamReader}
import java.nio.file._
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
        override def rowProcessed(row: Array[AnyRef], context: ParsingContext): Unit = {
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

  def writeStrings(path: Path, strings: Seq[String]): Unit = {
    val writer = Files.newBufferedWriter(path)

    try {
      strings.foreach(string => writer.write(string + "\n"))
    } finally {
      writer.close()
    }
  }

  def writeString(path: Path, string: String): Unit = {
    val writer = Files.newBufferedWriter(path)

    try {
      writer.write(string)
    } finally {
      writer.close()
    }
  }

  def fileContents(path: Path): String =
    scala.io.Source.fromFile(path.toFile).mkString
}
