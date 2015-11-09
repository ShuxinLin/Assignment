#!/usr/bin/python
import sys
   
if __name__ == '__main__':
	for line in sys.stdin:
		# Extract tag and line from line
		line = line.strip()
		line = line.split()
		tag = line[0]

		# check tag, then store every components into variables
		if tag == "mark":
			courseId = line[1]
			studentId = line [2]
			grade = line[3]
			# emit one "mark" record
			print '%s\t%s\t%s\t%s' % (studentId, tag, courseId, grade)
		else:
			if tag == "student":
				studentId = line[1]
				name = line[2] 
				# emit one "student" record
				print '%s\t%s\t%s' % (studentId, tag, name)