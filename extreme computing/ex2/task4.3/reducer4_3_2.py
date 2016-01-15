#!/usr/bin/python
import sys

# compute the total number of the answers accepted given the ownerUserId
# form answer_id list

prev_OwnerUserId = ''
Answer_id_list = ''
total_count = 0
for line in sys.stdin:
	line = line.strip()
	OwnerUserId, AnswerId, count = line.split('\t')
	count = int(count)
	if prev_OwnerUserId == OwnerUserId:
		Answer_id_list += AnswerId + ', '
		total_count += count
	else:
		if prev_OwnerUserId:
			print '%s\t%s\t%s' % (prev_OwnerUserId, Answer_id_list, total_count)
		Answer_id_list = AnswerId + ', '
		total_count = count
		prev_OwnerUserId = OwnerUserId
# emit the last record
if prev_OwnerUserId == OwnerUserId:
	print '%s\t%s\t%s' % (prev_OwnerUserId, Answer_id_list, total_count)