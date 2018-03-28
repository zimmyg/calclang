package com.zim.calc.inmemory.function;

import com.zim.calc.context.FieldDataType;
import com.zim.util.LRUCache;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This function is how we will declare date literals rather than having the language support it directly, which would
 * require some fancy grammar parsing and declaration stuff.
 */
public class MakeDateFunction extends InMemoryCalcFunction {
    private static final int DEFAULT_DATE_CACHE_SIZE = 10000;

    /* Use a cache for already-seen formatters and date objects, which results in a 20x speedup when outputting
     * constant values over an entire dataset at the cost of some extra memory
     */
    private LRUCache<String, SimpleDateFormat> formatterCache = new LRUCache<>(DEFAULT_DATE_CACHE_SIZE);
    private LRUCache<String, java.sql.Date> dateCache = new LRUCache<>(DEFAULT_DATE_CACHE_SIZE);

    @Override
    public List<Object> evaluate(List<List<Object>> argData) throws Exception {
        List<Object> result = new LinkedList<>();

        if (argData.size() != 2) {
            throw new Exception("Wrong number of arguments for MakeDate function!");
        }

        List<Object> dateDatas = argData.get(0);
        List<Object> dateFormats = argData.get(1);

        if (dateDatas == null || dateFormats == null) {
            throw new Exception("Invalid input data for MakeDate function!");
        }

        if (dateDatas.size() != dateFormats.size()) {
            throw new Exception("Data length mismatch!");
        }

        Iterator<Object> dateDataIt = argData.get(0).iterator();
        Iterator<Object> dateFormatIt = argData.get(1).iterator();


        while (dateDataIt.hasNext()) {
            String dateData = getString(dateDataIt.next());
            String dateFormat = getString(dateFormatIt.next());

            if (dateData == null || dateFormat == null) {
                throw new Exception("Invalid input data for MakeDate function!");
            }

            SimpleDateFormat format = formatterCache.get(dateFormat);
            if (format == null) {
                format = new SimpleDateFormat(dateFormat);
                formatterCache.put(dateFormat, format);
            }

            java.sql.Date sqlDate = dateCache.get(dateData);
            if (sqlDate == null) {
                java.util.Date d = format.parse(dateData);
                sqlDate = new java.sql.Date(d.getTime());

                dateCache.put(dateData, sqlDate);
            } else {
                // actually generate a new object, rather than outputting the same reference
                sqlDate = (java.sql.Date) sqlDate.clone();
            }

            result.add(sqlDate);
        }

        // clear caches to stop extra data hanging around
        formatterCache.clear();
        dateCache.clear();

        return result;
    }

    @Override
    public FieldDataType getReturnType() {
        return FieldDataType.DATE;
    }

    @Override
    public String getFunctionName() {
        return "DATE";
    }

    /*
     * This is a great argument for function overloading, because someone may not want/need to declare the format option
     */
    @Override
    public List<FieldDataType> getFunctionArguments() {
        List<FieldDataType> argTypes = new LinkedList<>();
        argTypes.add(FieldDataType.STRING); // first arg is date data
        argTypes.add(FieldDataType.STRING); // second arg is date format

        return argTypes;
    }
}
