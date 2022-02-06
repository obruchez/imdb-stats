package org.bruchez.olivier.imdbstats

import java.io.File
import java.net.URL
import scala.sys.process._

object DownloadAll {
  def main(args: Array[String]): Unit = {
    val filenames = Seq(
      "title.basics.tsv.gz",
      "title.crew.tsv.gz",
      "title.episode.tsv.gz",
      "title.principals.tsv.gz",
      "title.ratings.tsv.gz",
      "name.basics.tsv.gz"
    )

    filenames.foreach(download)
  }

  private def download(filename: String): Unit =
    (new URL(s"https://datasets.imdbws.com/$filename") #> new File(filename)).!!
}
