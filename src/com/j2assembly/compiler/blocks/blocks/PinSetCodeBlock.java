package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;
import com.j2assembly.resources.Port;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 12/08/2017.
 */
public class PinSetCodeBlock extends CodeBlock {

	private List<Token> pinTokens;


	public PinSetCodeBlock(List<Token> tokens) {
		super(TokenType.TOKEN_CLASS, tokens);
	}



	@Override
	protected void extractTokenData(List<Token> tokens) {

		if (!tokens.get(0).getToken().equalsIgnoreCase("PIN")
				&& !tokens.get(1).getToken().equalsIgnoreCase("setPin(")
				) {
			return;
		}
		pinTokens = new ArrayList<>();
		for (Token token : tokens) {
			pinTokens.add(token);
			if (token.getTokenType() == TokenType.TOKEN_SEMICOLON) {
				break;
			}
		}

		tokens.removeAll(pinTokens);
	}

	@Override
	public String generateCodeStart() {

		/*
		TOKEN_CLASS: Pin
		TOKEN_FUNCTION: setPin(
				TOKEN_NUMBER: 13
		TOKEN_COMMA: ,
		TOKEN_CLASS: Pin
		IDENTIFIER: HIGH
		TOKEN_RBRACKET: )
		TOKEN_SEMICOLON: ;
		//*/

		return getPortBitPattern(pinTokens.get(2).getToken(), pinTokens.get(5).getToken());
	}

	@Override
	public String generateCodeEnd() {
		return "";
	}

	private String getPortBitPattern(String pin, String value) {

		int bit = 0;
		Port port = null;
		for (Port p : Port.ports) {
			for (int i = 0; i < 8; i++) {
				if (pin.equalsIgnoreCase("" + p.getPins()[i])) {
					bit = i;
					port = p;
					break;
				}
			}
		}

		String code = "0b";
		for (int i = 0; i < 8; i++) {
			if (i == bit) {
				code += '1';
			} else {
				code += '0';
			}
		}

		String p = "PORT" + port.getName();

		return p + (value.equalsIgnoreCase("HIGH") ? " |= " : " &= ~") + code;
	}
}
