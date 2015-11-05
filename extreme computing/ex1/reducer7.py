#!/usr/bin/python
import sys

if __name__ == '__main__':
    row = []
    prev_row_id = '0'
    for line in sys.stdin:
        line = line.strip()
        # set row_id to be column_id and column_id to be row_id 
        # to achieve matrix transposition
        row_id, column_id, value = line.split('\t')

        if row_id == prev_row_id:
            row.append(value)
        else:
            if prev_row_id:
                # emit previous row and rebuild a new row
                print '%s\t%s'%(prev_row_id,' '.join(tuple(row)))
                row = []
                row.append(value)
        prev_row_id = row_id
    # Don't forget to emit last row
    print '%s\t%s'%(prev_row_id,' '.join(tuple(row)))