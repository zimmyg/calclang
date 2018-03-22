package com.zim.antlr.grammar.calcfield.context;

public class CalcFieldInputField {
    private String name = null;
    private String calculationIdentifier = null;
    private FieldDataType dataType = null;

    public CalcFieldInputField(FieldDataType dataType) {
        this(dataType, null, null);
    }

    public CalcFieldInputField(FieldDataType dataType, String calcId, String fieldName) {
        this.calculationIdentifier = calcId;
        this.name = fieldName;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalculationIdentifier() {
        return calculationIdentifier;
    }

    public void setCalculationIdentifier(String calculationIdentifier) {
        this.calculationIdentifier = calculationIdentifier;
    }

    public FieldDataType getDataType() {
        return dataType;
    }

    public void setDataType(FieldDataType dataType) {
        this.dataType = dataType;
    }
}
