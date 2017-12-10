set term png size 1024, 768

set key off

set style data boxes
set boxwidth 0.8 relative
set style fill solid 1.0

set xlabel "Duration (minutes)"
set ylabel "Frequency"

set xtics 1875,25,2125

set output 'duration-frequencies.png'
plot 'duration-frequencies.tsv' using 1:2 with boxes
