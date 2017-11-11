package org.bruchez.olivier.imdbstats

import java.io.{FileOutputStream, InputStream}

import com.amazonaws.{AmazonClientException, AmazonServiceException}
import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.GetObjectRequest

// Quickly adapted from GetObject.java sample code

object DownloadAll {
  private val bucketName = "imdb-datasets"

  def main(args: Array[String]): Unit = {
    val credentialsProvider = new AWSStaticCredentialsProvider(
      new BasicAWSCredentials(args(0), args(1)))

    val s3Client = new AmazonS3Client(credentialsProvider)

    val filenames = Seq("title.basics.tsv.gz",
                        "title.crew.tsv.gz",
                        "title.episode.tsv.gz",
                        "title.principals.tsv.gz",
                        "title.ratings.tsv.gz",
                        "name.basics.tsv.gz")

    filenames.foreach(download(s3Client, _))
  }

  private def download(s3Client: AmazonS3Client, filename: String): Unit = {
    val key = s"documents/v1/current/$filename"

    try {
      // Note: It's necessary to set RequesterPays to true
      val getObjectRequest =
        new GetObjectRequest(bucketName, key).withRequesterPays(true)
      println("Downloading object")
      val s3object = s3Client.getObject(getObjectRequest)
      println("Content-Type: " + s3object.getObjectMetadata.getContentType)
      writeFile(s3object.getObjectContent, filename)
    } catch {
      case ase: AmazonServiceException =>
        println("Caught an AmazonServiceException, which  means your request made it to " +
          "Amazon S3, but was rejected with an error response for some reason.")
        println("Error Message:    " + ase.getMessage)
        println("HTTP Status Code: " + ase.getStatusCode)
        println("AWS Error Code:   " + ase.getErrorCode)
        println("Error Type:       " + ase.getErrorType)
        println("Request ID:       " + ase.getRequestId)
      case ace: AmazonClientException =>
        println(
          "Caught an AmazonClientException, which means the client encountered an internal error " +
            "while trying to communicate with S3, such as not being able to access the network.")
        println("Error Message: " + ace.getMessage)
    }
  }

  private def writeFile(input: InputStream, filename: String): Unit = {
    val buf = new Array[Byte](1024 * 1024)
    val out = new FileOutputStream(filename)
    var eof = false

    while (!eof) {
      val count = input.read(buf)

      if (count < 0) {
        eof = true
      } else {
        if (Thread.interrupted) throw new InterruptedException
        out.write(buf, 0, count)
      }
    }

    out.close()
    input.close()
  }
}
