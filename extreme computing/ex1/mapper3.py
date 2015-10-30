#!/usr/bin/python
import sys

# mapper counts lines and words of its input 
    
if __name__ == '__main__':
	word_count = 0
	line_count = 0

	for line in sys.stdin:
		line = line.strip()
		line_count += 1
		word_count += len(line.split())

	print '%s\t%s' % (word_count, line_count)