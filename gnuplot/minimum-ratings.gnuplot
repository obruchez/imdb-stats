set term png size 1024, 768

set terminal png linewidth 2

set key off

set xlabel "Number of movies"
set ylabel "Minimum rating"

set xtics 0,20000,215000
set output 'minimum-ratings.5votes.png'
plot 'minimum-ratings.5votes.tsv' using 1:2 with lines

set xtics 0,20000,180000
set output 'minimum-ratings.10votes.png'
plot 'minimum-ratings.10votes.tsv' using 1:2 with lines

set xtics 0,10000,80000
set output 'minimum-ratings.100votes.png'
plot 'minimum-ratings.100votes.tsv' using 1:2 with lines

set xtics 0,5000,30000
set output 'minimum-ratings.1000votes.png'
plot 'minimum-ratings.1000votes.tsv' using 1:2 with lines

set xtics 0,1000,10000
set output 'minimum-ratings.10000votes.png'
plot 'minimum-ratings.10000votes.tsv' using 1:2 with lines

set key on

set xtics 0,20000,215000
set output 'minimum-ratings.multiplevotecounts.png'
plot 'minimum-ratings.5votes.tsv' using 1:2 with lines title "5 votes", \
     'minimum-ratings.10votes.tsv' using 1:2 with lines title "10 votes", \
     'minimum-ratings.100votes.tsv' using 1:2 with lines title "100 votes", \
     'minimum-ratings.1000votes.tsv' using 1:2 with lines title "1,000 votes", \
     'minimum-ratings.10000votes.tsv' using 1:2 with lines title "10,000 votes"

set xtics 0,500,5000
set output 'minimum-ratings.multiplevotecounts.5000movies.png'
plot [0:5000] 'minimum-ratings.5votes.tsv' using 1:2 with lines title "5 votes", \
              'minimum-ratings.10votes.tsv' using 1:2 with lines title "10 votes", \
              'minimum-ratings.100votes.tsv' using 1:2 with lines title "100 votes", \
              'minimum-ratings.1000votes.tsv' using 1:2 with lines title "1,000 votes", \
              'minimum-ratings.10000votes.tsv' using 1:2 with lines title "10,000 votes"

set key off

set xlabel "Number of votes"

set xtics 0,2000,30000

set output 'minimum-ratings.250movies.png'
plot 'minimum-ratings.250movies.tsv' using 1:2 with lines

set output 'minimum-ratings.1500movies.png'
plot 'minimum-ratings.1500movies.tsv' using 1:2 with lines
