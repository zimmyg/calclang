package com.zim.calc.context;

public enum FieldDataType {
    NUMERIC,
    BOOLEAN,
    STRING,
    DATE, // NOTE: no timestamp or time data types, they're all the same type and everything needs to handle them
}
