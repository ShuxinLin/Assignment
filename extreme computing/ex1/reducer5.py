#!/usr/bin/python
import sys

# reducer is the same as task_5's
# reducer is also used as combiner
# as a combiner, it used as aggregating step to lessen keys that are passed to the reducer

if __name__ == '__main__':
	prev_seq = ''
	value_total = 0

	for line in sys.stdin:
		line = line.strip()
		seq, value = line.split('\t', 1)
		value = int(value)

		if prev_seq == seq:
			value_total += value
		else:
			if prev_seq:
				print '%s\t%s' % (prev_seq, value_total)
			value_total = value
			prev_seq = seq

	if seq == prev_seq:
		print '%s\t%s' % (prev_seq, value_total)
