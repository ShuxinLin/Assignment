#!/usr/bin/python

import sys

# mapper is the same as task_4's

def words_seq(line, n):
  line = line.split()
  seq = []
  for i in range(len(line)-n+1):
  	seq.append(line[i:i+n])
  return seq

if __name__ == '__main__':
	for line in sys.stdin:
		line = line.strip()
		seq = words_seq(line, 2)

		for two_words in seq:
			print '%s\t%s' % (' '.join(two_words), 1)