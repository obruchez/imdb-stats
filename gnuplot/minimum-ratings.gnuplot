set term png size 1024, 768

set terminal png linewidth 2

set key off

set xlabel "Number of movies"
set ylabel "Minimum rating"

set xtics 0,20000,215000
set output 'minimum-ratings.5.png'
plot 'minimum-ratings.5.tsv' using 1:2 with lines

set xtics 0,20000,180000
set output 'minimum-ratings.10.png'
plot 'minimum-ratings.10.tsv' using 1:2 with lines

set xtics 0,10000,80000
set output 'minimum-ratings.100.png'
plot 'minimum-ratings.100.tsv' using 1:2 with lines

set xtics 0,5000,30000
set output 'minimum-ratings.1000.png'
plot 'minimum-ratings.1000.tsv' using 1:2 with lines

set xtics 0,1000,10000
set output 'minimum-ratings.10000.png'
plot 'minimum-ratings.10000.tsv' using 1:2 with lines

set key on

set xtics 0,20000,215000
set output 'minimum-ratings.all.png'
plot 'minimum-ratings.5.tsv' using 1:2 with lines title "5 votes", \
     'minimum-ratings.10.tsv' using 1:2 with lines title "10 votes", \
     'minimum-ratings.100.tsv' using 1:2 with lines title "100 votes", \
     'minimum-ratings.1000.tsv' using 1:2 with lines title "1,000 votes", \
     'minimum-ratings.10000.tsv' using 1:2 with lines title "10,000 votes"
