# imdb-stats

[![Build Status](https://travis-ci.org/obruchez/imdb-stats.svg?branch=master)](https://travis-ci.org/obruchez/imdb-stats)

Computes statistics using IMDb dataset files.

## Scripts

To download the IMDb dataset files:

```download.sh <S3 key> <S3 password>```

To compute the statistics, generate the plots / PNG images, and save the results into README.md:

```generate.sh```

## Statistics

As of March 03, 2019, there are 5,684,154 titles in the IMDb dataset files.

### Title types

Titles can be partitioned into 10 different types:

 - TV episode: 3,918,119 (68.93%)
 - Short film: 668,919 (11.77%)
 - Feature film: 512,676 (9.02%)
 - Video: 223,907 (3.94%)
 - TV series: 160,764 (2.83%)
 - TV movie: 125,781 (2.21%)
 - TV mini-series: 25,053 (0.44%)
 - Video game: 23,085 (0.41%)
 - TV special: 16,765 (0.29%)
 - TV short: 9,085 (0.16%)

### Years

5,372,470 titles (94.52%) have a start and/or end year defined:

- minimum: 1,874.00
- maximum: 2,115.00
- mean: 2,000.54
- median: 2,009.00
- mode: 2,017.00
- standard deviation: 21.59

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/year-frequencies.2025.png" width="600">

The earliest title in IMDb is [The Passage of Venus ](http://www.imdb.com/title/tt3155794/) (1874). And, yes, [100 Years](http://www.imdb.com/title/tt5174640/) is planned for release in 2115!

### Durations

1,717,396 titles (30.21%) have a runtime duration defined:

- minimum: 1.00
- maximum: 125,156.00
- mean: 45.41
- median: 30.00
- mode: 30.00
- standard deviation: 114.88

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.300.png" width="600">

Most durations above 1,000 minutes are experimental videos, total durations for series, mistakes, etc.

Here are the statistics and frequency plot for feature films only:

- count: 321,642
- minimum: 1.00
- maximum: 51,420.00
- mean: 87.62
- median: 88.00
- mode: 90.00
- standard deviation: 103.94

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.movies.png" width="600">

For short films only:

- count: 432,471
- minimum: 1.00
- maximum: 142.00
- mean: 13.12
- median: 11.00
- mode: 10.00
- standard deviation: 8.75

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.shorts.png" width="600">

There is some overlap between the short films and feature films. I'm not sure it totally makes sense (e.g. a feature film shorter than 10 minutes or a short film longer than 100 minutes?).

### Ratings

925,133 titles (16.28%) have ratings.

- minimum: 1.00
- maximum: 10.00
- mean: 6.91
- median: 7.10
- mode: 7.20
- standard deviation: 1.40

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-frequencies.png" width="600">

### Vote counts

Each title with a rating has at least 5 votes (this is a limit enforced by IMDb).

- minimum: 5.00
- maximum: 2,058,941.00
- mean: 958.71
- median: 20.00
- mode: 5.00
- standard deviation: 15,418.75

Most titles don't have much votes. The full frequency plot is not very useful:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.png" width="600">

If we zoom to 1,100 votes and less, we can see what's happening a little bit better:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.95.png" width="600">

95% of the titles with votes are in that area (i.e. about 1,100 votes and less):

- votes ≥ 10: 657,287 (71.05%)
- votes ≥ 100: 200,156 (21.64%)
- votes ≥ 1,000: 48,455 (5.24%)
- votes ≥ 10,000: 9,441 (1.02%)
- votes ≥ 100,000: 1,789 (0.19%)
- votes ≥ 1,000,000: 31 (0.00%)

Here is a list of the titles with more than 1,000,000 votes:

 1. 2,058,941 votes: [The Shawshank Redemption](http://www.imdb.com/title/tt0111161/)
 2. 2,025,249 votes: [The Dark Knight](http://www.imdb.com/title/tt0468569/)
 3. 1,804,511 votes: [Inception](http://www.imdb.com/title/tt1375666/)
 4. 1,647,695 votes: [Fight Club](http://www.imdb.com/title/tt0137523/)
 5. 1,609,231 votes: [Pulp Fiction](http://www.imdb.com/title/tt0110912/)
 6. 1,576,601 votes: [Forrest Gump](http://www.imdb.com/title/tt0109830/)
 7. 1,482,525 votes: [The Lord of the Rings: The Fellowship of the Ring](http://www.imdb.com/title/tt0120737/)
 8. 1,478,626 votes: [The Matrix](http://www.imdb.com/title/tt0133093/)
 9. 1,465,962 votes: [The Lord of the Rings: The Return of the King](http://www.imdb.com/title/tt0167260/)
 10. 1,415,970 votes: [Game of Thrones](http://www.imdb.com/title/tt0944947/)
 11. 1,412,447 votes: [The Godfather](http://www.imdb.com/title/tt0068646/)
 12. 1,364,364 votes: [The Dark Knight Rises](http://www.imdb.com/title/tt1345836/)
 13. 1,325,580 votes: [The Lord of the Rings: The Two Towers](http://www.imdb.com/title/tt0167261/)
 14. 1,264,649 votes: [Interstellar](http://www.imdb.com/title/tt0816692/)
 15. 1,261,921 votes: [Se7en](http://www.imdb.com/title/tt0114369/)
 16. 1,190,625 votes: [Gladiator](http://www.imdb.com/title/tt0172495/)
 17. 1,187,623 votes: [Django Unchained](http://www.imdb.com/title/tt1853728/)
 18. 1,173,791 votes: [Batman Begins](http://www.imdb.com/title/tt0372784/)
 19. 1,163,259 votes: [Breaking Bad](http://www.imdb.com/title/tt0903747/)
 20. 1,152,027 votes: [The Avengers](http://www.imdb.com/title/tt0848228/)
 21. 1,109,954 votes: [The Silence of the Lambs](http://www.imdb.com/title/tt0102926/)
 22. 1,103,174 votes: [Star Wars: Episode IV - A New Hope](http://www.imdb.com/title/tt0076759/)
 23. 1,098,334 votes: [Inglourious Basterds](http://www.imdb.com/title/tt0361748/)
 24. 1,088,160 votes: [Saving Private Ryan](http://www.imdb.com/title/tt0120815/)
 25. 1,066,456 votes: [Schindler's List](http://www.imdb.com/title/tt0108052/)
 26. 1,057,436 votes: [The Departed](http://www.imdb.com/title/tt0407887/)
 27. 1,046,245 votes: [The Prestige](http://www.imdb.com/title/tt0482571/)
 28. 1,033,352 votes: [Star Wars: Episode V - The Empire Strikes Back](http://www.imdb.com/title/tt0080684/)
 29. 1,028,434 votes: [Avatar](http://www.imdb.com/title/tt0499549/)
 30. 1,016,198 votes: [Memento](http://www.imdb.com/title/tt0209144/)
 31. 1,013,159 votes: [The Wolf of Wall Street](http://www.imdb.com/title/tt0993846/)

Most (but not all) of those titles are feature films. The mean/median numbers of votes for feature films are greater than the mean/median numbers of votes for all titles:

- minimum: 5.00
- maximum: 2,058,941.00
- mean: 3,180.90
- median: 45.00
- mode: 6.00
- standard deviation: 29,807.90

But the plot still doesn't look like a bell curve:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.movies.png" width="600">

- votes ≥ 10: 192,599 (83.22%)
- votes ≥ 100: 85,630 (37.00%)
- votes ≥ 1,000: 28,239 (12.20%)
- votes ≥ 10,000: 7,897 (3.41%)
- votes ≥ 100,000: 1,664 (0.72%)
- votes ≥ 1,000,000: 29 (0.01%)

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

 - votes ≥ 5 ⇒ rating ≥ 9.20
 - votes ≥ 10 ⇒ rating ≥ 9.00
 - votes ≥ 100 ⇒ rating ≥ 8.30
 - votes ≥ 1,000 ⇒ rating ≥ 8.10
 - votes ≥ 10,000 ⇒ rating ≥ 7.70
 - votes ≥ 25,000 ⇒ rating ≥ 7.40
 - votes ≥ 100,000 ⇒ rating ≥ 6.20

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.1500movies.png" width="600">

Example 2. What about 250 feature films?

 - votes ≥ 5 ⇒ rating ≥ 9.50
 - votes ≥ 10 ⇒ rating ≥ 9.40
 - votes ≥ 100 ⇒ rating ≥ 8.80
 - votes ≥ 1,000 ⇒ rating ≥ 8.60
 - votes ≥ 10,000 ⇒ rating ≥ 8.30
 - votes ≥ 25,000 ⇒ rating ≥ 8.20
 - votes ≥ 100,000 ⇒ rating ≥ 8.10

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.250movies.png" width="600">

At the time of writing, all the movies in the [IMDb Top 250](http://www.imdb.com/chart/top) have more than 25,000 votes and a rating of 8.0 or more. If I had to guess, I would have given a minimum rating of 8.1 for a maximum of 250 movies to watch and a minimum of 25'000 votes. The discrepancy probably comes from the fact that "only votes from regular IMDb voters are considered when creating the top 250 out of the full voting database". I have no way of knowing which vote comes from "regular IMDb voters". This information is not included in the IMDb dataset files.

### To-do

- check if there is a correlation between ratings and number of votes (plot + regression)
- check if there is a correlation between ratings and years (plot + regression)
