package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.List;

/**
 * Created by Jordan Laptop on 13/08/2017.
 */
public class PackageHostCodeBlock extends CodeBlock {

	String packagePath;

	public PackageHostCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_PACKAGE, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		if (
				tokens.get(0).getTokenType() != TokenType.TOKEN_PACKAGE
				) {
			return;
		}

		packagePath = tokens.get(0).getToken();
		tokens.remove(0);
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
