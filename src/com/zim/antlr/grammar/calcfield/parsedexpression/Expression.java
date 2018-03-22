package com.zim.antlr.grammar.calcfield.parsedexpression;

import com.zim.antlr.grammar.calcfield.context.FieldDataType;

public abstract class Expression {
    public abstract FieldDataType getResultType() throws Exception;
}
