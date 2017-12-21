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

Most durations above 1,000 minutes are experimental videos, total durations for series, mistakes, etc.

Here are the statistics and frequency plot for feature films only:

$movieDurationStats

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.movies.png" width="600">

For short films only:

$shortDurationStats

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.shorts.png" width="600">

There is some overlap between the short films and feature films. I'm not sure it totally makes sense (e.g. a feature film shorter than 10 minutes or a short film longer than 100 minutes?).

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

95% of the titles with votes are in that area (i.e. about 1,100 votes and less):

$voteCountPercentages

Here is a list of the titles with more than 1,000,000 votes:

$titlesWithMostVotes

Most (but not all) of those titles are feature films. The mean/median numbers of votes for feature films are greater than the mean/median numbers of votes for all titles:

$movieVoteCountStats

But the plot still doesn't look like a bell curve:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.movies.png" width="600">

$movieVoteCountPercentages

### Minimum ratings

Question: what's the minimum IMDb rating for a feature film that you should watch if you can only watch N feature films in your life?

Here's the plot if you take into account all feature films with ratings:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.5votes.png" width="600">

If you take into account only feature films with 100 votes or more:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.100votes.png" width="600">

And now with 10,000 votes or more:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.10000votes.png" width="600">

All the plots have the same shape: the more films you take, the less you have to be strict/conservative about the minimum rating. It makes complete sense.

If you put all the plots on the same image, it becomes clear in what way the minimum number of votes influences the minimum rating:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.multiplevotecounts.png" width="600">

The higher the number of votes, the lower the number of feature films there are with that many votes. In other words, you can be less strict/conservative about the minimum rating with movies that have lots of votes.

But let's be honest, shall we? You probably won't see more than 5,000 feature films in your entire life, unless you're a movie buff. So let's zoom a little:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.multiplevotecounts.5000movies.png" width="600">

At this scale, if becomes clear that the minimum number of votes becomes less important: the minimum rating doesn't go all the way down to 1; actually, it doesn't even go below 7.5 for most plots and doesn't go below ~6.5 for all of them. It appears that it's probably a good idea to stay clear of feature films with a rating lower than 7 or 8, depending on the number of films.

Example 1. Let's say you only have the time to watch 1,500 feature films. These are the minimum ratings for various minimum number of votes:

$minimumRatings(1500)

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.1500movies.png" width="600">

Example 2. What about 250 feature films?

$minimumRatings(250)

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.250movies.png" width="600">

At the time of writing, all the movies in the [IMDb Top 250](http://www.imdb.com/chart/top) have more than 25,000 votes and a rating of 8.0 or more. If I had to guess, I would have given a minimum rating of 8.1 for a maximum of 250 movies to watch and a minimum of 25'000 votes. The discrepancy probably comes from the fact that "only votes from regular IMDb voters are considered when creating the top 250 out of the full voting database". I have no way of knowing which vote comes from "regular IMDb voters". This information is not included in the IMDb dumps.

### To-do

- check if there is a correlation between ratings and number of votes (plot + regression)
- check if there is a correlation between ratings and years (plot + regression)
