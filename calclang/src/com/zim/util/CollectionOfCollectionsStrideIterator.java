package com.zim.util;

import java.util.Collection;
import java.util.Iterator;

public class CollectionOfCollectionsStrideIterator <T> extends CollectionStrideIterator <Collection<T>, T> {
    public CollectionOfCollectionsStrideIterator(Collection<Collection<T>> coll, int dataStride) {
        super(coll, dataStride);
    }

    @Override
    protected T getNext(Collection<T> innerItem, int dataStride) {
        int innerCurrent = 0;
        T val = null;
        for (T inner: innerItem) {
            if (dataStride == innerCurrent++) { // check comp and then increment
                val = inner;
                break;
            }
        }

        return val;
    }
}