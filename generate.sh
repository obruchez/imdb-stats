#!/usr/bin/env bash

# Generate the data used by Gnuplot

sbt -mem 8192 "runMain org.bruchez.olivier.imdbstats.GeneratePlots"

mkdir -p results

mv *.tsv results

cd results

# Generate the PNG images

gnuplot ../gnuplot/rating-frequencies.gnuplot

gnuplot ../gnuplot/year-frequencies.gnuplot

gnuplot ../gnuplot/rating-vote-count-frequencies.gnuplot

cd ..

# Generate README.md

sbt -mem 8192 "runMain org.bruchez.olivier.imdbstats.GenerateReadme"

stty echo
