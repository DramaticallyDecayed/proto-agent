// Generated from D:/code/IdeaProjects/ProtoAgent/union/src/main/resources/antlr_grammar_rules\StartFormat3Type.g4 by ANTLR 4.7
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
public class StartFormat3TypeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, FORMAT_NUM=6, COORDINATE=7, NAME=8, 
		DATA=9, SIGNED_NUMBER=10, NUMBER=11, WHITESPACE=12;
	public static final int
		RULE_start_format3_type = 0, RULE_type = 1, RULE_not_applicable = 2, RULE_change_total = 3, 
		RULE_change = 4, RULE_total = 5, RULE_num_list = 6, RULE_format = 7, RULE_format_num = 8, 
		RULE_formats = 9, RULE_none = 10, RULE_location = 11, RULE_places = 12, 
		RULE_coordinates = 13, RULE_coordinate = 14, RULE_identifier = 15, RULE_data = 16;
	public static final String[] ruleNames = {
		"start_format3_type", "type", "not_applicable", "change_total", "change", 
		"total", "num_list", "format", "format_num", "formats", "none", "location", 
		"places", "coordinates", "coordinate", "identifier", "data"
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

	@Override
	public String getGrammarFileName() { return "StartFormat3Type.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public StartFormat3TypeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Start_format3_typeContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public Start_format3_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_format3_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterStart_format3_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitStart_format3_type(this);
		}
	}

	public final Start_format3_typeContext start_format3_type() throws RecognitionException {
		Start_format3_typeContext _localctx = new Start_format3_typeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start_format3_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__3) | (1L << NAME) | (1L << DATA) | (1L << SIGNED_NUMBER) | (1L << NUMBER))) != 0)) {
				{
				{
				setState(34);
				type();
				}
				}
				setState(39);
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

	public static class TypeContext extends ParserRuleContext {
		public FormatContext format() {
			return getRuleContext(FormatContext.class,0);
		}
		public FormatsContext formats() {
			return getRuleContext(FormatsContext.class,0);
		}
		public DataContext data() {
			return getRuleContext(DataContext.class,0);
		}
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Change_totalContext change_total() {
			return getRuleContext(Change_totalContext.class,0);
		}
		public Num_listContext num_list() {
			return getRuleContext(Num_listContext.class,0);
		}
		public Not_applicableContext not_applicable() {
			return getRuleContext(Not_applicableContext.class,0);
		}
		public NoneContext none() {
			return getRuleContext(NoneContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_type);
		try {
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				format();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				formats();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(42);
				data();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
				location();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(44);
				identifier();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(45);
				change_total();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(46);
				num_list();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(47);
				not_applicable();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(48);
				none();
				}
				break;
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

	public static class Not_applicableContext extends ParserRuleContext {
		public Not_applicableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_applicable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterNot_applicable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitNot_applicable(this);
		}
	}

	public final Not_applicableContext not_applicable() throws RecognitionException {
		Not_applicableContext _localctx = new Not_applicableContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_not_applicable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(T__0);
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

	public static class Change_totalContext extends ParserRuleContext {
		public ChangeContext change() {
			return getRuleContext(ChangeContext.class,0);
		}
		public TotalContext total() {
			return getRuleContext(TotalContext.class,0);
		}
		public Change_totalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_change_total; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterChange_total(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitChange_total(this);
		}
	}

	public final Change_totalContext change_total() throws RecognitionException {
		Change_totalContext _localctx = new Change_totalContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_change_total);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			change();
			setState(54);
			match(T__1);
			setState(55);
			total();
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

	public static class ChangeContext extends ParserRuleContext {
		public TerminalNode SIGNED_NUMBER() { return getToken(StartFormat3TypeParser.SIGNED_NUMBER, 0); }
		public ChangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_change; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterChange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitChange(this);
		}
	}

	public final ChangeContext change() throws RecognitionException {
		ChangeContext _localctx = new ChangeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_change);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(SIGNED_NUMBER);
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

	public static class TotalContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(StartFormat3TypeParser.NUMBER, 0); }
		public TotalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_total; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterTotal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitTotal(this);
		}
	}

	public final TotalContext total() throws RecognitionException {
		TotalContext _localctx = new TotalContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_total);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(NUMBER);
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

	public static class Num_listContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public Num_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_num_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterNum_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitNum_list(this);
		}
	}

	public final Num_listContext num_list() throws RecognitionException {
		Num_listContext _localctx = new Num_listContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_num_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			identifier();
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(62);
				match(T__1);
				setState(63);
				identifier();
				}
				}
				setState(68);
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

	public static class FormatContext extends ParserRuleContext {
		public Format_numContext format_num() {
			return getRuleContext(Format_numContext.class,0);
		}
		public FormatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_format; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterFormat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitFormat(this);
		}
	}

	public final FormatContext format() throws RecognitionException {
		FormatContext _localctx = new FormatContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_format);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__2);
			setState(70);
			format_num();
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

	public static class Format_numContext extends ParserRuleContext {
		public TerminalNode FORMAT_NUM() { return getToken(StartFormat3TypeParser.FORMAT_NUM, 0); }
		public Format_numContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_format_num; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterFormat_num(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitFormat_num(this);
		}
	}

	public final Format_numContext format_num() throws RecognitionException {
		Format_numContext _localctx = new Format_numContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_format_num);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(FORMAT_NUM);
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

	public static class FormatsContext extends ParserRuleContext {
		public List<FormatContext> format() {
			return getRuleContexts(FormatContext.class);
		}
		public FormatContext format(int i) {
			return getRuleContext(FormatContext.class,i);
		}
		public FormatsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formats; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterFormats(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitFormats(this);
		}
	}

	public final FormatsContext formats() throws RecognitionException {
		FormatsContext _localctx = new FormatsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_formats);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			format();
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(75);
				match(T__1);
				setState(76);
				format();
				}
				}
				setState(81);
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

	public static class NoneContext extends ParserRuleContext {
		public NoneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_none; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterNone(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitNone(this);
		}
	}

	public final NoneContext none() throws RecognitionException {
		NoneContext _localctx = new NoneContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_none);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__3);
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

	public static class LocationContext extends ParserRuleContext {
		public PlacesContext places() {
			return getRuleContext(PlacesContext.class,0);
		}
		public CoordinatesContext coordinates() {
			return getRuleContext(CoordinatesContext.class,0);
		}
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitLocation(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_location);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			places();
			setState(85);
			match(T__4);
			setState(86);
			coordinates();
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

	public static class PlacesContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(StartFormat3TypeParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(StartFormat3TypeParser.NAME, i);
		}
		public PlacesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_places; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterPlaces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitPlaces(this);
		}
	}

	public final PlacesContext places() throws RecognitionException {
		PlacesContext _localctx = new PlacesContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_places);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(NAME);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(89);
				match(T__1);
				setState(90);
				match(NAME);
				}
				}
				setState(95);
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

	public static class CoordinatesContext extends ParserRuleContext {
		public List<CoordinateContext> coordinate() {
			return getRuleContexts(CoordinateContext.class);
		}
		public CoordinateContext coordinate(int i) {
			return getRuleContext(CoordinateContext.class,i);
		}
		public CoordinatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coordinates; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterCoordinates(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitCoordinates(this);
		}
	}

	public final CoordinatesContext coordinates() throws RecognitionException {
		CoordinatesContext _localctx = new CoordinatesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_coordinates);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			coordinate();
			setState(97);
			match(T__1);
			setState(98);
			coordinate();
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

	public static class CoordinateContext extends ParserRuleContext {
		public TerminalNode COORDINATE() { return getToken(StartFormat3TypeParser.COORDINATE, 0); }
		public CoordinateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coordinate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterCoordinate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitCoordinate(this);
		}
	}

	public final CoordinateContext coordinate() throws RecognitionException {
		CoordinateContext _localctx = new CoordinateContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_coordinate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(COORDINATE);
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

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(StartFormat3TypeParser.NAME, 0); }
		public TerminalNode NUMBER() { return getToken(StartFormat3TypeParser.NUMBER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_la = _input.LA(1);
			if ( !(_la==NAME || _la==NUMBER) ) {
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

	public static class DataContext extends ParserRuleContext {
		public TerminalNode DATA() { return getToken(StartFormat3TypeParser.DATA, 0); }
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).enterData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3TypeListener ) ((StartFormat3TypeListener)listener).exitData(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_data);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(DATA);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16m\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\3\2\7"+
		"\2&\n\2\f\2\16\2)\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\64\n\3"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\7\bC\n\b\f\b\16\b"+
		"F\13\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\7\13P\n\13\f\13\16\13S\13\13"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\7\16^\n\16\f\16\16\16a\13\16\3"+
		"\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\2\2\23\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"\2\3\4\2\n\n\r\r\2g\2\'\3\2\2\2\4\63\3"+
		"\2\2\2\6\65\3\2\2\2\b\67\3\2\2\2\n;\3\2\2\2\f=\3\2\2\2\16?\3\2\2\2\20"+
		"G\3\2\2\2\22J\3\2\2\2\24L\3\2\2\2\26T\3\2\2\2\30V\3\2\2\2\32Z\3\2\2\2"+
		"\34b\3\2\2\2\36f\3\2\2\2 h\3\2\2\2\"j\3\2\2\2$&\5\4\3\2%$\3\2\2\2&)\3"+
		"\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\3\3\2\2\2)\'\3\2\2\2*\64\5\20\t\2+\64\5"+
		"\24\13\2,\64\5\"\22\2-\64\5\30\r\2.\64\5 \21\2/\64\5\b\5\2\60\64\5\16"+
		"\b\2\61\64\5\6\4\2\62\64\5\26\f\2\63*\3\2\2\2\63+\3\2\2\2\63,\3\2\2\2"+
		"\63-\3\2\2\2\63.\3\2\2\2\63/\3\2\2\2\63\60\3\2\2\2\63\61\3\2\2\2\63\62"+
		"\3\2\2\2\64\5\3\2\2\2\65\66\7\3\2\2\66\7\3\2\2\2\678\5\n\6\289\7\4\2\2"+
		"9:\5\f\7\2:\t\3\2\2\2;<\7\f\2\2<\13\3\2\2\2=>\7\r\2\2>\r\3\2\2\2?D\5 "+
		"\21\2@A\7\4\2\2AC\5 \21\2B@\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\17"+
		"\3\2\2\2FD\3\2\2\2GH\7\5\2\2HI\5\22\n\2I\21\3\2\2\2JK\7\b\2\2K\23\3\2"+
		"\2\2LQ\5\20\t\2MN\7\4\2\2NP\5\20\t\2OM\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3"+
		"\2\2\2R\25\3\2\2\2SQ\3\2\2\2TU\7\6\2\2U\27\3\2\2\2VW\5\32\16\2WX\7\7\2"+
		"\2XY\5\34\17\2Y\31\3\2\2\2Z_\7\n\2\2[\\\7\4\2\2\\^\7\n\2\2][\3\2\2\2^"+
		"a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`\33\3\2\2\2a_\3\2\2\2bc\5\36\20\2cd\7\4"+
		"\2\2de\5\36\20\2e\35\3\2\2\2fg\7\t\2\2g\37\3\2\2\2hi\t\2\2\2i!\3\2\2\2"+
		"jk\7\13\2\2k#\3\2\2\2\7\'\63DQ_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}