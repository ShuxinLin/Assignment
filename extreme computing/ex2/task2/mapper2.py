#!/usr/bin/python
import sys
import string
import math

# task 2: calculate term frequency-inverse document frequency

term = []
# set the total number of files to be 17
N = 17.0

# read each term from terms.txt
for line in file('terms.txt'):
	line = line.strip()
	term.append(line)

for line in sys.stdin:
	line = line.strip()
	# split each line by ':' from the end of line and allocate
	word, total_count, file_list = line.rsplit(':',2)
	word = word.strip()
	
	if word in term:
		total_count = total_count.strip()
		file_list = file_list.strip()
		replace_punctuation = string.maketrans(string.punctuation, ' '*len(string.punctuation))
		# split file_list by every possible punctuation
		split_list = file_list.translate(replace_punctuation).split()
		# because file_list is sorted by file id, so check only the first file id
		if split_list[0] == 'd1':
			# the 3rd element of split_list is the frequency of word used in d1.txt
			tf = int(split_list[2])
		else:
			tf = 0
		idf = math.log((N/(1+int(total_count))),10)
		# compute tf_idf
		tf_idf = tf * idf
		# emit each record 
		print "%s, d1.txt = %s" % (word, str(tf_idf))