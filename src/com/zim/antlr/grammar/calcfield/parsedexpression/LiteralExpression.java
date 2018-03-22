package com.zim.antlr.grammar.calcfield.parsedexpression;

import com.zim.antlr.grammar.calcfield.context.FieldDataType;

public class LiteralExpression extends Expression {

    FieldDataType resultType = null;
    Object literalValue = null;

    @Override
    public FieldDataType getResultType() throws Exception {
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
