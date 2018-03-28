package com.zim.calc.inmemory.function;

import com.zim.calc.context.FieldDataType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FloorFunction extends InMemoryCalcFunction {
    @Override
    public FieldDataType getReturnType() {
        return FieldDataType.NUMERIC;
    }

    @Override
    public String getFunctionName() {
        return "FLOOR";
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
            result.add(bd.setScale(0, RoundingMode.FLOOR));
        }

        return result;
    }
}
