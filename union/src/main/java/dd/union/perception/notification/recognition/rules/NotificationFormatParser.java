// Generated from D:/code/IdeaProjects/ProtoAgent/union/src/main/resources/antlr_grammar_rules\NotificationFormat.g4 by ANTLR 4.7
package dd.union.perception.notification.recognition.rules;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NotificationFormatParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BULLET_SIGN=1, DEPLOYED_BULLET_SIGN=2, NON_DEPLOYED_BULLET_SIGN=3, TEXT=4, 
		WHITESPACE=5;
	public static final int
		RULE_notification = 0, RULE_ident_item = 1, RULE_bullet = 2, RULE_deployed_bullet = 3, 
		RULE_non_deployed_bullet = 4, RULE_text = 5;
	public static final String[] ruleNames = {
		"notification", "ident_item", "bullet", "deployed_bullet", "non_deployed_bullet", 
		"text"
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

	@Override
	public String getGrammarFileName() { return "NotificationFormat.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NotificationFormatParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class NotificationContext extends ParserRuleContext {
		public Ident_itemContext ident_item() {
			return getRuleContext(Ident_itemContext.class,0);
		}
		public NotificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).enterNotification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).exitNotification(this);
		}
	}

	public final NotificationContext notification() throws RecognitionException {
		NotificationContext _localctx = new NotificationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_notification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			ident_item();
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

	public static class Ident_itemContext extends ParserRuleContext {
		public List<BulletContext> bullet() {
			return getRuleContexts(BulletContext.class);
		}
		public BulletContext bullet(int i) {
			return getRuleContext(BulletContext.class,i);
		}
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Ident_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).enterIdent_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).exitIdent_item(this);
		}
	}

	public final Ident_itemContext ident_item() throws RecognitionException {
		Ident_itemContext _localctx = new Ident_itemContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ident_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			bullet();
			setState(15);
			text();
			setState(16);
			bullet();
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

	public static class BulletContext extends ParserRuleContext {
		public TerminalNode BULLET_SIGN() { return getToken(NotificationFormatParser.BULLET_SIGN, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(NotificationFormatParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(NotificationFormatParser.WHITESPACE, i);
		}
		public BulletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bullet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).enterBullet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).exitBullet(this);
		}
	}

	public final BulletContext bullet() throws RecognitionException {
		BulletContext _localctx = new BulletContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bullet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(18);
				match(WHITESPACE);
				}
				}
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(24);
			match(BULLET_SIGN);
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

	public static class Deployed_bulletContext extends ParserRuleContext {
		public TerminalNode DEPLOYED_BULLET_SIGN() { return getToken(NotificationFormatParser.DEPLOYED_BULLET_SIGN, 0); }
		public Deployed_bulletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deployed_bullet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).enterDeployed_bullet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).exitDeployed_bullet(this);
		}
	}

	public final Deployed_bulletContext deployed_bullet() throws RecognitionException {
		Deployed_bulletContext _localctx = new Deployed_bulletContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_deployed_bullet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(DEPLOYED_BULLET_SIGN);
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

	public static class Non_deployed_bulletContext extends ParserRuleContext {
		public TerminalNode NON_DEPLOYED_BULLET_SIGN() { return getToken(NotificationFormatParser.NON_DEPLOYED_BULLET_SIGN, 0); }
		public Non_deployed_bulletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_deployed_bullet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).enterNon_deployed_bullet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).exitNon_deployed_bullet(this);
		}
	}

	public final Non_deployed_bulletContext non_deployed_bullet() throws RecognitionException {
		Non_deployed_bulletContext _localctx = new Non_deployed_bulletContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_non_deployed_bullet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(NON_DEPLOYED_BULLET_SIGN);
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

	public static class TextContext extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(NotificationFormatParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(NotificationFormatParser.TEXT, i);
		}
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).enterText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotificationFormatListener ) ((NotificationFormatListener)listener).exitText(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_text);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TEXT) {
				{
				{
				setState(30);
				match(TEXT);
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\7\'\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\3\3\3\3\3\3\3\3\4\7\4\26"+
		"\n\4\f\4\16\4\31\13\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\7\7\"\n\7\f\7\16\7%"+
		"\13\7\3\7\2\2\b\2\4\6\b\n\f\2\2\2\"\2\16\3\2\2\2\4\20\3\2\2\2\6\27\3\2"+
		"\2\2\b\34\3\2\2\2\n\36\3\2\2\2\f#\3\2\2\2\16\17\5\4\3\2\17\3\3\2\2\2\20"+
		"\21\5\6\4\2\21\22\5\f\7\2\22\23\5\6\4\2\23\5\3\2\2\2\24\26\7\7\2\2\25"+
		"\24\3\2\2\2\26\31\3\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2\30\32\3\2\2\2\31"+
		"\27\3\2\2\2\32\33\7\3\2\2\33\7\3\2\2\2\34\35\7\4\2\2\35\t\3\2\2\2\36\37"+
		"\7\5\2\2\37\13\3\2\2\2 \"\7\6\2\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2"+
		"\2\2$\r\3\2\2\2%#\3\2\2\2\4\27#";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}