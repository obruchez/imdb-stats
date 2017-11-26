#!/usr/bin/env bash

sbt "runMain org.bruchez.olivier.imdbstats.GeneratePlots"

mkdir -p results

mv *.tsv results

cd results

gnuplot ../gnuplot/rating-vote-count-frequencies.gnuplot
