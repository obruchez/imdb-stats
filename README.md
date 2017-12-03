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

As of December 3, 2017, there are 4,667,234 titles in the IMDb dumps.

They can be partitioned in 10 different types:

 - TV episode: 3,094,331
 - Short film: 593,693
 - Feature film: 470,293
 - Video: 188,357
 - TV series: 139,223
 - TV movie: 121,504
 - TV mini-series: 20,710
 - Video game: 20,629
 - TV special: 10,393
 - TV short: 8,101

## Ratings

785,031 titles (16.82%) have ratings.

- count: 785,031
- minimum: 1.00
- maximum: 10.00
- mean: 6.94
- median: 7.10
- mode: 7.20
- standard deviation: 1.39

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-frequencies.png" width="500">

## Vote counts

Each title with a rating has at least 5 votes (this is a limit enforced by IMDb).

- count: 785,031
- minimum: 5.00
- maximum: 1,882,520.00
- mean: 994.34
- median: 20.00
- mode: 5.00
- standard deviation: 15,238.01

Most titles don't have much votes. The full frequency plot is not very useful:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.png" width="500">

If we zoom to 1,100 votes and less, we can see what's happening a little bit better:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.95.png" width="500">

About 95% of the entries with votes have 1,000 votes or less:

- vote counts >= 100: 173,563 (22.11%)
- vote counts >= 1,000: 41,737 (5.32%)
- vote counts >= 10,000: 8,516 (1.08%)
- vote counts >= 50,000: 2,992 (0.38%)
- vote counts >= 100,000: 1,598 (0.20%)
- vote counts >= 500,000: 159 (0.02%)
- vote counts >= 1,000,000: 23 (0.00%)

Here is a list of the titles with more than 1,000,000 votes:

 - 1,882,520 votes: 'The Shawshank Redemption'
 - 1,858,571 votes: 'The Dark Knight'
 - 1,647,649 votes: 'Inception'
 - 1,508,844 votes: 'Fight Club'
 - 1,472,586 votes: 'Pulp Fiction'
 - 1,420,328 votes: 'Forrest Gump'
 - 1,365,653 votes: 'The Lord of the Rings: The Fellowship of the Ring'
 - 1,354,240 votes: 'The Matrix'
 - 1,345,371 votes: 'The Lord of the Rings: The Return of the King'
 - 1,285,173 votes: 'The Godfather'
 - 1,265,803 votes: 'The Dark Knight Rises'
 - 1,263,262 votes: 'Game of Thrones'
 - 1,217,721 votes: 'The Lord of the Rings: The Two Towers'
 - 1,148,099 votes: 'Se7en'
 - 1,114,323 votes: 'Interstellar'
 - 1,092,287 votes: 'Gladiator'
 - 1,086,703 votes: 'Batman Begins'
 - 1,083,601 votes: 'Django Unchained'
 - 1,073,749 votes: 'The Avengers'
 - 1,039,824 votes: 'Breaking Bad'
 - 1,011,676 votes: 'Star Wars: Episode IV - A New Hope'
 - 1,002,303 votes: 'The Silence of the Lambs'
 - 1,000,534 votes: 'Inglourious Basterds'
 