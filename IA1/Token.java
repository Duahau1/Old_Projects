// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")

public class Token {

    private String token;
    private String lexeme;
/**
 * The constructor of this class. Create a token with a token and its lexeme
 * @param String token, string lexeme
 */
    public Token(String token, String lexeme) {
	this.token=token;
	this.lexeme=lexeme;
    }
/**
 * The other constructor of this class
 * @param String token
 */
    public Token(String token) {
	this(token,token);
    }
/** This method return the token part of the token
 * @return The token 
 */
    public String tok() { return token; } 
    /** This method return the lexeme part of the token
     * @return The lexeme 
     */    
    public String lex() { return lexeme; }
    /** This method compare between the two token without caring about their lexemes
     * @param Token t
     * @return The token 
     */
    public boolean equals(Token t) {
	return token.equals(t.token);
    }
    /** Tostring method
     * @return detail of the token 
     */
    public String toString() {
	return "<"+tok()+","+lex()+">";
    }

}
