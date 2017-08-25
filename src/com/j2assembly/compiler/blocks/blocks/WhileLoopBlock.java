package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.blocks.CodeBlockController;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

public class WhileLoopBlock extends CodeBlock {

	private List<Token> paramsFunction, functionTokens;
	private String codeParams;

    public WhileLoopBlock(List<Token> tokens) {
        super(TokenType.TOKEN_WHILE, tokens);
    }

    @Override
    protected void extractTokenData(List<Token> tokens) {

    	/*
		TOKEN_WHILE: while
		TOKEN_LBRACKET: (
		IDENTIFIER: true
		TOKEN_RBRACKET: )
		TOKEN_LCBRACKET: {
		TOKEN_FUNCTION: CLOCK_LOOP(
		TOKEN_RBRACKET: )
		TOKEN_SEMICOLON: ;
		TOKEN_RCBRACKET: }

		//*/

		if (!(tokens.get(0).getTokenType() == TokenType.TOKEN_WHILE)) {
			return;
		}
		paramsFunction = new ArrayList<>();
		functionTokens = new ArrayList<>();
		int brackets = 0, cBrackets = 0, index;

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
			//System.out.println(token.getToken() + " " + brackets + " " + cBrackets);
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
		paramsFunction.remove(0);
		paramsFunction.remove(0);
		paramsFunction.remove(paramsFunction.size() - 1);


		//GENERATE MORE CODE BLOCKS
		codeParams = "";
		childBlocks = CodeBlockController.processTokens(paramsFunction);
		for (CodeBlock codeBlock : childBlocks) {
			codeParams += codeBlock.getCode();
		}

		childBlocks = CodeBlockController.processTokens(functionTokens);
    }

    @Override
    public String generateCodeStart() {
        return "while (" + codeParams.replaceAll("\n", "") + ") {\n";
    }

    @Override
    public String generateCodeEnd() {
        return "}";
    }
}
