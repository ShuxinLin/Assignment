#!/usr/bin/python
import sys

# reducer does the count given two-word sequence
# note that the reducer input is sorted by key

if __name__ == '__main__':
	prev_seq = ''
	value_total = 0

	for line in sys.stdin:
		line = line.strip()
		seq, value = line.split('\t', 1)
		value = int(value)

		# check if current sequence is the same as the previous one
		# if yes, increment the value(count).
		# Otherwise, emit the previous pair and recount the new one
		if prev_seq == seq:
			value_total += value
		else:
			if prev_seq:
				print '%s\t%s' % (prev_seq, value_total)
			value_total = value
			prev_seq = seq

	# Don't forget the last pair
	if seq == prev_seq:
		print '%s\t%s' % (prev_seq, value_total)