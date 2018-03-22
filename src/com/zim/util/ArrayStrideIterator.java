package com.zim.util;

import java.util.Iterator;

public class ArrayStrideIterator <T> implements Iterator <T> {

    private T[][] arrayOfArrays;
    private int current;
    private int dataIndex;

    public ArrayStrideIterator(T[][] arrayOfArrays, int dataIndex) {
        this.arrayOfArrays = arrayOfArrays;
        this.dataIndex = dataIndex;

        current = 0;
    }

    @Override
    public boolean hasNext() {
        return arrayOfArrays != null && current < arrayOfArrays.length;
    }

    @Override
    public T next() {
        return arrayOfArrays[current++][dataIndex];
    }
}
