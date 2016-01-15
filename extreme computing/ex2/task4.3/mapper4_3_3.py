#!/usr/bin/python
import sys

# outputs the only record with max_count(number of answers accepted)

max_count = 0
max_Answer_id_list = ''
max_OwnerUserId = ''
for line in sys.stdin:
	line = line.strip()
	OwnerUserId, Answer_id_list, count = line.split('\t')
	count = count.strip()
	OwnerUserId = OwnerUserId.strip()
	Answer_id_list = Answer_id_list.strip()
	count = int(count)
	if count > max_count:
		max_count = count
		max_Answer_id_list = Answer_id_list[:-1]
		max_OwnerUserId = OwnerUserId
print '%s\t%s\t%s' % (max_count, max_OwnerUserId, max_Answer_id_list)