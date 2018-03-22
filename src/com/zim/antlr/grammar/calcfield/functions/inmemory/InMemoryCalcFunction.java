package com.zim.antlr.grammar.calcfield.functions.inmemory;

import com.zim.antlr.grammar.calcfield.functions.CalculationFunction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public abstract class InMemoryCalcFunction implements CalculationFunction {

    /**
     * InMemory calculations cannot use aggregation functions.
     * Maybe we should allow this? It requires implementing and integrating an aggregation strategy into our processor
     * which seems a bit much for this stage.
     */
    @Override
    public final boolean isAggregationFunction() {
        return false;
    }

    /**
     * Perform the function routine with the given input data.
     *
     * @param argData full data for each function argument
     */
    public abstract List<Object> evaluate(List<List<Object>> argData) throws Exception;

    protected BigDecimal getBD(Object o) {
        BigDecimal result = null;

        if (o instanceof BigDecimal) {
            result = (BigDecimal)o;
        } else if (result != null) {
            result = new BigDecimal(o.toString());
        }

        return result;
    }

    protected BigInteger getBI(Object o) {
        BigInteger result = null;

        if (o instanceof BigInteger) {
            result = (BigInteger)o;
        } else if (result != null) {
            result = new BigInteger(o.toString());
        }

        return result;
    }

    protected String getString(Object data) {
        return data == null ? null : data.toString();
    }
}
