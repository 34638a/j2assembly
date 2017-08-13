package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 13/08/2017.
 */
public class ClassCodeBlock extends CodeBlock {

	String classTitle;

	public ClassCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_CLASS_LITERAL, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		if (
				!(tokens.get(0).getTokenType() == TokenType.TOKEN_CLASS_LITERAL)
				) {
			return;
		}


		classTitle = tokens.get(1).getToken();
		tokens.remove(0);
		tokens.remove(0);
		tokens.remove(0);
		tokens.remove(tokens.size()-1);
	}

	@Override
	public String generateCodeStart() {
		return "";
	}

	@Override
	public String generateCodeEnd() {
		return "";
	}
}
