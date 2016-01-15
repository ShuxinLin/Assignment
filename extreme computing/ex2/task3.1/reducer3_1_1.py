#!/usr/bin/python

# count times of each page site called

import sys
prev_page = ''
total_count = 0
for line in sys.stdin:
	line = line.strip()
	page, count = line.split('\t', 1)
	count = int(count)
	if prev_page == page:
		total_count += count
	else:
		if prev_page:
			# emit page site and the number of times it is called 
			print '%s\t%s' % (prev_page, total_count)
		total_count = count
		prev_page = page
# emit the last page site
if page == prev_page:
	print '%s\t%s' % (prev_page, total_count)