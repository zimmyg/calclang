package com.zim.dataset;

import com.zim.util.ArrayIterator;
import com.zim.util.CollectionOfArraysStrideIterator;

import java.util.*;

public class CollectionOfArraysDataset extends Dataset {
    private Collection<Object[]> data;

    public CollectionOfArraysDataset(Collection<Object[]> data, DataOrder order) {
        super(order);
        this.data = data;
    }

    @Override
    protected int getInnerLength() {
        if (data == null || data.size() == 0) return 0;

        Object[] firstInner = data.iterator().next();
        return firstInner == null ? -1 : firstInner.length;
    }

    @Override
    protected int getOuterLength() {
        return data == null ? 0 : data.size();
    }

    @Override
    protected ArrayList<Iterator<Object>> getOuterIterators() {
        ArrayList<Iterator<Object>> result = new ArrayList<>();

        for (Object[] col: data) {
            result.add(new ArrayIterator<Object>(col));
        }

        return result;
    }

    @Override
    protected ArrayList<Iterator<Object>> getInnerIterators() {
        ArrayList<Iterator<Object>> result = new ArrayList<>();

        for (int i = 0; i < getInnerLength(); i++) {
            result.add(new CollectionOfArraysStrideIterator<>(data, i));
        }

        return result;
    }

    @Override
    public void addRow(Object[] row) {
        if (row == null) {
            return;
        }

        int origRowCount = getRowCount();
        int origColCount = getColCount();

        if ((long)origRowCount + 1L > Integer.MAX_VALUE && order == DataOrder.ROW) { // only throw this if the expanding axis is an array
            throw new ArithmeticException("This data structure cannot hold this many rows, integer overflow of row count! Use expandable data set in stead.");
        }
        if (origColCount != row.length) {
            throw new IllegalStateException("Column count mismatch when trying to add a new row!");
        }

        if (this.order == DataOrder.ROW) {
            data.add(row);
        } else {
            // a little more involved, need to copy and expand each column array to hold one more value
            Collection<Object[]> newData = new LinkedList<>(); // this is temp storage, we will stuff the new data back into the original collection afterwards to avoid concurrent modification
            Iterator<Object[]> origDataIt = data.iterator();
            int colIdx = 0;
            while (origDataIt.hasNext()) {
                Object[] col = origDataIt.next();
                Object[] newCol = new Object[col.length + 1]; // new array with space for one more row
                System.arraycopy(col, 0, newCol, 0, col.length); // copy original data into new array
                newCol[origRowCount] = row[colIdx]; // add new value to end of array

                origDataIt.remove(); // remove old col array from dataset
                newData.add(newCol);
                colIdx++;
            }

            // now go through and add all the new col arrays back into the original collection
            // data should be empty after we removed everything above
            for (Object[] newCol: newData) {
                data.add(newCol);
            }
        }
    }

    @Override
    public void addRow(Collection<Object> row) {
        if (row == null) {
            return;
        }

        Object[] rowArr = row.toArray();
        addRow(rowArr);
    }
}
