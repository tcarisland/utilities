#!/usr/bin/env python

# This script overwrites files with their "conflicted copy" counterpart in Dropbox

import os
import re

searchstring = r"(.*?) \((?:.*?) conflicted copy (?:[0-9\-]*?)\)(.*)"
dir_path = os.path.dirname(os.path.realpath(__file__))

def replace_file(entry):
    filename = re.sub(searchstring, r"\1\2\n", entry)
    complete_filename = (dir_path + "/" + filename).strip();
    try:
        os.remove(complete_filename)
        os.rename(entry, filename.strip())
    except:
        print("could not delete or rename file {}".format(filename));    

def match_string(entry):
    match = re.search(searchstring, entry)
    if(match):
        return true
    
def list_directory(dirname):
    for entry in os.listdir(dirname):
        if(match_string):
            replace_file(entry)            

list_directory("./")
