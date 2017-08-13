package com.j2assembly.compiler.tokenising;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jordan Laptop on 10/08/2017.
 */
public class Tokenizer {

	private String fileData;

	private Token lastToken;
	private boolean pushBack;


	public Tokenizer(String fileData, boolean silence) {
		this.fileData = fileData.replaceAll("//.*|(?s)/\\*.*?\\*/", "");//.replaceAll("\r", "");

		if (!silence) {
			System.out.println("Processing File:\n" + this.fileData + "\n________________________________\n\n");
		}
	}

	public Token nextToken() {

		fileData = fileData.trim();

		if (pushBack) {
			pushBack = false;
			return lastToken;
		}

		if (fileData.isEmpty()) {
			return (lastToken = new Token("", TokenType.TOKEN_EMPTY));
		}

		for (TokenType tokenType : TokenType.values()) {
			Matcher matcher = tokenType.getPattern().matcher(fileData);

			if (matcher.find()) {
				String tokenData = matcher.group().trim();
				fileData = matcher.replaceFirst("");

				return lastToken = tokenType.createToken(tokenData);
			}
		}
		throwCompilerError("No Parsable token for \"" + fileData + "\"");
		return null;
	}

	public boolean hasNextToken() {
		return !fileData.isEmpty();
	}

	public void pushBack() {
		if (lastToken != null) {
			this.pushBack = true;
		}
	}

	private void throwCompilerError(String errorMessage) {
		throw new IllegalStateException("Could not compile code: " + errorMessage);
	}
}
