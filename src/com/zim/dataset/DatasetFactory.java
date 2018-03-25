package com.zim.dataset;

import java.util.Collection;
import java.util.LinkedList;

public class DatasetFactory {

    // TODO: Type erasure why you do me like this? Work out a better way of doing this, or at least better naming
    public Dataset fromRowOrderedData_ArrayOfArrays(Object[][] raw) {
        return new ArrayOfArraysDataset(raw, Dataset.DataOrder.ROW);
    }

    public Dataset fromColOrderedData_ArrayOfArrays(Object[][] raw) {
        return new ArrayOfArraysDataset(raw, Dataset.DataOrder.COL);
    }

    public Dataset fromRowOrderedData_CollecOfArrays(Collection<Object[]> raw) {
        return new CollectionOfArraysDataset(raw, Dataset.DataOrder.ROW);
    }

    public Dataset fromColOrderedData_CollecOfArrays(Collection<Object[]> raw) {
        return new CollectionOfArraysDataset(raw, Dataset.DataOrder.COL);
    }

    public Dataset fromRowOrderedData_CollecOfCollecs(Collection<Collection<Object>> raw) {
        return new CollectionOfCollectionsDataset(raw, Dataset.DataOrder.ROW);
    }

    public Dataset fromColOrderedData_CollecOfCollecs(Collection<Collection<Object>> raw) {
        return new CollectionOfCollectionsDataset(raw, Dataset.DataOrder.COL);
    }

    public Dataset compose(Dataset initial, Dataset... sets) {
        return new CompositeDataset(initial, sets);
    }

    public Dataset emptyDatasetWithSize(int rows, int cols) {
        Object[][] rawData = new Object[rows][cols];
        return fromRowOrderedData_ArrayOfArrays(rawData);
    }

    public Dataset emptyExpandableDatasetWithCols(int colCount) {
        Collection<Collection<Object>> rawData = new LinkedList<>();
        for (int i = 0; i < colCount; i++) {
            rawData.add(new LinkedList<>());
        }
        return fromColOrderedData_CollecOfCollecs(rawData);
    }
}
