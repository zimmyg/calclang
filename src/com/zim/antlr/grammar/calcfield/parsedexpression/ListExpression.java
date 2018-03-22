package com.zim.antlr.grammar.calcfield.parsedexpression;

import com.zim.antlr.grammar.calcfield.context.FieldDataType;

import java.util.LinkedList;
import java.util.List;

public class ListExpression extends Expression {

    private FieldDataType resultType = null;

    List<Expression> listExpressions = new LinkedList<>();

    public List<Expression> getListExpressions() {
        return listExpressions;
    }

    public void setListExpressions(List<Expression> listExpressions) {
        this.listExpressions = listExpressions;
    }

    public void addExpression(Expression expr) {
        getListExpressions().add(expr);
    }

    @Override
    public FieldDataType getResultType() throws Exception {
        return resultType;
    }

    public void setResultType(FieldDataType resultType) {
        this.resultType = resultType;
    }
}
