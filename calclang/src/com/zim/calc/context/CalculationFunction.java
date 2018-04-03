package com.zim.calc.context;

/**
 * Base interface for all calculation functions.
 */
public interface CalculationFunction {

    /**
     * Array of signatures for this function. This allows overloading.
     */
    FunctionSignature[] getSignatures();

    /**
     * The callable name of this function. Eg. SUM, AVG, etc.
     */
    String getFunctionName();

    /**
     * Is this function an aggregation function? Ie. Does it work on more than one row of data at a time?
     */
    boolean isAggregationFunction();
}
