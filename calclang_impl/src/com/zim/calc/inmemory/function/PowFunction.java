package com.zim.calc.inmemory.function;

import com.zim.calc.context.CalcException;
import com.zim.calc.context.FieldDataType;
import com.zim.calc.context.FunctionSignature;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PowFunction extends InMemoryCalcFunction {
    private static final FunctionSignature[] signatures = new FunctionSignature[] {
            new FunctionSignature(new FieldDataType[] { FieldDataType.NUMERIC, FieldDataType.NUMERIC }, FieldDataType.NUMERIC),
    };

    private static final BigInteger INT_MAX_BI = new BigInteger(Integer.toString(Integer.MAX_VALUE));

    @Override
    public FunctionSignature[] getSignatures () {
        return signatures;
    }

    @Override
    public String getFunctionName() {
        return "POW";
    }

    @Override
    public List<Object> evaluate(List<List<Object>> argData, FunctionSignature sig) throws CalcException {
        if (!signatures[0].equals(sig)) {
            throw new CalcException("Unknown function signature!");
        }

        List<Object> result = new LinkedList<>();

        List<Object> applyToArg = argData.get(0);
        List<Object> powArg = argData.get(1);

        Iterator<Object> powIt = powArg.iterator();
        for (Object applyTo: applyToArg) {
            Object pow = powIt.next();

            BigDecimal applyToBD = getBD(applyTo);
            BigInteger powBI = getBI(pow);

            // Throw an exception if the value is larger than an int
            if (INT_MAX_BI.compareTo(powBI) < 0) {
                throw new CalcException("Pow value must not be larger than a signed 32bit integer.");
            }

            result.add(applyToBD.pow(powBI.intValue()));
        }

        return result;
    }
}
