package com.zim.util;

import java.util.Collection;

public class CollectionOfArraysStrideIterator <T> extends CollectionStrideIterator <T[], T> {
    public CollectionOfArraysStrideIterator(Collection<T[]> coll, int dataStride) {
        super(coll, dataStride);
    }

    @Override
    protected T getNext(T[] innerItem, int dataStride) {
        return innerItem[dataStride];
    }
}
