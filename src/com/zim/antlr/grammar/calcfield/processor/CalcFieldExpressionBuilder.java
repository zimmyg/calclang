package com.zim.antlr.grammar.calcfield.processor;

import com.zim.antlr.grammar.calcfield.context.FieldDataType;
import com.zim.antlr.grammar.calcfield.context.CalcFieldInputField;
import com.zim.antlr.grammar.calcfield.functions.CalculationFunction;
import com.zim.antlr.grammar.calcfield.generated.CalcFieldBaseVisitor;
import com.zim.antlr.grammar.calcfield.generated.CalcFieldLexer;
import com.zim.antlr.grammar.calcfield.generated.CalcFieldParser;
import com.zim.antlr.grammar.calcfield.parsedexpression.*;
import com.zim.antlr.grammar.calcfield.context.CalcFieldContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

public class CalcFieldExpressionBuilder  {

    private CalcFieldContext calcContext;

    public CalcFieldExpressionBuilder(CalcFieldContext context) {
        this.calcContext = context;
    }

    public Expression parseExpressionFromString(String inputCalcString) throws Exception {
        return parseExpressionFromAntlrStream(CharStreams.fromString(inputCalcString));
    }

    public Expression parseExpressionFromStream(InputStream is) throws Exception {
        return parseExpressionFromAntlrStream(CharStreams.fromStream(is));
    }

