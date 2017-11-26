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

As of November 26, 2017, 782977 entries have ratings:

- count: 782977
- minimum: 5.00
- maximum: 1879739.00
- mean: 994.78
- median: 21.00
- mode: 5.00
- standard deviation: 15233.99

Most entries don't have much votes. The full frequency plot is not very useful:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.png" width="500">

If we zoom to 1100 votes and less, we can see what's happening a little bit better:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.95.png" width="500">
