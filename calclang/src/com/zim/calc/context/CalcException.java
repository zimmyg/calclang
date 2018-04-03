package com.zim.calc.context;

public class CalcException extends Exception {
    private Object[] messageParameters;

    public CalcException(String message) {
        super(message);
    }

    public CalcException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalcException(String message, Object[] messageParameters) {
        super(message);
        this.messageParameters = messageParameters;
    }

    public CalcException(String message, Object[] messageParameters, Throwable cause) {
        super(message, cause);
        this.messageParameters = messageParameters;
    }

    public Object[] getMessageParameters () {
        return messageParameters;
    }
}
