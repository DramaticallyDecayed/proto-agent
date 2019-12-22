// Generated from D:/code/IdeaProjects/ProtoAgent/union/src/main/resources/antlr_grammar_rules\NotificationFormat.g4 by ANTLR 4.7
package dd.union.perception.notification.recognition.rules;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NotificationFormatLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BULLET_SIGN=1, DEPLOYED_BULLET_SIGN=2, NON_DEPLOYED_BULLET_SIGN=3, TEXT=4, 
		WHITESPACE=5;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"BULLET_SIGN", "DEPLOYED_BULLET_SIGN", "NON_DEPLOYED_BULLET_SIGN", "DIGIT_BULLET", 
		"SYMBOL_BULLET", "NUMBER", "SIGNED_NUMBER", "UNSIGNED_NUMBER", "TEXT", 
		"WORD", "DIGIT", "SYMBOL_START", "CAPITAL_EN", "LOWERCASE_EN", "SEPARATOR", 
		"SIGN", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "BULLET_SIGN", "DEPLOYED_BULLET_SIGN", "NON_DEPLOYED_BULLET_SIGN", 
		"TEXT", "WHITESPACE"
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


	public NotificationFormatLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "NotificationFormat.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\7\u00a0\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\5\2(\n\2\3\3\3\3\7\3,\n\3\f\3\16\3/\13\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\7\4<\n\4\f\4\16\4?\13\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\5\5X\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6d\n\6\3\7\3\7\5\7"+
		"h\n\7\3\b\6\bk\n\b\r\b\16\bl\3\b\3\b\6\bq\n\b\r\b\16\br\3\t\6\tv\n\t\r"+
		"\t\16\tw\3\n\3\n\3\n\3\n\6\n~\n\n\r\n\16\n\177\3\n\3\n\3\13\3\13\7\13"+
		"\u0086\n\13\f\13\16\13\u0089\13\13\3\f\3\f\3\r\3\r\3\r\5\r\u0090\n\r\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\6\22\u009b\n\22\r\22\16\22"+
		"\u009c\3\22\3\22\2\2\23\3\3\5\4\7\5\t\2\13\2\r\2\17\2\21\2\23\6\25\2\27"+
		"\2\31\2\33\2\35\2\37\2!\2#\7\3\2\6\4\2*+\u0412\u0451\7\2$$..\60\61<=\u2015"+
		"\u2015\4\2,-//\5\2\13\f\17\17\"\"\2\u00a6\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\23\3\2\2\2\2#\3\2\2\2\3\'\3\2\2\2\5)\3\2\2\2\79\3\2\2\2\tW"+
		"\3\2\2\2\13c\3\2\2\2\rg\3\2\2\2\17j\3\2\2\2\21u\3\2\2\2\23}\3\2\2\2\25"+
		"\u0083\3\2\2\2\27\u008a\3\2\2\2\31\u008f\3\2\2\2\33\u0091\3\2\2\2\35\u0093"+
		"\3\2\2\2\37\u0095\3\2\2\2!\u0097\3\2\2\2#\u009a\3\2\2\2%(\5\t\5\2&(\5"+
		"\13\6\2\'%\3\2\2\2\'&\3\2\2\2(\4\3\2\2\2)-\5\13\6\2*,\5#\22\2+*\3\2\2"+
		"\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2\2\2\60\61\7F\2\2\61"+
		"\62\7G\2\2\62\63\7R\2\2\63\64\7N\2\2\64\65\7Q\2\2\65\66\7[\2\2\66\67\7"+
		"G\2\2\678\7F\2\28\6\3\2\2\29=\5\13\6\2:<\5#\22\2;:\3\2\2\2<?\3\2\2\2="+
		";\3\2\2\2=>\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\7P\2\2AB\7Q\2\2BC\7P\2\2CD\7"+
		"/\2\2DE\7F\2\2EF\7G\2\2FG\7R\2\2GH\7N\2\2HI\7Q\2\2IJ\7[\2\2JK\7G\2\2K"+
		"L\7F\2\2L\b\3\2\2\2MN\5\21\t\2NO\7\60\2\2OX\3\2\2\2PQ\5\21\t\2QR\7+\2"+
		"\2RX\3\2\2\2ST\7*\2\2TU\5\21\t\2UV\7+\2\2VX\3\2\2\2WM\3\2\2\2WP\3\2\2"+
		"\2WS\3\2\2\2X\n\3\2\2\2YZ\5\33\16\2Z[\7\60\2\2[d\3\2\2\2\\]\5\33\16\2"+
		"]^\7+\2\2^d\3\2\2\2_`\7*\2\2`a\5\35\17\2ab\7+\2\2bd\3\2\2\2cY\3\2\2\2"+
		"c\\\3\2\2\2c_\3\2\2\2d\f\3\2\2\2eh\5\17\b\2fh\5\21\t\2ge\3\2\2\2gf\3\2"+
		"\2\2h\16\3\2\2\2ik\5#\22\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2mn\3"+
		"\2\2\2np\5!\21\2oq\5\27\f\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\20"+
		"\3\2\2\2tv\5\27\f\2ut\3\2\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\22\3\2\2"+
		"\2y~\5\25\13\2z~\5\37\20\2{~\5\r\7\2|~\5!\21\2}y\3\2\2\2}z\3\2\2\2}{\3"+
		"\2\2\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\u0082\b\n\2\2\u0082\24\3\2\2\2\u0083\u0087\5\31\r\2\u0084"+
		"\u0086\5\31\r\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3"+
		"\2\2\2\u0087\u0088\3\2\2\2\u0088\26\3\2\2\2\u0089\u0087\3\2\2\2\u008a"+
		"\u008b\4\62;\2\u008b\30\3\2\2\2\u008c\u0090\5\33\16\2\u008d\u0090\5\35"+
		"\17\2\u008e\u0090\t\2\2\2\u008f\u008c\3\2\2\2\u008f\u008d\3\2\2\2\u008f"+
		"\u008e\3\2\2\2\u0090\32\3\2\2\2\u0091\u0092\4C\\\2\u0092\34\3\2\2\2\u0093"+
		"\u0094\4c|\2\u0094\36\3\2\2\2\u0095\u0096\t\3\2\2\u0096 \3\2\2\2\u0097"+
		"\u0098\t\4\2\2\u0098\"\3\2\2\2\u0099\u009b\t\5\2\2\u009a\u0099\3\2\2\2"+
		"\u009b\u009c\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e"+
		"\3\2\2\2\u009e\u009f\b\22\2\2\u009f$\3\2\2\2\21\2\'-=Wcglrw}\177\u0087"+
		"\u008f\u009c\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}