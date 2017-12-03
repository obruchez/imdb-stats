set term png size 1024, 768

set key off

set style data boxes
set boxwidth 0.8 relative
set style fill solid 1.0

set xlabel "Rating"
set ylabel "Frequency"

set xtics 0,0.5,10

set output 'rating-frequencies.png'
plot 'rating-frequencies.tsv' using 1:2 with boxes
