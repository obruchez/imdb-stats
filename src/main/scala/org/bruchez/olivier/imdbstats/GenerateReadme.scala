package org.bruchez.olivier.imdbstats

import java.nio.file.Paths

object GenerateReadme {
  def main(args: Array[String]): Unit = {
    val templateContents = this.templateContents()

    val updatedContents = templateContents

    saveReadme(updatedContents)
  }

  def templateContents(): String =
    FileUtils.fileContents(Paths.get("README.template.md"))

  def saveReadme(contents: String): Unit =
    FileUtils.writeString(Paths.get("README.md"), contents)
}
