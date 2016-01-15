#!/usr/bin/python
import sys
import xml.etree.ElementTree as ET
import heapq

# find the 10 most popular questions in each file

heap_queue = []
for line in sys.stdin:
	line = line.strip()
	# format line to be parsed
	line = '' + line + ''
	parser = ET.fromstring(line)
	# if PostTypeId exits in line then extract value
	if 'PostTypeId' in parser.attrib:
		Id = parser.attrib['Id']
		PostTypeId = parser.attrib['PostTypeId']
		if PostTypeId == '1':
			if 'ViewCount' in parser.attrib:
				ViewCount = parser.attrib['ViewCount']
				ViewCount = int(ViewCount)
				# put viewcount-id pair into heap queue
				heapq.heappush(heap_queue, (ViewCount, Id))
				if len(heap_queue) > 10:
					# Pop the id with smallest viewCount from the heap
					heapq.heappop(heap_queue)
	# emit every top10 pair
for ViewCount,Id in heap_queue:
	print '%s\t%s'%(Id,str(ViewCount))