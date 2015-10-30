#!/usr/bin/python

import sys

# Remove duplicated lines from the produced lower-case version
# note that the input is sorted

if __name__ == '__main__':
	previous_line = ''
	for line in sys.stdin:
		line = line.strip()
		# emit current line if it is different from previous line
		if line != previous_line:
			print '%s' % (line)
		previous_line = line