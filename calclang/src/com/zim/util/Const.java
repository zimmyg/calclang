package com.zim.util;

public class Const {
    /**
     * Message key for when there are no available overloads for a function using the given data types.
     * Message parameters will be the function name, followed by a string containing a parenthesised comma-separated
     * list of the input data types for which there is no available overload.
     */
    public static final String NO_OVERLOADS_MSG = "zim.error.no.overloads";

    /**
     * Message key for when there is a data type mismatch of some sort.
     * Message parameters will be a string containing a parenthesised comma-separated list of the expected data types for
     * the given operation, followed by the same for the data types which were recieved.
     */
    public static final String DATA_TYPE_MISMATCH_MSG = "zim.error.type.mismatch";

    /**
     * Message key for when a syntax error is encountered during parsing.
     * Message parameters will be line number, char number in line, error message.
     */
    public static final String SYNTAX_ERROR_MSG = "zim.error.syntax";

    /**
     * Message key for when there are no available overloads for a function using the given data types.
     * Message parameters will be the function name, followed by a string containing a parenthesised comma-separated
     * list of the input data types for which there is no available overload.
     */
    public static final String INVALID_IDENT_MSG = "zim.error.invalid.ident";
}
