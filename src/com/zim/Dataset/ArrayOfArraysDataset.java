package com.zim.Dataset;

import com.zim.util.ArrayIterator;
import com.zim.util.ArrayStrideIterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ArrayOfArraysDataset extends Dataset {
    private Object[][] data;

    public ArrayOfArraysDataset(Object[][] data, DataOrder order) {
        super(order);
        this.data = data;
    }

    @Override
    protected int getInnerLength() {
        if (data == null || data.length == 0) return 0;

        Object[] firstInner = data[0];
        return firstInner == null ? 0 : firstInner.length;
    }

    @Override
    protected int getOuterLength() {
        return data == null ? 0 : data.length;
    }

    @Override
    public ArrayList<Iterator<Object>> getOuterIterators() {
        ArrayList<Iterator<Object>> result = new ArrayList<>();

        for (Object[] col: data) {
            result.add(new ArrayIterator<Object>(col));
        }

        return result;
    }

    @Override
    public ArrayList<Iterator<Object>> getInnerIterators() {
        ArrayList<Iterator<Object>> result = new ArrayList<>();

        for (int i = 0; i < getOuterLength(); i++) {
            result.add(new ArrayStrideIterator<>(data, i));
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

        if ((long)origRowCount + 1L > Integer.MAX_VALUE) {
            throw new ArithmeticException("This data structure cannot hold this many rows, integer overflow of row count! Use expandable data set in stead.");
        }
        if (origColCount != row.length) {
            throw new IllegalStateException("Column count mismatch when trying to add a new row!");
        }

        Object[][] origData = data;
        if (this.order == DataOrder.ROW) {
            data = new Object[origRowCount + 1][origColCount];
        } else {
            data = new Object[origColCount][origRowCount + 1];
        }

        for (int i = 0; i < origData.length; i++) {
            System.arraycopy(origData[i], 0, data[i], 0, origData[i].length);
        }

        if (this.order == DataOrder.ROW) {
            data = new Object[origRowCount + 1][origColCount];
            data[origRowCount] = row;
        } else {
            data = new Object[origColCount][origRowCount + 1];
            // a little more involved, need to add each col of the new row to the array
            int colIdx = 0;
            for (Object o: row) {
                data[colIdx][origRowCount] = o;
                colIdx++;
            }
        }
    }

    @Override
    public void addRow(Collection<Object> row) {
        if (row == null) {
            return;
        }

        addRow(row.toArray());
    }
}
