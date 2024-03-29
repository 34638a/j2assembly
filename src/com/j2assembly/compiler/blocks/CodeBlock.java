package com.j2assembly.compiler.blocks;

import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 12/08/2017.
 */
public abstract class CodeBlock {

	protected List<CodeBlock> childBlocks;
	private TokenType tokenType;

	public CodeBlock(TokenType tokenType, List<Token> tokens) {
		this.tokenType = tokenType;
		this.childBlocks = new ArrayList<>();
		extractTokenData(tokens);
	}

	protected abstract void extractTokenData(List<Token> tokens);

	public String getCode() {
		String code = generateCodeStart();
		for (CodeBlock child : childBlocks) {
			code += child.getCode();
		}
		code += generateCodeEnd();
		return code + "\n";
	}
	public abstract String generateCodeStart();
	public abstract String generateCodeEnd();
}
