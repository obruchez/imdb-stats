#!/usr/bin/env bash

# Generate the data used by Gnuplot

sbt -mem 32000 "runMain org.bruchez.olivier.imdbstats.GeneratePlots"

mkdir -p results

mv *.tsv results

cd results

# Generate the PNG images

gnuplot ../gnuplot/rating-frequencies.gnuplot

gnuplot ../gnuplot/year-frequencies.gnuplot

gnuplot ../gnuplot/duration-frequencies.gnuplot

gnuplot ../gnuplot/rating-vote-count-frequencies.gnuplot

gnuplot ../gnuplot/minimum-ratings.gnuplot

cd ..

# Generate README.md

sbt -mem 32000 "runMain org.bruchez.olivier.imdbstats.GenerateReadme"

stty echo
