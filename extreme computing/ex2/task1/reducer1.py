#!/usr/bin/python

import sys
from collections import defaultdict

# sort file list dictionary and turn it to be a list
def sort(file_list):
    l = []
    for key in sorted(file_list):
        pair = '(' + key + ',' + str(file_list[key]) + ')'
        l.append(pair)
    sorted_list = ','.join(l)
    return sorted_list


prev_word = ''
filename = ''

# use defaultdict class to store (filename, frequency) pairs
file_list = defaultdict(int)

for line in sys.stdin:
    line = line.strip()
    word, filename = line.split()
    if word == prev_word:
    	# increment frequency in the specific file
        file_list[filename] += 1
    else:
        if prev_word:
        	# sort file_list by file id and return a list with correct format
            sorted_list = sort(file_list)
            # emit each record
            print prev_word + " : " + str(len(file_list)) + " : {" + sorted_list + "}"
            # clear file_list dictionary
            file_list = defaultdict(int)
        file_list[filename] += 1
        prev_word = word
# emit last record
sorted_list = sort(file_list)
print prev_word + " : " + str(len(file_list)) + " : {" + sorted_list + "}"
