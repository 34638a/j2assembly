package com.j2assembly.compiler.tokenising;

import java.util.regex.Pattern;

/**
 * Created by Jordan Laptop on 10/08/2017.
 */
public enum TokenType {

	//A Token is used for flow control.
	
	//Common flow control characters
	TOKEN_LBRACKET(Pattern.compile("^(\\()"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("(", TOKEN_LBRACKET);
		}
	}),
	TOKEN_RBRACKET(Pattern.compile("^(\\))"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(")", TOKEN_RBRACKET);
		}
	}),
	TOKEN_LCBRACKET(Pattern.compile("^(\\{)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("{", TOKEN_LCBRACKET);
		}
	}),
	TOKEN_RCBRACKET(Pattern.compile("^(})"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("}", TOKEN_RCBRACKET);
		}
	}),
	TOKEN_LBBRACKET(Pattern.compile("^(\\[)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("[", TOKEN_LBBRACKET);
		}
	}),
	TOKEN_RBBRACKET(Pattern.compile("^(])"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("]", TOKEN_RBBRACKET);
		}
	}),
	TOKEN_COMMA(Pattern.compile("^(,)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(",", TOKEN_COMMA);
		}
	}),
	TOKEN_SEMICOLON(Pattern.compile("^(;)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(";", TOKEN_SEMICOLON);
		}
	}),
	TOKEN_ASSIGNMENT(Pattern.compile("^(=)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("=", TOKEN_ASSIGNMENT);
		}
	}),

	//Logical Flow Control Tokens
	TOKEN_IF(Pattern.compile("^(if\\s*)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, TOKEN_IF);
		}
	}),
	TOKEN_WHILE(Pattern.compile("^while\\s*"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, TOKEN_WHILE);
		}
	}),
	TOKEN_NEW(Pattern.compile("^(new\\s*)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, TOKEN_NEW);
		}
	}),
	TOKEN_RETURN(Pattern.compile("^(return\\s*.*?;)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData.replaceFirst("return\\s*", ""), TOKEN_PACKAGE);
		}
	}),
	TOKEN_PACKAGE(Pattern.compile("^(package\\s*.*?;)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData.replaceFirst("package\\s*", ""), TOKEN_PACKAGE);
		}
	}),
	TOKEN_IMPORT(Pattern.compile("^(import\\s*.*?;)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData.replaceFirst("import\\s*", ""), TOKEN_IMPORT);
		}
	}),
	TOKEN_STATIC(Pattern.compile("^(static\\s*)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData.trim(), TOKEN_STATIC);
		}
	}),
	TOKEN_VOID(Pattern.compile("^(void\\s*)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData.trim(), TOKEN_VOID);
		}
	}),

	//Protection Tokens
	TOKEN_PROTECTION_PUBLIC(Pattern.compile("^(public)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, TOKEN_PROTECTION_PUBLIC);
		}
	}),
	TOKEN_PROTECTION_PRIVATE(Pattern.compile("^(private)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, TOKEN_PROTECTION_PRIVATE);
		}
	}),
	TOKEN_PROTECTION_PROTECTED(Pattern.compile("^(protected)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, TOKEN_PROTECTION_PROTECTED);
		}
	}),

	TOKEN_CLASS(Pattern.compile("^([a-zA-Z_][a-zA-Z0-9_]*\\.)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData.replaceAll("\\.",""), TOKEN_CLASS);
		}
	}),
	//I don't think this will be triggered :(
	TOKEN_SUBCLASS(Pattern.compile("^(\\.[a-zA-Z_][a-zA-Z0-9_]*\\.)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData.replaceAll("\\.",""), TOKEN_SUBCLASS);
		}
	}),
	TOKEN_FUNCTION(Pattern.compile("^([a-zA-Z_][a-zA-Z0-9_]*\\()"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, TOKEN_FUNCTION);
		}
	}),
	//Grouping Tokens
	/*


	TOKEN_ENUM(Pattern.compile(""), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return null;
		}
	}),
	TOKEN_INTERFACE(Pattern.compile(""), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return null;
		}
	}),
	TOKEN_ARRAY(Pattern.compile(""), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return null;
		}
	}),
	//*/



	//Number token
	TOKEN_NUMBER(Pattern.compile("^((-)?[0-9]([0-9]*)?)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData , TOKEN_NUMBER);
		}
	}),

	//String literal
	TOKEN_STRING(Pattern.compile("^(\".*\")"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData.substring(1, tokenData.length()-1), TOKEN_STRING);
		}
	}),

	//A variable for all intents and purposes
	IDENTIFIER(Pattern.compile("^([a-zA-Z_][a-zA-Z0-9_]*)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, IDENTIFIER);
		}
	}),

	//Math related tokens
	TOKEN_MATH_ADDITION(Pattern.compile("\\+"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("+" , TOKEN_MATH_ADDITION);
		}
	}),
	TOKEN_MATH_SUBTRACTION(Pattern.compile("-"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("-" , TOKEN_MATH_SUBTRACTION);
		}
	}),
	TOKEN_MATH_MULTIPLY(Pattern.compile("\\*"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("*" , TOKEN_MATH_MULTIPLY);
		}
	}),
	TOKEN_MATH_DIVIDE(Pattern.compile("/"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("/" , TOKEN_MATH_DIVIDE);
		}
	}),
	TOKEN_MATH_MODULUS(Pattern.compile("%"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("%" , TOKEN_MATH_MODULUS);
		}
	}),

	//Boolean Math related tokens
	TOKEN_MATH_AND(Pattern.compile("^(&)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("&" , TOKEN_MATH_AND);
		}
	}),
	TOKEN_MATH_OR(Pattern.compile("^(\\|)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("|" , TOKEN_MATH_OR);
		}
	}),
	TOKEN_MATH_XOR(Pattern.compile("^(\\^)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("^" , TOKEN_MATH_XOR);
		}
	}),
	TOKEN_MATH_NOT(Pattern.compile("^(~)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("~" , TOKEN_MATH_NOT);
		}
	}),

	//Bit Shifting Math related tokens
	TOKEN_MATH_SHIFT_LEFT(Pattern.compile("^(<<)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("<<" , TOKEN_MATH_SHIFT_LEFT);
		}
	}),
	TOKEN_MATH_SHIFT_RIGHT(Pattern.compile("^(>>)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(">>" , TOKEN_MATH_SHIFT_LEFT);
		}
	}),

	//Comparison tokens
	TOKEN_COMPARE_EQUALS(Pattern.compile("^(==)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("==" , TOKEN_COMPARE_EQUALS);
		}
	}),
	TOKEN_COMPARE_GREATER(Pattern.compile("^(>)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(">" , TOKEN_COMPARE_GREATER);
		}
	}),
	TOKEN_COMPARE_LESSER(Pattern.compile("^(<)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("<" , TOKEN_COMPARE_LESSER);
		}
	}),
	TOKEN_COMPARE_NOTEQUALS(Pattern.compile("^(!=)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("!=" , TOKEN_COMPARE_NOTEQUALS);
		}
	}),
	TOKEN_COMPARE_GREATEREQUALS(Pattern.compile("^(>=)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(">=" , TOKEN_COMPARE_GREATEREQUALS);
		}
	}),
	TOKEN_COMPARE_LESSEREQUALS(Pattern.compile("^(<=)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token("<=" , TOKEN_COMPARE_LESSEREQUALS);
		}
	}),

	//Java Primitives (These contain literal data)
	PRIMITIVES_LONG64(Pattern.compile("^((-)?\\\\d*l)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, PRIMITIVES_LONG64);
		}
	}),
	PRIMITIVES_INT32(Pattern.compile("^((-)?\\\\d*l)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, PRIMITIVES_INT32);
		}
	}),
	PRIMITIVES_SHORT16(Pattern.compile("^((-)?\\\\d*l)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, PRIMITIVES_SHORT16);
		}
	}),
	PRIMITIVES_BYTE8(Pattern.compile("^((-)?\\\\d*l)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, PRIMITIVES_BYTE8);
		}
	}),
	PRIMITIVES_BOOLEAN(Pattern.compile("^([true|false])"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, PRIMITIVES_BOOLEAN);
		}
	}),
	PRIMITIVES_FLOAT(Pattern.compile("^((-)?\\\\d*\\\\.?\\\\d*)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, PRIMITIVES_FLOAT);
		}
	}),
	PRIMITIVES_DOUBLE(Pattern.compile("^((-)?\\\\d*\\\\.?\\\\d*)"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, PRIMITIVES_DOUBLE);
		}
	}),
	PRIMITIVES_CHAR(Pattern.compile("'[a-zA-Z0-9]'"), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, PRIMITIVES_CHAR);
		}
	}),

	//Empty for when all else fails
	TOKEN_EMPTY(Pattern.compile(""), new TokenGenerator() {
		@Override
		public Token createToken(String tokenData) {
			return new Token(tokenData, TOKEN_EMPTY);
		}
	}),

	;
	
	private Pattern pattern;
	private TokenGenerator tokenGenerator;
	
	TokenType(Pattern pattern, TokenGenerator tokenGenerator) {
		this.pattern = pattern;
		this.tokenGenerator = tokenGenerator;
	}

	/**
	 * Getter for property 'pattern'.
	 *
	 * @return Value for property 'pattern'.
	 */
	public Pattern getPattern() {
		return pattern;
	}


	/**
	 * Creates a new token with the given token data.
	 * @param tokenData The input data to convert to a token.
	 * @return A new Token.
	 */
	public Token createToken(String tokenData) {
		return tokenGenerator.createToken(tokenData);
	}
}
