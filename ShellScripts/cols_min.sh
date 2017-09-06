#/bin/bash

#A minimal version of the cols program to do exactly the same as the java program.
#Needs two arguments.

rm -f $2 && touch $2;

while IFS=" " read c1 c2
do
    echo $c2" "$c1 >>$2;
done < $1
