#!/usr/bin/python
import sys

# format the output

for line in sys.stdin:
	line = line.strip()
	count, OwnerUserId, Answer_id_list = line.split('\t')
	print "%s\t -> %s\t%s" % (OwnerUserId, count, Answer_id_list)