package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;
import com.j2assembly.resources.helpers.Port;

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

		Port.define(portToken.get(2).getToken().charAt(1), getPortPins(), getPortIODirection());
	}

	@Override
	public String generateCodeStart() {

		return "DDR" + portToken.get(2).getToken().charAt(1) + " = 0b" + getPortIODirectionString() + ';';
	}

	@Override
	public String generateCodeEnd() {
		return "";
	}


	private String getPortIODirectionString() {

		String code = "";
		for (boolean bool : getPortIODirection()) {
			if (bool) {
				code += '1';
			} else {
				code += '0';
			}
		}

		return code;
	}

	private boolean[] getPortIODirection() {
		boolean[] booleans = new boolean[8];
		for (int i = 0; i < 8; i++) {
			if (portToken.get(32 + i*3).getToken().equalsIgnoreCase("WRITE")) {
				booleans[i] = true;
			}
		}
		return booleans;
	}

	private int[] getPortPins() {
		int[] pins = new int[8];
		for (int i = 0; i < 8; i++) {
			pins[i] = Integer.parseInt(portToken.get(9 + i*2).getToken());
		}
		return pins;
	}
}
