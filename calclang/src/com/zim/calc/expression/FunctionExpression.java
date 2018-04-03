package com.zim.calc.expression;

import com.zim.calc.context.CalculationFunction;
import com.zim.calc.context.FieldDataType;
import com.zim.calc.context.FunctionSignature;

import java.util.LinkedList;
import java.util.List;

public class FunctionExpression extends Expression {

    private CalculationFunction function = null;
    private int signatureIndex;

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

    public int getSignatureIndex () {
        return signatureIndex;
    }

    public void setSignatureIndex (int signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    public FunctionSignature getActiveSignature () {
        return function.getSignatures()[signatureIndex];
    }

    @Override
    public FieldDataType getResultType() {
        return getActiveSignature().returnType;
    }
}
