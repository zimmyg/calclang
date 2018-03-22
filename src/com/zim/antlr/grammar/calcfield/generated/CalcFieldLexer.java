// Generated from C:/Users/Tim/IdeaProjects/ANTLR Test/src/com/zim/antlr/grammar/calcfield\CalcField.g4 by ANTLR 4.7
package com.zim.antlr.grammar.calcfield.generated;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalcFieldLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CASE=1, WHEN=2, THEN=3, ELSE=4, END=5, NUMERIC_LITERAL=6, BOOL_LITERAL=7, 
		STRING_LITERAL=8, LPAREN=9, RPAREN=10, COMMA=11, GT=12, LT=13, BANG=14, 
		EQUAL=15, LE=16, GE=17, NOTEQUAL=18, AND=19, OR=20, ADD=21, SUB=22, MUL=23, 
		DIV=24, CARET=25, MOD=26, INLIST=27, NOTINLIST=28, WS=29, IDENTIFIER=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"CASE", "WHEN", "THEN", "ELSE", "END", "NUMERIC_LITERAL", "DECIMAL_LITERAL", 
		"FLOAT_LITERAL", "BOOL_LITERAL", "STRING_LITERAL", "LPAREN", "RPAREN", 
		"COMMA", "GT", "LT", "BANG", "EQUAL", "LE", "GE", "NOTEQUAL", "AND", "OR", 
		"ADD", "SUB", "MUL", "DIV", "CARET", "MOD", "INLIST", "NOTINLIST", "WS", 
		"IDENTIFIER", "ExponentPart", "EscapeSequence", "HexDigit", "Digits", 
		"LetterOrDigit", "Letter"
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


	public CalcFieldLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CalcField.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u012c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\5\2X\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3b\n\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\5\4l\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5v\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\5\6~\n\6\3\7\3\7\5\7\u0082\n\7\3\b\3\b\5\b\u0086"+
		"\n\b\3\t\3\t\3\t\6\t\u008b\n\t\r\t\16\t\u008c\3\t\3\t\5\t\u0091\n\t\3"+
		"\t\5\t\u0094\n\t\3\t\3\t\3\t\5\t\u0099\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00ad\n\n\3\13\3\13\3"+
		"\13\7\13\u00b2\n\13\f\13\16\13\u00b5\13\13\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\31\3"+
		"\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\3 \6 \u00ed\n \r \16 \u00ee\3 \3 \3!\3!\7!\u00f5"+
		"\n!\f!\16!\u00f8\13!\3\"\3\"\5\"\u00fc\n\"\3\"\3\"\3#\3#\3#\3#\5#\u0104"+
		"\n#\3#\5#\u0107\n#\3#\3#\3#\6#\u010c\n#\r#\16#\u010d\3#\3#\3#\3#\3#\5"+
		"#\u0115\n#\3$\3$\3%\3%\7%\u011b\n%\f%\16%\u011e\13%\3%\5%\u0121\n%\3&"+
		"\3&\5&\u0125\n&\3\'\3\'\3\'\3\'\5\'\u012b\n\'\2\2(\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\2\21\2\23\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23"+
		")\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?\37A C\2E\2G\2I\2"+
		"K\2M\2\3\2\20\6\2\f\f\17\17$$^^\5\2\13\f\16\17\"\"\4\2GGgg\4\2--//\n\2"+
		"$$))^^ddhhppttvv\3\2\62\65\3\2\629\5\2\62;CHch\3\2\62;\4\2\62;aa\6\2&"+
		"&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\2\u0140"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2"+
		"\2\2A\3\2\2\2\3W\3\2\2\2\5a\3\2\2\2\7k\3\2\2\2\tu\3\2\2\2\13}\3\2\2\2"+
		"\r\u0081\3\2\2\2\17\u0085\3\2\2\2\21\u0098\3\2\2\2\23\u00ac\3\2\2\2\25"+
		"\u00ae\3\2\2\2\27\u00b8\3\2\2\2\31\u00ba\3\2\2\2\33\u00bc\3\2\2\2\35\u00be"+
		"\3\2\2\2\37\u00c0\3\2\2\2!\u00c2\3\2\2\2#\u00c4\3\2\2\2%\u00c6\3\2\2\2"+
		"\'\u00c9\3\2\2\2)\u00cc\3\2\2\2+\u00cf\3\2\2\2-\u00d2\3\2\2\2/\u00d5\3"+
		"\2\2\2\61\u00d7\3\2\2\2\63\u00d9\3\2\2\2\65\u00db\3\2\2\2\67\u00dd\3\2"+
		"\2\29\u00df\3\2\2\2;\u00e1\3\2\2\2=\u00e4\3\2\2\2?\u00ec\3\2\2\2A\u00f2"+
		"\3\2\2\2C\u00f9\3\2\2\2E\u0114\3\2\2\2G\u0116\3\2\2\2I\u0118\3\2\2\2K"+
		"\u0124\3\2\2\2M\u012a\3\2\2\2OP\7e\2\2PQ\7c\2\2QR\7u\2\2RX\7g\2\2ST\7"+
		"E\2\2TU\7C\2\2UV\7U\2\2VX\7G\2\2WO\3\2\2\2WS\3\2\2\2X\4\3\2\2\2YZ\7y\2"+
		"\2Z[\7j\2\2[\\\7g\2\2\\b\7p\2\2]^\7Y\2\2^_\7J\2\2_`\7G\2\2`b\7P\2\2aY"+
		"\3\2\2\2a]\3\2\2\2b\6\3\2\2\2cd\7v\2\2de\7j\2\2ef\7g\2\2fl\7p\2\2gh\7"+
		"V\2\2hi\7J\2\2ij\7G\2\2jl\7P\2\2kc\3\2\2\2kg\3\2\2\2l\b\3\2\2\2mn\7g\2"+
		"\2no\7n\2\2op\7u\2\2pv\7g\2\2qr\7G\2\2rs\7N\2\2st\7U\2\2tv\7G\2\2um\3"+
		"\2\2\2uq\3\2\2\2v\n\3\2\2\2wx\7g\2\2xy\7p\2\2y~\7f\2\2z{\7G\2\2{|\7P\2"+
		"\2|~\7F\2\2}w\3\2\2\2}z\3\2\2\2~\f\3\2\2\2\177\u0082\5\17\b\2\u0080\u0082"+
		"\5\21\t\2\u0081\177\3\2\2\2\u0081\u0080\3\2\2\2\u0082\16\3\2\2\2\u0083"+
		"\u0086\7\62\2\2\u0084\u0086\5I%\2\u0085\u0083\3\2\2\2\u0085\u0084\3\2"+
		"\2\2\u0086\20\3\2\2\2\u0087\u0088\5I%\2\u0088\u008a\7\60\2\2\u0089\u008b"+
		"\5I%\2\u008a\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c"+
		"\u008d\3\2\2\2\u008d\u0091\3\2\2\2\u008e\u008f\7\60\2\2\u008f\u0091\5"+
		"I%\2\u0090\u0087\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0093\3\2\2\2\u0092"+
		"\u0094\5C\"\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0099\3\2"+
		"\2\2\u0095\u0096\5I%\2\u0096\u0097\5C\"\2\u0097\u0099\3\2\2\2\u0098\u0090"+
		"\3\2\2\2\u0098\u0095\3\2\2\2\u0099\22\3\2\2\2\u009a\u009b\7v\2\2\u009b"+
		"\u009c\7t\2\2\u009c\u009d\7w\2\2\u009d\u00ad\7g\2\2\u009e\u009f\7V\2\2"+
		"\u009f\u00a0\7T\2\2\u00a0\u00a1\7W\2\2\u00a1\u00ad\7G\2\2\u00a2\u00a3"+
		"\7h\2\2\u00a3\u00a4\7c\2\2\u00a4\u00a5\7n\2\2\u00a5\u00a6\7u\2\2\u00a6"+
		"\u00ad\7g\2\2\u00a7\u00a8\7H\2\2\u00a8\u00a9\7C\2\2\u00a9\u00aa\7N\2\2"+
		"\u00aa\u00ab\7U\2\2\u00ab\u00ad\7G\2\2\u00ac\u009a\3\2\2\2\u00ac\u009e"+
		"\3\2\2\2\u00ac\u00a2\3\2\2\2\u00ac\u00a7\3\2\2\2\u00ad\24\3\2\2\2\u00ae"+
		"\u00b3\7$\2\2\u00af\u00b2\n\2\2\2\u00b0\u00b2\5E#\2\u00b1\u00af\3\2\2"+
		"\2\u00b1\u00b0\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4"+
		"\3\2\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7\7$\2\2\u00b7"+
		"\26\3\2\2\2\u00b8\u00b9\7*\2\2\u00b9\30\3\2\2\2\u00ba\u00bb\7+\2\2\u00bb"+
		"\32\3\2\2\2\u00bc\u00bd\7.\2\2\u00bd\34\3\2\2\2\u00be\u00bf\7@\2\2\u00bf"+
		"\36\3\2\2\2\u00c0\u00c1\7>\2\2\u00c1 \3\2\2\2\u00c2\u00c3\7#\2\2\u00c3"+
		"\"\3\2\2\2\u00c4\u00c5\7?\2\2\u00c5$\3\2\2\2\u00c6\u00c7\7>\2\2\u00c7"+
		"\u00c8\7?\2\2\u00c8&\3\2\2\2\u00c9\u00ca\7@\2\2\u00ca\u00cb\7?\2\2\u00cb"+
		"(\3\2\2\2\u00cc\u00cd\7#\2\2\u00cd\u00ce\7?\2\2\u00ce*\3\2\2\2\u00cf\u00d0"+
		"\7(\2\2\u00d0\u00d1\7(\2\2\u00d1,\3\2\2\2\u00d2\u00d3\7~\2\2\u00d3\u00d4"+
		"\7~\2\2\u00d4.\3\2\2\2\u00d5\u00d6\7-\2\2\u00d6\60\3\2\2\2\u00d7\u00d8"+
		"\7/\2\2\u00d8\62\3\2\2\2\u00d9\u00da\7,\2\2\u00da\64\3\2\2\2\u00db\u00dc"+
		"\7\61\2\2\u00dc\66\3\2\2\2\u00dd\u00de\7`\2\2\u00de8\3\2\2\2\u00df\u00e0"+
		"\7\'\2\2\u00e0:\3\2\2\2\u00e1\u00e2\7K\2\2\u00e2\u00e3\7P\2\2\u00e3<\3"+
		"\2\2\2\u00e4\u00e5\7P\2\2\u00e5\u00e6\7Q\2\2\u00e6\u00e7\7V\2\2\u00e7"+
		"\u00e8\7\"\2\2\u00e8\u00e9\7K\2\2\u00e9\u00ea\7P\2\2\u00ea>\3\2\2\2\u00eb"+
		"\u00ed\t\3\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ec\3\2"+
		"\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\b \2\2\u00f1"+
		"@\3\2\2\2\u00f2\u00f6\5M\'\2\u00f3\u00f5\5K&\2\u00f4\u00f3\3\2\2\2\u00f5"+
		"\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7B\3\2\2\2"+
		"\u00f8\u00f6\3\2\2\2\u00f9\u00fb\t\4\2\2\u00fa\u00fc\t\5\2\2\u00fb\u00fa"+
		"\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\5I%\2\u00fe"+
		"D\3\2\2\2\u00ff\u0100\7^\2\2\u0100\u0115\t\6\2\2\u0101\u0106\7^\2\2\u0102"+
		"\u0104\t\7\2\2\u0103\u0102\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0105\3\2"+
		"\2\2\u0105\u0107\t\b\2\2\u0106\u0103\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u0115\t\b\2\2\u0109\u010b\7^\2\2\u010a\u010c\7w\2"+
		"\2\u010b\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010b\3\2\2\2\u010d\u010e"+
		"\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\5G$\2\u0110\u0111\5G$\2\u0111"+
		"\u0112\5G$\2\u0112\u0113\5G$\2\u0113\u0115\3\2\2\2\u0114\u00ff\3\2\2\2"+
		"\u0114\u0101\3\2\2\2\u0114\u0109\3\2\2\2\u0115F\3\2\2\2\u0116\u0117\t"+
		"\t\2\2\u0117H\3\2\2\2\u0118\u0120\t\n\2\2\u0119\u011b\t\13\2\2\u011a\u0119"+
		"\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d"+
		"\u011f\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0121\t\n\2\2\u0120\u011c\3\2"+
		"\2\2\u0120\u0121\3\2\2\2\u0121J\3\2\2\2\u0122\u0125\5M\'\2\u0123\u0125"+
		"\t\n\2\2\u0124\u0122\3\2\2\2\u0124\u0123\3\2\2\2\u0125L\3\2\2\2\u0126"+
		"\u012b\t\f\2\2\u0127\u012b\n\r\2\2\u0128\u0129\t\16\2\2\u0129\u012b\t"+
		"\17\2\2\u012a\u0126\3\2\2\2\u012a\u0127\3\2\2\2\u012a\u0128\3\2\2\2\u012b"+
		"N\3\2\2\2\34\2Waku}\u0081\u0085\u008c\u0090\u0093\u0098\u00ac\u00b1\u00b3"+
		"\u00ee\u00f6\u00fb\u0103\u0106\u010d\u0114\u011c\u0120\u0124\u012a\3\2"+
		"\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}