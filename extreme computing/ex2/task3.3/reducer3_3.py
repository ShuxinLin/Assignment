#!/usr/bin/python
import sys
import datetime

# compute the time difference between the first and the last visit for each host

prev_host = ''
count = 0

# initialize start time and end time for the host
start_time = datetime.datetime(datetime.MAXYEAR, 12, 31)
end_time = datetime.datetime(datetime.MINYEAR, 1, 1)

for line in sys.stdin:
	line = line.strip()
	host, timestamp = line.split("\t")
	# decode timestamp to datetime class
	# the format of datetime is (year, month, day, hour, minute, second)
	time = datetime.datetime.strptime(timestamp, '%d/%b/%Y:%H:%M:%S')
	if host == prev_host:
		# compare current timestamp with start time and end time
		if time < start_time:
			start_time = time
		if time > end_time:
			end_time = time
		count += 1
	else:
		if prev_host:
			# if a host visits only once then print the timestamp of the visit
			# the count value indicates the frequency of host appearing in the input
			if count == 1:
				print "%s\t%s" % (prev_host, start_time)
			else:
				print "%s\t%s" % (prev_host, str(end_time-start_time))

			# clear and load the new time	
			start_time = time
			prev_host = host
			count = 1
			end_time = datetime.datetime(datetime.MINYEAR, 1, 1)
		else:
			# prev_host that is null means that this is the first host read in
			start_time = time
			prev_host = host
			count = 1
# emit the last host and its visit time difference
if count == 1:
	print "%s\t%s" % (prev_host, start_time)
else:
	print "%s\t%s" % (prev_host, str(end_time-start_time))