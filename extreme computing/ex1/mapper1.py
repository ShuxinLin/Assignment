#!/usr/bin/python

import sys

if __name__ == '__main__':
    for line in sys.stdin:
    	line = line.strip()
        lowercase_line = line.lower()
        print '%s' % (lowercase_line)

