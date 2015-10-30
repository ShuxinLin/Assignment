#!/usr/bin/python

import sys

# produce a version which is all lower-case

if __name__ == '__main__':
    for line in sys.stdin:
    	line = line.strip()
        lowercase_line = line.lower()
        # emit the lower-case line
        print '%s' % (lowercase_line)
