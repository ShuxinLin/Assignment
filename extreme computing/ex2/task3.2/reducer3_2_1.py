#!/usr/bin/python
import sys

# compute the frequency of 404 error given a host address
# emit the host with the result

prev_host = ''
total_count = 0
for line in sys.stdin:
	line = line.strip()
	host, count = line.split('\t', 1)
	count = int(count)
	if prev_host == host:
		total_count += count
	else:
		if prev_host:
			# emit host and its total frequncy of 404 errors
			print '%s\t%s' % (prev_host, total_count)
		total_count = count
		prev_host = host
# emit the last record
if host == prev_host:
	print '%s\t%s' % (prev_host, total_count)