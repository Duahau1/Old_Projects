// This class is a scanner for the program
// and programming language being interpreted.

import java.util.*;

public class Scanner {

	private String program;	// source program being interpreted
	private int pos;		// index of next char in program
	private Token token;	// last/current scanned token
	// sets of various characters and lexemes
	private Set<String> whitespace=new HashSet<String>();
	private Set<String> digits=new HashSet<String>();
	private Set<String> letters=new HashSet<String>();
	private Set<String> legits=new HashSet<String>();
	private Set<String> keywords=new HashSet<String>();
	private Set<String> operators=new HashSet<String>();


	/**
	 * initializers for previous sets
	 * @param s
	 * @param lo
	 * @param hi
	 */
	private void fill(Set<String> s, char lo, char hi) {
		for (char c=lo; c<=hi; c++)
			s.add(c+"");
	}    
	/**
	 * initializers for whitespace
	 * @param s
	 */
	private void initWhitespace(Set<String> s) {
		s.add(" ");
		s.add("\n");
		s.add("\t");
	}
	/**
	 * initializers for digits
	 * @param s
	 */
	private void initDigits(Set<String> s) {
		fill(s,'0','9');
	}
	/**
	 * initializers for Letters
	 * @param s
	 */
	private void initLetters(Set<String> s) {
		fill(s,'A','Z');
		fill(s,'a','z');
	}
	/**
	 * initializers for letters and numbers /identifiers
	 * @param s
	 */

	private void initLegits(Set<String> s) {
		s.addAll(letters);
		s.addAll(digits);
	}
	/**
	 * initializers for operators
	 * @param s
	 */

	private void initOperators(Set<String> s) {
		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		s.add(";");
		s.add("<");
		s.add(">");
		s.add(">=");
		s.add("<=");
		s.add("<>");
		s.add("==");
	}
	/**
	 * initializers for unaryMinus
	 * @param s
	 */

	private void initUnaryMinus(Set<String> s) {
		s.add("+-");
		s.add("--");
		s.add("*-");
		s.add("/-");
	}
	/**
	 * initializers for reserved keywords
	 * @param s
	 */
	private void initKeywords(Set<String> s) {
		s.add("rd");
		s.add("wr");
		s.add("if");
		s.add("then");
		s.add("else");
		s.add("while");
		s.add("do");
		s.add("begin");
		s.add("end");
	}

	/**
	 *  Constructor of this class
	 *  - squirrel-away source program   
	 *  - initialize sets
	 * @param program
	 */

	public Scanner(String program) {
		this.program=program;
		pos=0;
		token=null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);

	}


	/**
	 * This method notify when the scanner hits EOF
	 * 
	 * @return true when the scanner reaches the end of the program
	 */
	public boolean done() {
		return pos>=program.length();
	}
	/**
	 * This method tells you how many characters within the same set is in front of the current position
	 * (often being disconnected by whitespace) then jump to the next type of token in the set.
	 * @param s the type of set
	 */

	private void many(Set<String> s) {
		while (!done() && s.contains(program.charAt(pos)+""))
			pos++;
	}
	/**
	 * This method increase the pos till it finds a specific character and move to the character after it
	 * @param c the input character we need to find
	 */

	private void past(char c) {
		while (!done() && c!=program.charAt(pos))
			pos++;
		if (!done() && c==program.charAt(pos))
			pos++;
	}

	/**
	 * This method scans/processes numbers and make token based on scanning characters 
	 * 
	 */
	private void nextNumber() {
		int old=pos;
		many(digits);
		//Supporting double value
		if (!done() && program.charAt(pos) == '.') {
			pos++;
			many(digits);
		}
		token=new Token("num",program.substring(old,pos));
	}
	/**
	 * This method scans/processes numbers/letter and make token based on scanning characters
	 * Usually they are to process identifier/id 
	 * 
	 */
	private void nextKwId() {
		int old=pos;
		many(letters);
		many(legits);
		String lexeme=program.substring(old,pos);
		token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme);
	}
	/**
	 * This method scans/processes operators and make token based on scanning characters 
	 * We have 2 kinds of operators
	 * 
	 */
	private void nextOp() {
		int old=pos;
		pos=old+2;
		if (!done()) {
			String lexeme=program.substring(old,pos);
			if (operators.contains(lexeme)) {
				token=new Token(lexeme); // two-char operator
				return;
			}
		}
		pos=old+1;
		String lexeme=program.substring(old,pos);
		token=new Token(lexeme); // one-char operator
	}

	/** This method determines the kind of the next token (e.g., "id"),
	 *and calls a method to scan that token's lexeme (e.g., "foo").
	 * @return false if at end of file
	 */
	public boolean next() {

		if (done()) {
			//token=new Token("EOF");
			token=null;
			return false;
		}
		many(whitespace); 
		String c=program.charAt(pos)+"";
		if (c.equals("#")) {
			++pos;
			while (!done()) {
				if (program.charAt(pos) == '#') {
					++pos;
					return next();
				}
				++pos;
			}
			System.err.println("comment needs closing # at position " + pos);		
			return false;
		}
		if (digits.contains(c)||c.equals("."))
			nextNumber();
		else if (letters.contains(c)) 
			nextKwId();
		else if (operators.contains(c))
			nextOp();
		else {
			System.err.println("illegal character at position "+pos);
			pos++;
			return next();
		}
		return true;
	}

	/** This method scans the next lexeme,if the current token is the expected token.
	 * @throws SyntaxException
	 * @param token t 
	 */
	public void match(Token t) throws SyntaxException {
		if (curr() == null) {
			throw new SyntaxException(pos,t,new Token("EMPTY"));
		}
		if (!t.equals(curr()))
			throw new SyntaxException(pos,t,curr());
		next();
	}
	/**
	 * This method return the current token
	 * @return the current token 
	 */
	public Token curr() throws SyntaxException {

		if (token == null && pos == 0)
			throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));
		else if (token == null) 
			token = new Token("");
		return token;

	}

	/**	This method returns the position of the scanner in the program
	 * @return the position of the Scanner in its program.
	 */
	public int pos() { return pos; }

	// for unit testing
	public static void main(String[] args) {
		try {
			Scanner scanner=new Scanner(args[0]);
			while (scanner.next())
				System.out.println(scanner.curr());
		} catch (SyntaxException e) {
			System.err.println(e);
		}
	}

}