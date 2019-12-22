// Generated from D:/code/IdeaProjects/ProtoAgent/union/src/main/resources/antlr_grammar_rules\StartFormat3Type.g4 by ANTLR 4.7
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
public class StartFormat3TypeLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, FORMAT_NUM=6, COORDINATE=7, NAME=8, 
		DATA=9, SIGNED_NUMBER=10, NUMBER=11, WHITESPACE=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "FORMAT_NUM", "COORDINATE", "NAME", 
		"DATA", "SIGNED_NUMBER", "NUMBER", "DIGIT", "LETTER", "SEPARATOR", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'NOT APPLICABLE'", "','", "'ANC/SOA'", "'NONE'", "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "FORMAT_NUM", "COORDINATE", "NAME", 
		"DATA", "SIGNED_NUMBER", "NUMBER", "WHITESPACE"
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


	public StartFormat3TypeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "StartFormat3Type.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16\u0083\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bT\n\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\6\t^\n\t\r\t\16\t_\3\n\3\n\3\n\6\ne\n\n\r\n\16\nf\3\n"+
		"\3\n\3\n\3\13\3\13\6\13n\n\13\r\13\16\13o\3\f\6\fs\n\f\r\f\16\ft\3\r\3"+
		"\r\3\16\3\16\3\17\3\17\3\20\6\20~\n\20\r\20\16\20\177\3\20\3\20\2\2\21"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\2\33\2\35\2\37"+
		"\16\3\2\4\4\2--//\5\2\13\f\17\17\"\"\2\u008a\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5"+
		"\60\3\2\2\2\7\62\3\2\2\2\t:\3\2\2\2\13?\3\2\2\2\rA\3\2\2\2\17S\3\2\2\2"+
		"\21U\3\2\2\2\23a\3\2\2\2\25k\3\2\2\2\27r\3\2\2\2\31v\3\2\2\2\33x\3\2\2"+
		"\2\35z\3\2\2\2\37}\3\2\2\2!\"\7P\2\2\"#\7Q\2\2#$\7V\2\2$%\7\"\2\2%&\7"+
		"C\2\2&\'\7R\2\2\'(\7R\2\2()\7N\2\2)*\7K\2\2*+\7E\2\2+,\7C\2\2,-\7D\2\2"+
		"-.\7N\2\2./\7G\2\2/\4\3\2\2\2\60\61\7.\2\2\61\6\3\2\2\2\62\63\7C\2\2\63"+
		"\64\7P\2\2\64\65\7E\2\2\65\66\7\61\2\2\66\67\7U\2\2\678\7Q\2\289\7C\2"+
		"\29\b\3\2\2\2:;\7P\2\2;<\7Q\2\2<=\7P\2\2=>\7G\2\2>\n\3\2\2\2?@\7\61\2"+
		"\2@\f\3\2\2\2AB\5\27\f\2BC\7/\2\2CD\5\27\f\2DE\7\61\2\2EF\5\27\f\2F\16"+
		"\3\2\2\2GH\5\27\f\2HI\7/\2\2IJ\5\27\f\2JK\7/\2\2KL\5\27\f\2LM\5\33\16"+
		"\2MT\3\2\2\2NO\5\27\f\2OP\7/\2\2PQ\5\27\f\2QR\5\33\16\2RT\3\2\2\2SG\3"+
		"\2\2\2SN\3\2\2\2T\20\3\2\2\2U]\5\33\16\2V^\5\33\16\2W^\5\27\f\2X^\7/\2"+
		"\2Y^\5\37\20\2Z[\7.\2\2[^\7\"\2\2\\^\7\60\2\2]V\3\2\2\2]W\3\2\2\2]X\3"+
		"\2\2\2]Y\3\2\2\2]Z\3\2\2\2]\\\3\2\2\2^_\3\2\2\2_]\3\2\2\2_`\3\2\2\2`\22"+
		"\3\2\2\2ab\5\27\f\2bd\7/\2\2ce\5\33\16\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2"+
		"fg\3\2\2\2gh\3\2\2\2hi\7/\2\2ij\5\27\f\2j\24\3\2\2\2km\t\2\2\2ln\5\31"+
		"\r\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2p\26\3\2\2\2qs\5\31\r\2rq"+
		"\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\30\3\2\2\2vw\4\62;\2w\32\3\2\2"+
		"\2xy\4C\\\2y\34\3\2\2\2z{\4.\61\2{\36\3\2\2\2|~\t\3\2\2}|\3\2\2\2~\177"+
		"\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082"+
		"\b\20\2\2\u0082 \3\2\2\2\n\2S]_fot\177\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}