# imdb-stats

[![Build Status](https://travis-ci.org/obruchez/imdb-stats.svg?branch=master)](https://travis-ci.org/obruchez/imdb-stats)

Computes statistics using IMDb dumps.

## Scripts

To download the IMDb dumps:

```download.sh <S3 key> <S3 password>```

To compute the statistics, generate the plots / PNG images, and save the results into README.md:

```generate.sh```

## Statistics

As of December 17, 2017, there are 4,694,073 titles in the IMDb dumps.

### Title types

Titles can be partitioned into 10 different types:

 - TV episode: 3,115,681
 - Short film: 595,803
 - Feature film: 471,590
 - Video: 189,288
 - TV series: 139,747
 - TV movie: 121,824
 - TV mini-series: 20,875
 - Video game: 20,698
 - TV special: 10,418
 - TV short: 8,149

### Years

4,424,354 titles (94.25%) have a start and/or end year defined:

- minimum: 1,874.00
- maximum: 2,115.00
- mean: 1,999.20
- median: 2,008.00
- mode: 2,016.00
- standard deviation: 22.40

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/year-frequencies.2025.png" width="600">

The earliest title in IMDb is [The Passage of Venus ](http://www.imdb.com/title/tt3155794/) (1874). And, yes, [100 Years](http://www.imdb.com/title/tt5174640/) is planned for release in 2115!

### Durations

1,443,735 titles (30.76%) have a runtime duration defined:

- minimum: 1.00
- maximum: 125,156.00
- mean: 46.79
- median: 30.00
- mode: 30.00
- standard deviation: 116.57

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.300.png" width="600">

Most durations above 1,000 minutes are experimental videos, total durations for series, mistakes, etc.

Here are the statistics and frequency plot for feature films only:

- count: 291,907
- minimum: 1.00
- maximum: 14,400.00
- mean: 87.51
- median: 88.00
- mode: 90.00
- standard deviation: 53.58

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.movies.png" width="600">

For short films only:

- count: 387,965
- minimum: 1.00
- maximum: 1,834.00
- mean: 13.27
- median: 11.00
- mode: 10.00
- standard deviation: 10.04

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.shorts.png" width="600">

There is some overlap between the short films and feature films. I'm not sure it totally makes sense (e.g. a feature film shorter than 10 minutes or a short film longer than 100 minutes?).

### Ratings

788,329 titles (16.79%) have ratings.

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
- maximum: 1,887,261.00
- mean: 993.75
- median: 20.00
- mode: 5.00
- standard deviation: 15,247.74

Most titles don't have much votes. The full frequency plot is not very useful:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.png" width="600">

If we zoom to 1,100 votes and less, we can see what's happening a little bit better:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.95.png" width="600">

About 95% of the titles with votes have 1,000 votes or less:

- votes >= 10: 570,833 (72.41%)
- votes >= 100: 174,286 (22.11%)
- votes >= 1,000: 41,883 (5.31%)
- votes >= 10,000: 8,552 (1.08%)
- votes >= 100,000: 1,605 (0.20%)
- votes >= 1,000,000: 23 (0.00%)

Here is a list of the titles with more than 1,000,000 votes:

 1. 1,887,261 votes: [The Shawshank Redemption](http://www.imdb.com/title/tt0111161/)
 2. 1,863,001 votes: [The Dark Knight](http://www.imdb.com/title/tt0468569/)
 3. 1,651,848 votes: [Inception](http://www.imdb.com/title/tt1375666/)
 4. 1,512,571 votes: [Fight Club](http://www.imdb.com/title/tt0137523/)
 5. 1,476,191 votes: [Pulp Fiction](http://www.imdb.com/title/tt0110912/)
 6. 1,424,344 votes: [Forrest Gump](http://www.imdb.com/title/tt0109830/)
 7. 1,368,699 votes: [The Lord of the Rings: The Fellowship of the Ring](http://www.imdb.com/title/tt0120737/)
 8. 1,357,615 votes: [The Matrix](http://www.imdb.com/title/tt0133093/)
 9. 1,348,515 votes: [The Lord of the Rings: The Return of the King](http://www.imdb.com/title/tt0167260/)
 10. 1,288,572 votes: [The Godfather](http://www.imdb.com/title/tt0068646/)
 11. 1,268,496 votes: [The Dark Knight Rises](http://www.imdb.com/title/tt1345836/)
 12. 1,267,723 votes: [Game of Thrones](http://www.imdb.com/title/tt0944947/)
 13. 1,220,593 votes: [The Lord of the Rings: The Two Towers](http://www.imdb.com/title/tt0167261/)
 14. 1,150,931 votes: [Se7en](http://www.imdb.com/title/tt0114369/)
 15. 1,118,607 votes: [Interstellar](http://www.imdb.com/title/tt0816692/)
 16. 1,094,842 votes: [Gladiator](http://www.imdb.com/title/tt0172495/)
 17. 1,089,101 votes: [Batman Begins](http://www.imdb.com/title/tt0372784/)
 18. 1,086,589 votes: [Django Unchained](http://www.imdb.com/title/tt1853728/)
 19. 1,075,655 votes: [The Avengers](http://www.imdb.com/title/tt0848228/)
 20. 1,043,281 votes: [Breaking Bad](http://www.imdb.com/title/tt0903747/)
 21. 1,015,564 votes: [Star Wars: Episode IV - A New Hope](http://www.imdb.com/title/tt0076759/)
 22. 1,005,064 votes: [The Silence of the Lambs](http://www.imdb.com/title/tt0102926/)
 23. 1,003,465 votes: [Inglourious Basterds](http://www.imdb.com/title/tt0361748/)

### Minimum ratings

Question: what's the minimum IMDb rating for a feature film that you should watch if you can only watch N feature films in your life?

Here's the plot if you take into account all feature films with ratings:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.5.png" width="600">

If you take into account only feature films with 100 votes or more:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.100.png" width="600">

And now with 10,000 votes or more:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.10000.png" width="600">

All the plots have the same shape: the more films you take, the less you have to be strict/conservative about the minimum rating. It makes complete sense.

If you put all the plots on the same image, it becomes clear in what way the minimum number of votes influences the minimum rating:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.all.png" width="600">

The higher the number of votes, the lower the number of feature films there are with that many votes. In other words, you can be less strict/conservative about the minimum rating with movies that have lots of votes.

But let's be honest, shall we? You probably won't see more than 5,000 feature films in your entire life, unless you're a movie buff. So let's zoom a little:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.all.5000.png" width="600">

At this scale, if becomes clear that the minimum number of votes becomes less important: the minimum rating doesn't go all the way down to 1; actually, it doesn't even go below 7.5 for most plots and doesn't go below ~6.5 for all of them. It appears that it's probably a good idea to stay clear of feature films with a rating lower than 7 or 8, depending on the number of films.

Example 1. Let's say you only have the time to watch 1,500 feature films. These are the minimum ratings for various minimum number of votes:

 - votes >= 5 ⇒ rating >= 9.20
 - votes >= 10 ⇒ rating >= 9.00
 - votes >= 100 ⇒ rating >= 8.20
 - votes >= 1,000 ⇒ rating >= 8.10
 - votes >= 10,000 ⇒ rating >= 7.70
 - votes >= 25,000 ⇒ rating >= 7.30
 - votes >= 100,000 ⇒ rating >= 4.10

Example 2. What about 250 feature films?

 - votes >= 5 ⇒ rating >= 9.70
 - votes >= 10 ⇒ rating >= 9.40
 - votes >= 100 ⇒ rating >= 8.80
 - votes >= 1,000 ⇒ rating >= 8.60
 - votes >= 10,000 ⇒ rating >= 8.30
 - votes >= 25,000 ⇒ rating >= 8.20
 - votes >= 100,000 ⇒ rating >= 8.10

At the time of writing, all the movies in the [IMDb Top 250](http://www.imdb.com/chart/top) have more than 25,000 votes and a rating of 8.0 or more. If I had to guess, I would have given a minimum rating of 8.1 for a maximum of 250 movies to watch and a minimum of 25'000 votes. The discrepancy probably comes from the fact that "only votes from regular IMDb voters are considered when creating the top 250 out of the full voting database". I have no way of knowing which vote comes from "regular IMDb voters". This information is not included in the IMDb dumps.
