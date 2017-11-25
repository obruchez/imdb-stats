#!/usr/bin/env bash

sbt "runMain org.bruchez.olivier.imdbstats.ImdbStats"

mkdir -p results

mv *.tsv results

cd results

gnuplot ../gnuplot/rating-vote-count-frequencies.gnuplot
