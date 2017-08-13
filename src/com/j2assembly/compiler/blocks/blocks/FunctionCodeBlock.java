package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.blocks.CodeBlockController;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 12/08/2017.
 */
public class FunctionCodeBlock extends CodeBlock {


	private List<Token> tokensList, functionTokens;

	public FunctionCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_FUNCTION, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		if (
				!(
						tokens.get(1).getTokenType() == TokenType.TOKEN_FUNCTION ||
						tokens.get(2).getTokenType() == TokenType.TOKEN_FUNCTION
				)
				) {
			return;
		}
		tokensList = new ArrayList<>();
		int brackets = 1, cBrackets = 0;

		for (Token token : tokens) {
			tokensList.add(token);
			if (token.getTokenType() == TokenType.TOKEN_LBRACKET) {
				brackets++;
			}
			if (token.getTokenType() == TokenType.TOKEN_RBRACKET) {
				brackets--;
			}
			if (token.getTokenType() == TokenType.TOKEN_LCBRACKET) {
				cBrackets++;
			}
			if (token.getTokenType() == TokenType.TOKEN_RCBRACKET) {
				cBrackets--;
			}
			if (brackets == 0 && cBrackets == 0) {
				if (token.getTokenType() == TokenType.TOKEN_RCBRACKET) {
					break;
				}
				if (token.getTokenType() == TokenType.TOKEN_SEMICOLON) {
					tokensList = null;
					return;
				}
			}
		}

		tokens.removeAll(tokensList);
	}

	@Override
	public String generateCodeStart() {

		List<Token> params = new ArrayList<>(), helper = new ArrayList<>(tokensList);
		helper.remove(0);
		helper.remove(0);
		helper.remove(helper.size() - 1);
		int brackets = 1;

		for (Token token : helper) {
			params.add(token);
			if (token.getTokenType() == TokenType.TOKEN_LBRACKET) {
				brackets++;
			}
			if (token.getTokenType() == TokenType.TOKEN_RBRACKET) {
				brackets--;
			}
			if (brackets == 0) {
				break;
			}
		}

		helper.remove(params);
		params.remove(params.size()-1);
		helper.remove(0);

		//Split off and create the params

		//GENERATE MORE CODE BLOCKS
		String codeParams = "";
		childBlocks = CodeBlockController.processTokens(params);
		for (CodeBlock codeBlock : childBlocks) {
			codeParams += codeBlock.getCode();
		}
		childBlocks = CodeBlockController.processTokens(helper);

		return tokensList.get(0).getToken() + tokensList.get(1).getToken() + codeParams +") {\n";
	}

	@Override
	public String generateCodeEnd() {
		return "}";
	}
}
