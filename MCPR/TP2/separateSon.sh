#!/bin/bash

gcc init.c -o init
gcc ind.c -o ind
gcc first.c -o first

./init

for i in $(seq 0 29)
do
	./ind $i &
done

./first