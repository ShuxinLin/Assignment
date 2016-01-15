#!/usr/bin/python

import sys

# emit host message of each line

for line in sys.stdin:
	line = line.strip()
	# split line with '- -' and get host address
	host = line.split('- -')[0]
	host = host.strip()
	# get reply code
	reply = line.split()[-2]
	reply = reply.strip()
	if reply == '404':
		# emit host if reply code is 404
		print "%s\t%s" % (host, 1)