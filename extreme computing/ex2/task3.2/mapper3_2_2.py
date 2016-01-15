#!/usr/bin/python

import sys
import heapq

# mapper2 is used to emit the top 10 hosts that produced the most 404 errors
# use heap queue to store top 10
# each mapper only outputs 10 lines to save a bunch of sort work laten on

if __name__ == '__main__':
    heap_queue = []
    for line in sys.stdin:
        line = line.strip()
        host, value = line.split('\t',1)
        value = int(value)

        # push the value item onto the heap
        heapq.heappush(heap_queue, (value, host))
        if len(heap_queue) > 10:
        	# Pop and return the smallest item from the heap
            heapq.heappop(heap_queue)

    # emit every top10 pair
    for value,host in heap_queue:
        print '%s\t%s'%(value,host)