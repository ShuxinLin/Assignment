#!/usr/bin/python

import sys

# return all two-word sequences of each line in list format

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
			# emit every sequence and its count -- 1
			print '%s\t%s' % (' '.join(two_words), 1)