#!/usr/bin/python


import sys

# emit timestamp in each line

for line in sys.stdin:
	line = line.strip()
	# get timestamp by partitioning and splitting the line
	timestamp = line.partition('[')[2].partition(']')[0].split()[0]
	timestamp = timestamp.strip()
	host = line.split('- -')[0]
	host = host.strip()
	# emit host-timestamp pair of each line
	print "%s\t%s" % (host, timestamp)