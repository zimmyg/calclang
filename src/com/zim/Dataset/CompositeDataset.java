package com.zim.Dataset;

import java.util.*;

import static com.zim.Dataset.Dataset.DataOrder.ROW;

/**
 * This class defines a composite data set which is made op from one or more parallel data sets.
 * Parallel meaning that they must have the same number of rows and should be treated as collections
 * of columns from the same data set.
 *
 * While this class could allow nesting of composite datasets, in order to avoid performance issues that could cause,
 * when composing composite datasets, this composite dataset in stead pulls out the inner composed datasets into itself
 * rather than just pointing to the other composite as a regular dataset.
 */
public class CompositeDataset extends Dataset {
    private static final int REQUIRED_ROWS_UNSET_VALUE = Integer.MIN_VALUE; // -2^31, not likely to clash


    private List<Dataset> datasets;
    private int requiredRowCount = REQUIRED_ROWS_UNSET_VALUE;

    // the weird signature is so that we can guarantee at least one set is passed
    public CompositeDataset(Dataset first, Dataset... sets) {
        super(ROW); // a composite data set will always be row ordered, because we can delegate how exactly we would like to access our sub-datasets

        datasets = new LinkedList<>();

        addInitialDataset(first);
        if (sets != null) {
            int rowCount = 0;
            for (Dataset set: sets) {
                addDataset(set);
            }
        }
    }

    private void addInitialDataset(Dataset first) {
        if (first == null)
            throw new IllegalStateException("Initial dataset cannot be null!");

        // has this already been initialised and is being called again?
        if (datasets.size() != 0 || requiredRowCount != REQUIRED_ROWS_UNSET_VALUE)
            throw new IllegalStateException("Illegal attempt to reinitialise datasets!");

        requiredRowCount = first.getRowCount();
        addDataset(first);
    }

    public void addDataset(Dataset set) {
        if (set == null) return;

        if (set instanceof CompositeDataset) {
            // just compose this composite's sub-sets, rather than composing the composite itself, because of performance reasons
            CompositeDataset subComposite = (CompositeDataset)set;
            for (Dataset subset: subComposite.getDatasets()) {
                addDataset(subset);
            }
        } else {
            if (set.getRowCount() != requiredRowCount)
                throw new IllegalStateException("Dataset length does not match existing datasets!");

            this.datasets.add(set);
        }
    }

    public Collection<Dataset> getDatasets() {
        return new LinkedList<>(datasets); // return a copy
    }

    @Override
    protected int getInnerLength() {
        // if we are assuming to be row-ordered, our 'inner' would be the columns
        // simply sum the column counts of all sub-sets
        int colCount = 0;
        for (Dataset set: datasets) {
            colCount += set.getColCount();
        }

        return colCount;
    }

    @Override
    protected int getOuterLength() {
        // if we are assuming to be row-ordered, our 'outer' would be the rows, so return the row count
        // we already have this value cached, because they all must have the same row count
        return requiredRowCount;
    }

    @Override
    protected ArrayList<Iterator<Object>> getOuterIterators() {
        // we don't need to implement this, because we are always row-ordered and therefore this should never be called.
        // Might want to implement this in the future for completeness though
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    protected ArrayList<Iterator<Object>> getInnerIterators() {
        // the inner iterators are just the column iterators from all of our datasets
        ArrayList<Iterator<Object>> result = new ArrayList<>();
        for (Dataset set: datasets) {
            ArrayList<Iterator<Object>> subCols = set.getColumnDataIterators();
            result.addAll(subCols);
        }

        return result;
    }

    @Override
    public void addRow(Object[] row) {
        if (row == null) return;

        int origColCount = getColCount();
        if (row.length != origColCount) {
            throw new IllegalStateException("Column count mismatch when trying to add a new row!");
        }

        // break the row into groups for each sub-dataset
        int inputRowStartColIndex = 0;
        for(Dataset sub: this.datasets) {
            int subColCount = sub.getColCount();

            Object[] subRow = new Object[subColCount];
            System.arraycopy(row, inputRowStartColIndex, subRow, 0, subColCount);
            sub.addRow(subRow);

            inputRowStartColIndex += subColCount; // advance pointer for next dataset
        }
    }

    @Override
    public void addRow(Collection<Object> row) {
        if (row == null) return;

        addRow(row.toArray());
    }
}
