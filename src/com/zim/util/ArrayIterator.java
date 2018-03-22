package com.zim.util;

import java.util.Iterator;

public class ArrayIterator <T> implements Iterator <T> {

    private T[] array;
    private int current;

    public ArrayIterator(T[] array) {
        this.array = array;
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return array != null && current < array.length;
    }

    @Override
    public T next() {
        return array[current++];
    }
}
