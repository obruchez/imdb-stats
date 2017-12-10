set term png size 1024, 768

set key off

set style data boxes
set boxwidth 0.8 relative
set style fill solid 1.0

set xlabel "Duration (minutes)"
set ylabel "Frequency"

set xtics 0,50,500

set output 'duration-frequencies.500.png'
plot 'duration-frequencies.500.tsv' using 1:2 with boxes