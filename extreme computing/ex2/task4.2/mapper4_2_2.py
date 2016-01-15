#!/usr/bin/python
import sys

# outputs only the record with max frequency

max_count = 0
max_Question_list = ''
max_AnswererId = ''
for line in sys.stdin:
	line = line.strip()
	AnswererId, Question_list, count = line.split('\t')
	count = count.strip()
	AnswererId = AnswererId.strip()
	Question_list = Question_list.strip()
	count = int(count)
	if count > max_count:
		max_count = count
		# there is ',' at the end of list to be removed
		max_Question_list = Question_list[:-1]
		max_AnswererId = AnswererId
# emit the only record with max_count
print '%s\t%s\t%s' % (max_count, max_AnswererId, max_Question_list)