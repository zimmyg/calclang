package com.zim.calc.inmemory.function;

import com.zim.calc.context.FieldDataType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PowFunction extends InMemoryCalcFunction {
    @Override
    public FieldDataType getReturnType() {
        return FieldDataType.NUMERIC;
    }

    @Override
    public String getFunctionName() {
        return "POW";
    }

    @Override
    public List<FieldDataType> getFunctionArguments() {
        List<FieldDataType> args = new LinkedList<>();
        args.add(FieldDataType.NUMERIC);
        args.add(FieldDataType.NUMERIC);

        return args;
    }

    @Override
    public List<Object> evaluate(List<List<Object>> argData) throws Exception {
        List<Object> result = new LinkedList<>();

        List<Object> applyToArg = argData.get(0);
        List<Object> powArg = argData.get(1);

        Iterator<Object> powIt = powArg.iterator();
        for (Object applyTo: applyToArg) {
            Object pow = powIt.next();

            BigDecimal applyToBD = getBD(applyTo);
            BigInteger powBI = getBI(pow);

            // NOTE: Using intValueExact so that it will throw an exception if the value is larger than an int
            result.add(applyToBD.pow(powBI.intValueExact()));
        }

        return null;
    }
}
