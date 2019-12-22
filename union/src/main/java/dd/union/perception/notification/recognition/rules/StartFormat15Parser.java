// Generated from D:/code/IdeaProjects/ProtoAgent/union/src/main/resources/antlr_grammar_rules\StartFormat15.g4 by ANTLR 4.7
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
public class StartFormat15Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, BULLET_SIGN=16, 
		TEXT=17, WHITESPACE=18;
	public static final int
		RULE_start_format15 = 0, RULE_header = 1, RULE_item = 2, RULE_main_item_element = 3, 
		RULE_ident_item = 4, RULE_reference_item = 5, RULE_content_items = 6, 
		RULE_remarks_item = 7, RULE_end_item = 8, RULE_reference = 9, RULE_ident_reference = 10, 
		RULE_content = 11, RULE_content_item = 12, RULE_planned_date = 13, RULE_launch_area = 14, 
		RULE_impact_area = 15, RULE_impact_area_reentry = 16, RULE_impact_area_items = 17, 
		RULE_circle = 18, RULE_circle_radius = 19, RULE_circle_center = 20, RULE_convex_polyhedron = 21, 
		RULE_single_or_multiple = 22, RULE_frequences = 23, RULE_table_line = 24, 
		RULE_bullet = 25, RULE_text = 26;
	public static final String[] ruleNames = {
		"start_format15", "header", "item", "main_item_element", "ident_item", 
		"reference_item", "content_items", "remarks_item", "end_item", "reference", 
		"ident_reference", "content", "content_item", "planned_date", "launch_area", 
		"impact_area", "impact_area_reentry", "impact_area_items", "circle", "circle_radius", 
		"circle_center", "convex_polyhedron", "single_or_multiple", "frequences", 
		"table_line", "bullet", "text"
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

	@Override
	public String getGrammarFileName() { return "StartFormat15.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public StartFormat15Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Start_format15Context extends ParserRuleContext {
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public ItemContext item() {
			return getRuleContext(ItemContext.class,0);
		}
		public Start_format15Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_format15; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterStart_format15(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitStart_format15(this);
		}
	}

	public final Start_format15Context start_format15() throws RecognitionException {
		Start_format15Context _localctx = new Start_format15Context(_ctx, getState());
		enterRule(_localctx, 0, RULE_start_format15);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			header();
			setState(55);
			item();
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

	public static class HeaderContext extends ParserRuleContext {
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
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

	public static class ItemContext extends ParserRuleContext {
		public List<BulletContext> bullet() {
			return getRuleContexts(BulletContext.class);
		}
		public BulletContext bullet(int i) {
			return getRuleContext(BulletContext.class,i);
		}
		public List<Main_item_elementContext> main_item_element() {
			return getRuleContexts(Main_item_elementContext.class);
		}
		public Main_item_elementContext main_item_element(int i) {
			return getRuleContext(Main_item_elementContext.class,i);
		}
		public ItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitItem(this);
		}
	}

	public final ItemContext item() throws RecognitionException {
		ItemContext _localctx = new ItemContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_item);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(59);
				bullet();
				setState(60);
				main_item_element();
				}
				}
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BULLET_SIGN || _la==WHITESPACE );
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

	public static class Main_item_elementContext extends ParserRuleContext {
		public Ident_itemContext ident_item() {
			return getRuleContext(Ident_itemContext.class,0);
		}
		public Reference_itemContext reference_item() {
			return getRuleContext(Reference_itemContext.class,0);
		}
		public Content_itemsContext content_items() {
			return getRuleContext(Content_itemsContext.class,0);
		}
		public Remarks_itemContext remarks_item() {
			return getRuleContext(Remarks_itemContext.class,0);
		}
		public End_itemContext end_item() {
			return getRuleContext(End_itemContext.class,0);
		}
		public Main_item_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main_item_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterMain_item_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitMain_item_element(this);
		}
	}

	public final Main_item_elementContext main_item_element() throws RecognitionException {
		Main_item_elementContext _localctx = new Main_item_elementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_main_item_element);
		try {
			setState(71);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
			case BULLET_SIGN:
			case TEXT:
			case WHITESPACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				ident_item();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				reference_item();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				content_items();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				remarks_item();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				end_item();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Ident_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterIdent_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitIdent_item(this);
		}
	}

	public final Ident_itemContext ident_item() throws RecognitionException {
		Ident_itemContext _localctx = new Ident_itemContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ident_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			text();
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

	public static class Reference_itemContext extends ParserRuleContext {
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public Reference_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterReference_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitReference_item(this);
		}
	}

	public final Reference_itemContext reference_item() throws RecognitionException {
		Reference_itemContext _localctx = new Reference_itemContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_reference_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			reference();
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

	public static class Content_itemsContext extends ParserRuleContext {
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public Content_itemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content_items; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterContent_items(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitContent_items(this);
		}
	}

	public final Content_itemsContext content_items() throws RecognitionException {
		Content_itemsContext _localctx = new Content_itemsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_content_items);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			content();
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

	public static class Remarks_itemContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Remarks_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_remarks_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterRemarks_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitRemarks_item(this);
		}
	}

	public final Remarks_itemContext remarks_item() throws RecognitionException {
		Remarks_itemContext _localctx = new Remarks_itemContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_remarks_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__1);
			{
			setState(80);
			text();
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

	public static class End_itemContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public End_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterEnd_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitEnd_item(this);
		}
	}

	public final End_itemContext end_item() throws RecognitionException {
		End_itemContext _localctx = new End_itemContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_end_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__2);
			setState(83);
			text();
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

	public static class ReferenceContext extends ParserRuleContext {
		public Ident_referenceContext ident_reference() {
			return getRuleContext(Ident_referenceContext.class,0);
		}
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(T__3);
			setState(86);
			ident_reference();
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

	public static class Ident_referenceContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Ident_referenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterIdent_reference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitIdent_reference(this);
		}
	}

	public final Ident_referenceContext ident_reference() throws RecognitionException {
		Ident_referenceContext _localctx = new Ident_referenceContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ident_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			text();
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

	public static class ContentContext extends ParserRuleContext {
		public List<BulletContext> bullet() {
			return getRuleContexts(BulletContext.class);
		}
		public BulletContext bullet(int i) {
			return getRuleContext(BulletContext.class,i);
		}
		public List<Content_itemContext> content_item() {
			return getRuleContexts(Content_itemContext.class);
		}
		public Content_itemContext content_item(int i) {
			return getRuleContext(Content_itemContext.class,i);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitContent(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_content);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(T__4);
			setState(94); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(91);
					bullet();
					setState(92);
					content_item();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(96); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class Content_itemContext extends ParserRuleContext {
		public Planned_dateContext planned_date() {
			return getRuleContext(Planned_dateContext.class,0);
		}
		public Launch_areaContext launch_area() {
			return getRuleContext(Launch_areaContext.class,0);
		}
		public Impact_areaContext impact_area() {
			return getRuleContext(Impact_areaContext.class,0);
		}
		public Single_or_multipleContext single_or_multiple() {
			return getRuleContext(Single_or_multipleContext.class,0);
		}
		public FrequencesContext frequences() {
			return getRuleContext(FrequencesContext.class,0);
		}
		public Content_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterContent_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitContent_item(this);
		}
	}

	public final Content_itemContext content_item() throws RecognitionException {
		Content_itemContext _localctx = new Content_itemContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_content_item);
		try {
			setState(103);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				planned_date();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				launch_area();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(100);
				impact_area();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(101);
				single_or_multiple();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(102);
				frequences();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Planned_dateContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Planned_dateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_planned_date; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterPlanned_date(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitPlanned_date(this);
		}
	}

	public final Planned_dateContext planned_date() throws RecognitionException {
		Planned_dateContext _localctx = new Planned_dateContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_planned_date);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__5);
			setState(106);
			text();
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

	public static class Launch_areaContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Launch_areaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_launch_area; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterLaunch_area(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitLaunch_area(this);
		}
	}

	public final Launch_areaContext launch_area() throws RecognitionException {
		Launch_areaContext _localctx = new Launch_areaContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_launch_area);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__6);
			setState(109);
			text();
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

	public static class Impact_areaContext extends ParserRuleContext {
		public Impact_area_reentryContext impact_area_reentry() {
			return getRuleContext(Impact_area_reentryContext.class,0);
		}
		public List<BulletContext> bullet() {
			return getRuleContexts(BulletContext.class);
		}
		public BulletContext bullet(int i) {
			return getRuleContext(BulletContext.class,i);
		}
		public List<Impact_area_itemsContext> impact_area_items() {
			return getRuleContexts(Impact_area_itemsContext.class);
		}
		public Impact_area_itemsContext impact_area_items(int i) {
			return getRuleContext(Impact_area_itemsContext.class,i);
		}
		public Impact_areaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_impact_area; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterImpact_area(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitImpact_area(this);
		}
	}

	public final Impact_areaContext impact_area() throws RecognitionException {
		Impact_areaContext _localctx = new Impact_areaContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_impact_area);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__7);
			setState(112);
			impact_area_reentry();
			setState(118);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(113);
					bullet();
					setState(114);
					impact_area_items();
					}
					} 
				}
				setState(120);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class Impact_area_reentryContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Impact_area_reentryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_impact_area_reentry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterImpact_area_reentry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitImpact_area_reentry(this);
		}
	}

	public final Impact_area_reentryContext impact_area_reentry() throws RecognitionException {
		Impact_area_reentryContext _localctx = new Impact_area_reentryContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_impact_area_reentry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			text();
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

	public static class Impact_area_itemsContext extends ParserRuleContext {
		public CircleContext circle() {
			return getRuleContext(CircleContext.class,0);
		}
		public Convex_polyhedronContext convex_polyhedron() {
			return getRuleContext(Convex_polyhedronContext.class,0);
		}
		public Impact_area_itemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_impact_area_items; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterImpact_area_items(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitImpact_area_items(this);
		}
	}

	public final Impact_area_itemsContext impact_area_items() throws RecognitionException {
		Impact_area_itemsContext _localctx = new Impact_area_itemsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_impact_area_items);
		try {
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				circle();
				}
				break;
			case EOF:
			case BULLET_SIGN:
			case TEXT:
			case WHITESPACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				convex_polyhedron();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class CircleContext extends ParserRuleContext {
		public Circle_radiusContext circle_radius() {
			return getRuleContext(Circle_radiusContext.class,0);
		}
		public Circle_centerContext circle_center() {
			return getRuleContext(Circle_centerContext.class,0);
		}
		public CircleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_circle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterCircle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitCircle(this);
		}
	}

	public final CircleContext circle() throws RecognitionException {
		CircleContext _localctx = new CircleContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_circle);
		try {
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				circle_radius();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				circle_center();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Circle_radiusContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Circle_radiusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_circle_radius; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterCircle_radius(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitCircle_radius(this);
		}
	}

	public final Circle_radiusContext circle_radius() throws RecognitionException {
		Circle_radiusContext _localctx = new Circle_radiusContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_circle_radius);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__8);
			setState(132);
			text();
			setState(133);
			match(T__9);
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

	public static class Circle_centerContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Circle_centerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_circle_center; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterCircle_center(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitCircle_center(this);
		}
	}

	public final Circle_centerContext circle_center() throws RecognitionException {
		Circle_centerContext _localctx = new Circle_centerContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_circle_center);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(T__10);
			setState(136);
			text();
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

	public static class Convex_polyhedronContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Convex_polyhedronContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_convex_polyhedron; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterConvex_polyhedron(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitConvex_polyhedron(this);
		}
	}

	public final Convex_polyhedronContext convex_polyhedron() throws RecognitionException {
		Convex_polyhedronContext _localctx = new Convex_polyhedronContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_convex_polyhedron);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			text();
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

	public static class Single_or_multipleContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Single_or_multipleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_or_multiple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterSingle_or_multiple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitSingle_or_multiple(this);
		}
	}

	public final Single_or_multipleContext single_or_multiple() throws RecognitionException {
		Single_or_multipleContext _localctx = new Single_or_multipleContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_single_or_multiple);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(T__11);
			setState(141);
			text();
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

	public static class FrequencesContext extends ParserRuleContext {
		public List<BulletContext> bullet() {
			return getRuleContexts(BulletContext.class);
		}
		public BulletContext bullet(int i) {
			return getRuleContext(BulletContext.class,i);
		}
		public List<Table_lineContext> table_line() {
			return getRuleContexts(Table_lineContext.class);
		}
		public Table_lineContext table_line(int i) {
			return getRuleContext(Table_lineContext.class,i);
		}
		public FrequencesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_frequences; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterFrequences(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitFrequences(this);
		}
	}

	public final FrequencesContext frequences() throws RecognitionException {
		FrequencesContext _localctx = new FrequencesContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_frequences);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__12);
			setState(144);
			match(T__13);
			setState(145);
			match(T__14);
			setState(151);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(146);
					bullet();
					setState(147);
					table_line();
					}
					} 
				}
				setState(153);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class Table_lineContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Table_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterTable_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitTable_line(this);
		}
	}

	public final Table_lineContext table_line() throws RecognitionException {
		Table_lineContext _localctx = new Table_lineContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_table_line);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			text();
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
		public TerminalNode BULLET_SIGN() { return getToken(StartFormat15Parser.BULLET_SIGN, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(StartFormat15Parser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(StartFormat15Parser.WHITESPACE, i);
		}
		public BulletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bullet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterBullet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitBullet(this);
		}
	}

	public final BulletContext bullet() throws RecognitionException {
		BulletContext _localctx = new BulletContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_bullet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(156);
				match(WHITESPACE);
				}
				}
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(162);
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

	public static class TextContext extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(StartFormat15Parser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(StartFormat15Parser.TEXT, i);
		}
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).enterText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat15Listener ) ((StartFormat15Listener)listener).exitText(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_text);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TEXT) {
				{
				{
				setState(164);
				match(TEXT);
				}
				}
				setState(169);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24\u00ad\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\6\4A\n"+
		"\4\r\4\16\4B\3\5\3\5\3\5\3\5\3\5\5\5J\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\6\ra\n\r\r"+
		"\r\16\rb\3\16\3\16\3\16\3\16\3\16\5\16j\n\16\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\7\21w\n\21\f\21\16\21z\13\21\3\22\3\22"+
		"\3\23\3\23\5\23\u0080\n\23\3\24\3\24\5\24\u0084\n\24\3\25\3\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\7\31\u0098\n\31\f\31\16\31\u009b\13\31\3\32\3\32\3\33\7\33\u00a0\n"+
		"\33\f\33\16\33\u00a3\13\33\3\33\3\33\3\34\7\34\u00a8\n\34\f\34\16\34\u00ab"+
		"\13\34\3\34\2\2\35\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\66\2\2\2\u00a1\28\3\2\2\2\4;\3\2\2\2\6@\3\2\2\2\bI\3\2\2\2\nK\3"+
		"\2\2\2\fM\3\2\2\2\16O\3\2\2\2\20Q\3\2\2\2\22T\3\2\2\2\24W\3\2\2\2\26Z"+
		"\3\2\2\2\30\\\3\2\2\2\32i\3\2\2\2\34k\3\2\2\2\36n\3\2\2\2 q\3\2\2\2\""+
		"{\3\2\2\2$\177\3\2\2\2&\u0083\3\2\2\2(\u0085\3\2\2\2*\u0089\3\2\2\2,\u008c"+
		"\3\2\2\2.\u008e\3\2\2\2\60\u0091\3\2\2\2\62\u009c\3\2\2\2\64\u00a1\3\2"+
		"\2\2\66\u00a9\3\2\2\289\5\4\3\29:\5\6\4\2:\3\3\2\2\2;<\7\3\2\2<\5\3\2"+
		"\2\2=>\5\64\33\2>?\5\b\5\2?A\3\2\2\2@=\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3"+
		"\2\2\2C\7\3\2\2\2DJ\5\n\6\2EJ\5\f\7\2FJ\5\16\b\2GJ\5\20\t\2HJ\5\22\n\2"+
		"ID\3\2\2\2IE\3\2\2\2IF\3\2\2\2IG\3\2\2\2IH\3\2\2\2J\t\3\2\2\2KL\5\66\34"+
		"\2L\13\3\2\2\2MN\5\24\13\2N\r\3\2\2\2OP\5\30\r\2P\17\3\2\2\2QR\7\4\2\2"+
		"RS\5\66\34\2S\21\3\2\2\2TU\7\5\2\2UV\5\66\34\2V\23\3\2\2\2WX\7\6\2\2X"+
		"Y\5\26\f\2Y\25\3\2\2\2Z[\5\66\34\2[\27\3\2\2\2\\`\7\7\2\2]^\5\64\33\2"+
		"^_\5\32\16\2_a\3\2\2\2`]\3\2\2\2ab\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\31\3\2"+
		"\2\2dj\5\34\17\2ej\5\36\20\2fj\5 \21\2gj\5.\30\2hj\5\60\31\2id\3\2\2\2"+
		"ie\3\2\2\2if\3\2\2\2ig\3\2\2\2ih\3\2\2\2j\33\3\2\2\2kl\7\b\2\2lm\5\66"+
		"\34\2m\35\3\2\2\2no\7\t\2\2op\5\66\34\2p\37\3\2\2\2qr\7\n\2\2rx\5\"\22"+
		"\2st\5\64\33\2tu\5$\23\2uw\3\2\2\2vs\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2"+
		"\2\2y!\3\2\2\2zx\3\2\2\2{|\5\66\34\2|#\3\2\2\2}\u0080\5&\24\2~\u0080\5"+
		",\27\2\177}\3\2\2\2\177~\3\2\2\2\u0080%\3\2\2\2\u0081\u0084\5(\25\2\u0082"+
		"\u0084\5*\26\2\u0083\u0081\3\2\2\2\u0083\u0082\3\2\2\2\u0084\'\3\2\2\2"+
		"\u0085\u0086\7\13\2\2\u0086\u0087\5\66\34\2\u0087\u0088\7\f\2\2\u0088"+
		")\3\2\2\2\u0089\u008a\7\r\2\2\u008a\u008b\5\66\34\2\u008b+\3\2\2\2\u008c"+
		"\u008d\5\66\34\2\u008d-\3\2\2\2\u008e\u008f\7\16\2\2\u008f\u0090\5\66"+
		"\34\2\u0090/\3\2\2\2\u0091\u0092\7\17\2\2\u0092\u0093\7\20\2\2\u0093\u0099"+
		"\7\21\2\2\u0094\u0095\5\64\33\2\u0095\u0096\5\62\32\2\u0096\u0098\3\2"+
		"\2\2\u0097\u0094\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\61\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009d\5\66\34"+
		"\2\u009d\63\3\2\2\2\u009e\u00a0\7\24\2\2\u009f\u009e\3\2\2\2\u00a0\u00a3"+
		"\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a4\u00a5\7\22\2\2\u00a5\65\3\2\2\2\u00a6\u00a8\7\23"+
		"\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9"+
		"\u00aa\3\2\2\2\u00aa\67\3\2\2\2\u00ab\u00a9\3\2\2\2\fBIbix\177\u0083\u0099"+
		"\u00a1\u00a9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}