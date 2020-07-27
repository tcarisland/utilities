#!/usr/bin/python

import subprocess
import sys

#width = 2048;
#height = 2048;
#rows = 8;
#columns = 8;
#filename = "filename.png"

def crop(w, h, x, y, fn, fnp, r, c):
    command = 'convert {} -crop {}x{}+{}+{} {}_{}_{}.png'.format(fn, w, h, x, y, fnp, r, c)
    #print(command)
    subprocess.Popen(command.split())

def cropAll(width, height, rows, columns, filename):
    tileheight = height / rows;
    tilewidth = width / columns;
    fnp = filename.split(".")[0]    
    for r in range(rows):
        for c in range(columns):
            crop(tilewidth, tileheight, c * tilewidth, r * tileheight, filename, fnp, r, c)

cropAll(int(sys.argv[1]), int(sys.argv[2]), int(sys.argv[3]), int(sys.argv[4]), sys.argv[5])
