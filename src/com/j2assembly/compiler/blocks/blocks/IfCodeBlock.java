package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 12/08/2017.
 */
public class IfCodeBlock extends CodeBlock {

	private List<Token> ifList, function;

	public IfCodeBlock(TokenType tokenType, List<Token> tokens) {
		super(tokenType, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		int brace = 0;
		ifList = new ArrayList<>();
		for (Token token : tokens) {
			ifList.add(token);
			if (token.getTokenType() == TokenType.TOKEN_LBRACKET) {
				brace++;
			} else if (token.getTokenType() == TokenType.TOKEN_RBRACKET) {
				brace--;
			}
			if (brace == 0) {
				break;
			}
		}
		tokens.removeAll(ifList);

		function = new ArrayList<>();
		for (Token token : tokens) {
			function.add(token);
			if (token.getTokenType() == TokenType.TOKEN_LCBRACKET) {
				brace++;
			} else if (token.getTokenType() == TokenType.TOKEN_RCBRACKET) {
				brace--;
			}
			if (brace == 0) {
				break;
			}
		}
	}

	@Override
	public String generateCodeStart() {

		String code = "if (";

		//COMPARISON HERE
		return code + ") {";
	}

	@Override
	public String generateCodeEnd() {
		return "}";
	}
}
