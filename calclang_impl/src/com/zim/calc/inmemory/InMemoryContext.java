package com.zim.calc.inmemory;


import com.zim.calc.context.CalcFieldContext;
import com.zim.calc.context.CalcFieldInputField;
import com.zim.calc.context.CalculationFunction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemoryContext implements CalcFieldContext {
    private Map<String, CalculationFunction> funcMap = null;
    private Map<String, CalcFieldInputField> fieldMap = null;

    public InMemoryContext() {
        this(new LinkedList<>(), new LinkedList<>());
    }

    public InMemoryContext(List<CalculationFunction> functions, List<CalcFieldInputField> fields) {
        if (functions == null) {
            functions = new LinkedList<>();
        }
        if (fields == null) {
            fields = new LinkedList<>();
        }

        funcMap = new HashMap<>();
        for (CalculationFunction func: functions) {
            funcMap.put(func.getFunctionName(), func);
        }

        fieldMap = new HashMap<>();
        for (CalcFieldInputField field: fields) {
            fieldMap.put(field.getCalculationIdentifier(), field);
        }
    }

    @Override
    public boolean concatenationAllowed() {
        return true;
    }

    @Override
    public CalculationFunction getFunctionForId(String id) {
        CalculationFunction func = funcMap.get(id);
        return func;
    }

    @Override
    public CalcFieldInputField getFieldForId(String id) {
        CalcFieldInputField field = fieldMap.get(id);
        return field;
    }
}
