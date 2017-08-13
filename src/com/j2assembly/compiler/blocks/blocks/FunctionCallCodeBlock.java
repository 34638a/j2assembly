package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 13/08/2017.
 */
public class FunctionCallCodeBlock extends CodeBlock {


	private List<Token> tokensList, functionTokens;

	public FunctionCallCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_FUNCTION, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		if (
				tokens.get(0).getTokenType() != TokenType.TOKEN_FUNCTION
				) {
			return;
		}
		tokensList = new ArrayList<>();
		int brackets = 1;

		for (Token token : tokens) {
			tokensList.add(token);
			if (token.getTokenType() == TokenType.TOKEN_LBRACKET) {
				brackets++;
			}
			if (token.getTokenType() == TokenType.TOKEN_RBRACKET) {
				brackets--;
			}
			if (brackets == 0) {
				if (token.getTokenType() == TokenType.TOKEN_SEMICOLON) {
					break;
				}
				if (token.getTokenType() == TokenType.TOKEN_LCBRACKET) {
					tokensList = null;
					return;
				}

			}
		}

		tokens.removeAll(tokensList);
	}

	@Override
	public String generateCodeStart() {

		if (tokensList.size() > 3) {
			List<Token> tokensSub = new ArrayList<>(tokensList);
			tokensSub.remove(0);
			tokensSub.remove(tokensSub.size()-1);
			tokensSub.remove(tokensSub.size()-1);
			//Split off and create the params

			//GENERATE MORE CODE BLOCKS
			//TODO
		}
		return tokensList.get(0).getToken();
	}

	@Override
	public String generateCodeEnd() {
		return ");";
	}
}
