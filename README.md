# imdb-stats

[![Build Status](https://travis-ci.org/obruchez/imdb-stats.svg?branch=master)](https://travis-ci.org/obruchez/imdb-stats)

Computes statistics using IMDb dumps.

## Scripts

To download the IMDb dumps:

```download.sh <S3 key> <S3 password>```

To compute the statistics, generate the plots / PNG images, and save the results into README.md:

```generate.sh```

## Statistics

As of December 10, 2017, there are 4,678,063 titles in the IMDb dumps.

### Title types

Titles can be partitioned into 10 different types:

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

### Years

4,409,682 titles (94.26%) have a start and/or end year defined:

- minimum: 1,874.00
- maximum: 2,115.00
- mean: 1,999.18
- median: 2,008.00
- mode: 2,016.00
- standard deviation: 22.42

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/year-frequencies.2025.png" width="600">

The earliest title in IMDb is [The Passage of Venus ](http://www.imdb.com/title/tt3155794/) (1874). And, yes, [100 Years](http://www.imdb.com/title/tt5174640/) is planned for release in 2115!

### Durations

1,440,906 titles (30.80%) have a runtime duration defined:

- minimum: 0.00
- maximum: 125,156.00
- mean: 46.80
- median: 30.00
- mode: 30.00
- standard deviation: 116.67

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.300.png" width="600">

Most durations above 1,000 minutes are mistakes, experimental videos, total durations for series, etc.

Here are the statistics and frequency plot for feature films only:

- count: 291,540
- minimum: 1.00
- maximum: 14,400.00
- mean: 87.52
- median: 88.00
- mode: 90.00
- standard deviation: 53.59

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.movies.png" width="600">

### Ratings

786,561 titles (16.81%) have ratings.

- minimum: 1.00
- maximum: 10.00
- mean: 6.94
- median: 7.10
- mode: 7.20
- standard deviation: 1.39

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-frequencies.png" width="600">

### Vote counts

Each title with a rating has at least 5 votes (this is a limit enforced by IMDb).

- minimum: 5.00
- maximum: 1,884,541.00
- mean: 993.97
- median: 20.00
- mode: 5.00
- standard deviation: 15,240.54

Most titles don't have much votes. The full frequency plot is not very useful:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.png" width="600">

If we zoom to 1,100 votes and less, we can see what's happening a little bit better:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.95.png" width="600">

About 95% of the titles with votes have 1,000 votes or less:

- votes >= 10: 569,717 (72.43%)
- votes >= 100: 173,925 (22.11%)
- votes >= 1,000: 41,822 (5.32%)
- votes >= 10,000: 8,527 (1.08%)
- votes >= 100,000: 1,602 (0.20%)
- votes >= 1,000,000: 23 (0.00%)

Here is a list of the titles with more than 1,000,000 votes:

 1. 1,884,541 votes: [The Shawshank Redemption](http://www.imdb.com/title/tt0111161/)
 2. 1,860,469 votes: [The Dark Knight](http://www.imdb.com/title/tt0468569/)
 3. 1,649,480 votes: [Inception](http://www.imdb.com/title/tt1375666/)
 4. 1,510,460 votes: [Fight Club](http://www.imdb.com/title/tt0137523/)
 5. 1,474,140 votes: [Pulp Fiction](http://www.imdb.com/title/tt0110912/)
 6. 1,422,029 votes: [Forrest Gump](http://www.imdb.com/title/tt0109830/)
 7. 1,366,914 votes: [The Lord of the Rings: The Fellowship of the Ring](http://www.imdb.com/title/tt0120737/)
 8. 1,355,662 votes: [The Matrix](http://www.imdb.com/title/tt0133093/)
 9. 1,346,670 votes: [The Lord of the Rings: The Return of the King](http://www.imdb.com/title/tt0167260/)
 10. 1,286,635 votes: [The Godfather](http://www.imdb.com/title/tt0068646/)
 11. 1,266,981 votes: [The Dark Knight Rises](http://www.imdb.com/title/tt1345836/)
 12. 1,265,135 votes: [Game of Thrones](http://www.imdb.com/title/tt0944947/)
 13. 1,218,915 votes: [The Lord of the Rings: The Two Towers](http://www.imdb.com/title/tt0167261/)
 14. 1,149,297 votes: [Se7en](http://www.imdb.com/title/tt0114369/)
 15. 1,116,105 votes: [Interstellar](http://www.imdb.com/title/tt0816692/)
 16. 1,093,352 votes: [Gladiator](http://www.imdb.com/title/tt0172495/)
 17. 1,087,730 votes: [Batman Begins](http://www.imdb.com/title/tt0372784/)
 18. 1,084,819 votes: [Django Unchained](http://www.imdb.com/title/tt1853728/)
 19. 1,074,638 votes: [The Avengers](http://www.imdb.com/title/tt0848228/)
 20. 1,041,328 votes: [Breaking Bad](http://www.imdb.com/title/tt0903747/)
 21. 1,012,747 votes: [Star Wars: Episode IV - A New Hope](http://www.imdb.com/title/tt0076759/)
 22. 1,003,494 votes: [The Silence of the Lambs](http://www.imdb.com/title/tt0102926/)
 23. 1,001,780 votes: [Inglourious Basterds](http://www.imdb.com/title/tt0361748/)
