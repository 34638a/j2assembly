package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 13/08/2017.
 */
public class ClockDefineCodeBlock extends CodeBlock {

	private List<Token> tokensList;

	public ClockDefineCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_EMPTY, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {

		/*
		TOKEN_CLASS: Clock
		TOKEN_FUNCTION: define(
		TOKEN_NUMBER: 16
		TOKEN_COMMA: ,
		TOKEN_CLASS: Clock
		TOKEN_CLASS: ClockScalar
		IDENTIFIER: MHz
		TOKEN_RBRACKET: )
		TOKEN_SEMICOLON: ;
		 */
		//*/
		if (
				!tokens.get(0).getToken().equalsIgnoreCase("Clock") &&
						!tokens.get(1).getToken().equalsIgnoreCase("define(")
				) {
			return;
		}
		tokensList = new ArrayList<>();

		for (Token token : tokens) {
			tokensList.add(token);
			if (token.getTokenType() == TokenType.TOKEN_SEMICOLON) {
				break;
			}
		}

		tokens.removeAll(tokensList);
	}

	@Override
	public String generateCodeStart() {
		return "set_clock_speed(CPU_" + tokensList.get(2).getToken() + tokensList.get(7) + ");";
	}

	@Override
	public String generateCodeEnd() {
		return "";
	}
}
