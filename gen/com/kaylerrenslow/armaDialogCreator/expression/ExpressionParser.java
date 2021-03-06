// Generated from D:/Archive/Intellij Files/Arma Tools/Arma Dialog Creator/src/com/kaylerrenslow/armaDialogCreator/expression\Expression.g4 by ANTLR 4.7
package com.kaylerrenslow.armaDialogCreator.expression;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		String=1, Quote=2, DQuote=3, LCurly=4, RCurly=5, LBracket=6, RBracket=7, 
		Plus=8, Minus=9, FSlash=10, Perc=11, Caret=12, Star=13, LParen=14, RParen=15, 
		Comma=16, Min=17, Max=18, If=19, Then=20, Else=21, ExitWith=22, Select=23, 
		Count=24, For=25, From=26, To=27, Step=28, Do=29, Str=30, EqEq=31, NotEq=32, 
		Lt=33, LtEq=34, Gt=35, GtEq=36, Equal=37, Semicolon=38, Identifier=39, 
		IntegerLiteral=40, FloatLiteral=41, Digits=42, DecSignificand=43, DecExponent=44, 
		HexLiteral=45, HexDigits=46, Letter=47, LetterOrDigit=48, WhiteSpace=49, 
		Comment=50;
	public static final int
		RULE_statements = 0, RULE_statement = 1, RULE_assignment = 2, RULE_code = 3, 
		RULE_expression = 4, RULE_caret_expression_helper = 5, RULE_unary_expression = 6, 
		RULE_paren_expression = 7, RULE_literal_expression = 8, RULE_if_expression = 9, 
		RULE_for_expression = 10, RULE_array = 11, RULE_int_value = 12, RULE_float_value = 13;
	public static final String[] ruleNames = {
		"statements", "statement", "assignment", "code", "expression", "caret_expression_helper", 
		"unary_expression", "paren_expression", "literal_expression", "if_expression", 
		"for_expression", "array", "int_value", "float_value"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'''", "'\"'", "'{'", "'}'", "'['", "']'", "'+'", "'-'", "'/'", 
		"'%'", "'^'", "'*'", "'('", "')'", "','", null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "'=='", "'!='", 
		"'<'", "'<='", "'>'", "'>='", "'='", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "String", "Quote", "DQuote", "LCurly", "RCurly", "LBracket", "RBracket", 
		"Plus", "Minus", "FSlash", "Perc", "Caret", "Star", "LParen", "RParen", 
		"Comma", "Min", "Max", "If", "Then", "Else", "ExitWith", "Select", "Count", 
		"For", "From", "To", "Step", "Do", "Str", "EqEq", "NotEq", "Lt", "LtEq", 
		"Gt", "GtEq", "Equal", "Semicolon", "Identifier", "IntegerLiteral", "FloatLiteral", 
		"Digits", "DecSignificand", "DecExponent", "HexLiteral", "HexDigits", 
		"Letter", "LetterOrDigit", "WhiteSpace", "Comment"
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
	public String getGrammarFileName() { return "Expression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StatementsContext extends ParserRuleContext {
		public List<AST.Statement> lst;
		public StatementContext s;
		public StatementContext s2;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> Semicolon() { return getTokens(ExpressionParser.Semicolon); }
		public TerminalNode Semicolon(int i) {
			return getToken(ExpressionParser.Semicolon, i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitStatements(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statements);
		 ((StatementsContext)_localctx).lst =  new ArrayList<>(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(28);
					((StatementsContext)_localctx).s = statement();
					setState(29);
					match(Semicolon);
					_localctx.lst.add(((StatementsContext)_localctx).s.ast);
					}
					} 
				}
				setState(36);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(37);
			((StatementsContext)_localctx).s2 = statement();
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Semicolon) {
				{
				setState(38);
				match(Semicolon);
				}
			}

			_localctx.lst.add(((StatementsContext)_localctx).s2.ast);
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

	public static class StatementContext extends ParserRuleContext {
		public AST.Statement ast;
		public AssignmentContext a;
		public ExpressionContext e;
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				((StatementContext)_localctx).a = assignment();
				((StatementContext)_localctx).ast =  new AST.Statement(((StatementContext)_localctx).a.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				((StatementContext)_localctx).e = expression(0);
				((StatementContext)_localctx).ast =  new AST.Statement(((StatementContext)_localctx).e.ast);
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

	public static class AssignmentContext extends ParserRuleContext {
		public AST.Assignment ast;
		public Token i;
		public ExpressionContext e;
		public TerminalNode Equal() { return getToken(ExpressionParser.Equal, 0); }
		public TerminalNode Identifier() { return getToken(ExpressionParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			((AssignmentContext)_localctx).i = match(Identifier);
			setState(52);
			match(Equal);
			setState(53);
			((AssignmentContext)_localctx).e = expression(0);
			((AssignmentContext)_localctx).ast =  new AST.Assignment((((AssignmentContext)_localctx).i!=null?((AssignmentContext)_localctx).i.getText():null), ((AssignmentContext)_localctx).e.ast);
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

	public static class CodeContext extends ParserRuleContext {
		public AST.Code ast;
		public List<AST.Statement> lst;
		public StatementsContext s;
		public TerminalNode LCurly() { return getToken(ExpressionParser.LCurly, 0); }
		public TerminalNode RCurly() { return getToken(ExpressionParser.RCurly, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitCode(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_code);
		 ((CodeContext)_localctx).lst =  new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(LCurly);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << String) | (1L << LCurly) | (1L << LBracket) | (1L << Plus) | (1L << Minus) | (1L << LParen) | (1L << If) | (1L << Count) | (1L << For) | (1L << Str) | (1L << Identifier) | (1L << IntegerLiteral) | (1L << FloatLiteral) | (1L << HexLiteral))) != 0)) {
				{
				setState(57);
				((CodeContext)_localctx).s = statements();
				((CodeContext)_localctx).lst = ((CodeContext)_localctx).s.lst;
				}
			}

			setState(62);
			match(RCurly);
			((CodeContext)_localctx).ast =  new AST.Code(_localctx.lst);
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

	public static class ExpressionContext extends ParserRuleContext {
		public AST.Expr ast;
		public ExpressionContext count_l;
		public ExpressionContext ls;
		public ExpressionContext lf;
		public ExpressionContext lperc;
		public ExpressionContext lexpon;
		public ExpressionContext la;
		public ExpressionContext lm;
		public ExpressionContext lcomp;
		public ExpressionContext lmax;
		public ExpressionContext lmin;
		public ExpressionContext select_e;
		public ExpressionContext count_r;
		public ExpressionContext str_exp;
		public Unary_expressionContext lu;
		public Paren_expressionContext lp;
		public If_expressionContext ifexp;
		public For_expressionContext forexp;
		public CodeContext codeExp;
		public Literal_expressionContext ll;
		public ExpressionContext rs;
		public ExpressionContext rf;
		public ExpressionContext rperc;
		public ExpressionContext ra;
		public ExpressionContext rm;
		public Token compOp;
		public ExpressionContext rcomp;
		public ExpressionContext rmax;
		public ExpressionContext rmin;
		public ExpressionContext select_i;
		public TerminalNode Count() { return getToken(ExpressionParser.Count, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Str() { return getToken(ExpressionParser.Str, 0); }
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public Paren_expressionContext paren_expression() {
			return getRuleContext(Paren_expressionContext.class,0);
		}
		public If_expressionContext if_expression() {
			return getRuleContext(If_expressionContext.class,0);
		}
		public For_expressionContext for_expression() {
			return getRuleContext(For_expressionContext.class,0);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public Literal_expressionContext literal_expression() {
			return getRuleContext(Literal_expressionContext.class,0);
		}
		public TerminalNode Star() { return getToken(ExpressionParser.Star, 0); }
		public TerminalNode FSlash() { return getToken(ExpressionParser.FSlash, 0); }
		public TerminalNode Perc() { return getToken(ExpressionParser.Perc, 0); }
		public TerminalNode Plus() { return getToken(ExpressionParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(ExpressionParser.Minus, 0); }
		public TerminalNode EqEq() { return getToken(ExpressionParser.EqEq, 0); }
		public TerminalNode NotEq() { return getToken(ExpressionParser.NotEq, 0); }
		public TerminalNode LtEq() { return getToken(ExpressionParser.LtEq, 0); }
		public TerminalNode Lt() { return getToken(ExpressionParser.Lt, 0); }
		public TerminalNode GtEq() { return getToken(ExpressionParser.GtEq, 0); }
		public TerminalNode Gt() { return getToken(ExpressionParser.Gt, 0); }
		public TerminalNode Max() { return getToken(ExpressionParser.Max, 0); }
		public TerminalNode Min() { return getToken(ExpressionParser.Min, 0); }
		public TerminalNode Select() { return getToken(ExpressionParser.Select, 0); }
		public TerminalNode Caret() { return getToken(ExpressionParser.Caret, 0); }
		public Caret_expression_helperContext caret_expression_helper() {
			return getRuleContext(Caret_expression_helperContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpression(this);
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
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Count:
				{
				setState(66);
				match(Count);
				setState(67);
				((ExpressionContext)_localctx).count_r = expression(19);
				((ExpressionContext)_localctx).ast =  new AST.CountExpr(null, ((ExpressionContext)_localctx).count_r.ast);
				}
				break;
			case Str:
				{
				setState(70);
				match(Str);
				setState(71);
				((ExpressionContext)_localctx).str_exp = expression(17);
				((ExpressionContext)_localctx).ast =  new AST.StrExpr(((ExpressionContext)_localctx).str_exp.ast);
				}
				break;
			case Plus:
			case Minus:
				{
				setState(74);
				((ExpressionContext)_localctx).lu = unary_expression();
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).lu.ast;
				}
				break;
			case LParen:
				{
				setState(77);
				((ExpressionContext)_localctx).lp = paren_expression();
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).lp.ast;
				}
				break;
			case If:
				{
				setState(80);
				((ExpressionContext)_localctx).ifexp = if_expression();
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).ifexp.ast;
				}
				break;
			case For:
				{
				setState(83);
				((ExpressionContext)_localctx).forexp = for_expression();
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).forexp.ast;
				}
				break;
			case LCurly:
				{
				setState(86);
				((ExpressionContext)_localctx).codeExp = code();
				((ExpressionContext)_localctx).ast =  new AST.CodeExpr(((ExpressionContext)_localctx).codeExp.ast);
				}
				break;
			case String:
			case LBracket:
			case Identifier:
			case IntegerLiteral:
			case FloatLiteral:
			case HexLiteral:
				{
				setState(89);
				((ExpressionContext)_localctx).ll = literal_expression();
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).ll.ast;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(150);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(148);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.count_l = _prevctx;
						_localctx.count_l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(94);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(95);
						match(Count);
						setState(96);
						((ExpressionContext)_localctx).count_r = expression(19);
						((ExpressionContext)_localctx).ast =  new AST.CountExpr(((ExpressionContext)_localctx).count_l.ast, ((ExpressionContext)_localctx).count_r.ast);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.ls = _prevctx;
						_localctx.ls = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(99);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(100);
						match(Star);
						setState(101);
						((ExpressionContext)_localctx).rs = expression(15);
						((ExpressionContext)_localctx).ast =  new AST.MultExpr(((ExpressionContext)_localctx).ls.ast, ((ExpressionContext)_localctx).rs.ast);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lf = _prevctx;
						_localctx.lf = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(104);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(105);
						match(FSlash);
						setState(106);
						((ExpressionContext)_localctx).rf = expression(14);
						((ExpressionContext)_localctx).ast =  new AST.DivExpr(((ExpressionContext)_localctx).lf.ast, ((ExpressionContext)_localctx).rf.ast);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lperc = _prevctx;
						_localctx.lperc = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(109);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(110);
						match(Perc);
						setState(111);
						((ExpressionContext)_localctx).rperc = expression(13);
						((ExpressionContext)_localctx).ast =  new AST.ModExpr(((ExpressionContext)_localctx).lperc.ast, ((ExpressionContext)_localctx).rperc.ast);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.la = _prevctx;
						_localctx.la = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(114);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(115);
						match(Plus);
						setState(116);
						((ExpressionContext)_localctx).ra = expression(11);
						((ExpressionContext)_localctx).ast =  new AST.AddExpr(((ExpressionContext)_localctx).la.ast, ((ExpressionContext)_localctx).ra.ast);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lm = _prevctx;
						_localctx.lm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(119);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(120);
						match(Minus);
						setState(121);
						((ExpressionContext)_localctx).rm = expression(10);
						((ExpressionContext)_localctx).ast =  new AST.SubExpr(((ExpressionContext)_localctx).lm.ast, ((ExpressionContext)_localctx).rm.ast);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lcomp = _prevctx;
						_localctx.lcomp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(124);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(125);
						((ExpressionContext)_localctx).compOp = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EqEq) | (1L << NotEq) | (1L << Lt) | (1L << LtEq) | (1L << Gt) | (1L << GtEq))) != 0)) ) {
							((ExpressionContext)_localctx).compOp = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(126);
						((ExpressionContext)_localctx).rcomp = expression(9);
						((ExpressionContext)_localctx).ast =  new AST.CompExpr(((ExpressionContext)_localctx).lcomp.ast, ((ExpressionContext)_localctx).rcomp.ast, (((ExpressionContext)_localctx).compOp!=null?((ExpressionContext)_localctx).compOp.getText():null));
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lmax = _prevctx;
						_localctx.lmax = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(129);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(130);
						match(Max);
						setState(131);
						((ExpressionContext)_localctx).rmax = expression(8);
						((ExpressionContext)_localctx).ast =  new AST.MaxExpr(((ExpressionContext)_localctx).lmax.ast, ((ExpressionContext)_localctx).rmax.ast);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lmin = _prevctx;
						_localctx.lmin = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(134);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(135);
						match(Min);
						setState(136);
						((ExpressionContext)_localctx).rmin = expression(7);
						((ExpressionContext)_localctx).ast =  new AST.MinExpr(((ExpressionContext)_localctx).lmin.ast, ((ExpressionContext)_localctx).rmin.ast);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.select_e = _prevctx;
						_localctx.select_e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(139);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(140);
						match(Select);
						setState(141);
						((ExpressionContext)_localctx).select_i = expression(6);
						((ExpressionContext)_localctx).ast =  new AST.SelectExpr(((ExpressionContext)_localctx).select_e.ast, ((ExpressionContext)_localctx).select_i.ast);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lexpon = _prevctx;
						_localctx.lexpon = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(144);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(145);
						match(Caret);
						{
						((ExpressionContext)_localctx).ast =  new AST.ExponentExpr(((ExpressionContext)_localctx).lexpon.ast);
						setState(147);
						caret_expression_helper((AST.ExponentExpr)_localctx.ast);
						}
						}
						break;
					}
					} 
				}
				setState(152);
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

	public static class Caret_expression_helperContext extends ParserRuleContext {
		public AST.ExponentExpr ast;
		public ExpressionContext e1;
		public ExpressionContext e2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Caret() { return getTokens(ExpressionParser.Caret); }
		public TerminalNode Caret(int i) {
			return getToken(ExpressionParser.Caret, i);
		}
		public Caret_expression_helperContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Caret_expression_helperContext(ParserRuleContext parent, int invokingState, AST.ExponentExpr ast) {
			super(parent, invokingState);
			this.ast = ast;
		}
		@Override public int getRuleIndex() { return RULE_caret_expression_helper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterCaret_expression_helper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitCaret_expression_helper(this);
		}
	}

	public final Caret_expression_helperContext caret_expression_helper(AST.ExponentExpr ast) throws RecognitionException {
		Caret_expression_helperContext _localctx = new Caret_expression_helperContext(_ctx, getState(), ast);
		enterRule(_localctx, 10, RULE_caret_expression_helper);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(153);
					((Caret_expression_helperContext)_localctx).e1 = expression(0);
					setState(154);
					match(Caret);
					_localctx.ast.getExprs().add(((Caret_expression_helperContext)_localctx).e1.ast);
					}
					} 
				}
				setState(161);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(162);
			((Caret_expression_helperContext)_localctx).e2 = expression(0);
			_localctx.ast.getExprs().add(((Caret_expression_helperContext)_localctx).e2.ast);
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

	public static class Unary_expressionContext extends ParserRuleContext {
		public AST.UnaryExpr ast;
		public Paren_expressionContext ep;
		public Literal_expressionContext ep1;
		public Paren_expressionContext em;
		public Literal_expressionContext em1;
		public TerminalNode Plus() { return getToken(ExpressionParser.Plus, 0); }
		public Paren_expressionContext paren_expression() {
			return getRuleContext(Paren_expressionContext.class,0);
		}
		public Literal_expressionContext literal_expression() {
			return getRuleContext(Literal_expressionContext.class,0);
		}
		public TerminalNode Minus() { return getToken(ExpressionParser.Minus, 0); }
		public Unary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterUnary_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitUnary_expression(this);
		}
	}

	public final Unary_expressionContext unary_expression() throws RecognitionException {
		Unary_expressionContext _localctx = new Unary_expressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_unary_expression);
		try {
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				match(Plus);
				setState(166);
				((Unary_expressionContext)_localctx).ep = paren_expression();
				((Unary_expressionContext)_localctx).ast =  new AST.UnaryExpr(true, ((Unary_expressionContext)_localctx).ep.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				match(Plus);
				setState(170);
				((Unary_expressionContext)_localctx).ep1 = literal_expression();
				((Unary_expressionContext)_localctx).ast =  new AST.UnaryExpr(true, ((Unary_expressionContext)_localctx).ep1.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(173);
				match(Minus);
				setState(174);
				((Unary_expressionContext)_localctx).em = paren_expression();
				((Unary_expressionContext)_localctx).ast =  new AST.UnaryExpr(false, ((Unary_expressionContext)_localctx).em.ast);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(177);
				match(Minus);
				setState(178);
				((Unary_expressionContext)_localctx).em1 = literal_expression();
				((Unary_expressionContext)_localctx).ast =  new AST.UnaryExpr(false, ((Unary_expressionContext)_localctx).em1.ast);
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

	public static class Paren_expressionContext extends ParserRuleContext {
		public AST.ParenExpr ast;
		public ExpressionContext e;
		public TerminalNode LParen() { return getToken(ExpressionParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(ExpressionParser.RParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Paren_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paren_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterParen_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitParen_expression(this);
		}
	}

	public final Paren_expressionContext paren_expression() throws RecognitionException {
		Paren_expressionContext _localctx = new Paren_expressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_paren_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(LParen);
			setState(184);
			((Paren_expressionContext)_localctx).e = expression(0);
			setState(185);
			match(RParen);
			((Paren_expressionContext)_localctx).ast =  new AST.ParenExpr(((Paren_expressionContext)_localctx).e.ast);
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

	public static class Literal_expressionContext extends ParserRuleContext {
		public AST.LiteralExpr ast;
		public Token id;
		public Int_valueContext i;
		public Float_valueContext f;
		public Token s;
		public ArrayContext a;
		public TerminalNode Identifier() { return getToken(ExpressionParser.Identifier, 0); }
		public Int_valueContext int_value() {
			return getRuleContext(Int_valueContext.class,0);
		}
		public Float_valueContext float_value() {
			return getRuleContext(Float_valueContext.class,0);
		}
		public TerminalNode String() { return getToken(ExpressionParser.String, 0); }
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public Literal_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterLiteral_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitLiteral_expression(this);
		}
	}

	public final Literal_expressionContext literal_expression() throws RecognitionException {
		Literal_expressionContext _localctx = new Literal_expressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_literal_expression);
		try {
			setState(201);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				((Literal_expressionContext)_localctx).id = match(Identifier);
				((Literal_expressionContext)_localctx).ast =  new AST.IdentifierExpr((((Literal_expressionContext)_localctx).id!=null?((Literal_expressionContext)_localctx).id.getText():null));
				}
				break;
			case IntegerLiteral:
			case HexLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				((Literal_expressionContext)_localctx).i = int_value();
				((Literal_expressionContext)_localctx).ast =  new AST.IntegerExpr(((Literal_expressionContext)_localctx).i.i);
				}
				break;
			case FloatLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(193);
				((Literal_expressionContext)_localctx).f = float_value();
				((Literal_expressionContext)_localctx).ast =  new AST.FloatExpr(((Literal_expressionContext)_localctx).f.d);
				}
				break;
			case String:
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				((Literal_expressionContext)_localctx).s = match(String);
				((Literal_expressionContext)_localctx).ast =  new AST.StringExpr((((Literal_expressionContext)_localctx).s!=null?((Literal_expressionContext)_localctx).s.getText():null));
				}
				break;
			case LBracket:
				enterOuterAlt(_localctx, 5);
				{
				setState(198);
				((Literal_expressionContext)_localctx).a = array();
				((Literal_expressionContext)_localctx).ast =  ((Literal_expressionContext)_localctx).a.ast;
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

	public static class If_expressionContext extends ParserRuleContext {
		public AST.IfExpr ast;
		public ExpressionContext cond;
		public ExpressionContext exitWith;
		public ArrayContext arr;
		public ExpressionContext condIsTrue;
		public ExpressionContext condIsFalse;
		public TerminalNode If() { return getToken(ExpressionParser.If, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ExitWith() { return getToken(ExpressionParser.ExitWith, 0); }
		public TerminalNode Then() { return getToken(ExpressionParser.Then, 0); }
		public TerminalNode Else() { return getToken(ExpressionParser.Else, 0); }
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public If_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterIf_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitIf_expression(this);
		}
	}

	public final If_expressionContext if_expression() throws RecognitionException {
		If_expressionContext _localctx = new If_expressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_if_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(If);
			setState(204);
			((If_expressionContext)_localctx).cond = expression(0);
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				{
				setState(205);
				match(ExitWith);
				setState(206);
				((If_expressionContext)_localctx).exitWith = expression(0);
				((If_expressionContext)_localctx).ast =  new AST.IfExpr(((If_expressionContext)_localctx).cond.ast, ((If_expressionContext)_localctx).exitWith.ast, null, AST.IfExpr.Type.ExitWith);
				}
				}
				break;
			case 2:
				{
				{
				setState(209);
				match(Then);
				setState(210);
				((If_expressionContext)_localctx).arr = array();
				((If_expressionContext)_localctx).ast =  new AST.IfExpr(((If_expressionContext)_localctx).cond.ast, ((If_expressionContext)_localctx).arr.ast);
				}
				}
				break;
			case 3:
				{
				{
				setState(213);
				match(Then);
				setState(214);
				((If_expressionContext)_localctx).condIsTrue = expression(0);
				setState(215);
				match(Else);
				setState(216);
				((If_expressionContext)_localctx).condIsFalse = expression(0);
				((If_expressionContext)_localctx).ast =  new AST.IfExpr(((If_expressionContext)_localctx).cond.ast, ((If_expressionContext)_localctx).condIsTrue.ast, ((If_expressionContext)_localctx).condIsFalse.ast, AST.IfExpr.Type.IfThen);
				}
				}
				break;
			case 4:
				{
				{
				setState(219);
				match(Then);
				setState(220);
				((If_expressionContext)_localctx).condIsTrue = expression(0);
				((If_expressionContext)_localctx).ast =  new AST.IfExpr(((If_expressionContext)_localctx).cond.ast, ((If_expressionContext)_localctx).condIsTrue.ast, null, AST.IfExpr.Type.IfThen);
				}
				}
				break;
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

	public static class For_expressionContext extends ParserRuleContext {
		public AST.ForExpr ast;
		public ExpressionContext var;
		public ExpressionContext fromExp;
		public ExpressionContext toExp;
		public ExpressionContext stepExp;
		public ExpressionContext doExp;
		public ExpressionContext arr;
		public TerminalNode For() { return getToken(ExpressionParser.For, 0); }
		public TerminalNode From() { return getToken(ExpressionParser.From, 0); }
		public TerminalNode To() { return getToken(ExpressionParser.To, 0); }
		public TerminalNode Step() { return getToken(ExpressionParser.Step, 0); }
		public TerminalNode Do() { return getToken(ExpressionParser.Do, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public For_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterFor_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitFor_expression(this);
		}
	}

	public final For_expressionContext for_expression() throws RecognitionException {
		For_expressionContext _localctx = new For_expressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_for_expression);
		try {
			setState(253);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(225);
				match(For);
				setState(226);
				((For_expressionContext)_localctx).var = expression(0);
				setState(227);
				match(From);
				setState(228);
				((For_expressionContext)_localctx).fromExp = expression(0);
				setState(229);
				match(To);
				setState(230);
				((For_expressionContext)_localctx).toExp = expression(0);
				setState(231);
				match(Step);
				setState(232);
				((For_expressionContext)_localctx).stepExp = expression(0);
				setState(233);
				match(Do);
				setState(234);
				((For_expressionContext)_localctx).doExp = expression(0);
				((For_expressionContext)_localctx).ast =  new AST.ForVarExpr(((For_expressionContext)_localctx).var.ast, ((For_expressionContext)_localctx).fromExp.ast, ((For_expressionContext)_localctx).toExp.ast, ((For_expressionContext)_localctx).stepExp.ast, ((For_expressionContext)_localctx).doExp.ast);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(237);
				match(For);
				setState(238);
				((For_expressionContext)_localctx).var = expression(0);
				setState(239);
				match(From);
				setState(240);
				((For_expressionContext)_localctx).fromExp = expression(0);
				setState(241);
				match(To);
				setState(242);
				((For_expressionContext)_localctx).toExp = expression(0);
				setState(243);
				match(Do);
				setState(244);
				((For_expressionContext)_localctx).doExp = expression(0);
				((For_expressionContext)_localctx).ast =  new AST.ForVarExpr(((For_expressionContext)_localctx).var.ast, ((For_expressionContext)_localctx).fromExp.ast, ((For_expressionContext)_localctx).toExp.ast, null, ((For_expressionContext)_localctx).doExp.ast);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(247);
				match(For);
				setState(248);
				((For_expressionContext)_localctx).arr = expression(0);
				setState(249);
				match(Do);
				setState(250);
				((For_expressionContext)_localctx).doExp = expression(0);
				((For_expressionContext)_localctx).ast =  new AST.ForArrExpr(((For_expressionContext)_localctx).arr.ast, ((For_expressionContext)_localctx).doExp.ast);
				}
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

	public static class ArrayContext extends ParserRuleContext {
		public AST.Array ast;
		public List<AST.Expr> items;
		public ExpressionContext e1;
		public ExpressionContext e2;
		public TerminalNode LBracket() { return getToken(ExpressionParser.LBracket, 0); }
		public TerminalNode RBracket() { return getToken(ExpressionParser.RBracket, 0); }
		public List<TerminalNode> Comma() { return getTokens(ExpressionParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(ExpressionParser.Comma, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitArray(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_array);
		((ArrayContext)_localctx).items =  new ArrayList<>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(LBracket);
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << String) | (1L << LCurly) | (1L << LBracket) | (1L << Plus) | (1L << Minus) | (1L << LParen) | (1L << If) | (1L << Count) | (1L << For) | (1L << Str) | (1L << Identifier) | (1L << IntegerLiteral) | (1L << FloatLiteral) | (1L << HexLiteral))) != 0)) {
				{
				setState(256);
				((ArrayContext)_localctx).e1 = expression(0);
				_localctx.items.add(((ArrayContext)_localctx).e1.ast);
				}
			}

			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(261);
				match(Comma);
				setState(262);
				((ArrayContext)_localctx).e2 = expression(0);
				_localctx.items.add(((ArrayContext)_localctx).e2.ast);
				}
				}
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(270);
			match(RBracket);
			((ArrayContext)_localctx).ast =  new AST.Array(_localctx.items);
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

	public static class Int_valueContext extends ParserRuleContext {
		public Integer i;
		public Token il;
		public Token hl;
		public TerminalNode IntegerLiteral() { return getToken(ExpressionParser.IntegerLiteral, 0); }
		public TerminalNode HexLiteral() { return getToken(ExpressionParser.HexLiteral, 0); }
		public Int_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterInt_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitInt_value(this);
		}
	}

	public final Int_valueContext int_value() throws RecognitionException {
		Int_valueContext _localctx = new Int_valueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_int_value);
		try {
			setState(277);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntegerLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(273);
				((Int_valueContext)_localctx).il = match(IntegerLiteral);
				((Int_valueContext)_localctx).i =  new Integer((((Int_valueContext)_localctx).il!=null?((Int_valueContext)_localctx).il.getText():null));
				}
				break;
			case HexLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(275);
				((Int_valueContext)_localctx).hl = match(HexLiteral);
				((Int_valueContext)_localctx).i =  new Integer(Integer.decode((((Int_valueContext)_localctx).hl!=null?((Int_valueContext)_localctx).hl.getText():null)));
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

	public static class Float_valueContext extends ParserRuleContext {
		public Double d;
		public Token fl;
		public TerminalNode FloatLiteral() { return getToken(ExpressionParser.FloatLiteral, 0); }
		public Float_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_float_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterFloat_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitFloat_value(this);
		}
	}

	public final Float_valueContext float_value() throws RecognitionException {
		Float_valueContext _localctx = new Float_valueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_float_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			((Float_valueContext)_localctx).fl = match(FloatLiteral);
			((Float_valueContext)_localctx).d =  new Double((((Float_valueContext)_localctx).fl!=null?((Float_valueContext)_localctx).fl.getText():null));
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
		case 4:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 18);
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 10);
		case 5:
			return precpred(_ctx, 9);
		case 6:
			return precpred(_ctx, 8);
		case 7:
			return precpred(_ctx, 7);
		case 8:
			return precpred(_ctx, 6);
		case 9:
			return precpred(_ctx, 5);
		case 10:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\64\u011d\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\7\2#\n\2\f\2"+
		"\16\2&\13\2\3\2\3\2\5\2*\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3\64\n"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5?\n\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6_\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u0097\n\6\f\6\16\6\u009a"+
		"\13\6\3\7\3\7\3\7\3\7\7\7\u00a0\n\7\f\7\16\7\u00a3\13\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00b8"+
		"\n\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\5\n\u00cc\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00e2\n\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0100\n\f\3\r\3\r\3\r\3\r\5"+
		"\r\u0106\n\r\3\r\3\r\3\r\3\r\7\r\u010c\n\r\f\r\16\r\u010f\13\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\5\16\u0118\n\16\3\17\3\17\3\17\3\17\2\3\n\20"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\3\3\2!&\2\u0134\2$\3\2\2\2\4\63"+
		"\3\2\2\2\6\65\3\2\2\2\b:\3\2\2\2\n^\3\2\2\2\f\u00a1\3\2\2\2\16\u00b7\3"+
		"\2\2\2\20\u00b9\3\2\2\2\22\u00cb\3\2\2\2\24\u00cd\3\2\2\2\26\u00ff\3\2"+
		"\2\2\30\u0101\3\2\2\2\32\u0117\3\2\2\2\34\u0119\3\2\2\2\36\37\5\4\3\2"+
		"\37 \7(\2\2 !\b\2\1\2!#\3\2\2\2\"\36\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3"+
		"\2\2\2%\'\3\2\2\2&$\3\2\2\2\')\5\4\3\2(*\7(\2\2)(\3\2\2\2)*\3\2\2\2*+"+
		"\3\2\2\2+,\b\2\1\2,\3\3\2\2\2-.\5\6\4\2./\b\3\1\2/\64\3\2\2\2\60\61\5"+
		"\n\6\2\61\62\b\3\1\2\62\64\3\2\2\2\63-\3\2\2\2\63\60\3\2\2\2\64\5\3\2"+
		"\2\2\65\66\7)\2\2\66\67\7\'\2\2\678\5\n\6\289\b\4\1\29\7\3\2\2\2:>\7\6"+
		"\2\2;<\5\2\2\2<=\b\5\1\2=?\3\2\2\2>;\3\2\2\2>?\3\2\2\2?@\3\2\2\2@A\7\7"+
		"\2\2AB\b\5\1\2B\t\3\2\2\2CD\b\6\1\2DE\7\32\2\2EF\5\n\6\25FG\b\6\1\2G_"+
		"\3\2\2\2HI\7 \2\2IJ\5\n\6\23JK\b\6\1\2K_\3\2\2\2LM\5\16\b\2MN\b\6\1\2"+
		"N_\3\2\2\2OP\5\20\t\2PQ\b\6\1\2Q_\3\2\2\2RS\5\24\13\2ST\b\6\1\2T_\3\2"+
		"\2\2UV\5\26\f\2VW\b\6\1\2W_\3\2\2\2XY\5\b\5\2YZ\b\6\1\2Z_\3\2\2\2[\\\5"+
		"\22\n\2\\]\b\6\1\2]_\3\2\2\2^C\3\2\2\2^H\3\2\2\2^L\3\2\2\2^O\3\2\2\2^"+
		"R\3\2\2\2^U\3\2\2\2^X\3\2\2\2^[\3\2\2\2_\u0098\3\2\2\2`a\f\24\2\2ab\7"+
		"\32\2\2bc\5\n\6\25cd\b\6\1\2d\u0097\3\2\2\2ef\f\20\2\2fg\7\17\2\2gh\5"+
		"\n\6\21hi\b\6\1\2i\u0097\3\2\2\2jk\f\17\2\2kl\7\f\2\2lm\5\n\6\20mn\b\6"+
		"\1\2n\u0097\3\2\2\2op\f\16\2\2pq\7\r\2\2qr\5\n\6\17rs\b\6\1\2s\u0097\3"+
		"\2\2\2tu\f\f\2\2uv\7\n\2\2vw\5\n\6\rwx\b\6\1\2x\u0097\3\2\2\2yz\f\13\2"+
		"\2z{\7\13\2\2{|\5\n\6\f|}\b\6\1\2}\u0097\3\2\2\2~\177\f\n\2\2\177\u0080"+
		"\t\2\2\2\u0080\u0081\5\n\6\13\u0081\u0082\b\6\1\2\u0082\u0097\3\2\2\2"+
		"\u0083\u0084\f\t\2\2\u0084\u0085\7\24\2\2\u0085\u0086\5\n\6\n\u0086\u0087"+
		"\b\6\1\2\u0087\u0097\3\2\2\2\u0088\u0089\f\b\2\2\u0089\u008a\7\23\2\2"+
		"\u008a\u008b\5\n\6\t\u008b\u008c\b\6\1\2\u008c\u0097\3\2\2\2\u008d\u008e"+
		"\f\7\2\2\u008e\u008f\7\31\2\2\u008f\u0090\5\n\6\b\u0090\u0091\b\6\1\2"+
		"\u0091\u0097\3\2\2\2\u0092\u0093\f\r\2\2\u0093\u0094\7\16\2\2\u0094\u0095"+
		"\b\6\1\2\u0095\u0097\5\f\7\2\u0096`\3\2\2\2\u0096e\3\2\2\2\u0096j\3\2"+
		"\2\2\u0096o\3\2\2\2\u0096t\3\2\2\2\u0096y\3\2\2\2\u0096~\3\2\2\2\u0096"+
		"\u0083\3\2\2\2\u0096\u0088\3\2\2\2\u0096\u008d\3\2\2\2\u0096\u0092\3\2"+
		"\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\13\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009c\5\n\6\2\u009c\u009d\7\16\2"+
		"\2\u009d\u009e\b\7\1\2\u009e\u00a0\3\2\2\2\u009f\u009b\3\2\2\2\u00a0\u00a3"+
		"\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a4\u00a5\5\n\6\2\u00a5\u00a6\b\7\1\2\u00a6\r\3\2\2\2"+
		"\u00a7\u00a8\7\n\2\2\u00a8\u00a9\5\20\t\2\u00a9\u00aa\b\b\1\2\u00aa\u00b8"+
		"\3\2\2\2\u00ab\u00ac\7\n\2\2\u00ac\u00ad\5\22\n\2\u00ad\u00ae\b\b\1\2"+
		"\u00ae\u00b8\3\2\2\2\u00af\u00b0\7\13\2\2\u00b0\u00b1\5\20\t\2\u00b1\u00b2"+
		"\b\b\1\2\u00b2\u00b8\3\2\2\2\u00b3\u00b4\7\13\2\2\u00b4\u00b5\5\22\n\2"+
		"\u00b5\u00b6\b\b\1\2\u00b6\u00b8\3\2\2\2\u00b7\u00a7\3\2\2\2\u00b7\u00ab"+
		"\3\2\2\2\u00b7\u00af\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b8\17\3\2\2\2\u00b9"+
		"\u00ba\7\20\2\2\u00ba\u00bb\5\n\6\2\u00bb\u00bc\7\21\2\2\u00bc\u00bd\b"+
		"\t\1\2\u00bd\21\3\2\2\2\u00be\u00bf\7)\2\2\u00bf\u00cc\b\n\1\2\u00c0\u00c1"+
		"\5\32\16\2\u00c1\u00c2\b\n\1\2\u00c2\u00cc\3\2\2\2\u00c3\u00c4\5\34\17"+
		"\2\u00c4\u00c5\b\n\1\2\u00c5\u00cc\3\2\2\2\u00c6\u00c7\7\3\2\2\u00c7\u00cc"+
		"\b\n\1\2\u00c8\u00c9\5\30\r\2\u00c9\u00ca\b\n\1\2\u00ca\u00cc\3\2\2\2"+
		"\u00cb\u00be\3\2\2\2\u00cb\u00c0\3\2\2\2\u00cb\u00c3\3\2\2\2\u00cb\u00c6"+
		"\3\2\2\2\u00cb\u00c8\3\2\2\2\u00cc\23\3\2\2\2\u00cd\u00ce\7\25\2\2\u00ce"+
		"\u00e1\5\n\6\2\u00cf\u00d0\7\30\2\2\u00d0\u00d1\5\n\6\2\u00d1\u00d2\b"+
		"\13\1\2\u00d2\u00e2\3\2\2\2\u00d3\u00d4\7\26\2\2\u00d4\u00d5\5\30\r\2"+
		"\u00d5\u00d6\b\13\1\2\u00d6\u00e2\3\2\2\2\u00d7\u00d8\7\26\2\2\u00d8\u00d9"+
		"\5\n\6\2\u00d9\u00da\7\27\2\2\u00da\u00db\5\n\6\2\u00db\u00dc\b\13\1\2"+
		"\u00dc\u00e2\3\2\2\2\u00dd\u00de\7\26\2\2\u00de\u00df\5\n\6\2\u00df\u00e0"+
		"\b\13\1\2\u00e0\u00e2\3\2\2\2\u00e1\u00cf\3\2\2\2\u00e1\u00d3\3\2\2\2"+
		"\u00e1\u00d7\3\2\2\2\u00e1\u00dd\3\2\2\2\u00e2\25\3\2\2\2\u00e3\u00e4"+
		"\7\33\2\2\u00e4\u00e5\5\n\6\2\u00e5\u00e6\7\34\2\2\u00e6\u00e7\5\n\6\2"+
		"\u00e7\u00e8\7\35\2\2\u00e8\u00e9\5\n\6\2\u00e9\u00ea\7\36\2\2\u00ea\u00eb"+
		"\5\n\6\2\u00eb\u00ec\7\37\2\2\u00ec\u00ed\5\n\6\2\u00ed\u00ee\b\f\1\2"+
		"\u00ee\u0100\3\2\2\2\u00ef\u00f0\7\33\2\2\u00f0\u00f1\5\n\6\2\u00f1\u00f2"+
		"\7\34\2\2\u00f2\u00f3\5\n\6\2\u00f3\u00f4\7\35\2\2\u00f4\u00f5\5\n\6\2"+
		"\u00f5\u00f6\7\37\2\2\u00f6\u00f7\5\n\6\2\u00f7\u00f8\b\f\1\2\u00f8\u0100"+
		"\3\2\2\2\u00f9\u00fa\7\33\2\2\u00fa\u00fb\5\n\6\2\u00fb\u00fc\7\37\2\2"+
		"\u00fc\u00fd\5\n\6\2\u00fd\u00fe\b\f\1\2\u00fe\u0100\3\2\2\2\u00ff\u00e3"+
		"\3\2\2\2\u00ff\u00ef\3\2\2\2\u00ff\u00f9\3\2\2\2\u0100\27\3\2\2\2\u0101"+
		"\u0105\7\b\2\2\u0102\u0103\5\n\6\2\u0103\u0104\b\r\1\2\u0104\u0106\3\2"+
		"\2\2\u0105\u0102\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u010d\3\2\2\2\u0107"+
		"\u0108\7\22\2\2\u0108\u0109\5\n\6\2\u0109\u010a\b\r\1\2\u010a\u010c\3"+
		"\2\2\2\u010b\u0107\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\u0110\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0111\7\t"+
		"\2\2\u0111\u0112\b\r\1\2\u0112\31\3\2\2\2\u0113\u0114\7*\2\2\u0114\u0118"+
		"\b\16\1\2\u0115\u0116\7/\2\2\u0116\u0118\b\16\1\2\u0117\u0113\3\2\2\2"+
		"\u0117\u0115\3\2\2\2\u0118\33\3\2\2\2\u0119\u011a\7+\2\2\u011a\u011b\b"+
		"\17\1\2\u011b\35\3\2\2\2\21$)\63>^\u0096\u0098\u00a1\u00b7\u00cb\u00e1"+
		"\u00ff\u0105\u010d\u0117";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}