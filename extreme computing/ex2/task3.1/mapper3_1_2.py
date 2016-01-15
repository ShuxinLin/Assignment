#!/usr/bin/python

import sys
import heapq
# emit the most popular page site in each file
# the mapper input is the first reducer output

if __name__ == '__main__':
	max_value = 0
	max_page = ''
	for line in sys.stdin:
		line = line.strip()
		page, value = line.split('\t',1)
		value = int(value)
		if value > max_value:
			max_value = value
			max_page = page
	# emit top 1 pair
	print '%s\t%s'%(str(max_value),max_page)