package com.zim.calc.expression;

import com.zim.calc.antlr.generated.CalcFieldBaseVisitor;
import com.zim.calc.antlr.generated.CalcFieldLexer;
import com.zim.calc.antlr.generated.CalcFieldParser;
import com.zim.calc.context.*;
import com.zim.util.CalcConstants;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public abstract class CalcFieldExpressionBuilder  {

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
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ThrowErrorHandler());

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CalcFieldParser parser = new CalcFieldParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ThrowErrorHandler());

        CalcFieldParser.CalculationContext expression = parser.calculation();
        CalcFieldExpressionVisitor exprVisitor = new CalcFieldExpressionVisitor();
        Expression result = exprVisitor.visit(expression);
        if (result == null && exprVisitor.getException() != null) {
            throw exprVisitor.getException();
        }

        return result;
    }

    protected abstract FieldDataType validateExpression(BinaryOpExpression parsedExpression) throws CalcException;
    protected abstract FieldDataType validateExpression(CaseExpression expr) throws CalcException;
    protected abstract FieldDataType validateExpression(ListExpression expr) throws CalcException;
    protected abstract FieldDataType validateExpression(UnaryOpExpression expr) throws CalcException;

    // helper methods

    /**
     * Do these two data types constitute a string concatenation operation, and is string concatenation allowed by the context?
     */
    protected boolean isStringConcatenationAndAllowed(FieldDataType lhsType, FieldDataType rhsType) {
        // string with numeric or string with string
        return calcContext.concatenationAllowed() && ((lhsType == FieldDataType.STRING && rhsType == FieldDataType.STRING) ||
                (lhsType == FieldDataType.STRING && rhsType == FieldDataType.NUMERIC) ||
                (lhsType == FieldDataType.NUMERIC && rhsType == FieldDataType.STRING));
    }

    private String serializeDataTypesArray(FieldDataType[] types) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if (types != null) {
            boolean first = true;
            for (FieldDataType dt: types) {
                if (!first) sb.append(", ");
                first = false;
                sb.append(dt.toString());
            }
        }
        return sb.append(")").toString();
    }

    protected CalcException getNoOverloadException(String funcName, FieldDataType[] gotTypes) {
        String gotTypesStr = serializeDataTypesArray(gotTypes);
        return new CalcException(CalcConstants.NO_OVERLOADS_MSG, new Object[] { funcName, gotTypesStr });
    }

    protected CalcException getDataTypesMismatchException(FieldDataType[] wanted, FieldDataType[] got) {
        String wantedStr = serializeDataTypesArray(wanted);
        String gotStr = serializeDataTypesArray(got);
        Object[] messageParams = new Object[] { wantedStr, gotStr };

        return new CalcException(CalcConstants.DATA_TYPE_MISMATCH_MSG, messageParams);
    }

    protected CalcException getSyntaxErrorException(int line, int charPositionInLine, String msg, Exception cause) {
        Object[] messageParams = new Object[] { line, charPositionInLine, msg };
        return new CalcException(CalcConstants.SYNTAX_ERROR_MSG, messageParams, cause);
    }

    // have to define our own error handler which throws the errors as exceptions.
    // The default implementation just prints the error and continues, where we want it to throw and cancel.
    private class ThrowErrorHandler extends BaseErrorListener {
        @Override
        public void syntaxError (Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                                 String msg, RecognitionException e) throws ParseCancellationException {
            // TODO: See if we can use the arguments of this function to inspect the error more closely in order to give
            // TODO: better error messages, rather than just using the message we're given, which is not localisable.
            CalcException cause = getSyntaxErrorException(line, charPositionInLine, msg, e);
            throw new ParseCancellationException("Error in line " + line + ":" + charPositionInLine + " " + msg, cause);
        }
    }

    // Using an inner class, because I don't want to expose the API for the ANTLR expression visitor, since it works
    // with the ANTLR generated expressions, and I don't want the overlap in expression terminology to confuse users
    // between the expressions that this class produces versus the ones it operates on.
    private class CalcFieldExpressionVisitor extends CalcFieldBaseVisitor<Expression> {
        private CalcException ex;

        /**
         * If there was an error while visiting, we can't throw exceptions because the base class does not allow us to.
         * To check if parsing failed, check the exception.
         */
        CalcException getException () {
            return ex;
        }

        @Override
        public Expression visitCalculation (CalcFieldParser.CalculationContext ctx) {
            return ctx.expression().accept(this);
        }

        @Override
        public Expression visitParenExpr (CalcFieldParser.ParenExprContext ctx) {
            // need to implement this specifically, because the base visitor is not smart enough to work it out and always returns null
            return ctx.expression().accept(this);
        }

        @Override
        public Expression visitParenComp (CalcFieldParser.ParenCompContext ctx) {
            // need to implement this specifically, because the base visitor is not smart enough to work it out and always returns null
            return ctx.comparison().accept(this);
        }

        @Override
        public Expression visitBinaryOpExpr (CalcFieldParser.BinaryOpExprContext ctx) {
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
                expr.setResultType(validateExpression(expr));
            } catch (CalcException e) {
                this.ex = e;
                return null; // break out of parsing, something went wrong
            }

            return expr;
        }

        @Override
        public Expression visitUnaryOpExpr (CalcFieldParser.UnaryOpExprContext ctx) {
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
                expr.setResultType(validateExpression(expr));
            } catch (CalcException e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitLiteral (CalcFieldParser.LiteralContext ctx) {

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
                    this.ex = new CalcException(CalcConstants.INVALID_IDENT_MSG, new Object[] { node.getText() });
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
        public Expression visitFunctionCall (CalcFieldParser.FunctionCallContext ctx) {
            TerminalNode funcNameNode = ctx.IDENTIFIER();
            CalculationFunction func = calcContext.getFunctionForId(funcNameNode.getText());
            if (func == null) {
                this.ex = new CalcException(CalcConstants.INVALID_IDENT_MSG, new Object[] { funcNameNode.getText() });
                return null;
            }

            List<Expression> evaluatedParams = new LinkedList<>();
            for (CalcFieldParser.ExpressionContext subCtx : ctx.expression()) {
                Expression parsedSub = visit(subCtx);
                if (parsedSub == null) return null;

                evaluatedParams.add(parsedSub);
            }

            FieldDataType[] inputTypes =  new FieldDataType[evaluatedParams.size()];
            for (int i = 0; i < inputTypes.length; i++) {
                inputTypes[i] = evaluatedParams.get(i).getResultType();
            }

            FunctionSignature[] signatures = func.getSignatures();
            int activeSigIndex = -1;
            for (int i = 0; i < signatures.length; i++) {
                FunctionSignature sig = signatures[i];
                FieldDataType[] argTypes = sig.argumentTypes;
                if (evaluatedParams.size() == argTypes.length &&
                        argTypesMatch(argTypes, inputTypes)) {
                    // function signature matches, use this one
                    activeSigIndex = i;
                    break;
                }
            }

            if (activeSigIndex < 0) {
                this.ex = getNoOverloadException(func.getFunctionName(), inputTypes);
                return null;
            }

            FunctionExpression expr = new FunctionExpression();
            expr.setFunction(func);
            expr.setArgumentExpressions(evaluatedParams);
            expr.setSignatureIndex(activeSigIndex);

            return expr;
        }

        private boolean argTypesMatch(FieldDataType[] expectedTypes, FieldDataType[] inputTypes) {
            if (expectedTypes.length != inputTypes.length) {
                return false;
            }

            for (int i = 0; i < expectedTypes.length; i++) {
                FieldDataType expected = expectedTypes[i];
                FieldDataType actual = inputTypes[i];
                if (expected != actual) return false;
            }

            return true;
        }

        @Override
        public Expression visitCaseStatement (CalcFieldParser.CaseStatementContext ctx) {
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
                expr.setResultType(validateExpression(expr));
            } catch (CalcException e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitEqualityComp (CalcFieldParser.EqualityCompContext ctx) {
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
                expr.setResultType(validateExpression(expr));
            } catch (CalcException e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitLogicalComp (CalcFieldParser.LogicalCompContext ctx) {
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
                expr.setResultType(validateExpression(expr));
            } catch (CalcException e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitNotComp (CalcFieldParser.NotCompContext ctx) {
            Expression cmp = visit(ctx.cpm);

            if (cmp == null) return null;

            UnaryOpExpression expr = new UnaryOpExpression();
            expr.setOperand(cmp);
            expr.setOperator(UnaryOpExpression.UnaryOperator.BANG);

            try {
                expr.setResultType(validateExpression(expr));
            } catch (CalcException e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitInListExpr (CalcFieldParser.InListExprContext ctx) {
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
                expr.setResultType(validateExpression(expr));
            } catch (CalcException e) {
                this.ex = e;
                return null;
            }

            return expr;
        }

        @Override
        public Expression visitListExpression (CalcFieldParser.ListExpressionContext ctx) {
            ListExpression expr = new ListExpression();
            for (CalcFieldParser.ExpressionContext exprCtx : ctx.expression()) {
                Expression subExpr = visit(exprCtx);
                if (subExpr == null) return null; // break if something went wrong

                expr.addExpression(subExpr);
            }

            try {
                expr.setResultType(validateExpression(expr));
            } catch (CalcException e) {
                this.ex = e;
                return null;
            }

            return expr;
        }
    }
}
