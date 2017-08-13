package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 13/08/2017.
 */
public class ClockDelayCodeBlock extends CodeBlock {


	private List<Token> tokensList;

	public ClockDelayCodeBlock(TokenType tokenType, List<Token> tokens) {
		super(tokenType, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		if (
				!tokens.get(0).getToken().equalsIgnoreCase("Clock") &&
						!tokens.get(1).getToken().equalsIgnoreCase("delay(")
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
		return "set_clock_speed(CPU_8MHz);";

	}

	@Override
	public String generateCodeEnd() {
		return null;
	}
}
