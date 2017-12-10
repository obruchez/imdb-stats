# imdb-stats

[![Build Status](https://travis-ci.org/obruchez/imdb-stats.svg?branch=master)](https://travis-ci.org/obruchez/imdb-stats)

Computes statistics using IMDb dumps.

## Scripts

To download the IMDb dumps:

```download.sh <S3 key> <S3 password>```

To compute the statistics, generate the plots / PNG images, and save the results into README.md:

```generate.sh```

## Statistics

As of $date, there are $titleCount titles in the IMDb dumps.

### Title types

Titles can be partitioned into 10 different types:

$titleTypesWithCounts

### Years

$titleWithYearCount titles ($titleWithYearPercentage) have a start and/or end year defined:

$yearStats

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/year-frequencies.2025.png" width="600">

The earliest title in IMDb is [The Passage of Venus ](http://www.imdb.com/title/tt3155794/) (1874). And, yes, [100 Years](http://www.imdb.com/title/tt5174640/) is planned for release in 2115!

### Durations

$titleWithDurationCount titles ($titleWithDurationPercentage) have a runtime duration defined:

$durationStats

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.300.png" width="600">

Most durations above 1,000 minutes are mistakes, experimental videos, total durations for series, etc.

Here are the statistics and frequency plot for feature films only:

$movieDurationStats

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.movies.png" width="600">

### Ratings

$titleWithRatingCount titles ($titleWithRatingPercentage) have ratings.

$ratingStats

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-frequencies.png" width="600">

### Vote counts

Each title with a rating has at least 5 votes (this is a limit enforced by IMDb).

$voteCountStats

Most titles don't have much votes. The full frequency plot is not very useful:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.png" width="600">

If we zoom to 1,100 votes and less, we can see what's happening a little bit better:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.95.png" width="600">

About 95% of the titles with votes have 1,000 votes or less:

$voteCountPercentages

Here is a list of the titles with more than 1,000,000 votes:

$titlesWithMostVotes

### Minimum ratings

Question: what's the minimum IMDb rating for a feature film that you should watch if you can only watch N feature films in your life?

Here's the plot if you take into account all feature films with ratings:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.5.png" width="600">

If you take into account only feature films with 100 votes or more:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.100.png" width="600">

And now with 10000 votes or more:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.10000.png" width="600">
