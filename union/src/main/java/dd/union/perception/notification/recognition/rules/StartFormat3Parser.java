// Generated from D:/code/IdeaProjects/ProtoAgent/union/src/main/resources/antlr_grammar_rules\StartFormat3.g4 by ANTLR 4.7
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
public class StartFormat3Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, LOCATION_TITLE=15, BULLET_SIGN=16, 
		DEPLOYED_BULLET_SIGN=17, NON_DEPLOYED_BULLET_SIGN=18, TEXT=19, WHITESPACE=20;
	public static final int
		RULE_start_format3 = 0, RULE_header = 1, RULE_item = 2, RULE_main_item_element = 3, 
		RULE_ident_item = 4, RULE_reference_item = 5, RULE_content_item = 6, RULE_remarks_item = 7, 
		RULE_end_item = 8, RULE_reference = 9, RULE_ident_reference = 10, RULE_content = 11, 
		RULE_data_changed = 12, RULE_data_changed_bullet = 13, RULE_data_changed_description = 14, 
		RULE_unit_description = 15, RULE_unit = 16, RULE_location = 17, RULE_location_description = 18, 
		RULE_location_description_element = 19, RULE_coordinate_element = 20, 
		RULE_place = 21, RULE_submarine = 22, RULE_launcher = 23, RULE_unique_identifier = 24, 
		RULE_deployed = 25, RULE_non_deployed = 26, RULE_identifier_unit = 27, 
		RULE_change_description = 28, RULE_facility_description = 29, RULE_date_change = 30, 
		RULE_bullet = 31, RULE_deployed_bullet = 32, RULE_non_deployed_bullet = 33, 
		RULE_text = 34;
	public static final String[] ruleNames = {
		"start_format3", "header", "item", "main_item_element", "ident_item", 
		"reference_item", "content_item", "remarks_item", "end_item", "reference", 
		"ident_reference", "content", "data_changed", "data_changed_bullet", "data_changed_description", 
		"unit_description", "unit", "location", "location_description", "location_description_element", 
		"coordinate_element", "place", "submarine", "launcher", "unique_identifier", 
		"deployed", "non_deployed", "identifier_unit", "change_description", "facility_description", 
		"date_change", "bullet", "deployed_bullet", "non_deployed_bullet", "text"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'NOTIFICATION OF EACH CHANGE IN DATA FOR EACH CATEGORY OF DATA CONTAINED IN PART TWO OF THE PROTOCOL'", 
		"'REMARKS:'", "'END OF'", "'REFERENCE(S):'", "'CONTENT'", "'DATA CHANGE'", 
		"'TYPE, CATEGORY (AND VARIANT OR VERSION IF APPLICABLE) OF ITEM(S):'", 
		"'NAME AND COORDINATES:'", "'SUBMARINE NAME:'", "'DESIGNATION OF LAUNCHER AND COORDINATES:'", 
		"'UNIQUE IDENTIFIER(S) OF ITEM(S)'", "'CHANGE IN NUMBER OF ITEMS, TOTAL NUMBER OF ITEMS AFTER CHANGE:'", 
		"'FACILITY CHANGED:'", "'DATE OF CHANGE:'", "'LOCATION OF CHANGE'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "LOCATION_TITLE", "BULLET_SIGN", "DEPLOYED_BULLET_SIGN", 
		"NON_DEPLOYED_BULLET_SIGN", "TEXT", "WHITESPACE"
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
	public String getGrammarFileName() { return "StartFormat3.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public StartFormat3Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Start_format3Context extends ParserRuleContext {
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public ItemContext item() {
			return getRuleContext(ItemContext.class,0);
		}
		public Start_format3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_format3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterStart_format3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitStart_format3(this);
		}
	}

	public final Start_format3Context start_format3() throws RecognitionException {
		Start_format3Context _localctx = new Start_format3Context(_ctx, getState());
		enterRule(_localctx, 0, RULE_start_format3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			header();
			setState(71);
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
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
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
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitItem(this);
		}
	}

	public final ItemContext item() throws RecognitionException {
		ItemContext _localctx = new ItemContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_item);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(75);
				bullet();
				setState(76);
				main_item_element();
				}
				}
				setState(80); 
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
		public Content_itemContext content_item() {
			return getRuleContext(Content_itemContext.class,0);
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
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterMain_item_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitMain_item_element(this);
		}
	}

	public final Main_item_elementContext main_item_element() throws RecognitionException {
		Main_item_elementContext _localctx = new Main_item_elementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_main_item_element);
		try {
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
			case BULLET_SIGN:
			case TEXT:
			case WHITESPACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				ident_item();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				reference_item();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				content_item();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 4);
				{
				setState(85);
				remarks_item();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 5);
				{
				setState(86);
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
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterIdent_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitIdent_item(this);
		}
	}

	public final Ident_itemContext ident_item() throws RecognitionException {
		Ident_itemContext _localctx = new Ident_itemContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ident_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
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
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterReference_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitReference_item(this);
		}
	}

	public final Reference_itemContext reference_item() throws RecognitionException {
		Reference_itemContext _localctx = new Reference_itemContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_reference_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
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

	public static class Content_itemContext extends ParserRuleContext {
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public Content_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterContent_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitContent_item(this);
		}
	}

	public final Content_itemContext content_item() throws RecognitionException {
		Content_itemContext _localctx = new Content_itemContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_content_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
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
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterRemarks_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitRemarks_item(this);
		}
	}

	public final Remarks_itemContext remarks_item() throws RecognitionException {
		Remarks_itemContext _localctx = new Remarks_itemContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_remarks_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__1);
			{
			setState(96);
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
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterEnd_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitEnd_item(this);
		}
	}

	public final End_itemContext end_item() throws RecognitionException {
		End_itemContext _localctx = new End_itemContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_end_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__2);
			setState(99);
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
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__3);
			setState(102);
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
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterIdent_reference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitIdent_reference(this);
		}
	}

	public final Ident_referenceContext ident_reference() throws RecognitionException {
		Ident_referenceContext _localctx = new Ident_referenceContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ident_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
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
		public List<Data_changedContext> data_changed() {
			return getRuleContexts(Data_changedContext.class);
		}
		public Data_changedContext data_changed(int i) {
			return getRuleContext(Data_changedContext.class,i);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitContent(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_content);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__4);
			setState(108); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(107);
					data_changed();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(110); 
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

	public static class Data_changedContext extends ParserRuleContext {
		public Data_changed_bulletContext data_changed_bullet() {
			return getRuleContext(Data_changed_bulletContext.class,0);
		}
		public List<Data_changed_descriptionContext> data_changed_description() {
			return getRuleContexts(Data_changed_descriptionContext.class);
		}
		public Data_changed_descriptionContext data_changed_description(int i) {
			return getRuleContext(Data_changed_descriptionContext.class,i);
		}
		public Data_changedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_changed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterData_changed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitData_changed(this);
		}
	}

	public final Data_changedContext data_changed() throws RecognitionException {
		Data_changedContext _localctx = new Data_changedContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_data_changed);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			data_changed_bullet();
			setState(114); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(113);
					data_changed_description();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(116); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class Data_changed_bulletContext extends ParserRuleContext {
		public BulletContext bullet() {
			return getRuleContext(BulletContext.class,0);
		}
		public Data_changed_bulletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_changed_bullet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterData_changed_bullet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitData_changed_bullet(this);
		}
	}

	public final Data_changed_bulletContext data_changed_bullet() throws RecognitionException {
		Data_changed_bulletContext _localctx = new Data_changed_bulletContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_data_changed_bullet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			bullet();
			setState(119);
			match(T__5);
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

	public static class Data_changed_descriptionContext extends ParserRuleContext {
		public Unit_descriptionContext unit_description() {
			return getRuleContext(Unit_descriptionContext.class,0);
		}
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public Unique_identifierContext unique_identifier() {
			return getRuleContext(Unique_identifierContext.class,0);
		}
		public Change_descriptionContext change_description() {
			return getRuleContext(Change_descriptionContext.class,0);
		}
		public Facility_descriptionContext facility_description() {
			return getRuleContext(Facility_descriptionContext.class,0);
		}
		public Date_changeContext date_change() {
			return getRuleContext(Date_changeContext.class,0);
		}
		public Data_changed_descriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_changed_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterData_changed_description(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitData_changed_description(this);
		}
	}

	public final Data_changed_descriptionContext data_changed_description() throws RecognitionException {
		Data_changed_descriptionContext _localctx = new Data_changed_descriptionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_data_changed_description);
		try {
			setState(127);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				unit_description();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				location();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				unique_identifier();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(124);
				change_description();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(125);
				facility_description();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(126);
				date_change();
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

	public static class Unit_descriptionContext extends ParserRuleContext {
		public BulletContext bullet() {
			return getRuleContext(BulletContext.class,0);
		}
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public Unit_descriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unit_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterUnit_description(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitUnit_description(this);
		}
	}

	public final Unit_descriptionContext unit_description() throws RecognitionException {
		Unit_descriptionContext _localctx = new Unit_descriptionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_unit_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			bullet();
			setState(130);
			match(T__6);
			setState(131);
			unit();
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

	public static class UnitContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public UnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitUnit(this);
		}
	}

	public final UnitContext unit() throws RecognitionException {
		UnitContext _localctx = new UnitContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_unit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
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

	public static class LocationContext extends ParserRuleContext {
		public BulletContext bullet() {
			return getRuleContext(BulletContext.class,0);
		}
		public TerminalNode LOCATION_TITLE() { return getToken(StartFormat3Parser.LOCATION_TITLE, 0); }
		public Location_descriptionContext location_description() {
			return getRuleContext(Location_descriptionContext.class,0);
		}
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitLocation(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_location);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			bullet();
			setState(136);
			match(LOCATION_TITLE);
			setState(137);
			location_description();
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

	public static class Location_descriptionContext extends ParserRuleContext {
		public List<BulletContext> bullet() {
			return getRuleContexts(BulletContext.class);
		}
		public BulletContext bullet(int i) {
			return getRuleContext(BulletContext.class,i);
		}
		public List<Location_description_elementContext> location_description_element() {
			return getRuleContexts(Location_description_elementContext.class);
		}
		public Location_description_elementContext location_description_element(int i) {
			return getRuleContext(Location_description_elementContext.class,i);
		}
		public Location_descriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterLocation_description(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitLocation_description(this);
		}
	}

	public final Location_descriptionContext location_description() throws RecognitionException {
		Location_descriptionContext _localctx = new Location_descriptionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_location_description);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(142); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(139);
					bullet();
					setState(140);
					location_description_element();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(144); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class Location_description_elementContext extends ParserRuleContext {
		public Coordinate_elementContext coordinate_element() {
			return getRuleContext(Coordinate_elementContext.class,0);
		}
		public SubmarineContext submarine() {
			return getRuleContext(SubmarineContext.class,0);
		}
		public LauncherContext launcher() {
			return getRuleContext(LauncherContext.class,0);
		}
		public Location_description_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location_description_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterLocation_description_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitLocation_description_element(this);
		}
	}

	public final Location_description_elementContext location_description_element() throws RecognitionException {
		Location_description_elementContext _localctx = new Location_description_elementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_location_description_element);
		try {
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				coordinate_element();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				submarine();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(148);
				launcher();
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

	public static class Coordinate_elementContext extends ParserRuleContext {
		public PlaceContext place() {
			return getRuleContext(PlaceContext.class,0);
		}
		public Coordinate_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coordinate_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterCoordinate_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitCoordinate_element(this);
		}
	}

	public final Coordinate_elementContext coordinate_element() throws RecognitionException {
		Coordinate_elementContext _localctx = new Coordinate_elementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_coordinate_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(T__7);
			setState(152);
			place();
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

	public static class PlaceContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public PlaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_place; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterPlace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitPlace(this);
		}
	}

	public final PlaceContext place() throws RecognitionException {
		PlaceContext _localctx = new PlaceContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_place);
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

	public static class SubmarineContext extends ParserRuleContext {
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public SubmarineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_submarine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterSubmarine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitSubmarine(this);
		}
	}

	public final SubmarineContext submarine() throws RecognitionException {
		SubmarineContext _localctx = new SubmarineContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_submarine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(T__8);
			setState(157);
			unit();
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

	public static class LauncherContext extends ParserRuleContext {
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public LauncherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_launcher; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterLauncher(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitLauncher(this);
		}
	}

	public final LauncherContext launcher() throws RecognitionException {
		LauncherContext _localctx = new LauncherContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_launcher);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(T__9);
			setState(160);
			unit();
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

	public static class Unique_identifierContext extends ParserRuleContext {
		public BulletContext bullet() {
			return getRuleContext(BulletContext.class,0);
		}
		public Deployed_bulletContext deployed_bullet() {
			return getRuleContext(Deployed_bulletContext.class,0);
		}
		public DeployedContext deployed() {
			return getRuleContext(DeployedContext.class,0);
		}
		public Non_deployed_bulletContext non_deployed_bullet() {
			return getRuleContext(Non_deployed_bulletContext.class,0);
		}
		public Non_deployedContext non_deployed() {
			return getRuleContext(Non_deployedContext.class,0);
		}
		public Unique_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unique_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterUnique_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitUnique_identifier(this);
		}
	}

	public final Unique_identifierContext unique_identifier() throws RecognitionException {
		Unique_identifierContext _localctx = new Unique_identifierContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_unique_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			bullet();
			setState(163);
			match(T__10);
			{
			setState(164);
			deployed_bullet();
			setState(165);
			deployed();
			}
			{
			setState(167);
			non_deployed_bullet();
			setState(168);
			non_deployed();
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

	public static class DeployedContext extends ParserRuleContext {
		public List<BulletContext> bullet() {
			return getRuleContexts(BulletContext.class);
		}
		public BulletContext bullet(int i) {
			return getRuleContext(BulletContext.class,i);
		}
		public List<Identifier_unitContext> identifier_unit() {
			return getRuleContexts(Identifier_unitContext.class);
		}
		public Identifier_unitContext identifier_unit(int i) {
			return getRuleContext(Identifier_unitContext.class,i);
		}
		public DeployedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deployed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterDeployed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitDeployed(this);
		}
	}

	public final DeployedContext deployed() throws RecognitionException {
		DeployedContext _localctx = new DeployedContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_deployed);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(170);
				bullet();
				setState(171);
				identifier_unit();
				}
				}
				setState(175); 
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

	public static class Non_deployedContext extends ParserRuleContext {
		public List<BulletContext> bullet() {
			return getRuleContexts(BulletContext.class);
		}
		public BulletContext bullet(int i) {
			return getRuleContext(BulletContext.class,i);
		}
		public List<Identifier_unitContext> identifier_unit() {
			return getRuleContexts(Identifier_unitContext.class);
		}
		public Identifier_unitContext identifier_unit(int i) {
			return getRuleContext(Identifier_unitContext.class,i);
		}
		public Non_deployedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_deployed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterNon_deployed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitNon_deployed(this);
		}
	}

	public final Non_deployedContext non_deployed() throws RecognitionException {
		Non_deployedContext _localctx = new Non_deployedContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_non_deployed);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(180); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(177);
					bullet();
					setState(178);
					identifier_unit();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(182); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class Identifier_unitContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Identifier_unitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_unit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterIdentifier_unit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitIdentifier_unit(this);
		}
	}

	public final Identifier_unitContext identifier_unit() throws RecognitionException {
		Identifier_unitContext _localctx = new Identifier_unitContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_identifier_unit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
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

	public static class Change_descriptionContext extends ParserRuleContext {
		public BulletContext bullet() {
			return getRuleContext(BulletContext.class,0);
		}
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Change_descriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_change_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterChange_description(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitChange_description(this);
		}
	}

	public final Change_descriptionContext change_description() throws RecognitionException {
		Change_descriptionContext _localctx = new Change_descriptionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_change_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			bullet();
			setState(187);
			match(T__11);
			setState(188);
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

	public static class Facility_descriptionContext extends ParserRuleContext {
		public BulletContext bullet() {
			return getRuleContext(BulletContext.class,0);
		}
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public Facility_descriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_facility_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterFacility_description(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitFacility_description(this);
		}
	}

	public final Facility_descriptionContext facility_description() throws RecognitionException {
		Facility_descriptionContext _localctx = new Facility_descriptionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_facility_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			bullet();
			setState(191);
			match(T__12);
			setState(192);
			unit();
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

	public static class Date_changeContext extends ParserRuleContext {
		public BulletContext bullet() {
			return getRuleContext(BulletContext.class,0);
		}
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public Date_changeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date_change; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterDate_change(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitDate_change(this);
		}
	}

	public final Date_changeContext date_change() throws RecognitionException {
		Date_changeContext _localctx = new Date_changeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_date_change);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			bullet();
			setState(195);
			match(T__13);
			setState(196);
			unit();
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
		public TerminalNode BULLET_SIGN() { return getToken(StartFormat3Parser.BULLET_SIGN, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(StartFormat3Parser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(StartFormat3Parser.WHITESPACE, i);
		}
		public BulletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bullet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterBullet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitBullet(this);
		}
	}

	public final BulletContext bullet() throws RecognitionException {
		BulletContext _localctx = new BulletContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_bullet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(198);
				match(WHITESPACE);
				}
				}
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(204);
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
		public TerminalNode DEPLOYED_BULLET_SIGN() { return getToken(StartFormat3Parser.DEPLOYED_BULLET_SIGN, 0); }
		public Deployed_bulletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deployed_bullet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterDeployed_bullet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitDeployed_bullet(this);
		}
	}

	public final Deployed_bulletContext deployed_bullet() throws RecognitionException {
		Deployed_bulletContext _localctx = new Deployed_bulletContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_deployed_bullet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
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
		public TerminalNode NON_DEPLOYED_BULLET_SIGN() { return getToken(StartFormat3Parser.NON_DEPLOYED_BULLET_SIGN, 0); }
		public Non_deployed_bulletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_deployed_bullet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterNon_deployed_bullet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitNon_deployed_bullet(this);
		}
	}

	public final Non_deployed_bulletContext non_deployed_bullet() throws RecognitionException {
		Non_deployed_bulletContext _localctx = new Non_deployed_bulletContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_non_deployed_bullet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
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
		public List<TerminalNode> TEXT() { return getTokens(StartFormat3Parser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(StartFormat3Parser.TEXT, i);
		}
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).enterText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StartFormat3Listener ) ((StartFormat3Listener)listener).exitText(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_text);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TEXT) {
				{
				{
				setState(210);
				match(TEXT);
				}
				}
				setState(215);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u00db\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\6\4Q\n\4\r\4\16"+
		"\4R\3\5\3\5\3\5\3\5\3\5\5\5Z\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\6\ro\n\r\r\r\16\rp\3\16\3\16"+
		"\6\16u\n\16\r\16\16\16v\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\5"+
		"\20\u0082\n\20\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\6\24\u0091\n\24\r\24\16\24\u0092\3\25\3\25\3\25\5\25\u0098"+
		"\n\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\6\33\u00b0\n\33\r\33\16"+
		"\33\u00b1\3\34\3\34\3\34\6\34\u00b7\n\34\r\34\16\34\u00b8\3\35\3\35\3"+
		"\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\7!\u00ca\n!\f!\16"+
		"!\u00cd\13!\3!\3!\3\"\3\"\3#\3#\3$\7$\u00d6\n$\f$\16$\u00d9\13$\3$\2\2"+
		"%\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDF"+
		"\2\2\2\u00ca\2H\3\2\2\2\4K\3\2\2\2\6P\3\2\2\2\bY\3\2\2\2\n[\3\2\2\2\f"+
		"]\3\2\2\2\16_\3\2\2\2\20a\3\2\2\2\22d\3\2\2\2\24g\3\2\2\2\26j\3\2\2\2"+
		"\30l\3\2\2\2\32r\3\2\2\2\34x\3\2\2\2\36\u0081\3\2\2\2 \u0083\3\2\2\2\""+
		"\u0087\3\2\2\2$\u0089\3\2\2\2&\u0090\3\2\2\2(\u0097\3\2\2\2*\u0099\3\2"+
		"\2\2,\u009c\3\2\2\2.\u009e\3\2\2\2\60\u00a1\3\2\2\2\62\u00a4\3\2\2\2\64"+
		"\u00af\3\2\2\2\66\u00b6\3\2\2\28\u00ba\3\2\2\2:\u00bc\3\2\2\2<\u00c0\3"+
		"\2\2\2>\u00c4\3\2\2\2@\u00cb\3\2\2\2B\u00d0\3\2\2\2D\u00d2\3\2\2\2F\u00d7"+
		"\3\2\2\2HI\5\4\3\2IJ\5\6\4\2J\3\3\2\2\2KL\7\3\2\2L\5\3\2\2\2MN\5@!\2N"+
		"O\5\b\5\2OQ\3\2\2\2PM\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\7\3\2\2\2"+
		"TZ\5\n\6\2UZ\5\f\7\2VZ\5\16\b\2WZ\5\20\t\2XZ\5\22\n\2YT\3\2\2\2YU\3\2"+
		"\2\2YV\3\2\2\2YW\3\2\2\2YX\3\2\2\2Z\t\3\2\2\2[\\\5F$\2\\\13\3\2\2\2]^"+
		"\5\24\13\2^\r\3\2\2\2_`\5\30\r\2`\17\3\2\2\2ab\7\4\2\2bc\5F$\2c\21\3\2"+
		"\2\2de\7\5\2\2ef\5F$\2f\23\3\2\2\2gh\7\6\2\2hi\5\26\f\2i\25\3\2\2\2jk"+
		"\5F$\2k\27\3\2\2\2ln\7\7\2\2mo\5\32\16\2nm\3\2\2\2op\3\2\2\2pn\3\2\2\2"+
		"pq\3\2\2\2q\31\3\2\2\2rt\5\34\17\2su\5\36\20\2ts\3\2\2\2uv\3\2\2\2vt\3"+
		"\2\2\2vw\3\2\2\2w\33\3\2\2\2xy\5@!\2yz\7\b\2\2z\35\3\2\2\2{\u0082\5 \21"+
		"\2|\u0082\5$\23\2}\u0082\5\62\32\2~\u0082\5:\36\2\177\u0082\5<\37\2\u0080"+
		"\u0082\5> \2\u0081{\3\2\2\2\u0081|\3\2\2\2\u0081}\3\2\2\2\u0081~\3\2\2"+
		"\2\u0081\177\3\2\2\2\u0081\u0080\3\2\2\2\u0082\37\3\2\2\2\u0083\u0084"+
		"\5@!\2\u0084\u0085\7\t\2\2\u0085\u0086\5\"\22\2\u0086!\3\2\2\2\u0087\u0088"+
		"\5F$\2\u0088#\3\2\2\2\u0089\u008a\5@!\2\u008a\u008b\7\21\2\2\u008b\u008c"+
		"\5&\24\2\u008c%\3\2\2\2\u008d\u008e\5@!\2\u008e\u008f\5(\25\2\u008f\u0091"+
		"\3\2\2\2\u0090\u008d\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\'\3\2\2\2\u0094\u0098\5*\26\2\u0095\u0098\5.\30\2"+
		"\u0096\u0098\5\60\31\2\u0097\u0094\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0096"+
		"\3\2\2\2\u0098)\3\2\2\2\u0099\u009a\7\n\2\2\u009a\u009b\5,\27\2\u009b"+
		"+\3\2\2\2\u009c\u009d\5F$\2\u009d-\3\2\2\2\u009e\u009f\7\13\2\2\u009f"+
		"\u00a0\5\"\22\2\u00a0/\3\2\2\2\u00a1\u00a2\7\f\2\2\u00a2\u00a3\5\"\22"+
		"\2\u00a3\61\3\2\2\2\u00a4\u00a5\5@!\2\u00a5\u00a6\7\r\2\2\u00a6\u00a7"+
		"\5B\"\2\u00a7\u00a8\5\64\33\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\5D#\2\u00aa"+
		"\u00ab\5\66\34\2\u00ab\63\3\2\2\2\u00ac\u00ad\5@!\2\u00ad\u00ae\58\35"+
		"\2\u00ae\u00b0\3\2\2\2\u00af\u00ac\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af"+
		"\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\65\3\2\2\2\u00b3\u00b4\5@!\2\u00b4"+
		"\u00b5\58\35\2\u00b5\u00b7\3\2\2\2\u00b6\u00b3\3\2\2\2\u00b7\u00b8\3\2"+
		"\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\67\3\2\2\2\u00ba\u00bb"+
		"\5F$\2\u00bb9\3\2\2\2\u00bc\u00bd\5@!\2\u00bd\u00be\7\16\2\2\u00be\u00bf"+
		"\5F$\2\u00bf;\3\2\2\2\u00c0\u00c1\5@!\2\u00c1\u00c2\7\17\2\2\u00c2\u00c3"+
		"\5\"\22\2\u00c3=\3\2\2\2\u00c4\u00c5\5@!\2\u00c5\u00c6\7\20\2\2\u00c6"+
		"\u00c7\5\"\22\2\u00c7?\3\2\2\2\u00c8\u00ca\7\26\2\2\u00c9\u00c8\3\2\2"+
		"\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce"+
		"\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf\7\22\2\2\u00cfA\3\2\2\2\u00d0"+
		"\u00d1\7\23\2\2\u00d1C\3\2\2\2\u00d2\u00d3\7\24\2\2\u00d3E\3\2\2\2\u00d4"+
		"\u00d6\7\25\2\2\u00d5\u00d4\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3"+
		"\2\2\2\u00d7\u00d8\3\2\2\2\u00d8G\3\2\2\2\u00d9\u00d7\3\2\2\2\rRYpv\u0081"+
		"\u0092\u0097\u00b1\u00b8\u00cb\u00d7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}