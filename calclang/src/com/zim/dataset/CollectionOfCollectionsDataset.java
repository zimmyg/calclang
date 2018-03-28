package com.zim.dataset;

import com.zim.util.CollectionOfCollectionsStrideIterator;

import java.util.*;

public class CollectionOfCollectionsDataset extends Dataset {
    private Collection<Collection<Object>> data;

    protected CollectionOfCollectionsDataset(Collection<Collection<Object>> data, DataOrder order) {
        super(order);
        this.data = data;
    }

    @Override
    protected int getInnerLength() {
        if (data == null || data.size() == 0) return 0;

        Collection<Object> firstInner = data.iterator().next();
        return firstInner == null ? -1 : firstInner.size();
    }

    @Override
    protected int getOuterLength() {
        return data == null ? 0 : data.size();
    }

    @Override
    protected ArrayList<Iterator<Object>> getOuterIterators() {
        ArrayList<Iterator<Object>> result = new ArrayList<>();

        for (Collection<Object> col: data) {
            result.add(col.iterator());
        }

        return result;
    }

    @Override
    protected ArrayList<Iterator<Object>> getInnerIterators() {
        ArrayList<Iterator<Object>> result = new ArrayList<>();

        for (int i = 0; i < getOuterLength(); i++) {
            result.add(new CollectionOfCollectionsStrideIterator<>((Collection<Collection<Object>>)data, i));
        }

        return result;
    }

    @Override
    public void addRow(Object[] row) {
        if (row == null) {
            return;
        }

        LinkedList<Object> newRow = new LinkedList<>();
        for (Object o: row) {
            newRow.add(o);
        }
        addRow(newRow);
    }

    @Override
    public void addRow(Collection<Object> row) {
        if (row == null) {
            return;
        }

        int origColCount = getColCount();

        if (origColCount != row.size()) {
            throw new IllegalStateException("Column count mismatch when trying to add a new row!");
        }

        if (this.order == DataOrder.ROW) {
            data.add(row);
        } else {
            Iterator<Object> rowIt = row.iterator();
            for (Collection<Object> col: data) {
                col.add(rowIt.next());
            }
        }
    }
}
