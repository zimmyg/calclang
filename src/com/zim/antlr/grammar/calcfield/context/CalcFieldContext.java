package com.zim.antlr.grammar.calcfield.context;

import com.zim.antlr.grammar.calcfield.functions.CalculationFunction;

public interface CalcFieldContext {

    boolean concatenationAllowed();

    CalculationFunction getFunctionForId(String id);
    CalcFieldInputField getFieldForId(String id);
}
