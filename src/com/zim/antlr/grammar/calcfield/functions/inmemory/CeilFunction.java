package com.zim.antlr.grammar.calcfield.functions.inmemory;

import com.zim.antlr.grammar.calcfield.context.FieldDataType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CeilFunction extends InMemoryCalcFunction {
    @Override
    public FieldDataType getReturnType() {
        return FieldDataType.NUMERIC;
    }

    @Override
    public String getFunctionName() {
        return "CEIL";
    }

    @Override
    public List<FieldDataType> getFunctionArguments() {
        return Collections.singletonList(FieldDataType.NUMERIC);
    }

    @Override
    public List<Object> evaluate(List<List<Object>> argData) throws Exception {
        List<Object> result = new LinkedList<>();

        List<Object> data = argData.get(0);
        for (Object d: data) {
            BigDecimal bd = getBD(d);
            result.add(bd.setScale(0, RoundingMode.CEILING));
        }

        return result;
    }
}
