package com.zim.calc.inmemory.function;

import com.zim.calc.context.CalcException;
import com.zim.calc.context.FieldDataType;
import com.zim.calc.context.FunctionSignature;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DateCompareFunction extends InMemoryCalcFunction {

    private static final FunctionSignature[] signatures = new FunctionSignature[] {
            new FunctionSignature(new FieldDataType[] { FieldDataType.DATE, FieldDataType.DATE }, FieldDataType.NUMERIC),
            new FunctionSignature(new FieldDataType[] { FieldDataType.TIMESTAMP, FieldDataType.DATE }, FieldDataType.NUMERIC),
            new FunctionSignature(new FieldDataType[] { FieldDataType.DATE, FieldDataType.TIMESTAMP }, FieldDataType.NUMERIC),
            new FunctionSignature(new FieldDataType[] { FieldDataType.TIMESTAMP, FieldDataType.TIMESTAMP }, FieldDataType.NUMERIC),
    };

    @Override
    public List<Object> evaluate(List<List<Object>> argData, FunctionSignature sig) throws CalcException {
        List<Object> result = new LinkedList<>();

        if (argData.size() != 2) {
            throw new CalcException("Wrong number of arguments for DateCompare function!");
        }

        List<Object> lhsDates = argData.get(0);
        List<Object> rhsDates = argData.get(1);

        if (lhsDates == null || rhsDates == null) {
            throw new CalcException("Invalid input data for DateCompare function!");
        }

        if (lhsDates.size() != rhsDates.size()) {
            throw new CalcException("Data length mismatch!");
        }

        Iterator<Object> lhsIt = lhsDates.iterator();
        Iterator<Object> rhsIt = rhsDates.iterator();

        while (lhsIt.hasNext()) {
            Object lhsObj = lhsIt.next();
            Object rhsObj = rhsIt.next();

            if (lhsObj == null || rhsObj == null) {
                result.add(null);
            } else if (!(lhsObj instanceof Date) || !(rhsObj instanceof Date)) {
                throw new CalcException("Invalid input data for DateCompare function!");
            } else {
                Date lhs = (Date) lhsObj;
                Date rhs = (Date) rhsObj;

                int cmp = lhs.compareTo(rhs);
                result.add(cmp);
            }
        }

        return result;
    }

    @Override
    public FunctionSignature[] getSignatures () {
        return signatures;
    }

    @Override
    public String getFunctionName() {
        return "DATECMP";
    }
}
