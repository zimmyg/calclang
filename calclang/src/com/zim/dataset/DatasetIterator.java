package com.zim.dataset;

import java.util.ArrayList;
import java.util.Iterator;

public class DatasetIterator implements Iterator<Object[]> {

    private Object[] currentRow;
    private ArrayList<Iterator<Object>> columnIterators;

    public DatasetIterator(Dataset set) {
        currentRow = new Object[set.getColCount()];
        columnIterators = set.getColumnDataIterators();

        if (currentRow.length != columnIterators.size()) throw new IllegalStateException("Data length mismatch!");
    }

    private Iterator<Object> getIteratorFor(int col) {
        if (columnIterators == null) return null;
        if (columnIterators.size() <= col) return null;

        return columnIterators.get(col);
    }

    @Override
    public boolean hasNext() {
        // since they should all have the same row count, just check the first one
        Iterator<Object> firstIt = getIteratorFor(0);
        return firstIt != null && firstIt.hasNext();
    }

    @Override
    public Object[] next() {
        for (int i = 0; i < currentRow.length; i++) {
            Iterator<Object> it = getIteratorFor(i);
            if (it != null && it.hasNext()) {
                currentRow[i] = it.next();
            }
        }

        return currentRow;
    }

    @Override
    public void remove () {
        throw new UnsupportedOperationException("Not implemented!");
    }
}
