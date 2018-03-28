package com.zim.calc.expression;

import com.zim.calc.context.FieldDataType;

public class BinaryOpExpression extends Expression {

    private FieldDataType resultType = null;

    private Expression lhs = null;
    private Expression rhs = null;
    private BinaryOperator operator = null;

    public BinaryOpExpression() {}

    public enum BinaryOperator {
        MUL,
        DIV,
        MOD,
        ADD,
        SUB,
        AND,
        OR,
        GT,
        LT,
        LE,
        GE,
        EQUAL,
        NOTEQUAL,
        INLIST,
        NOTINLIST
    }

    public Expression getLhs() {
        return lhs;
    }

    public void setLhs(Expression lhs) {
        this.lhs = lhs;
    }

    public Expression getRhs() {
        return rhs;
    }

    public void setRhs(Expression rhs) {
        this.rhs = rhs;
    }

    public BinaryOperator getOperator() {
        return operator;
    }

    public void setOperator(BinaryOperator operator) {
        this.operator = operator;
    }

    @Override
    public FieldDataType getResultType() throws Exception {
        return resultType;
    }

    public void setResultType(FieldDataType resultType) {
        this.resultType = resultType;
    }
}
