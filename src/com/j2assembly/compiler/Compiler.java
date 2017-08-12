package com.j2assembly.compiler;

import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.Tokenizer;
import com.j2assembly.example.ARDUINOJ_BLINK;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 9/08/2017.
 */
public class Compiler {

	private List<Token> tokenList;
	private boolean silence;

	public Compiler(Class MainClass, boolean silence) throws IOException {
		this.tokenList = new ArrayList<>();
		this.silence = silence;

		interperate(MainClass.getCanonicalName());

	}

	private List<Token> interperate(String path) {
		List<Token> tokens = new ArrayList<>();
		Tokenizer tokenizer = new Tokenizer(path);

		while (tokenizer.hasNextToken()) {
			Token t = tokenizer.nextToken();
			tokens.add(t);
			if (silence) {
				System.out.println(t.getTokenType() + ": " + t.getToken());
			}
		}

		return tokens;
	}

	private String getClassPath(String packagePath) throws IOException {
		return new String(Files.readAllBytes(
				Paths.get(new File("src/" + packagePath.replace('.','/') + ".java").getCanonicalPath())
		));
	}
}
