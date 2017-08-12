package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 12/08/2017.
 */
public class PortCodeBlock extends CodeBlock {

	private List<Token> portToken;

	public PortCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_CLASS, tokens);
	}

	@Override
	protected void extractTokenData(List<Token> tokens) {

		if (!tokens.get(0).getToken().equalsIgnoreCase("port")) {
			return;
		}
		portToken = new ArrayList<>();
		for (Token token : tokens) {
			portToken.add(token);
			if (token.getTokenType() == TokenType.TOKEN_SEMICOLON) {
				break;
			}
		}

		tokens.removeAll(portToken);
	}

	@Override
	public String generateCodeStart() {

		return "DDR" + portToken.get(2) + " = 0b" + getPortIODirection() + ';';
	}

	@Override
	public String generateCodeEnd() {
		return "";
	}


	private String getPortIODirection() {

		String code = "";
		for (int i = 0; i < 8; ) {
			if (portToken.get(32 + i*3).getToken().equalsIgnoreCase("WRITE")) {
				code += '1';
			} else {
				code += '0';
			}
		}

		return code;
	}
}
