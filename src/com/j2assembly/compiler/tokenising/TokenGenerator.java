package com.j2assembly.compiler.tokenising;

/**
 * A helper class for generating tokens.
 */
public interface TokenGenerator {

	/**
	 * Create Token is a helper function for the TokenType enum function of the same name.
	 * @param tokenData The data that needs to be used to make the token.
	 * @return A new Token.
	 * @see Token
	 * @see TokenType
	 * @see Tokenizer
	 */
	Token createToken(String tokenData);
}
