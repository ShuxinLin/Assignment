#!/usr/bin/python
import sys
import xml.etree.ElementTree as ET

# outputs answerer_id with question_id answered and frequency 1

for line in sys.stdin:
	line = line.strip()
	line = '' + line + ''
	parser = ET.fromstring(line)
	# if this line is an answer line
	if parser.attrib['PostTypeId'] == '2':
		if 'OwnerUserId' in parser.attrib:
			AnswererId = parser.attrib['OwnerUserId']
			if 'ParentId' in parser.attrib:
				# find out the question id answered
				QuestionId = parser.attrib['ParentId']
				print "%s\t%s\t%s" % (AnswererId, QuestionId, 1)