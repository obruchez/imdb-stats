# imdb-stats

[![Scala CI](https://github.com/obruchez/imdb-stats/actions/workflows/scala.yml/badge.svg)](https://github.com/obruchez/imdb-stats/actions/workflows/scala.yml)

Computes statistics using IMDb dataset files.

## Scripts

To download the IMDb dataset files:

```download.sh <S3 key> <S3 password>```

To compute the statistics, generate the plots / PNG images, and save the results into README.md:

```generate.sh```

## Statistics

As of February 06, 2022, there are 8.671.097 titles in the IMDb dataset files.

### Title types

Titles can be partitioned into 10 different types:

 - TV episode: 6.484.165 (74,78%)
 - Short film: 854.492 (9,85%)
 - Feature film: 601.101 (6,93%)
 - Video: 257.374 (2,97%)
 - TV series: 220.573 (2,54%)
 - TV movie: 134.794 (1,55%)
 - TV mini-series: 41.945 (0,48%)
 - TV special: 35.845 (0,41%)
 - Video game: 30.354 (0,35%)
 - TV short: 10.452 (0,12%)
 - TV pilot: 2 (0,00%)

### Years

7.557.081 titles (87,15%) have a start and/or end year defined:

- minimum: 1.874,00
- maximum: 2.028,00
- mean: 2.004,03
- median: 2.011,00
- mode: 2.018,00
- standard deviation: 20,38

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/year-frequencies.2025.png" width="600">

The earliest title in IMDb is [The Passage of Venus ](http://www.imdb.com/title/tt3155794/) (1874). And, yes, [100 Years](http://www.imdb.com/title/tt5174640/) is planned for release in 2115!

### Durations

2.350.973 titles (27,11%) have a runtime duration defined:

- minimum: 1,00
- maximum: 51.420,00
- mean: 44,53
- median: 30,00
- mode: 30,00
- standard deviation: 72,32

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.300.png" width="600">

Most durations above 1,000 minutes are experimental videos, total durations for series, mistakes, etc.

Here are the statistics and frequency plot for feature films only:

- count: 377.980
- minimum: 1,00
- maximum: 51.420,00
- mean: 89,84
- median: 89,00
- mode: 90,00
- standard deviation: 128,31

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.movies.png" width="600">

For short films only:

- count: 554.888
- minimum: 1,00
- maximum: 1.440,00
- mean: 13,12
- median: 11,00
- mode: 10,00
- standard deviation: 9,28

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/duration-frequencies.shorts.png" width="600">

There is some overlap between the short films and feature films. I'm not sure it totally makes sense (e.g. a feature film shorter than 10 minutes or a short film longer than 100 minutes?).

### Ratings

1.212.002 titles (13,98%) have ratings.

- minimum: 1,00
- maximum: 10,00
- mean: 6,92
- median: 7,10
- mode: 7,20
- standard deviation: 1,40

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-frequencies.png" width="600">

### Vote counts

Each title with a rating has at least 5 votes (this is a limit enforced by IMDb).

- minimum: 5,00
- maximum: 2.538.931,00
- mean: 997,82
- median: 24,00
- mode: 7,00
- standard deviation: 16.650,68

Most titles don't have much votes. The full frequency plot is not very useful:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.png" width="600">

If we zoom to 1,100 votes and less, we can see what's happening a little bit better:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.95.png" width="600">

95% of the titles with votes are in that area (i.e. about 1,100 votes and less):

- votes ≥ 10: 960.761 (79,27%)
- votes ≥ 100: 293.053 (24,18%)
- votes ≥ 1.000: 71.291 (5,88%)
- votes ≥ 10.000: 12.443 (1,03%)
- votes ≥ 100.000: 2.316 (0,19%)
- votes ≥ 1.000.000: 55 (0,00%)

Here is a list of the titles with more than 1,000,000 votes:

 1. 2.538.931 votes: [The Shawshank Redemption](http://www.imdb.com/title/tt0111161/)
 2. 2.489.321 votes: [The Dark Knight](http://www.imdb.com/title/tt0468569/)
 3. 2.230.374 votes: [Inception](http://www.imdb.com/title/tt1375666/)
 4. 1.998.005 votes: [Fight Club](http://www.imdb.com/title/tt0137523/)
 5. 1.959.136 votes: [Forrest Gump](http://www.imdb.com/title/tt0109830/)
 6. 1.953.988 votes: [Pulp Fiction](http://www.imdb.com/title/tt0110912/)
 7. 1.945.929 votes: [Game of Thrones](http://www.imdb.com/title/tt0944947/)
 8. 1.832.227 votes: [The Matrix](http://www.imdb.com/title/tt0133093/)
 9. 1.772.521 votes: [The Lord of the Rings: The Fellowship of the Ring](http://www.imdb.com/title/tt0120737/)
 10. 1.750.953 votes: [The Lord of the Rings: The Return of the King](http://www.imdb.com/title/tt0167260/)
 11. 1.747.116 votes: [The Godfather](http://www.imdb.com/title/tt0068646/)
 12. 1.684.206 votes: [Interstellar](http://www.imdb.com/title/tt0816692/)
 13. 1.659.906 votes: [Breaking Bad](http://www.imdb.com/title/tt0903747/)
 14. 1.614.570 votes: [The Dark Knight Rises](http://www.imdb.com/title/tt1345836/)
 15. 1.581.972 votes: [The Lord of the Rings: The Two Towers](http://www.imdb.com/title/tt0167261/)
 16. 1.557.229 votes: [Se7en](http://www.imdb.com/title/tt0114369/)
 17. 1.467.478 votes: [Django Unchained](http://www.imdb.com/title/tt1853728/)
 18. 1.432.350 votes: [Gladiator](http://www.imdb.com/title/tt0172495/)
 19. 1.390.153 votes: [Batman Begins](http://www.imdb.com/title/tt0372784/)
 20. 1.369.352 votes: [Inglourious Basterds](http://www.imdb.com/title/tt0361748/)
 21. 1.364.122 votes: [The Silence of the Lambs](http://www.imdb.com/title/tt0102926/)
 22. 1.336.640 votes: [The Avengers](http://www.imdb.com/title/tt0848228/)
 23. 1.324.663 votes: [Saving Private Ryan](http://www.imdb.com/title/tt0120815/)
 24. 1.310.588 votes: [The Wolf of Wall Street](http://www.imdb.com/title/tt0993846/)
 25. 1.303.086 votes: [Star Wars: Episode IV - A New Hope](http://www.imdb.com/title/tt0076759/)
 26. 1.296.533 votes: [Schindler's List](http://www.imdb.com/title/tt0108052/)
 27. 1.274.336 votes: [The Prestige](http://www.imdb.com/title/tt0482571/)
 28. 1.267.696 votes: [The Departed](http://www.imdb.com/title/tt0407887/)
 29. 1.235.041 votes: [The Green Mile](http://www.imdb.com/title/tt0120689/)
 30. 1.233.387 votes: [Shutter Island](http://www.imdb.com/title/tt1130884/)
 31. 1.231.605 votes: [Star Wars: Episode V - The Empire Strikes Back](http://www.imdb.com/title/tt0080684/)
 32. 1.211.893 votes: [The Godfather: Part II](http://www.imdb.com/title/tt0071562/)
 33. 1.191.839 votes: [Memento](http://www.imdb.com/title/tt0209144/)
 34. 1.177.783 votes: [Avatar](http://www.imdb.com/title/tt0499549/)
 35. 1.141.348 votes: [Back to the Future](http://www.imdb.com/title/tt0088763/)
 36. 1.138.611 votes: [Joker](http://www.imdb.com/title/tt7286456/)
 37. 1.121.998 votes: [Guardians of the Galaxy](http://www.imdb.com/title/tt2015381/)
 38. 1.120.977 votes: [American Beauty](http://www.imdb.com/title/tt0169547/)
 39. 1.118.908 votes: [Titanic](http://www.imdb.com/title/tt0120338/)
 40. 1.109.062 votes: [Léon: The Professional](http://www.imdb.com/title/tt0110413/)
 41. 1.097.598 votes: [Goodfellas](http://www.imdb.com/title/tt0099685/)
 42. 1.086.575 votes: [V for Vendetta](http://www.imdb.com/title/tt0434409/)
 43. 1.084.633 votes: [American History X](http://www.imdb.com/title/tt0120586/)
 44. 1.068.895 votes: [Kill Bill: Vol. 1](http://www.imdb.com/title/tt0266697/)
 45. 1.066.807 votes: [WALL·E](http://www.imdb.com/title/tt0910970/)
 46. 1.061.444 votes: [Pirates of the Caribbean: The Curse of the Black Pearl](http://www.imdb.com/title/tt0325980/)
 47. 1.054.271 votes: [Terminator 2: Judgment Day](http://www.imdb.com/title/tt0103064/)
 48. 1.048.164 votes: [The Usual Suspects](http://www.imdb.com/title/tt0114814/)
 49. 1.016.334 votes: [The Truman Show](http://www.imdb.com/title/tt0120382/)
 50. 1.011.059 votes: [Iron Man](http://www.imdb.com/title/tt0371746/)
 51. 1.007.754 votes: [Avengers: Endgame](http://www.imdb.com/title/tt4154796/)
 52. 1.007.479 votes: [The Lion King](http://www.imdb.com/title/tt0110357/)
 53. 1.006.767 votes: [Star Wars: Episode VI - Return of the Jedi](http://www.imdb.com/title/tt0086190/)
 54. 1.005.363 votes: [Braveheart](http://www.imdb.com/title/tt0112573/)
 55. 1.003.487 votes: [Finding Nemo](http://www.imdb.com/title/tt0266543/)

Most (but not all) of those titles are feature films. The mean/median numbers of votes for feature films are greater than the mean/median numbers of votes for all titles:

- minimum: 5,00
- maximum: 2.538.931,00
- mean: 3.462,79
- median: 57,00
- mode: 8,00
- standard deviation: 33.488,44

But the plot still doesn't look like a bell curve:

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/rating-vote-count-frequencies.movies.png" width="600">

- votes ≥ 10: 247.612 (90,20%)
- votes ≥ 100: 110.649 (40,31%)
- votes ≥ 1.000: 36.624 (13,34%)
- votes ≥ 10.000: 9.731 (3,54%)
- votes ≥ 100.000: 2.097 (0,76%)
- votes ≥ 1.000.000: 53 (0,02%)

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

 - votes ≥ 5 ⇒ rating ≥ 9,20
 - votes ≥ 10 ⇒ rating ≥ 9,10
 - votes ≥ 100 ⇒ rating ≥ 8,30
 - votes ≥ 1.000 ⇒ rating ≥ 8,10
 - votes ≥ 10.000 ⇒ rating ≥ 7,70
 - votes ≥ 25.000 ⇒ rating ≥ 7,50
 - votes ≥ 100.000 ⇒ rating ≥ 6,70

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.1500movies.png" width="600">

Example 2. What about 250 feature films?

 - votes ≥ 5 ⇒ rating ≥ 9,60
 - votes ≥ 10 ⇒ rating ≥ 9,50
 - votes ≥ 100 ⇒ rating ≥ 9,00
 - votes ≥ 1.000 ⇒ rating ≥ 8,70
 - votes ≥ 10.000 ⇒ rating ≥ 8,30
 - votes ≥ 25.000 ⇒ rating ≥ 8,20
 - votes ≥ 100.000 ⇒ rating ≥ 8,10

<img src="https://raw.githubusercontent.com/obruchez/imdb-stats/master/results/minimum-ratings.250movies.png" width="600">

At the time of writing, all the movies in the [IMDb Top 250](http://www.imdb.com/chart/top) have more than 25,000 votes and a rating of 8.0 or more. If I had to guess, I would have given a minimum rating of 8.1 for a maximum of 250 movies to watch and a minimum of 25'000 votes. The discrepancy probably comes from the fact that "only votes from regular IMDb voters are considered when creating the top 250 out of the full voting database". I have no way of knowing which vote comes from "regular IMDb voters". This information is not included in the IMDb dataset files.

### To-do

- check if there is a correlation between ratings and number of votes (plot + regression)
- check if there is a correlation between ratings and years (plot + regression)
