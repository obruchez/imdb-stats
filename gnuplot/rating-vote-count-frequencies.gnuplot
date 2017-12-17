set term png size 1024, 768

set key off

set style data boxes
set boxwidth 0.8 relative
set style fill solid 1.0

set xlabel "Vote count"
set ylabel "Frequency"

set xtics 0,250000,2000000

set output 'rating-vote-count-frequencies.png'
plot 'rating-vote-count-frequencies.tsv' using 1:2 with boxes

set xtics 0,100,1100

set output 'rating-vote-count-frequencies.95.png'
plot 'rating-vote-count-frequencies.95.tsv' using 1:2 with boxes

set xtics 0,100,1100

set output 'rating-vote-count-frequencies.movies.png'
plot 'rating-vote-count-frequencies.movies.tsv' using 1:2 with boxes

set xlabel "log_{10}(vote count)"

set xtics 0,1,7

set output 'rating-vote-count-frequencies.log10.png'
plot 'rating-vote-count-frequencies.log10.tsv' using 1:2 with boxes
