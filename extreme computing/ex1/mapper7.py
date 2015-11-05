#!/usr/bin/python
import sys

# mapper reads each value in a line
if __name__ == '__main__':
	for line in sys.stdin:
		line = line.strip()
		row_id, values = line.split('\t')
		value_list = values.split()
		
		for column_id in range(len(value_list)):
			# emit each value with column_id and row_id
			print '%s\t%s\t%s' % (column_id, row_id, value_list[column_id])