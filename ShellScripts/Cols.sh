#!/bin/bash -x

#Name: cols
#Author: Thor Arisland
#This program replaces the first two columns with each other in a csv file separated by a single whitespace.
#Usage: ./Cols.sh [input file] [output file]
#Output file is optional.
#If no output file is provided, the input file will be overwritten after the columns have been replaced.


if [ $# == "1" ] ; then
    output="${1%.*}_temp.${1##*.}";
fi

rm -f $output && touch $output;

while IFS=" " read c1 c2
do
    echo $c2" "$c1 >> $output;
done < $1    

if [ $# == "1" ] ; then
    mv $output $1;
fi
   
