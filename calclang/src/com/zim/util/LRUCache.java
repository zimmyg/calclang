package com.zim.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This is not a very fast implementation of an LRU cache because this reorders the list whenever something is accessed.
 */
public class LRUCache<KT, VT> extends LinkedHashMap<KT, VT> {
    private int maxSize;

    public LRUCache(int maxSize) {
        // initialize with the max size, because why not
        super(maxSize, 0.7f, true);

        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<KT, VT> eldest) {
        return size() > maxSize; // we want to remove the eldest element when the size has exceeded the max size
    }
}
