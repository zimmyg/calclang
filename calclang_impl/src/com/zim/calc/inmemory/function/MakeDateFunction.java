package com.zim.calc.inmemory.function;

import com.zim.calc.context.CalcException;
import com.zim.calc.context.FieldDataType;
import com.zim.calc.context.FunctionSignature;
import com.zim.util.LRUCache;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This function is how we will declare date literals rather than having the language support it directly, which would
 * require some fancy grammar parsing and declaration stuff.
 */
public class MakeDateFunction extends InMemoryCalcFunction {
    private static final int DEFAULT_DATE_CACHE_SIZE = 10000;

    /* Use a cache for already-seen formatters and date objects, which results in an ~20x speedup when outputting
     * constant values over an entire dataset at the cost of some extra memory.
     */
    private LRUCache<String, SimpleDateFormat> formatterCache = new LRUCache<>(DEFAULT_DATE_CACHE_SIZE);
    private LRUCache<String, Date> dateCache = new LRUCache<>(DEFAULT_DATE_CACHE_SIZE);
    private Calendar cal = new GregorianCalendar();

    private static final FunctionSignature makeDateSig =
            new FunctionSignature(new FieldDataType[] { FieldDataType.STRING, FieldDataType.STRING }, FieldDataType.DATE);
    private static final FunctionSignature truncateTsSig =
            new FunctionSignature(new FieldDataType[] { FieldDataType.TIMESTAMP }, FieldDataType.DATE);

    private static final FunctionSignature[] signatures = new FunctionSignature[] { makeDateSig, truncateTsSig };

    @Override
    public FunctionSignature[] getSignatures () {
        return signatures;
    }

    @Override
    public String getFunctionName() {
        return "DATE";
    }

    @Override
    public List<Object> evaluate(List<List<Object>> argData, FunctionSignature sig) throws CalcException {
        List<Object> result = null;
        if (sig.equals(makeDateSig)) {
            result = evalMakeDate(argData);
        } else if (sig.equals(truncateTsSig)) {
            result = evalTruncate(argData);
        }

        return result;
    }

    private List<Object> evalMakeDate(List<List<Object>> argData) throws CalcException {
        List<Object> result = new LinkedList<>();

        if (argData.size() != 2) {
            throw new CalcException("Wrong number of arguments for MakeDate function!");
        }

        List<Object> dateDatas = argData.get(0);
        List<Object> dateFormats = argData.get(1);

        if (dateDatas == null || dateFormats == null) {
            throw new CalcException("Invalid input data for MakeDate function!");
        }

        if (dateDatas.size() != dateFormats.size()) {
            throw new CalcException("Data length mismatch!");
        }

        Iterator<Object> dateDataIt = argData.get(0).iterator();
        Iterator<Object> dateFormatIt = argData.get(1).iterator();


        while (dateDataIt.hasNext()) {
            String dateData = getString(dateDataIt.next());
            String dateFormat = getString(dateFormatIt.next());

            if (dateData == null || dateFormat == null) {
                result.add(null);
                continue;
            }

            SimpleDateFormat format = formatterCache.get(dateFormat);
            if (format == null) {
                format = new SimpleDateFormat(dateFormat);
                formatterCache.put(dateFormat, format);
            }

            Date dt = dateCache.get(dateData);
            if (dt == null) {

                try {
                    dt = format.parse(dateData);
                } catch (Exception e) {
                    throw new CalcException("Error while parsing date format!", e);
                }
                dateCache.put(dateData, dt);
            } else {
                // actually generate a new object, rather than outputting the same reference
                dt = (Date) dt.clone();
            }

            result.add(dt);
        }

        // clear caches to stop extra data hanging around
        formatterCache.clear();
        dateCache.clear();

        return result;
    }

    private List<Object> evalTruncate(List<List<Object>> argData) throws CalcException {
        List<Object> result = new LinkedList<>();

        if (argData.size() != 1) {
            throw new CalcException("Wrong number of arguments for TruncateTimestamp function!");
        }

        List<Object> timestamps = argData.get(0);
        if (timestamps == null) {
            throw new CalcException("Invalid input data for TruncateTimestamp function!");
        }

        for (Object o: timestamps) {
            Date dt = null;
            if (o instanceof Timestamp) {
                Timestamp ts = (Timestamp)o;
                cal.setTime(ts);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                dt = new Date(cal.getTimeInMillis());

                cal.clear();
            }

            result.add(dt);
        }

        return result;
    }
}
