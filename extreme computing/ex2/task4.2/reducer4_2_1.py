#!/usr/bin/python
import sys

# outputs answerer id, question list and its total number

prev_AnswererId = ''
Question_list = ''
total_count = 0
for line in sys.stdin:
	line = line.strip()
	AnswererId, QuestionId, count = line.split('\t')
	count = int(count)
	if prev_AnswererId == AnswererId:
		# append new question id
		Question_list += QuestionId + ', '
		total_count += count
	else:
		if prev_AnswererId:
			# emit record
			print '%s\t%s\t%s' % (prev_AnswererId, Question_list, total_count)
		# clear and initialize
		Question_list = QuestionId + ', '
		total_count = count
		prev_AnswererId = AnswererId
# emit the last record
if AnswererId == prev_AnswererId:
	print '%s\t%s\t%s' % (prev_AnswererId, Question_list, total_count)