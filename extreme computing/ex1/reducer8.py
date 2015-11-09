#!/usr/bin/python
import sys

# I define each line of output as a record

if __name__ == '__main__':
	record = ''
	prev_tag = ''
	for line in sys.stdin:
		line = line.strip().split('\t')
		
		# Check tag
		tag = line[1]
		if tag == 'student':
			# if the tag change from "mark" to "student", it means a new record starts
			if prev_tag == 'mark':
				print record
				record = ''
			record = line[2] + ' -->'
			prev_tag = tag
		# if tag is "mark", continue to add mark_pair at the tail of record
		if tag == 'mark':
			mark_pair = '(' + line[2] + ',' + line[3] + ')'
			record = " ".join((record, mark_pair))
			prev_tag = tag
	# don't forget to emit last record
	print record