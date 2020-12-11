import java.util.*;

/**
 *  This class is a recursive-descent parser, modeled after the programming
 *  language's grammar. It constructs and has-a Scanner for the program being
 *  parsed.
 *  @author jbuffenb
 *  @author arjunshukla
 */
public class Parser {

	private Scanner scanner;

	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}

	private Token curr() throws SyntaxException {
		return scanner.curr();
	}
	
	private boolean done() {
		return scanner.done();
	}
	
	private int pos() {
		return scanner.pos();
	}

	// The following functions parse each type of Node

	private NodeMulop parseMulop() throws SyntaxException {
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(),"*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(),"/");
		}
		return null;
	}

	private NodeAddop parseAddop() throws SyntaxException {
		if (curr().equals(new Token("+"))) {
			match("+");
			return new NodeAddop(pos(),"+");
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeAddop(pos(),"-");
		}
		return null;
	}

	private NodeRelop parseRelop() throws SyntaxException {
		if (curr().equals(new Token("<"))) {
			match("<");
			return new NodeRelop(pos(),"<");
		}
		if (curr().equals(new Token("<="))) {
			match("<=");
			return new NodeRelop(pos(),"<=");
		}
		if (curr().equals(new Token(">"))) {
			match(">");
			return new NodeRelop(pos(),">");
		}
		if (curr().equals(new Token(">="))) {
			match(">=");
			return new NodeRelop(pos(),">=");
		}
		if (curr().equals(new Token("<>"))) {
			match("<>");
			return new NodeRelop(pos(),"<>");
		}
		if (curr().equals(new Token("=="))) {
			match("==");
			return new NodeRelop(pos(),"==");
		}
		return null;
	}

	private NodeFact parseFact() throws SyntaxException {
		if (curr().equals(new Token("-"))) {
			match("-");
			NodeFact fact = parseFact();
			return new NodeFactFact(fact);
		}			
		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr = parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id = curr();
			match("id");
			return new NodeFactId(pos(),id.lex());
		}
		Token num = curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact = parseFact();
		NodeMulop mulop = parseMulop();
		if (mulop == null)
			return new NodeTerm(fact,null,null);
		NodeTerm term = parseTerm();
		term.append(new NodeTerm(fact,mulop,null));
		return term;
	}

	private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term = parseTerm();
		NodeAddop addop = parseAddop();
		if (addop == null)
			return new NodeExpr(term,null,null);
		NodeExpr expr = parseExpr();
		expr.append(new NodeExpr(term,addop,null));
		return expr;
	}

	private NodeBoolExpr parseBoolExpr() throws SyntaxException {
		NodeExpr expr1 = parseExpr();
		NodeRelop relop = parseRelop();
		NodeExpr expr2 = parseExpr();
		return new NodeBoolExpr(expr1, relop, expr2);
	}

	private NodeAssn parseAssn() throws SyntaxException {
		Token id = curr();
		match("id");
		match("=");
		NodeExpr expr = parseExpr();
		NodeAssn assn = new NodeAssn(id.lex(),expr);
		return assn;
	}

	private NodeRd parseRd() throws SyntaxException {
		match("rd");
		String id = curr().lex();
		match("id");
		return new NodeRd(id);
	}

	private NodeWr parseWr() throws SyntaxException {
		match("wr");
		NodeExpr expr = parseExpr();
		return new NodeWr(expr);
	}

	private NodeStmtIf parseStmtIf() throws SyntaxException {
		match("if");
		NodeBoolExpr boolexpr = parseBoolExpr();
		match("then");
		NodeStmt ifstmt = parseStmt();
		NodeStmt elsestmt = null;
		if (curr().equals(new Token("else"))) {
			match("else");
			elsestmt = parseStmt();
		}
		return new NodeStmtIf(boolexpr, ifstmt, elsestmt);
	}

	private NodeWhile parseWhile() throws SyntaxException {
		match("while");
		NodeBoolExpr boolexpr = parseBoolExpr();
		match("do");
		NodeStmt stmt = parseStmt();
		return new NodeWhile(boolexpr, stmt);
	}

	private NodeStmt parseStmt() throws SyntaxException {
		NodeStmt retval = null;
		if (curr().equals(new Token("if"))) {
			retval = parseStmtIf();
		}
		else if (curr().equals(new Token("wr"))) {
			retval =  parseWr();
		}
		else if (curr().equals(new Token("rd"))) {
			retval = parseRd();
		}
		else if (curr().equals(new Token("while"))) {
			retval = parseWhile();
		}
		else if (curr().equals(new Token("begin"))) {
			match("begin");
			retval = parseBlock();
			match("end");
		}
		else if (curr().equals(new Token("id"))) {
			retval = parseAssn();
		}
		else {
			throw new SyntaxException(pos(), new Token("statement token"), curr());
		}

//		match(";");
		return retval;
	}

	private NodeBlock parseBlock() throws SyntaxException {
		NodeStmt stmt = parseStmt();
		if (!curr().equals((new Token(";")))) {
			if (curr().equals(new Token("")) || curr().equals(new Token("end"))) {
				return new NodeBlock(stmt,null);
			}
			else {
				throw new SyntaxException(pos(), new Token("block end"), curr());
			}
		}
		match(";");
		NodeBlock block = new NodeBlock(stmt,null);
		block.append(parseBlock());
		return block;
	}

	private NodeProg parseProg() throws SyntaxException {
		return new NodeProg(parseBlock());
	}
	
	/**
	 * Returns a Node. This Node is the root of a parse tree for the given
	 * program.
	 * @param program the program to parse
	 * @return the root Node of the resulting parse tree.
	 */
	public Node parse(String program) throws SyntaxException {
		scanner = new Scanner(program);
		scanner.next();
		return parseProg();
	}
}
