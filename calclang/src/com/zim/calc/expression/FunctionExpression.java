package com.zim.calc.expression;

import com.zim.calc.context.CalculationFunction;
import com.zim.calc.context.FieldDataType;

import java.util.LinkedList;
import java.util.List;

public class FunctionExpression extends Expression {

    private FieldDataType resultType = null;

    private CalculationFunction function = null;
    private List<Expression> argumentExpressions = new LinkedList<>();

    public CalculationFunction getFunction() {
        return function;
    }

    public void setFunction(CalculationFunction function) {
        this.function = function;
    }

    public List<Expression> getArgumentExpressions() {
        return argumentExpressions;
    }

    public void setArgumentExpressions(List<Expression> argumentExpressions) {
        this.argumentExpressions = argumentExpressions;
    }

    @Override
    public FieldDataType getResultType() throws Exception {
        return resultType;
    }

    public void setResultType(FieldDataType resultType) {
        this.resultType = resultType;
    }
}
