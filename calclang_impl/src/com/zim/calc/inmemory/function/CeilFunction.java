package com.zim.calc.inmemory.function;

import com.zim.calc.context.CalcException;
import com.zim.calc.context.FieldDataType;
import com.zim.calc.context.FunctionSignature;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class CeilFunction extends InMemoryCalcFunction {
    private static final FunctionSignature[] signatures = new FunctionSignature[] {
        new FunctionSignature(new FieldDataType[] { FieldDataType.NUMERIC }, FieldDataType.NUMERIC),
    };

    @Override
    public FunctionSignature[] getSignatures () {
        return signatures;
    }

    @Override
    public String getFunctionName() {
        return "CEIL";
    }

    @Override
    public List<Object> evaluate(List<List<Object>> argData, FunctionSignature sig) throws CalcException {
        if (!signatures[0].equals(sig)) {
            throw new CalcException("Unknown function signature!");
        }

        List<Object> result = new LinkedList<>();

        List<Object> data = argData.get(0);
        for (Object d: data) {
            BigDecimal bd = getBD(d);
            result.add(bd.setScale(0, RoundingMode.CEILING));
        }

        return result;
    }
}
