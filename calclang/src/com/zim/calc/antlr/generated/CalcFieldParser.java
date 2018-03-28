// Generated from C:/Users/Tim/IdeaProjects/ANTLR Test/src/com/zim/antlr/grammar/calcfield\CalcField.g4 by ANTLR 4.7
package com.zim.calc.antlr.generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalcFieldParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CASE=1, WHEN=2, THEN=3, ELSE=4, END=5, NUMERIC_LITERAL=6, BOOL_LITERAL=7, 
		STRING_LITERAL=8, LPAREN=9, RPAREN=10, COMMA=11, GT=12, LT=13, BANG=14, 
		EQUAL=15, LE=16, GE=17, NOTEQUAL=18, AND=19, OR=20, ADD=21, SUB=22, MUL=23, 
		DIV=24, CARET=25, MOD=26, INLIST=27, NOTINLIST=28, WS=29, IDENTIFIER=30;
	public static final int
		RULE_expression = 0, RULE_listExpression = 1, RULE_binaryOp = 2, RULE_unaryOp = 3, 
		RULE_comparison = 4, RULE_caseStatement = 5, RULE_functionCall = 6, RULE_literal = 7;
	public static final String[] ruleNames = {
		"expression", "listExpression", "binaryOp", "unaryOp", "comparison", "caseStatement", 
		"functionCall", "literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, "'('", "')'", "','", 
		"'>'", "'<'", "'!'", "'='", "'<='", "'>='", "'!='", "'&&'", "'||'", "'+'", 
		"'-'", "'*'", "'/'", "'^'", "'%'", "'IN'", "'NOT IN'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "CASE", "WHEN", "THEN", "ELSE", "END", "NUMERIC_LITERAL", "BOOL_LITERAL", 
		"STRING_LITERAL", "LPAREN", "RPAREN", "COMMA", "GT", "LT", "BANG", "EQUAL", 
		"LE", "GE", "NOTEQUAL", "AND", "OR", "ADD", "SUB", "MUL", "DIV", "CARET", 
		"MOD", "INLIST", "NOTINLIST", "WS", "IDENTIFIER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CalcField.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CalcFieldParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnaryOpExprContext extends ExpressionContext {
		public ExpressionContext exp;
		public UnaryOpContext unaryOp() {
			return getRuleContext(UnaryOpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryOpExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterUnaryOpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitUnaryOpExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitUnaryOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncExprContext extends ExpressionContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FuncExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralExprContext extends ExpressionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InListExprContext extends ExpressionContext {
		public ExpressionContext lhs;
		public ListExpressionContext rhs;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INLIST() { return getToken(CalcFieldParser.INLIST, 0); }
		public TerminalNode NOTINLIST() { return getToken(CalcFieldParser.NOTINLIST, 0); }
		public ListExpressionContext listExpression() {
			return getRuleContext(ListExpressionContext.class,0);
		}
		public InListExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterInListExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitInListExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitInListExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOpExprContext extends ExpressionContext {
		public ExpressionContext lhs;
		public ExpressionContext rhs;
		public BinaryOpContext binaryOp() {
			return getRuleContext(BinaryOpContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOpExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterBinaryOpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitBinaryOpExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitBinaryOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CaseExprContext extends ExpressionContext {
		public CaseStatementContext caseStatement() {
			return getRuleContext(CaseStatementContext.class,0);
		}
		public CaseExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterCaseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitCaseExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitCaseExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(CalcFieldParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CalcFieldParser.RPAREN, 0); }
		public ParenExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(17);
				match(LPAREN);
				setState(18);
				expression(0);
				setState(19);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new UnaryOpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(21);
				unaryOp();
				setState(22);
				((UnaryOpExprContext)_localctx).exp = expression(4);
				}
				break;
			case 3:
				{
				_localctx = new FuncExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(24);
				functionCall();
				}
				break;
			case 4:
				{
				_localctx = new CaseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(25);
				caseStatement();
				}
				break;
			case 5:
				{
				_localctx = new LiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(26);
				literal();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(38);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(36);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryOpExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOpExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(29);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(30);
						binaryOp();
						setState(31);
						((BinaryOpExprContext)_localctx).rhs = expression(7);
						}
						break;
					case 2:
						{
						_localctx = new InListExprContext(new ExpressionContext(_parentctx, _parentState));
						((InListExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(33);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(34);
						_la = _input.LA(1);
						if ( !(_la==INLIST || _la==NOTINLIST) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(35);
						((InListExprContext)_localctx).rhs = listExpression();
						}
						break;
					}
					} 
				}
				setState(40);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ListExpressionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(CalcFieldParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CalcFieldParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CalcFieldParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CalcFieldParser.COMMA, i);
		}
		public ListExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterListExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitListExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitListExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListExpressionContext listExpression() throws RecognitionException {
		ListExpressionContext _localctx = new ListExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_listExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(LPAREN);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CASE) | (1L << NUMERIC_LITERAL) | (1L << BOOL_LITERAL) | (1L << STRING_LITERAL) | (1L << LPAREN) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(42);
				expression(0);
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(43);
					match(COMMA);
					setState(44);
					expression(0);
					}
					}
					setState(49);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(52);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryOpContext extends ParserRuleContext {
		public TerminalNode MUL() { return getToken(CalcFieldParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(CalcFieldParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(CalcFieldParser.MOD, 0); }
		public TerminalNode ADD() { return getToken(CalcFieldParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(CalcFieldParser.SUB, 0); }
		public TerminalNode AND() { return getToken(CalcFieldParser.AND, 0); }
		public TerminalNode OR() { return getToken(CalcFieldParser.OR, 0); }
		public TerminalNode GT() { return getToken(CalcFieldParser.GT, 0); }
		public TerminalNode LT() { return getToken(CalcFieldParser.LT, 0); }
		public TerminalNode LE() { return getToken(CalcFieldParser.LE, 0); }
		public TerminalNode GE() { return getToken(CalcFieldParser.GE, 0); }
		public TerminalNode EQUAL() { return getToken(CalcFieldParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(CalcFieldParser.NOTEQUAL, 0); }
		public BinaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterBinaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitBinaryOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitBinaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOpContext binaryOp() throws RecognitionException {
		BinaryOpContext _localctx = new BinaryOpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_binaryOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << EQUAL) | (1L << LE) | (1L << GE) | (1L << NOTEQUAL) | (1L << AND) | (1L << OR) | (1L << ADD) | (1L << SUB) | (1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryOpContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(CalcFieldParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(CalcFieldParser.SUB, 0); }
		public TerminalNode BANG() { return getToken(CalcFieldParser.BANG, 0); }
		public UnaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterUnaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitUnaryOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitUnaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOpContext unaryOp() throws RecognitionException {
		UnaryOpContext _localctx = new UnaryOpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_unaryOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BANG) | (1L << ADD) | (1L << SUB))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonContext extends ParserRuleContext {
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
	 
		public ComparisonContext() { }
		public void copyFrom(ComparisonContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LogicalCompContext extends ComparisonContext {
		public ComparisonContext lhs;
		public ComparisonContext rhs;
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public TerminalNode OR() { return getToken(CalcFieldParser.OR, 0); }
		public TerminalNode AND() { return getToken(CalcFieldParser.AND, 0); }
		public LogicalCompContext(ComparisonContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterLogicalComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitLogicalComp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitLogicalComp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprCompContext extends ComparisonContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprCompContext(ComparisonContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterExprComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitExprComp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitExprComp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotCompContext extends ComparisonContext {
		public ComparisonContext cpm;
		public TerminalNode BANG() { return getToken(CalcFieldParser.BANG, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public NotCompContext(ComparisonContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterNotComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitNotComp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitNotComp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenCompContext extends ComparisonContext {
		public TerminalNode LPAREN() { return getToken(CalcFieldParser.LPAREN, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CalcFieldParser.RPAREN, 0); }
		public ParenCompContext(ComparisonContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterParenComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitParenComp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitParenComp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualityCompContext extends ComparisonContext {
		public ExpressionContext lhs;
		public ExpressionContext rhs;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(CalcFieldParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(CalcFieldParser.NOTEQUAL, 0); }
		public TerminalNode GT() { return getToken(CalcFieldParser.GT, 0); }
		public TerminalNode LT() { return getToken(CalcFieldParser.LT, 0); }
		public TerminalNode LE() { return getToken(CalcFieldParser.LE, 0); }
		public TerminalNode GE() { return getToken(CalcFieldParser.GE, 0); }
		public EqualityCompContext(ComparisonContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterEqualityComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitEqualityComp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitEqualityComp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		return comparison(0);
	}

	private ComparisonContext comparison(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ComparisonContext _localctx = new ComparisonContext(_ctx, _parentState);
		ComparisonContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_comparison, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				_localctx = new ParenCompContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(59);
				match(LPAREN);
				setState(60);
				comparison(0);
				setState(61);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new NotCompContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(63);
				match(BANG);
				setState(64);
				((NotCompContext)_localctx).cpm = comparison(5);
				}
				break;
			case 3:
				{
				_localctx = new EqualityCompContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(65);
				((EqualityCompContext)_localctx).lhs = expression(0);
				setState(66);
				_la = _input.LA(1);
				if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(67);
				((EqualityCompContext)_localctx).rhs = expression(0);
				}
				break;
			case 4:
				{
				_localctx = new EqualityCompContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(69);
				((EqualityCompContext)_localctx).lhs = expression(0);
				setState(70);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << LE) | (1L << GE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(71);
				((EqualityCompContext)_localctx).rhs = expression(0);
				}
				break;
			case 5:
				{
				_localctx = new ExprCompContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(73);
				expression(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicalCompContext(new ComparisonContext(_parentctx, _parentState));
					((LogicalCompContext)_localctx).lhs = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_comparison);
					setState(76);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(77);
					_la = _input.LA(1);
					if ( !(_la==AND || _la==OR) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(78);
					((LogicalCompContext)_localctx).rhs = comparison(5);
					}
					} 
				}
				setState(83);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CaseStatementContext extends ParserRuleContext {
		public ComparisonContext cmp;
		public ExpressionContext thenExp;
		public ExpressionContext elseExp;
		public TerminalNode CASE() { return getToken(CalcFieldParser.CASE, 0); }
		public TerminalNode END() { return getToken(CalcFieldParser.END, 0); }
		public List<TerminalNode> WHEN() { return getTokens(CalcFieldParser.WHEN); }
		public TerminalNode WHEN(int i) {
			return getToken(CalcFieldParser.WHEN, i);
		}
		public List<TerminalNode> THEN() { return getTokens(CalcFieldParser.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(CalcFieldParser.THEN, i);
		}
		public TerminalNode ELSE() { return getToken(CalcFieldParser.ELSE, 0); }
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CaseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterCaseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitCaseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitCaseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseStatementContext caseStatement() throws RecognitionException {
		CaseStatementContext _localctx = new CaseStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_caseStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(CASE);
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(85);
				match(WHEN);
				setState(86);
				((CaseStatementContext)_localctx).cmp = comparison(0);
				setState(87);
				match(THEN);
				setState(88);
				((CaseStatementContext)_localctx).thenExp = expression(0);
				}
				}
				setState(92); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WHEN );
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(94);
				match(ELSE);
				setState(95);
				((CaseStatementContext)_localctx).elseExp = expression(0);
				}
			}

			setState(98);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(CalcFieldParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(CalcFieldParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CalcFieldParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CalcFieldParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CalcFieldParser.COMMA, i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(IDENTIFIER);
			setState(101);
			match(LPAREN);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CASE) | (1L << NUMERIC_LITERAL) | (1L << BOOL_LITERAL) | (1L << STRING_LITERAL) | (1L << LPAREN) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(102);
				expression(0);
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(103);
					match(COMMA);
					setState(104);
					expression(0);
					}
					}
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(112);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(CalcFieldParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(CalcFieldParser.STRING_LITERAL, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(CalcFieldParser.BOOL_LITERAL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(CalcFieldParser.IDENTIFIER, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalcFieldListener ) ((CalcFieldListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcFieldVisitor ) return ((CalcFieldVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERIC_LITERAL) | (1L << BOOL_LITERAL) | (1L << STRING_LITERAL) | (1L << IDENTIFIER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 4:
			return comparison_sempred((ComparisonContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		}
		return true;
	}
	private boolean comparison_sempred(ComparisonContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 w\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\5\2\36\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\'\n"+
		"\2\f\2\16\2*\13\2\3\3\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63\13\3\5\3\65\n"+
		"\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\5\6M\n\6\3\6\3\6\3\6\7\6R\n\6\f\6\16\6U\13\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\6\7]\n\7\r\7\16\7^\3\7\3\7\5\7c\n\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\7\bl\n\b\f\b\16\bo\13\b\5\bq\n\b\3\b\3\b\3\t\3\t\3\t\2"+
		"\4\2\n\n\2\4\6\b\n\f\16\20\2\t\3\2\35\36\5\2\16\17\21\32\34\34\4\2\20"+
		"\20\27\30\4\2\21\21\24\24\4\2\16\17\22\23\3\2\25\26\4\2\b\n  \2\177\2"+
		"\35\3\2\2\2\4+\3\2\2\2\68\3\2\2\2\b:\3\2\2\2\nL\3\2\2\2\fV\3\2\2\2\16"+
		"f\3\2\2\2\20t\3\2\2\2\22\23\b\2\1\2\23\24\7\13\2\2\24\25\5\2\2\2\25\26"+
		"\7\f\2\2\26\36\3\2\2\2\27\30\5\b\5\2\30\31\5\2\2\6\31\36\3\2\2\2\32\36"+
		"\5\16\b\2\33\36\5\f\7\2\34\36\5\20\t\2\35\22\3\2\2\2\35\27\3\2\2\2\35"+
		"\32\3\2\2\2\35\33\3\2\2\2\35\34\3\2\2\2\36(\3\2\2\2\37 \f\b\2\2 !\5\6"+
		"\4\2!\"\5\2\2\t\"\'\3\2\2\2#$\f\7\2\2$%\t\2\2\2%\'\5\4\3\2&\37\3\2\2\2"+
		"&#\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)\3\3\2\2\2*(\3\2\2\2+\64\7\13"+
		"\2\2,\61\5\2\2\2-.\7\r\2\2.\60\5\2\2\2/-\3\2\2\2\60\63\3\2\2\2\61/\3\2"+
		"\2\2\61\62\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\64,\3\2\2\2\64\65\3\2\2"+
		"\2\65\66\3\2\2\2\66\67\7\f\2\2\67\5\3\2\2\289\t\3\2\29\7\3\2\2\2:;\t\4"+
		"\2\2;\t\3\2\2\2<=\b\6\1\2=>\7\13\2\2>?\5\n\6\2?@\7\f\2\2@M\3\2\2\2AB\7"+
		"\20\2\2BM\5\n\6\7CD\5\2\2\2DE\t\5\2\2EF\5\2\2\2FM\3\2\2\2GH\5\2\2\2HI"+
		"\t\6\2\2IJ\5\2\2\2JM\3\2\2\2KM\5\2\2\2L<\3\2\2\2LA\3\2\2\2LC\3\2\2\2L"+
		"G\3\2\2\2LK\3\2\2\2MS\3\2\2\2NO\f\6\2\2OP\t\7\2\2PR\5\n\6\7QN\3\2\2\2"+
		"RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\13\3\2\2\2US\3\2\2\2V\\\7\3\2\2WX\7\4"+
		"\2\2XY\5\n\6\2YZ\7\5\2\2Z[\5\2\2\2[]\3\2\2\2\\W\3\2\2\2]^\3\2\2\2^\\\3"+
		"\2\2\2^_\3\2\2\2_b\3\2\2\2`a\7\6\2\2ac\5\2\2\2b`\3\2\2\2bc\3\2\2\2cd\3"+
		"\2\2\2de\7\7\2\2e\r\3\2\2\2fg\7 \2\2gp\7\13\2\2hm\5\2\2\2ij\7\r\2\2jl"+
		"\5\2\2\2ki\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2nq\3\2\2\2om\3\2\2\2p"+
		"h\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\7\f\2\2s\17\3\2\2\2tu\t\b\2\2u\21\3\2"+
		"\2\2\r\35&(\61\64LS^bmp";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}