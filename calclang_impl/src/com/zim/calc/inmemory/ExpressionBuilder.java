package com.zim.calc.inmemory;

import com.zim.calc.context.CalcException;
import com.zim.calc.context.CalcFieldContext;
import com.zim.calc.context.FieldDataType;
import com.zim.calc.expression.*;

import java.util.Iterator;

public class ExpressionBuilder extends CalcFieldExpressionBuilder {
    public ExpressionBuilder (CalcFieldContext context) {
        super(context);
    }

    @Override
    protected FieldDataType validateExpression (BinaryOpExpression parsedExpression) throws CalcException {
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
                    throw getDataTypesMismatchException(
                            new FieldDataType[] { FieldDataType.NUMERIC, FieldDataType.NUMERIC }, // wanted
                            new FieldDataType[] { lhsType, rhsType }); // got
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
                    throw getNoOverloadException("OPERATOR ADD", new FieldDataType[] { lhsType, rhsType });
                }

                break;
            }

            // logical
            case AND:
            case OR: {
                // arguments must be boolean expressions
                if (lhsType != FieldDataType.BOOLEAN || rhsType != FieldDataType.BOOLEAN) {
                    throw getDataTypesMismatchException(
                            new FieldDataType[] { FieldDataType.BOOLEAN, FieldDataType.BOOLEAN }, // wanted
                            new FieldDataType[] { lhsType, rhsType }); // got
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
                    throw getDataTypesMismatchException(
                            new FieldDataType[] { FieldDataType.BOOLEAN, FieldDataType.BOOLEAN }, // wanted
                            new FieldDataType[] { lhsType, rhsType }); // got
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
                    throw getDataTypesMismatchException(
                            new FieldDataType[] { lhsType }, // wanted
                            new FieldDataType[] { rhsType }); // got
                }

                resultType = FieldDataType.BOOLEAN;
                break;
            }
        }

        return resultType;
    }

    @Override
    protected FieldDataType validateExpression (CaseExpression expr) throws CalcException {
        FieldDataType thenTypeAgg = null;
        Iterator<Expression> thenIt = expr.getThenExpressions().iterator();
        for (Expression when : expr.getWhenExpressions()) {
            FieldDataType whenType = when.getResultType();
            if (whenType != FieldDataType.BOOLEAN) {
                throw new CalcException("WHEN clause must have boolean result!");
            }

            FieldDataType thenType = thenIt.next().getResultType();
            if (thenTypeAgg == null) {
                thenTypeAgg = thenType;
            } else if (thenTypeAgg != thenType) {
                throw new CalcException("All CASE expression branches must produce the same data type result!");
            }
        }

        if (expr.getElseExpression() != null) {
            FieldDataType elseType = expr.getElseExpression().getResultType();
            if (elseType != thenTypeAgg) {
                throw new CalcException("All CASE expression branches must produce the same data type result!");
            }
        }

        return thenTypeAgg;
    }

    @Override
    protected FieldDataType validateExpression (ListExpression expr) throws CalcException {
        if (expr.getListExpressions() == null || expr.getListExpressions().isEmpty()) {
            throw new CalcException("INLIST cannot be empty!");
        }

        FieldDataType aggType = null;
        for (Expression subEx : expr.getListExpressions()) {
            FieldDataType exprType = subEx.getResultType();
            if (aggType == null) {
                aggType = exprType;
            }

            if (aggType == null) {
                throw new CalcException("Return type cannot be null!");
            }

            if (aggType != exprType) {
                throw getDataTypesMismatchException(new FieldDataType[] { aggType } , new FieldDataType[] { exprType });
            }
        }

        return aggType;
    }

    @Override
    protected FieldDataType validateExpression (UnaryOpExpression expr) throws CalcException {
        FieldDataType operandType = expr.getOperand().getResultType();

        FieldDataType resultType = null;
        switch (expr.getOperator()) {
            // numeric sign operator
            case ADD:
            case SUB: {
                if (operandType != FieldDataType.NUMERIC) {
                    throw getDataTypesMismatchException(new FieldDataType[] { FieldDataType.NUMERIC }, new FieldDataType[] { operandType });
                }

                resultType = FieldDataType.NUMERIC;
                break;
            }

            // boolean not operator
            case BANG: {
                if (operandType != FieldDataType.BOOLEAN) {
                    throw getDataTypesMismatchException(new FieldDataType[] { FieldDataType.BOOLEAN }, new FieldDataType[] { operandType });
                }

                resultType = FieldDataType.BOOLEAN;
                break;
            }
        }

        return resultType;
    }
}
