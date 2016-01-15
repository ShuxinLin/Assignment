#!/usr/bin/python
import sys
import xml.etree.ElementTree as ET

# load answer_id_list and outputs records with ownerUserId if her/his answer is accepted

answer_id_list = []
# read each term from answer_id_list.txt
for line in file('answer_id_list.txt'):
	line = line.strip()
	answer_id_list.append(line)

for line in sys.stdin:
	line = line.strip()
	line = '' + line + ''
	parser = ET.fromstring(line)
	if parser.attrib['PostTypeId'] == '2':
		Id = parser.attrib['Id']
		# if the answer post is accepted
		if Id in answer_id_list:
			if 'OwnerUserId' in parser.attrib:
				OwnerUserId = parser.attrib['OwnerUserId']
				print '%s\t%s\t%s' % (OwnerUserId, Id, 1)