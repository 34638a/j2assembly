package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 12/08/2017.
 */
public class JCompilerIncludeCodeBlock extends CodeBlock {

	private List<Token> includeTokens;

	public JCompilerIncludeCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_CLASS, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		if (
				!tokens.get(0).getToken().equalsIgnoreCase("JCompiler") &&
				!tokens.get(1).getToken().equalsIgnoreCase("include(")
				) {
			return;
		}
		includeTokens = new ArrayList<>();

		for (Token token : tokens) {
			includeTokens.add(token);
			if (token.getTokenType() == TokenType.TOKEN_SEMICOLON) {
				break;
			}
		}

		tokens.removeAll(includeTokens);
	}

	@Override
	public String generateCodeStart() {

		String code = "";
		for (int i = 0; i < (includeTokens.size()-3)/2; i++) {
			code += includeTokens.get(2 + i*2) + "\n";
		}
		return code;
	}

	@Override
	public String generateCodeEnd() {
		return "";
	}
}
