#!/usr/bin/python
import sys

# format the output

for line in sys.stdin:
	line = line.strip()
	count, owner_id, post_id_list = line.split('\t')
	print '%s\t -> %s' % (owner_id, post_id_list)