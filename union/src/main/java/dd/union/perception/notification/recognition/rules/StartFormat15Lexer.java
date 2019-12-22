// Generated from D:/code/IdeaProjects/ProtoAgent/union/src/main/resources/antlr_grammar_rules\StartFormat15.g4 by ANTLR 4.7
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
public class StartFormat15Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, BULLET_SIGN=16, 
		TEXT=17, WHITESPACE=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "BULLET_SIGN", "DIGIT_BULLET", 
		"SYMBOL_BULLET", "NUMBER", "SIGNED_NUMBER", "UNSIGNED_NUMBER", "TEXT", 
		"WORD", "DIGIT", "SYMBOL_START", "CAPITAL_EN", "LOWERCASE_EN", "SEPARATOR", 
		"SIGN", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'NOTIFICATION OF LAUNCH OF AN ICBM OR SLBM'", "'REMARKS:'", "'END OF'", 
		"'REFERENCE(S):'", "'CONTENT'", "'PLANNED DATE OF LAUNCH:'", "'LAUNCH AREA:'", 
		"'REENTRY VEHICLE IMPACT AREA:'", "'A CIRCLE HAVING A RADIUS OF:'", "'KM; AND'", 
		"'THE CENTER HAVING COORDINATES:'", "'SINGLE LAUNCH/MORE THAN ONE LAUNCH:'", 
		"'TELEMETRY BROADCAST FREQUENCIES (MHZ):'", "'FREQUENCY'", "'MODULATION TYPE'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "BULLET_SIGN", "TEXT", "WHITESPACE"
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


	public StartFormat15Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "StartFormat15.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u01d1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\5\21\u017d\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\5\22\u0189\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u0195\n\23\3\24\3\24\5\24\u0199\n\24\3\25\6\25\u019c\n\25\r\25\16"+
		"\25\u019d\3\25\3\25\6\25\u01a2\n\25\r\25\16\25\u01a3\3\26\6\26\u01a7\n"+
		"\26\r\26\16\26\u01a8\3\27\3\27\3\27\3\27\6\27\u01af\n\27\r\27\16\27\u01b0"+
		"\3\27\3\27\3\30\3\30\7\30\u01b7\n\30\f\30\16\30\u01ba\13\30\3\31\3\31"+
		"\3\32\3\32\3\32\5\32\u01c1\n\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\6\37\u01cc\n\37\r\37\16\37\u01cd\3\37\3\37\2\2 \3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\2%\2"+
		"\'\2)\2+\2-\23/\2\61\2\63\2\65\2\67\29\2;\2=\24\3\2\6\4\2*+\u0412\u0451"+
		"\7\2$$..\60\61<=\u2015\u2015\4\2,-//\5\2\13\f\17\17\"\"\2\u01d5\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2-\3\2\2\2\2"+
		"=\3\2\2\2\3?\3\2\2\2\5i\3\2\2\2\7r\3\2\2\2\ty\3\2\2\2\13\u0087\3\2\2\2"+
		"\r\u008f\3\2\2\2\17\u00a7\3\2\2\2\21\u00b4\3\2\2\2\23\u00d1\3\2\2\2\25"+
		"\u00ee\3\2\2\2\27\u00f6\3\2\2\2\31\u0115\3\2\2\2\33\u0139\3\2\2\2\35\u0160"+
		"\3\2\2\2\37\u016a\3\2\2\2!\u017c\3\2\2\2#\u0188\3\2\2\2%\u0194\3\2\2\2"+
		"\'\u0198\3\2\2\2)\u019b\3\2\2\2+\u01a6\3\2\2\2-\u01ae\3\2\2\2/\u01b4\3"+
		"\2\2\2\61\u01bb\3\2\2\2\63\u01c0\3\2\2\2\65\u01c2\3\2\2\2\67\u01c4\3\2"+
		"\2\29\u01c6\3\2\2\2;\u01c8\3\2\2\2=\u01cb\3\2\2\2?@\7P\2\2@A\7Q\2\2AB"+
		"\7V\2\2BC\7K\2\2CD\7H\2\2DE\7K\2\2EF\7E\2\2FG\7C\2\2GH\7V\2\2HI\7K\2\2"+
		"IJ\7Q\2\2JK\7P\2\2KL\7\"\2\2LM\7Q\2\2MN\7H\2\2NO\7\"\2\2OP\7N\2\2PQ\7"+
		"C\2\2QR\7W\2\2RS\7P\2\2ST\7E\2\2TU\7J\2\2UV\7\"\2\2VW\7Q\2\2WX\7H\2\2"+
		"XY\7\"\2\2YZ\7C\2\2Z[\7P\2\2[\\\7\"\2\2\\]\7K\2\2]^\7E\2\2^_\7D\2\2_`"+
		"\7O\2\2`a\7\"\2\2ab\7Q\2\2bc\7T\2\2cd\7\"\2\2de\7U\2\2ef\7N\2\2fg\7D\2"+
		"\2gh\7O\2\2h\4\3\2\2\2ij\7T\2\2jk\7G\2\2kl\7O\2\2lm\7C\2\2mn\7T\2\2no"+
		"\7M\2\2op\7U\2\2pq\7<\2\2q\6\3\2\2\2rs\7G\2\2st\7P\2\2tu\7F\2\2uv\7\""+
		"\2\2vw\7Q\2\2wx\7H\2\2x\b\3\2\2\2yz\7T\2\2z{\7G\2\2{|\7H\2\2|}\7G\2\2"+
		"}~\7T\2\2~\177\7G\2\2\177\u0080\7P\2\2\u0080\u0081\7E\2\2\u0081\u0082"+
		"\7G\2\2\u0082\u0083\7*\2\2\u0083\u0084\7U\2\2\u0084\u0085\7+\2\2\u0085"+
		"\u0086\7<\2\2\u0086\n\3\2\2\2\u0087\u0088\7E\2\2\u0088\u0089\7Q\2\2\u0089"+
		"\u008a\7P\2\2\u008a\u008b\7V\2\2\u008b\u008c\7G\2\2\u008c\u008d\7P\2\2"+
		"\u008d\u008e\7V\2\2\u008e\f\3\2\2\2\u008f\u0090\7R\2\2\u0090\u0091\7N"+
		"\2\2\u0091\u0092\7C\2\2\u0092\u0093\7P\2\2\u0093\u0094\7P\2\2\u0094\u0095"+
		"\7G\2\2\u0095\u0096\7F\2\2\u0096\u0097\7\"\2\2\u0097\u0098\7F\2\2\u0098"+
		"\u0099\7C\2\2\u0099\u009a\7V\2\2\u009a\u009b\7G\2\2\u009b\u009c\7\"\2"+
		"\2\u009c\u009d\7Q\2\2\u009d\u009e\7H\2\2\u009e\u009f\7\"\2\2\u009f\u00a0"+
		"\7N\2\2\u00a0\u00a1\7C\2\2\u00a1\u00a2\7W\2\2\u00a2\u00a3\7P\2\2\u00a3"+
		"\u00a4\7E\2\2\u00a4\u00a5\7J\2\2\u00a5\u00a6\7<\2\2\u00a6\16\3\2\2\2\u00a7"+
		"\u00a8\7N\2\2\u00a8\u00a9\7C\2\2\u00a9\u00aa\7W\2\2\u00aa\u00ab\7P\2\2"+
		"\u00ab\u00ac\7E\2\2\u00ac\u00ad\7J\2\2\u00ad\u00ae\7\"\2\2\u00ae\u00af"+
		"\7C\2\2\u00af\u00b0\7T\2\2\u00b0\u00b1\7G\2\2\u00b1\u00b2\7C\2\2\u00b2"+
		"\u00b3\7<\2\2\u00b3\20\3\2\2\2\u00b4\u00b5\7T\2\2\u00b5\u00b6\7G\2\2\u00b6"+
		"\u00b7\7G\2\2\u00b7\u00b8\7P\2\2\u00b8\u00b9\7V\2\2\u00b9\u00ba\7T\2\2"+
		"\u00ba\u00bb\7[\2\2\u00bb\u00bc\7\"\2\2\u00bc\u00bd\7X\2\2\u00bd\u00be"+
		"\7G\2\2\u00be\u00bf\7J\2\2\u00bf\u00c0\7K\2\2\u00c0\u00c1\7E\2\2\u00c1"+
		"\u00c2\7N\2\2\u00c2\u00c3\7G\2\2\u00c3\u00c4\7\"\2\2\u00c4\u00c5\7K\2"+
		"\2\u00c5\u00c6\7O\2\2\u00c6\u00c7\7R\2\2\u00c7\u00c8\7C\2\2\u00c8\u00c9"+
		"\7E\2\2\u00c9\u00ca\7V\2\2\u00ca\u00cb\7\"\2\2\u00cb\u00cc\7C\2\2\u00cc"+
		"\u00cd\7T\2\2\u00cd\u00ce\7G\2\2\u00ce\u00cf\7C\2\2\u00cf\u00d0\7<\2\2"+
		"\u00d0\22\3\2\2\2\u00d1\u00d2\7C\2\2\u00d2\u00d3\7\"\2\2\u00d3\u00d4\7"+
		"E\2\2\u00d4\u00d5\7K\2\2\u00d5\u00d6\7T\2\2\u00d6\u00d7\7E\2\2\u00d7\u00d8"+
		"\7N\2\2\u00d8\u00d9\7G\2\2\u00d9\u00da\7\"\2\2\u00da\u00db\7J\2\2\u00db"+
		"\u00dc\7C\2\2\u00dc\u00dd\7X\2\2\u00dd\u00de\7K\2\2\u00de\u00df\7P\2\2"+
		"\u00df\u00e0\7I\2\2\u00e0\u00e1\7\"\2\2\u00e1\u00e2\7C\2\2\u00e2\u00e3"+
		"\7\"\2\2\u00e3\u00e4\7T\2\2\u00e4\u00e5\7C\2\2\u00e5\u00e6\7F\2\2\u00e6"+
		"\u00e7\7K\2\2\u00e7\u00e8\7W\2\2\u00e8\u00e9\7U\2\2\u00e9\u00ea\7\"\2"+
		"\2\u00ea\u00eb\7Q\2\2\u00eb\u00ec\7H\2\2\u00ec\u00ed\7<\2\2\u00ed\24\3"+
		"\2\2\2\u00ee\u00ef\7M\2\2\u00ef\u00f0\7O\2\2\u00f0\u00f1\7=\2\2\u00f1"+
		"\u00f2\7\"\2\2\u00f2\u00f3\7C\2\2\u00f3\u00f4\7P\2\2\u00f4\u00f5\7F\2"+
		"\2\u00f5\26\3\2\2\2\u00f6\u00f7\7V\2\2\u00f7\u00f8\7J\2\2\u00f8\u00f9"+
		"\7G\2\2\u00f9\u00fa\7\"\2\2\u00fa\u00fb\7E\2\2\u00fb\u00fc\7G\2\2\u00fc"+
		"\u00fd\7P\2\2\u00fd\u00fe\7V\2\2\u00fe\u00ff\7G\2\2\u00ff\u0100\7T\2\2"+
		"\u0100\u0101\7\"\2\2\u0101\u0102\7J\2\2\u0102\u0103\7C\2\2\u0103\u0104"+
		"\7X\2\2\u0104\u0105\7K\2\2\u0105\u0106\7P\2\2\u0106\u0107\7I\2\2\u0107"+
		"\u0108\7\"\2\2\u0108\u0109\7E\2\2\u0109\u010a\7Q\2\2\u010a\u010b\7Q\2"+
		"\2\u010b\u010c\7T\2\2\u010c\u010d\7F\2\2\u010d\u010e\7K\2\2\u010e\u010f"+
		"\7P\2\2\u010f\u0110\7C\2\2\u0110\u0111\7V\2\2\u0111\u0112\7G\2\2\u0112"+
		"\u0113\7U\2\2\u0113\u0114\7<\2\2\u0114\30\3\2\2\2\u0115\u0116\7U\2\2\u0116"+
		"\u0117\7K\2\2\u0117\u0118\7P\2\2\u0118\u0119\7I\2\2\u0119\u011a\7N\2\2"+
		"\u011a\u011b\7G\2\2\u011b\u011c\7\"\2\2\u011c\u011d\7N\2\2\u011d\u011e"+
		"\7C\2\2\u011e\u011f\7W\2\2\u011f\u0120\7P\2\2\u0120\u0121\7E\2\2\u0121"+
		"\u0122\7J\2\2\u0122\u0123\7\61\2\2\u0123\u0124\7O\2\2\u0124\u0125\7Q\2"+
		"\2\u0125\u0126\7T\2\2\u0126\u0127\7G\2\2\u0127\u0128\7\"\2\2\u0128\u0129"+
		"\7V\2\2\u0129\u012a\7J\2\2\u012a\u012b\7C\2\2\u012b\u012c\7P\2\2\u012c"+
		"\u012d\7\"\2\2\u012d\u012e\7Q\2\2\u012e\u012f\7P\2\2\u012f\u0130\7G\2"+
		"\2\u0130\u0131\7\"\2\2\u0131\u0132\7N\2\2\u0132\u0133\7C\2\2\u0133\u0134"+
		"\7W\2\2\u0134\u0135\7P\2\2\u0135\u0136\7E\2\2\u0136\u0137\7J\2\2\u0137"+
		"\u0138\7<\2\2\u0138\32\3\2\2\2\u0139\u013a\7V\2\2\u013a\u013b\7G\2\2\u013b"+
		"\u013c\7N\2\2\u013c\u013d\7G\2\2\u013d\u013e\7O\2\2\u013e\u013f\7G\2\2"+
		"\u013f\u0140\7V\2\2\u0140\u0141\7T\2\2\u0141\u0142\7[\2\2\u0142\u0143"+
		"\7\"\2\2\u0143\u0144\7D\2\2\u0144\u0145\7T\2\2\u0145\u0146\7Q\2\2\u0146"+
		"\u0147\7C\2\2\u0147\u0148\7F\2\2\u0148\u0149\7E\2\2\u0149\u014a\7C\2\2"+
		"\u014a\u014b\7U\2\2\u014b\u014c\7V\2\2\u014c\u014d\7\"\2\2\u014d\u014e"+
		"\7H\2\2\u014e\u014f\7T\2\2\u014f\u0150\7G\2\2\u0150\u0151\7S\2\2\u0151"+
		"\u0152\7W\2\2\u0152\u0153\7G\2\2\u0153\u0154\7P\2\2\u0154\u0155\7E\2\2"+
		"\u0155\u0156\7K\2\2\u0156\u0157\7G\2\2\u0157\u0158\7U\2\2\u0158\u0159"+
		"\7\"\2\2\u0159\u015a\7*\2\2\u015a\u015b\7O\2\2\u015b\u015c\7J\2\2\u015c"+
		"\u015d\7\\\2\2\u015d\u015e\7+\2\2\u015e\u015f\7<\2\2\u015f\34\3\2\2\2"+
		"\u0160\u0161\7H\2\2\u0161\u0162\7T\2\2\u0162\u0163\7G\2\2\u0163\u0164"+
		"\7S\2\2\u0164\u0165\7W\2\2\u0165\u0166\7G\2\2\u0166\u0167\7P\2\2\u0167"+
		"\u0168\7E\2\2\u0168\u0169\7[\2\2\u0169\36\3\2\2\2\u016a\u016b\7O\2\2\u016b"+
		"\u016c\7Q\2\2\u016c\u016d\7F\2\2\u016d\u016e\7W\2\2\u016e\u016f\7N\2\2"+
		"\u016f\u0170\7C\2\2\u0170\u0171\7V\2\2\u0171\u0172\7K\2\2\u0172\u0173"+
		"\7Q\2\2\u0173\u0174\7P\2\2\u0174\u0175\7\"\2\2\u0175\u0176\7V\2\2\u0176"+
		"\u0177\7[\2\2\u0177\u0178\7R\2\2\u0178\u0179\7G\2\2\u0179 \3\2\2\2\u017a"+
		"\u017d\5#\22\2\u017b\u017d\5%\23\2\u017c\u017a\3\2\2\2\u017c\u017b\3\2"+
		"\2\2\u017d\"\3\2\2\2\u017e\u017f\5+\26\2\u017f\u0180\7\60\2\2\u0180\u0189"+
		"\3\2\2\2\u0181\u0182\5+\26\2\u0182\u0183\7+\2\2\u0183\u0189\3\2\2\2\u0184"+
		"\u0185\7*\2\2\u0185\u0186\5+\26\2\u0186\u0187\7+\2\2\u0187\u0189\3\2\2"+
		"\2\u0188\u017e\3\2\2\2\u0188\u0181\3\2\2\2\u0188\u0184\3\2\2\2\u0189$"+
		"\3\2\2\2\u018a\u018b\5\65\33\2\u018b\u018c\7\60\2\2\u018c\u0195\3\2\2"+
		"\2\u018d\u018e\5\65\33\2\u018e\u018f\7+\2\2\u018f\u0195\3\2\2\2\u0190"+
		"\u0191\7*\2\2\u0191\u0192\5\67\34\2\u0192\u0193\7+\2\2\u0193\u0195\3\2"+
		"\2\2\u0194\u018a\3\2\2\2\u0194\u018d\3\2\2\2\u0194\u0190\3\2\2\2\u0195"+
		"&\3\2\2\2\u0196\u0199\5)\25\2\u0197\u0199\5+\26\2\u0198\u0196\3\2\2\2"+
		"\u0198\u0197\3\2\2\2\u0199(\3\2\2\2\u019a\u019c\5=\37\2\u019b\u019a\3"+
		"\2\2\2\u019c\u019d\3\2\2\2\u019d\u019b\3\2\2\2\u019d\u019e\3\2\2\2\u019e"+
		"\u019f\3\2\2\2\u019f\u01a1\5;\36\2\u01a0\u01a2\5\61\31\2\u01a1\u01a0\3"+
		"\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4"+
		"*\3\2\2\2\u01a5\u01a7\5\61\31\2\u01a6\u01a5\3\2\2\2\u01a7\u01a8\3\2\2"+
		"\2\u01a8\u01a6\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9,\3\2\2\2\u01aa\u01af"+
		"\5/\30\2\u01ab\u01af\59\35\2\u01ac\u01af\5\'\24\2\u01ad\u01af\5;\36\2"+
		"\u01ae\u01aa\3\2\2\2\u01ae\u01ab\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01ad"+
		"\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1"+
		"\u01b2\3\2\2\2\u01b2\u01b3\b\27\2\2\u01b3.\3\2\2\2\u01b4\u01b8\5\63\32"+
		"\2\u01b5\u01b7\5\63\32\2\u01b6\u01b5\3\2\2\2\u01b7\u01ba\3\2\2\2\u01b8"+
		"\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\60\3\2\2\2\u01ba\u01b8\3\2\2"+
		"\2\u01bb\u01bc\4\62;\2\u01bc\62\3\2\2\2\u01bd\u01c1\5\65\33\2\u01be\u01c1"+
		"\5\67\34\2\u01bf\u01c1\t\2\2\2\u01c0\u01bd\3\2\2\2\u01c0\u01be\3\2\2\2"+
		"\u01c0\u01bf\3\2\2\2\u01c1\64\3\2\2\2\u01c2\u01c3\4C\\\2\u01c3\66\3\2"+
		"\2\2\u01c4\u01c5\4c|\2\u01c58\3\2\2\2\u01c6\u01c7\t\3\2\2\u01c7:\3\2\2"+
		"\2\u01c8\u01c9\t\4\2\2\u01c9<\3\2\2\2\u01ca\u01cc\t\5\2\2\u01cb\u01ca"+
		"\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd\u01cb\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce"+
		"\u01cf\3\2\2\2\u01cf\u01d0\b\37\2\2\u01d0>\3\2\2\2\17\2\u017c\u0188\u0194"+
		"\u0198\u019d\u01a3\u01a8\u01ae\u01b0\u01b8\u01c0\u01cd\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}