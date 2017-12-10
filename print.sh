#!/usr/bin/env bash

sbt -mem 8192 "runMain org.bruchez.olivier.imdbstats.PrintStats"

stty echo
