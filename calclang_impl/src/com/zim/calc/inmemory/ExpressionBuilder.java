package com.zim.calc.inmemory;

import com.zim.calc.context.CalcFieldContext;
import com.zim.calc.context.FieldDataType;
import com.zim.calc.expression.*;

import java.util.Iterator;
import java.util.List;

public class ExpressionBuilder extends CalcFieldExpressionBuilder {
    public ExpressionBuilder (CalcFieldContext context) {
        super(context);
    }

    @Override
    protected FieldDataType validateExpression (BinaryOpExpression parsedExpression) throws Exception {
        FieldDataType lhsType = parsedExpression.getLhs().getResultType();
        FieldDataType rhsType = parsedExpression.getRhs().getResultType();

        FieldDataType resultType = null;
        switch (parsedExpression.getOperator()) {
            // arithmetic (or concatenate)
            case MUL:
            case DIV:
            case MOD:
            case SUB: {
                // these all require both sides to be numeric
                if (lhsType != FieldDataType.NUMERIC || rhsType != FieldDataType.NUMERIC) {
                    throw getDataTypesMismatchException(lhsType, rhsType);
                }

                resultType = FieldDataType.NUMERIC;
                break;
            }

            case ADD: {
                // this can be numeric add or string concatenate
                if (lhsType == FieldDataType.NUMERIC && rhsType == FieldDataType.NUMERIC) {
                    resultType = FieldDataType.NUMERIC;
                } else if (isStringConcatenationAndAllowed(lhsType, rhsType)) {
                    resultType = FieldDataType.STRING;
                } else {
                    throw getDataTypesMismatchException(lhsType, rhsType);
                }

                break;
            }

            // logical
            case AND:
            case OR: {
                // arguments must be boolean expressions
                if (lhsType != FieldDataType.BOOLEAN || rhsType != FieldDataType.BOOLEAN) {
                    throw getDataTypesMismatchException(lhsType, rhsType);
                }

                resultType = FieldDataType.BOOLEAN;
                break;
            }

            // numeric comparison
            case GT:
            case LT:
            case LE:
            case GE: {
                if (lhsType != FieldDataType.NUMERIC || rhsType != FieldDataType.NUMERIC) {
                    throw getDataTypesMismatchException(lhsType, rhsType);
                }

                resultType = FieldDataType.NUMERIC;
                break;
            }

            // any comparison
            case EQUAL:
            case NOTEQUAL:
            case INLIST:
            case NOTINLIST: {
                if (lhsType != rhsType) {
                    throw getDataTypesMismatchException(lhsType, rhsType);
                }

                resultType = FieldDataType.BOOLEAN;
                break;
            }
        }

        return resultType;
    }

    @Override
    protected FieldDataType validateExpression (CaseExpression expr) throws Exception {
        FieldDataType thenTypeAgg = null;
        Iterator<Expression> thenIt = expr.getThenExpressions().iterator();
        for (Expression when : expr.getWhenExpressions()) {
            FieldDataType whenType = when.getResultType();
            if (whenType != FieldDataType.BOOLEAN) {
                throw new Exception("WHEN clause must have boolean result!");
            }

            FieldDataType thenType = thenIt.next().getResultType();
            if (thenTypeAgg == null) {
                thenTypeAgg = thenType;
            } else if (thenTypeAgg != thenType) {
                throw new Exception("All CASE expression branches must produce the same data type result!");
            }
        }

        if (expr.getElseExpression() != null) {
            FieldDataType elseType = expr.getElseExpression().getResultType();
            if (elseType != thenTypeAgg) {
                throw new Exception("All CASE expression branches must produce the same data type result!");
            }
        }

        return thenTypeAgg;
    }

    @Override
    protected FieldDataType validateExpression (FunctionExpression expr) throws Exception {
        List<FieldDataType> expectedArgTypes = expr.getFunction().getFunctionArguments();
        Iterator<Expression> argIt = expr.getArgumentExpressions().iterator();
        for (FieldDataType expectedType : expectedArgTypes) {
            Expression argExp = argIt.next();
            FieldDataType actualType = argExp.getResultType();
            if (expectedType != actualType) {
                throw getDataTypesMismatchException(expectedType, actualType);
            }
        }

        return expr.getFunction().getReturnType();
    }

    @Override
    protected FieldDataType validateExpression (ListExpression expr) throws Exception {
        if (expr.getListExpressions() == null || expr.getListExpressions().isEmpty()) {
            throw new Exception("INLIST cannot be empty!");
        }

        FieldDataType aggType = null;
        for (Expression subEx : expr.getListExpressions()) {
            FieldDataType exprType = subEx.getResultType();
            if (aggType == null) {
                aggType = exprType;
            }

            if (aggType == null) {
                throw new Exception("Return type cannot be null!");
            }

            if (aggType != exprType) {
                throw getDataTypesMismatchException(aggType, exprType);
            }
        }

        return aggType;
    }

    @Override
    protected FieldDataType validateExpression (UnaryOpExpression expr) throws Exception {
        FieldDataType operandType = expr.getOperand().getResultType();

        FieldDataType resultType = null;
        switch (expr.getOperator()) {
            // numeric sign operator
            case ADD:
            case SUB: {
                if (operandType != FieldDataType.NUMERIC) {
                    throw getDataTypesMismatchException(FieldDataType.NUMERIC, operandType);
                }

                resultType = FieldDataType.NUMERIC;
                break;
            }

            // boolean not operator
            case BANG: {
                if (operandType != FieldDataType.BOOLEAN) {
                    throw getDataTypesMismatchException(FieldDataType.BOOLEAN, operandType);
                }

                resultType = FieldDataType.BOOLEAN;
                break;
            }
        }

        return resultType;
    }
}
