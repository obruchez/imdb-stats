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

## Vote counts

As of December 3, 2017, 785031 entries have ratings:

- count: 785031
- minimum: 5.00
- maximum: 1882520.00
- mean: 994.34
- median: 20.00
- mode: 5.00
- standard deviation: 15238.01

Most entries don't have much votes. The full frequency plot is not very useful:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.png" width="500">

If we zoom to 1100 votes and less, we can see what's happening a little bit better:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.95.png" width="500">

About 95% of the entries with votes have 1000 votes or less:

- vote counts >= 100: 173563 (22.11%)
- vote counts >= 1000: 41737 (5.32%)
- vote counts >= 10000: 8516 (1.08%)
- vote counts >= 50000: 2992 (0.38%)
- vote counts >= 100000: 1598 (0.20%)
- vote counts >= 500000: 159 (0.02%)
- vote counts >= 1000000: 23 (0.00%)
