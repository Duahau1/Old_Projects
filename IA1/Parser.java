// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

import java.util.*;

public class Parser {
    private Scanner scanner;
/**
 * This method is used to check if there is a match between 2 tokens
 * @param s
 * @throws SyntaxException
 */
    private void match(String s) throws SyntaxException {
	scanner.match(new Token(s));
    }
/**
 * This method return the current token in the scanner
 * @return the current token
 * @throws SyntaxException
 */
    private Token curr() throws SyntaxException {
	return scanner.curr();
    }

  /**
 * This method return the latest position in the scanner
 * @return the latest position in scanning
 */
    private int pos() {
	return scanner.pos();
    }
/**
 * This method is parsing the mul op whether it is * or /
 * @return a node mul 
 * @throws SyntaxException
 */
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
    /**
     * This method is parsing the add op whether it is + or -
     * @return a node add 
     * @throws SyntaxException
     */
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
   /**
    * This method parse a new node called relop for boolean expression
    * @return new node relop
    * @throws SyntaxException
    */
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
    
    /**
     * This method is parsing the fact block whether it is ( or ) or id
     * @return a node fact 
     * @throws SyntaxException
     */
    private NodeFact parseFact() throws SyntaxException {
    	if (curr().equals(new Token("-"))) {
			match("-");
			NodeFact fact = parseFact();
			return new NodeUnaryMinus(fact);
		}
    	if (curr().equals(new Token("("))) {
	    match("(");
	    NodeExpr expr=parseExpr();
	    match(")");
	    return new NodeFactExpr(expr);
	}
	if (curr().equals(new Token("id"))) {
	    Token id=curr();
	    match("id");
	    return new NodeFactId(pos(),id.lex());
	}
	Token num=curr();
	match("num");
	return new NodeFactNum(num.lex());
    }
    /**
     * This method is parsing the term block 
     * @return a node term
     * @throws SyntaxException
     */
    private NodeTerm parseTerm() throws SyntaxException {
	NodeFact fact=parseFact();
	NodeMulop mulop=parseMulop();
	if (mulop==null)
	    return new NodeTerm(fact,null,null);
	NodeTerm term=parseTerm();
	term.append(new NodeTerm(fact,mulop,null));
	return term;
    }
    /**
     * This method is parsing the expr block
     * @return a node expr 
     * @throws SyntaxException
     */
    private NodeExpr parseExpr() throws SyntaxException {
	NodeTerm term=parseTerm();
	NodeAddop addop=parseAddop();
	if (addop==null)
	    return new NodeExpr(term,null,null);
	NodeExpr expr=parseExpr();
	expr.append(new NodeExpr(term,addop,null));
	return expr;
    }
   /**
    * This method parse the boolean expression block
    * @return a new node boolean expression
    * @throws SyntaxException
    */
    private NodeBoolExpr parseBoolExpr() throws SyntaxException {
		NodeExpr expr1 = parseExpr();
		NodeRelop relop = parseRelop();
		NodeExpr expr2 = parseExpr();
		return new NodeBoolExpr(expr1, relop, expr2);
	}
    /**
     * This method is parsing the assn block 
     * @return a node assn 
     * @throws SyntaxException
     */
    private NodeAssn parseAssn() throws SyntaxException {
	Token id=curr();
	match("id");
	match("=");
	NodeExpr expr=parseExpr();
	NodeAssn assn=new NodeAssn(id.lex(),expr);
	return assn;
    }
   /**
    * This method parse the read node
    * @return a new read node
    * @throws SyntaxException
    */
    private NodeStmtRd parseRd() throws SyntaxException {
		match("rd");
		String id = curr().lex();
		match("id");
		return new NodeStmtRd(id);
	}
/**
 * This method parse the write node
 * @return a new write node
 * @throws SyntaxException
 */
	private NodeStmtWr parseWr() throws SyntaxException {
		match("wr");
		NodeExpr expr = parseExpr();
		return new NodeStmtWr(expr);
	}
    
  /**
   * This method parse the stmtif node
   * @return the new stmtif node
   * @throws SyntaxException
   */
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
/**
 * This method parse the while block
 * @return a new while node
 * @throws SyntaxException
 */
	private NodeStmtWhile parseWhile() throws SyntaxException {
		match("while");
		NodeBoolExpr boolexpr = parseBoolExpr();
		match("do");
		NodeStmt stmt = parseStmt();
		return new NodeStmtWhile(boolexpr, stmt);
	}

    /**
     * This method is parsing the stmt block
     * @return a node stmt 
     * @throws SyntaxException
     */
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
		return retval;
    }
   /**
    * This method parse the block
    * @return a new block node according to the grammar
    * @throws SyntaxException
    */
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
/**
 * This method parse the program
 * @return the new Program node
 * @throws SyntaxException
 */
	private NodeProgram parseProgram() throws SyntaxException {
		return new NodeProgram(parseBlock());
	}
    /**
     * This method is parsing the big node block 
     * @return a node 
     * @throws SyntaxException
     */
    public Node parse(String program) throws SyntaxException {
	scanner=new Scanner(program);
	scanner.next();
	return parseProgram();
    }

}