    private Expression parseExpressionFromAntlrStream(CharStream antlrStream) throws Exception {
        CalcFieldLexer lexer = new CalcFieldLexer(antlrStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcFieldParser parser = new CalcFieldParser(tokens);

        CalcFieldParser.ExpressionContext expression = parser.expression();
        CalcFieldExpressionVisitor exprVisitor = new CalcFieldExpressionVisitor();
        Expression result = exprVisitor.visit(expression);
        if (result == null && exprVisitor.getException() != null) {
            throw exprVisitor.getException();
        }

        return result;
    }

    // Using an inner class, because I don't want to expose the API for the ANTLR expression visitor, since it works
    // with the ANTLR generated expressions, and I don't want the overlap in expression terminology to confuse users
    // between the expressions that this class produces versus the ones it operates on.
    private class CalcFieldExpressionVisitor extends CalcFieldBaseVisitor<Expression> {
        private Exception ex;

        /**
         * If there was an error while visiting, we can't throw exceptions because the base class does not allow us to.
         * To check if parsing failed, check the exception.
         */
        public Exception getException() {
            return ex;
        }

        @Override
        public Expression visitParenExpr(CalcFieldParser.ParenExprContext ctx) {
            // need to implement this specifically, because the base visitor is not smart enough to work it out and always returns null
            return ctx.expression().accept(this);
        }

        @Override
        public Expression visitParenComp(CalcFieldParser.ParenCompContext ctx) {
            // need to implement this specifically, because the base visitor is not smart enough to work it out and always returns null
            return ctx.comparison().accept(this);
        }

        @Override
        public Expression visitBinaryOpExpr(CalcFieldParser.BinaryOpExprContext ctx) {
            // child 1 will be the operator itself
            Expression lhs = ctx.getChild(0).accept(this);
            Expression rhs = ctx.getChild(2).accept(this);

            // if something went wrong while parsing the subexpressions, just break out, the user can check the exception
            if (lhs == null || rhs == null) return null;

            CalcFieldParser.BinaryOpContext binOp = ctx.binaryOp();

            BinaryOpExpression expr = new BinaryOpExpression();
            expr.setLhs(lhs);
            expr.setRhs(rhs);

            BinaryOpExpression.BinaryOperator operator = null;
            if (binOp.ADD() != null) {
                operator = BinaryOpExpression.BinaryOperator.ADD;
            } else if (binOp.SUB() != null) {
                operator = BinaryOpExpression.BinaryOperator.SUB;
            } else if (binOp.MUL() != null) {
                operator = BinaryOpExpression.BinaryOperator.MUL;
            } else if (binOp.DIV() != null) {
                operator = BinaryOpExpression.BinaryOperator.DIV;
            } else if (binOp.MOD() != null) {
                operator = BinaryOpExpression.BinaryOperator.MOD;

            } else if (binOp.EQUAL() != null) {
                operator = BinaryOpExpression.BinaryOperator.EQUAL;
            } else if (binOp.NOTEQUAL() != null) {
                operator = BinaryOpExpression.BinaryOperator.NOTEQUAL;

            } else if (binOp.AND() != null) {
                operator = BinaryOpExpression.BinaryOperator.AND;
            } else if (binOp.OR() != null) {
                operator = BinaryOpExpression.BinaryOperator.OR;
            }
            expr.setOperator(operator);

            try {
                expr.setResultType(evaluateResultType(expr));
            } catch (Exception e) {
                this.ex = e;
                return null; // break out of parsing, something went wrong
            }

            return expr;
        }

        @Override
        public Expression visitUnaryOpExpr(CalcFieldParser.UnaryOpExprContext ctx) {
            // child 1 will be the operator itself
            Expression operand = ctx.getChild(1).accept(this);
            if (operand == null) return null; // break if something went wrong

            CalcFieldParser.UnaryOpContext unaryOp = ctx.unaryOp();

            UnaryOpExpression expr = new UnaryOpExpression();
            expr.setOperand(operand);

            UnaryOpExpression.UnaryOperator operator = null;
            if (unaryOp.ADD() != null) {
                operator = UnaryOpExpression.UnaryOperator.ADD;

            } else if (unaryOp.SUB() != null) {
                operator = UnaryOpExpression.UnaryOperator.SUB;

            } else if (unaryOp.BANG() != null) {
                operator = UnaryOpExpression.UnaryOperator.BANG;

            }
            expr.setOperator(operator);

            try {
                expr.setResultType(evaluateResultType(expr));
            } catch (Exception e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitLiteral(CalcFieldParser.LiteralContext ctx) {

            FieldDataType type = null;
            Object data = null;

            TerminalNode node;
            if ((node = ctx.BOOL_LITERAL()) != null) {
                data = Boolean.valueOf(node.getText());
                type = FieldDataType.BOOLEAN;

            } else if ((node = ctx.STRING_LITERAL()) != null) {
                String text = node.getText();
                if (text != null) {
                    // clip the quotes from the string literal
                    text = text.substring(1);
                    text = text.substring(0, text.length() - 1);

                    data = text;
                }

                type = FieldDataType.STRING;

            } else if ((node = ctx.NUMERIC_LITERAL()) != null) {
                data = new BigDecimal(node.toString());
                type = FieldDataType.NUMERIC;

            } else if ((node = ctx.IDENTIFIER()) != null) {
                CalcFieldInputField field = calcContext.getFieldForId(node.getText());
                if (field == null) {
                    this.ex = new Exception("Field not found: " + node.getText());
                    return null;
                }

                data = field;
                type = field.getDataType();
            }

            LiteralExpression expr = new LiteralExpression();
            expr.setLiteralValue(data);
            expr.setResultType(type);

            return expr;
        }

        @Override
        public Expression visitFunctionCall(CalcFieldParser.FunctionCallContext ctx) {
            TerminalNode funcNameNode = ctx.IDENTIFIER();
            CalculationFunction func = calcContext.getFunctionForId(funcNameNode.getText());
            if (func == null) {
                this.ex = new Exception("Function not found: " + funcNameNode.getText());
                return null;
            }

            List<Expression> evaluatedParams = new LinkedList<>();
            for (CalcFieldParser.ExpressionContext subCtx: ctx.expression()) {
                Expression parsedSub = visit(subCtx);
                if (parsedSub == null) return null;

                evaluatedParams.add(parsedSub);
            }

            List<FieldDataType> argTypes = func.getFunctionArguments();
            int argLength = argTypes != null ? argTypes.size() : 0;
            if (evaluatedParams.size() != argLength) {
                this.ex = new Exception("Function argument count incorrect");
                return null;
            }

            FunctionExpression expr = new FunctionExpression();
            expr.setFunction(func);
            expr.setArgumentExpressions(evaluatedParams);

            try {
                expr.setResultType(evaluateResultType(expr));
            } catch (Exception e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitCaseStatement(CalcFieldParser.CaseStatementContext ctx) {
            CaseExpression expr = new CaseExpression();

            for (int i = 0; i < ctx.comparison().size(); i++) {
                CalcFieldParser.ComparisonContext when = ctx.comparison(i);
                CalcFieldParser.ExpressionContext then = ctx.expression(i);

                Expression whenExp = visit(when);
                if (whenExp == null) return null; // break if something went wrong

                Expression thenExp = visit(then);
                if (thenExp == null) return null; // break if something went wrong

                expr.addWhenExpression(whenExp);
                expr.addThenExpression(thenExp);
            }

            CalcFieldParser.ExpressionContext elseExpr = ctx.elseExp;
            if (elseExpr != null) {
                Expression elseParsed = visit(elseExpr);
                if (elseParsed == null) return null; // break if something went wrong

                expr.setElseExpression(elseParsed);
            }

            try {
                expr.setResultType(evaluateResultType(expr));
            } catch (Exception e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitEqualityComp(CalcFieldParser.EqualityCompContext ctx) {
            Expression lhs = visit(ctx.lhs);
            Expression rhs = visit(ctx.rhs);

            if (lhs == null || rhs == null) return null; // break if something went wrong

            BinaryOpExpression expr = new BinaryOpExpression();
            expr.setLhs(lhs);
            expr.setRhs(rhs);

            BinaryOpExpression.BinaryOperator operator = null;
            if (ctx.EQUAL() != null) {
                operator = BinaryOpExpression.BinaryOperator.EQUAL;

            } else if (ctx.NOTEQUAL() != null) {
                operator = BinaryOpExpression.BinaryOperator.NOTEQUAL;

            } else if (ctx.GT() != null) {
                operator = BinaryOpExpression.BinaryOperator.GT;

            } else if (ctx.GE() != null) {
                operator = BinaryOpExpression.BinaryOperator.GE;

            } else if (ctx.LT() != null) {
                operator = BinaryOpExpression.BinaryOperator.LT;

            } else if (ctx.LE() != null) {
                operator = BinaryOpExpression.BinaryOperator.LE;

            }
            expr.setOperator(operator);

            try {
                expr.setResultType(evaluateResultType(expr));
            } catch (Exception e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitLogicalComp(CalcFieldParser.LogicalCompContext ctx) {
            Expression lhs = visit(ctx.lhs);
            Expression rhs = visit(ctx.rhs);

            if (lhs == null || rhs == null) return null;

            BinaryOpExpression expr = new BinaryOpExpression();
            expr.setLhs(lhs);
            expr.setRhs(rhs);

            BinaryOpExpression.BinaryOperator operator = null;
            if (ctx.AND() != null) {
                operator = BinaryOpExpression.BinaryOperator.AND;

            } else if (ctx.OR() != null) {
                operator = BinaryOpExpression.BinaryOperator.OR;

            }
            expr.setOperator(operator);

            try {
                expr.setResultType(evaluateResultType(expr));
            } catch (Exception e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitNotComp(CalcFieldParser.NotCompContext ctx) {
            Expression cmp = visit(ctx.cpm);

            if (cmp == null) return null;

            UnaryOpExpression expr = new UnaryOpExpression();
            expr.setOperand(cmp);
            expr.setOperator(UnaryOpExpression.UnaryOperator.BANG);

            try {
                expr.setResultType(evaluateResultType(expr));
            } catch (Exception e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitInListExpr(CalcFieldParser.InListExprContext ctx) {
            Expression lhs = visit(ctx.lhs);
            Expression rhs = visit(ctx.rhs);

            if (lhs == null || rhs == null) return null;

            BinaryOpExpression expr = new BinaryOpExpression();
            expr.setLhs(lhs);
            expr.setRhs(rhs);

            BinaryOpExpression.BinaryOperator operator = null;
            if (ctx.INLIST() != null) {
                operator = BinaryOpExpression.BinaryOperator.INLIST;
            } else if (ctx.NOTINLIST() != null) {
                operator = BinaryOpExpression.BinaryOperator.NOTINLIST;

            }
            expr.setOperator(operator);

            try {
                expr.setResultType(evaluateResultType(expr));
            } catch (Exception e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitListExpression(CalcFieldParser.ListExpressionContext ctx) {
            ListExpression expr = new ListExpression();
            for (CalcFieldParser.ExpressionContext exprCtx: ctx.expression()) {
                Expression subExpr = visit(exprCtx);
                if (subExpr == null) return null; // break if something went wrong

                expr.addExpression(subExpr);
            }

            try {
                expr.setResultType(evaluateResultType(expr));
            } catch (Exception e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        private FieldDataType evaluateResultType(BinaryOpExpression parsedExpression) throws Exception {
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

        private FieldDataType evaluateResultType(CaseExpression expr) throws Exception {
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

        // the result type will always be the same, but we will need to evaluate the function arguments
        private FieldDataType evaluateResultType(FunctionExpression expr) throws Exception {
            List<FieldDataType> expectedArgTypes = expr.getFunction().getFunctionArguments();
            Iterator<Expression> argIt = expr.getArgumentExpressions().iterator();
            for (FieldDataType expectedType: expectedArgTypes) {
                Expression argExp = argIt.next();
                FieldDataType actualType = argExp.getResultType();
                if (expectedType != actualType) {
                    throw getDataTypesMismatchException(expectedType, actualType);
                }
            }

            return expr.getFunction().getReturnType();
        }

        private FieldDataType evaluateResultType(ListExpression expr) throws Exception {
            if (expr.getListExpressions() == null || expr.getListExpressions().isEmpty()) {
                throw new Exception("INLIST cannot be empty!");
            }

            FieldDataType aggType = null;
            for (Expression subEx: expr.getListExpressions()) {
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

        private FieldDataType evaluateResultType(UnaryOpExpression expr) throws Exception {
            FieldDataType operandType = expr.getOperand().getResultType();

            FieldDataType resultType = null;
            switch(expr.getOperator()) {
                // numeric sign operator
                case ADD:
                case SUB:
                {
                    if (operandType != FieldDataType.NUMERIC) {
                        throw getDataTypesMismatchException(FieldDataType.NUMERIC, operandType);
                    }

                    resultType = FieldDataType.NUMERIC;
                    break;
                }

                // boolean not operator
                case BANG:
                {
                    if (operandType != FieldDataType.BOOLEAN) {
                        throw getDataTypesMismatchException(FieldDataType.BOOLEAN, operandType);
                    }

                    resultType = FieldDataType.BOOLEAN;
                    break;
                }
            }

            return resultType;
        }

        private boolean isStringConcatenationAndAllowed(FieldDataType lhsType, FieldDataType rhsType) {
            // string with numeric or string with string
            return calcContext.concatenationAllowed() && ((lhsType == FieldDataType.STRING && rhsType == FieldDataType.STRING) ||
                                                                  (lhsType == FieldDataType.STRING && rhsType == FieldDataType.NUMERIC) ||
                                                                  (lhsType == FieldDataType.NUMERIC && rhsType == FieldDataType.STRING));
        }

        private Exception getDataTypesMismatchException(FieldDataType... mismatchedTypes) {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            if (mismatchedTypes != null) {
                for (FieldDataType dt: mismatchedTypes) {
                    sb.append(dt.toString()).append(", ");
                }
            }
            String mismatches = sb.toString();

            // remove trailing comma
            int trailingComma = mismatches.lastIndexOf(",");
            if (trailingComma >= 0) {
                mismatches = mismatches.substring(0, trailingComma);
            }

            // close paren
            mismatches += ")";

            return new Exception("Data types do not match! " + mismatches);
        }
    }
}
