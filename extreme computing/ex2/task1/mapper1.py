#!/usr/bin/python

import sys
import string
import os

# task 1: inverted index

for line in sys.stdin:
	full_path = os.environ["mapreduce_map_input_file"]
	# split full_path into path and filename, get filename only
	filename = os.path.split(full_path)[-1]
	line = line.strip()
	# split line by blank space
	words = line.split()
	# emit each word with current filename
	for word in words:
	    print "%s\t%s" % (word, filename)
