// Generated from C:/IJWorkspace/calclang/calclang/src/com/zim/calc/antlr\CalcField.g4 by ANTLR 4.7
package com.zim.calc.antlr.generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalcFieldParser}.
 */
public interface CalcFieldListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalcFieldParser#calculation}.
	 * @param ctx the parse tree
	 */
	void enterCalculation(CalcFieldParser.CalculationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcFieldParser#calculation}.
	 * @param ctx the parse tree
	 */
	void exitCalculation(CalcFieldParser.CalculationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryOpExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOpExpr(CalcFieldParser.UnaryOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryOpExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOpExpr(CalcFieldParser.UnaryOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncExpr(CalcFieldParser.FuncExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncExpr(CalcFieldParser.FuncExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(CalcFieldParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(CalcFieldParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inListExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInListExpr(CalcFieldParser.InListExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inListExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInListExpr(CalcFieldParser.InListExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryOpExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOpExpr(CalcFieldParser.BinaryOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryOpExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOpExpr(CalcFieldParser.BinaryOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpr(CalcFieldParser.CaseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpr(CalcFieldParser.CaseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(CalcFieldParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(CalcFieldParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcFieldParser#listExpression}.
	 * @param ctx the parse tree
	 */
	void enterListExpression(CalcFieldParser.ListExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcFieldParser#listExpression}.
	 * @param ctx the parse tree
	 */
	void exitListExpression(CalcFieldParser.ListExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcFieldParser#binaryOp}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOp(CalcFieldParser.BinaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcFieldParser#binaryOp}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOp(CalcFieldParser.BinaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcFieldParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(CalcFieldParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcFieldParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(CalcFieldParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterLogicalComp(CalcFieldParser.LogicalCompContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitLogicalComp(CalcFieldParser.LogicalCompContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterExprComp(CalcFieldParser.ExprCompContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitExprComp(CalcFieldParser.ExprCompContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterNotComp(CalcFieldParser.NotCompContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitNotComp(CalcFieldParser.NotCompContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterParenComp(CalcFieldParser.ParenCompContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitParenComp(CalcFieldParser.ParenCompContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterEqualityComp(CalcFieldParser.EqualityCompContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitEqualityComp(CalcFieldParser.EqualityCompContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcFieldParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(CalcFieldParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcFieldParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(CalcFieldParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcFieldParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(CalcFieldParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcFieldParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(CalcFieldParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcFieldParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CalcFieldParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcFieldParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CalcFieldParser.LiteralContext ctx);
}