package com.j2assembly.compiler.tokenising;

/**
 * Created by Jordan Laptop on 9/08/2017.
 */
public class Token {

	private String token;
	private TokenType tokenType;

	/**
	 * Constructs a Token with a String value and a distinct Token Type
	 * @param token The Token Value.
	 * @param tokenType The Token Type.
	 * @see TokenType
	 */
	public Token(String token, TokenType tokenType) {
		this.token = token;
		this.tokenType = tokenType;
	}

	/**
	 * Getter for property 'token'.
	 *
	 * @return Value for property 'token'.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Getter for property 'tokenType'.
	 *
	 * @return Value for property 'tokenType'.
	 */
	public TokenType getTokenType() {
		return tokenType;
	}
}
