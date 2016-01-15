#!/usr/bin/python

import sys

# extract page message in each line

for line in sys.stdin:
	line = line.strip()
	# extract each request in each line
	request = line.split('"')[1]
	request = request.strip()
	# the correct request should be something like "<command> <page site> ..."
	# split the request and get the page site
	tokens = request.split()
	if len(tokens) > 1:
		page_site = tokens[1]
		# emit page_site with 1
		print '%s\t%s' % (page_site, 1)