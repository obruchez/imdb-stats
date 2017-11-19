set term png size 1024, 768

set style data boxes
set boxwidth 0.8 relative
set style fill solid 1.0

set xlabel "Vote count"
set ylabel "Frequency"

set key off

set output 'rating-vote-count-frequencies.png'
plot 'rating-vote-count-frequencies.tsv' using 1:2 with boxes

set output 'rating-vote-count-frequencies.95.png'
plot 'rating-vote-count-frequencies.95.tsv' using 1:2 with boxes

set xlabel "log_{10}(vote count)"

set output 'rating-vote-count-frequencies.log10.png'
plot 'rating-vote-count-frequencies.log10.tsv' using 1:2 with boxes
