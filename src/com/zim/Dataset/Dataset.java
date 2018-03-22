package com.zim.Dataset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class Dataset {
    enum DataOrder {
        /**
         * Indicates that the outer collection houses a collection of row data, as such: [ [col1_val1, col2_val1],
         * [col1_val2, col2_val2], ... ]
         */
        ROW,

        /**
         * Indicates that the outer collection houses a collection of column data, as such: [ [col1_val1, col1_val2],
         * [col2_val1, col2_val2], ... ]
         */
        COL
    }


    protected DataOrder order;
    protected Dataset(DataOrder order) {
        this.order = order;
    }

    protected abstract int getInnerLength();
    protected abstract int getOuterLength();
    protected abstract ArrayList<Iterator<Object>> getOuterIterators();
    protected abstract ArrayList<Iterator<Object>> getInnerIterators();

    public abstract void addRow(Object[] row);
    public abstract void addRow(Collection<Object> row);

    public int getRowCount() {
        switch (order) {
            case ROW: return getOuterLength();
            case COL: return getInnerLength();
        }

        return 0;
    }

    public int getColCount() {
        switch (order) {
            case COL: return getOuterLength();
            case ROW: return getInnerLength();
        }

        return 0;
    }

    /**
     * We want the column iterators to have good random access performance, so ensure that they are ArrayList
     */
    public ArrayList<Iterator<Object>> getColumnDataIterators() {
        switch (order) {
            case ROW: return getInnerIterators();
            case COL: return getOuterIterators();
        }

        return null;
    }

    public DatasetIterator iterator() {
        return new DatasetIterator(this);
    }
}
