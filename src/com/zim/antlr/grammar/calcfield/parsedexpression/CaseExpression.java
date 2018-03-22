package com.zim.antlr.grammar.calcfield.parsedexpression;

import com.zim.antlr.grammar.calcfield.context.FieldDataType;

import java.util.LinkedList;
import java.util.List;

public class CaseExpression extends Expression {

    private FieldDataType resultType = null;

    private List<Expression> whenExpressions = new LinkedList<>();
    private List<Expression> thenExpressions = new LinkedList<>();
    private Expression elseExpression = null;

    public List<Expression> getWhenExpressions() {
        return whenExpressions;
    }

    public void setWhenExpressions(List<Expression> whenExpressions) {
        this.whenExpressions = whenExpressions;
    }

    public void addWhenExpression(Expression expr) {
        getWhenExpressions().add(expr);
    }

    public List<Expression> getThenExpressions() {
        return thenExpressions;
    }

    public void setThenExpressions(List<Expression> thenExpressions) {
        this.thenExpressions = thenExpressions;
    }

    public void addThenExpression(Expression expr) {
        getThenExpressions().add(expr);
    }

    public Expression getElseExpression() {
        return elseExpression;
    }

    public void setElseExpression(Expression elseExpression) {
        this.elseExpression = elseExpression;
    }

    @Override
    public FieldDataType getResultType() throws Exception {
        return resultType;
    }

    public void setResultType(FieldDataType resultType) {
        this.resultType = resultType;
    }
}
