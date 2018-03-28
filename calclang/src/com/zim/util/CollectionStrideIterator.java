package com.zim.util;

import java.util.Collection;
import java.util.Iterator;

public abstract class CollectionStrideIterator<CT, VT> implements Iterator<VT> {

    private Collection<CT> coll;
    private Iterator<CT> outerIterator;
    private int dataStride;

    public CollectionStrideIterator(Collection<CT> coll, int dataStride) {
        this.coll = coll;
        this.dataStride = dataStride;

        if (coll != null) {
            outerIterator = coll.iterator();
        }
    }

    @Override
    public boolean hasNext() {
        return outerIterator != null && outerIterator.hasNext();
    }

    @Override
    public VT next() {
        return getNext(outerIterator.next(), dataStride);
    }

    protected abstract VT getNext(CT innerItem, int dataStride);
}
