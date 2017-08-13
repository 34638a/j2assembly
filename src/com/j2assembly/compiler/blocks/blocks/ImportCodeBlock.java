package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.List;

/**
 * Created by Jordan Laptop on 13/08/2017.
 */
public class ImportCodeBlock extends CodeBlock {


	String importPath;

	public ImportCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_IMPORT, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {
		if (
				tokens.get(0).getTokenType() != TokenType.TOKEN_IMPORT
				) {
			return;
		}

		importPath = tokens.get(0).getToken();
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
