package com.zim.antlr.grammar.calcfield.functions.inmemory;

import com.zim.antlr.grammar.calcfield.context.FieldDataType;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DateCompareFunction extends InMemoryCalcFunction {

    @Override
    public List<Object> evaluate(List<List<Object>> argData) throws Exception {
        List<Object> result = new LinkedList<>();

        if (argData.size() != 2) {
            throw new Exception("Wrong number of arguments for DateCompare function!");
        }

        List<Object> lhsDates = argData.get(0);
        List<Object> rhsDates = argData.get(1);

        if (lhsDates == null || rhsDates == null) {
            throw new Exception("Invalid input data for DateCompare function!");
        }

        if (lhsDates.size() != rhsDates.size()) {
            throw new Exception("Data length mismatch!");
        }

        Iterator<Object> lhsIt = lhsDates.iterator();
        Iterator<Object> rhsIt = rhsDates.iterator();

        while (lhsIt.hasNext()) {
            Object lhsObj = lhsIt.next();
            Object rhsObj = rhsIt.next();
            if (lhsObj == null || rhsObj == null ||
                    !(lhsObj instanceof Date) || !(rhsObj instanceof Date)) {
                throw new Exception("Invalid input data for DateCompare function!");
            }

            Date lhs = (Date) lhsObj;
            Date rhs = (Date) rhsObj;

            int cmp = lhs.compareTo(rhs);
            result.add(cmp);
        }

        return result;
    }

    @Override
    public FieldDataType getReturnType() {
        return FieldDataType.NUMERIC;
    }

    @Override
    public String getFunctionName() {
        return "DATECMP";
    }

    @Override
    public List<FieldDataType> getFunctionArguments() {
        List<FieldDataType> args = new LinkedList<>();
        args.add(FieldDataType.DATE);
        args.add(FieldDataType.DATE);

        return args;
    }
}
