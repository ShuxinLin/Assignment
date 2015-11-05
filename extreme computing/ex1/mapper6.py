#!/usr/bin/python

import sys
import heapq

# each mapper takes part of task_5 output and extracts top20 frequent sequences
# heapq provides an implementation of heaq sort algorithm

if __name__ == '__main__':
    heap_queue = []
    for line in sys.stdin:
        line = line.strip()
        seq, value = line.split('\t',1)
        value = int(value)

        # push the value item onto the heap
        heapq.heappush(heap_queue, (value, seq))
        if len(heap_queue) > 20:
        	# Pop and return the smallest item from the heap
            heapq.heappop(heap_queue)

    # emit every top20 pair
    for value,seq in heap_queue:
        print '%s\t%s'%(seq,value)