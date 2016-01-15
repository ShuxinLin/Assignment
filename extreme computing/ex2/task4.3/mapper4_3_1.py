#!/usr/bin/python
import sys
import xml.etree.ElementTree as ET

# emit the acceptedAnswerId for each question post

for line in sys.stdin:
	line = line.strip()
	line = '' + line + ''
	parser = ET.fromstring(line)
	if parser.attrib['PostTypeId'] == '1':
		if 'AcceptedAnswerId' in parser.attrib:
			AcceptedAnswerId = parser.attrib['AcceptedAnswerId']
			print '%s' % (AcceptedAnswerId)