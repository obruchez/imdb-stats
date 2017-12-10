#!/usr/bin/env bash

sbt -mem 8192 "runMain org.bruchez.olivier.imdbstats.GeneratePlots"

mkdir -p results

mv *.tsv results

cd results

gnuplot ../gnuplot/rating-frequencies.gnuplot

gnuplot ../gnuplot/year-frequencies.gnuplot

gnuplot ../gnuplot/rating-vote-count-frequencies.gnuplot

stty echo
