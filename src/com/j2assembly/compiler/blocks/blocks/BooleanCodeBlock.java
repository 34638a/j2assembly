package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.List;

/**
 * Created by Jordan Laptop on 13/08/2017.
 */
public class BooleanCodeBlock extends CodeBlock {

	private boolean bool;

	public BooleanCodeBlock(List<Token> tokens) {
		super(TokenType.PRIMITIVES_BOOLEAN, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		if (!(
				tokens.get(0).getToken().equalsIgnoreCase("TRUE") ||
				tokens.get(0).getToken().equalsIgnoreCase("FALSE")

		)) {
			return;
		} else {
			bool = tokens.get(0).getToken().equalsIgnoreCase("TRUE") ? true : false;
			tokens.remove(0);
		}
	}

	@Override
	public String generateCodeStart() {
		return bool ? " 1 " : " 0 ";
	}

	@Override
	public String generateCodeEnd() {
		return "";
	}
}
