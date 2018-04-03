package com.zim.calc.expression;

import com.zim.calc.context.FieldDataType;

public class LiteralExpression extends Expression {

    FieldDataType resultType = null;
    Object literalValue = null;

    @Override
    public FieldDataType getResultType() {
        return resultType;
    }

    public void setResultType(FieldDataType resultType) {
        this.resultType = resultType;
    }

    public Object getLiteralValue() {
        return literalValue;
    }

    public void setLiteralValue(Object literalValue) {
        this.literalValue = literalValue;
    }
}
