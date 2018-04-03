package com.zim.calc.context;

import java.util.Arrays;
import java.util.Objects;

public final class FunctionSignature {
    public final FieldDataType[] argumentTypes;
    public final FieldDataType returnType;

    public FunctionSignature(FieldDataType[] argumentTypes, FieldDataType returnType) {
        this.argumentTypes = argumentTypes;
        this.returnType = returnType;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionSignature that = (FunctionSignature) o;
        return Arrays.equals(argumentTypes, that.argumentTypes) &&
                returnType == that.returnType;
    }

    @Override
    public int hashCode () {
        int result = Objects.hash(returnType);
        result = 31 * result + Arrays.hashCode(argumentTypes);
        return result;
    }
}
