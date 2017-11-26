#!/usr/bin/env bash

sbt "runMain org.bruchez.olivier.imdbstats.DownloadAll $1 $2"
