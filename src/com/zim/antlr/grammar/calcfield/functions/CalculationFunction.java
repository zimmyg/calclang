package com.zim.antlr.grammar.calcfield.functions;

import com.zim.antlr.grammar.calcfield.context.FieldDataType;

import java.util.List;

/**
 * Base interface for all calculation functions.
 */
public interface CalculationFunction {

    /**
     * The data type of the returned values from this function.
     */
    FieldDataType getReturnType();

    /**
     * The callable name of this function. Eg. SUM, AVG, etc.
     */
    String getFunctionName();


    /**
     * An ordered list of the data type for each argument to this function.
     */
    List<FieldDataType> getFunctionArguments();


    /**
     * Is this function an aggregation function? Ie. Does it work on more than one row of data at a time?
     */
    boolean isAggregationFunction();
}
