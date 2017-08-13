package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 12/08/2017.
 */
public class FunctionCodeBlock extends CodeBlock {

	private List<Token> tokensList;

	public FunctionCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_FUNCTION, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		if (
				!tokens.get(0).getToken().equalsIgnoreCase("JCompiler") &&
						!tokens.get(1).getToken().equalsIgnoreCase("include(")
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
		return null;
	}

	@Override
	public String generateCodeEnd() {
		return null;
	}
}
