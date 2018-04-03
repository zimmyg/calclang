package com.zim.calc.expression;

import com.zim.calc.context.FieldDataType;

public class UnaryOpExpression extends Expression {

    private FieldDataType resultType = null;

    private Expression operand = null;
    private UnaryOperator operator = null;

    public enum UnaryOperator {
        ADD,
        SUB,
        BANG,
    }

    public Expression getOperand() {
        return operand;
    }

    public void setOperand(Expression operand) {
        this.operand = operand;
    }

    public UnaryOperator getOperator() {
        return operator;
    }

    public void setOperator(UnaryOperator operator) {
        this.operator = operator;
    }

    @Override
    public FieldDataType getResultType() {
        return resultType;
    }

    public void setResultType(FieldDataType resultType) {
        this.resultType = resultType;
    }
}
