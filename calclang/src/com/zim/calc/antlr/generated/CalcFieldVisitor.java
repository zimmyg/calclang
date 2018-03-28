// Generated from C:/Users/Tim/IdeaProjects/ANTLR Test/src/com/zim/antlr/grammar/calcfield\CalcField.g4 by ANTLR 4.7
package com.zim.calc.antlr.generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalcFieldParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalcFieldVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code unaryOpExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOpExpr(CalcFieldParser.UnaryOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncExpr(CalcFieldParser.FuncExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpr(CalcFieldParser.LiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inListExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInListExpr(CalcFieldParser.InListExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryOpExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOpExpr(CalcFieldParser.BinaryOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpr(CalcFieldParser.CaseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link CalcFieldParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(CalcFieldParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcFieldParser#listExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListExpression(CalcFieldParser.ListExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcFieldParser#binaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOp(CalcFieldParser.BinaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcFieldParser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(CalcFieldParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalComp(CalcFieldParser.LogicalCompContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprComp(CalcFieldParser.ExprCompContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotComp(CalcFieldParser.NotCompContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenComp(CalcFieldParser.ParenCompContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityComp}
	 * labeled alternative in {@link CalcFieldParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityComp(CalcFieldParser.EqualityCompContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcFieldParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(CalcFieldParser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcFieldParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(CalcFieldParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcFieldParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(CalcFieldParser.LiteralContext ctx);
}