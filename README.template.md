# imdb-stats

[![Build Status](https://travis-ci.org/obruchez/imdb-stats.svg?branch=master)](https://travis-ci.org/obruchez/imdb-stats)

Computes statistics using IMDb dumps.

To download the IMDb dumps:

```download.sh <S3 key> <S3 password>```

To generate the plots / PNG images:

```generate.sh```

Generated plots / PNG images are stored in /results.

To print statistics:

```print.sh```

## Titles

As of December 10, 2017, there are 4,678,063 titles in the IMDb dumps.

They can be partitioned into 10 different types:

 - TV episode: 3,102,768
 - Short film: 594,612
 - Feature film: 470,893
 - Video: 188,750
 - TV series: 139,457
 - TV movie: 121,644
 - TV mini-series: 20,780
 - Video game: 20,652
 - TV special: 10,399
 - TV short: 8,108

4,409,682 titles (94.26%) have a start and/or end year defined:

- count: 4,409,679
- minimum: 1,874.00
- maximum: 2,115.00
- mean: 1,999.18
- median: 2,008.00
- mode: 2,016.00
- standard deviation: 22.42

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/year-frequencies.2025.png" width="500">

The earliest title in IMDb is [The Passage of Venus ](http://www.imdb.com/title/tt3155794/) (1874). And, yes, [100 Years](http://www.imdb.com/title/tt5174640/) is planned for release in 2115!

## Ratings

786,561 titles (16.81%) have ratings.

- count: 786,561
- minimum: 1.00
- maximum: 10.00
- mean: 6.94
- median: 7.10
- mode: 7.20
- standard deviation: 1.39

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-frequencies.png" width="500">

## Vote counts

Each title with a rating has at least 5 votes (this is a limit enforced by IMDb).

- count: 786,561
- minimum: 5.00
- maximum: 1,884,541.00
- mean: 993.97
- median: 20.00
- mode: 5.00
- standard deviation: 15,240.54

Most titles don't have much votes. The full frequency plot is not very useful:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.png" width="500">

If we zoom to 1,100 votes and less, we can see what's happening a little bit better:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.95.png" width="500">

About 95% of the titles with votes have 1,000 votes or less:

- vote counts >= 100: 173,925 (22.11%)
- vote counts >= 1,000: 41,822 (5.32%)
- vote counts >= 10,000: 8,527 (1.08%)
- vote counts >= 50,000: 2,998 (0.38%)
- vote counts >= 100,000: 1,602 (0.20%)
- vote counts >= 500,000: 159 (0.02%)
- vote counts >= 1,000,000: 23 (0.00%)

Here is a list of the titles with more than 1,000,000 votes:

 - 1,884,541 votes: 'The Shawshank Redemption'
 - 1,860,469 votes: 'The Dark Knight'
 - 1,649,480 votes: 'Inception'
 - 1,510,460 votes: 'Fight Club'
 - 1,474,140 votes: 'Pulp Fiction'
 - 1,422,029 votes: 'Forrest Gump'
 - 1,366,914 votes: 'The Lord of the Rings: The Fellowship of the Ring'
 - 1,355,662 votes: 'The Matrix'
 - 1,346,670 votes: 'The Lord of the Rings: The Return of the King'
 - 1,286,635 votes: 'The Godfather'
 - 1,266,981 votes: 'The Dark Knight Rises'
 - 1,265,135 votes: 'Game of Thrones'
 - 1,218,915 votes: 'The Lord of the Rings: The Two Towers'
 - 1,149,297 votes: 'Se7en'
 - 1,116,105 votes: 'Interstellar'
 - 1,093,352 votes: 'Gladiator'
 - 1,087,730 votes: 'Batman Begins'
 - 1,084,819 votes: 'Django Unchained'
 - 1,074,638 votes: 'The Avengers'
 - 1,041,328 votes: 'Breaking Bad'
 - 1,012,747 votes: 'Star Wars: Episode IV - A New Hope'
 - 1,003,494 votes: 'The Silence of the Lambs'
 - 1,001,780 votes: 'Inglourious Basterds'
 