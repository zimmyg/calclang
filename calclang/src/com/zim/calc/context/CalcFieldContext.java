package com.zim.calc.context;

public interface CalcFieldContext {

    boolean concatenationAllowed();

    CalculationFunction getFunctionForId(String id);
    CalcFieldInputField getFieldForId(String id);
}
