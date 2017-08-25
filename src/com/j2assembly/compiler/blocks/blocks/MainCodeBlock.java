package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.blocks.CodeBlockController;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 25/08/2017.
 */
public class MainCodeBlock extends CodeBlock {

	private List<Token> paramsFunction, functionTokens;
	private String codeParams;

	public MainCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_MAIN, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		if (
				tokens.get(0).getTokenType() != TokenType.TOKEN_MAIN
				) {
			return;
		}
		paramsFunction = new ArrayList<>();
		functionTokens = new ArrayList<>();
		int brackets = 1, cBrackets = 0, index = 0;

		for (index = 0; index < tokens.size(); index++) {
			Token token = tokens.get(index);
			paramsFunction.add(token);
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
			if (brackets == 0) {
				if (token.getTokenType() == TokenType.TOKEN_RBRACKET) {
					break;
				}
				if (token.getTokenType() == TokenType.TOKEN_SEMICOLON) {
					paramsFunction = null;
					return;
				}
			}
		}


		for (index = index + 1; index < tokens.size(); index++) {
			Token token = tokens.get(index);
			functionTokens.add(token);
			if (token.getTokenType() == TokenType.TOKEN_LCBRACKET) {
				cBrackets++;
			}
			if (token.getTokenType() == TokenType.TOKEN_RCBRACKET) {
				cBrackets--;
			}
			if (cBrackets == 0) {
				if (token.getTokenType() == TokenType.TOKEN_RCBRACKET) {
					break;
				}
			}
		}

		tokens.removeAll(paramsFunction);
		tokens.removeAll(functionTokens);

		functionTokens.remove(0);
		functionTokens.remove(functionTokens.size()-1);
		if (paramsFunction.get(0).getTokenType() == TokenType.TOKEN_STATIC) {

			paramsFunction.remove(0);
		}

		codeParams = "int main(";

		childBlocks = CodeBlockController.processTokens(functionTokens);
	}

	@Override
	public String generateCodeStart() {
		return codeParams + ") {";
	}

	@Override
	public String generateCodeEnd() {
		return "}";
	}
}
