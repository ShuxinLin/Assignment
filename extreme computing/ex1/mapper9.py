#!/usr/bin/python
import sys
import re

# mapper emits student name and his/her grade point average(gpa) 

if __name__ == '__main__':
	name = ''
	grade = 0
	gpa = 0
	for line in sys.stdin:
		line = line.strip().split()
		# check if the student takes more than 4 courses
		if len(line) > 6:
			gpa = 0
			grade = 0
			name = line[0]
			for i in range(2,len(line)):
				# use regular expression module to extract the mark of each course
				grade += int(re.findall(r"[\w']+", line[i])[-1])
			# calculate the gpa
			gpa = float(grade)/(len(line)-2)
			# emit name/gpa pair
			print "%s\t%s" % (name,str(round(gpa,2)))
