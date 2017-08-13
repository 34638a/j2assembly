package com.j2assembly.compiler;

import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.Tokenizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 9/08/2017.
 */
public class JCompiler {

	private static List<String> includes = new ArrayList<>();

	private List<Token> tokenList;
	private boolean silence;

	public JCompiler(Class MainClass, boolean silence) throws IOException {
		this.tokenList = new ArrayList<>();
		this.silence = silence;

		interpret(MainClass.getCanonicalName());
	}

	private List<Token> interpret(String path) {
		List<Token> tokens = new ArrayList<>();
		Tokenizer tokenizer = new Tokenizer(path, silence);

		while (tokenizer.hasNextToken()) {
			Token t = tokenizer.nextToken();
			tokens.add(t);
			if (!silence) {
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

	/**
	 * Getter for property 'tokenList'.
	 *
	 * @return Value for property 'tokenList'.
	 */
	public List<Token> getTokenList() {
		return tokenList;
	}

	public static void include(String library) {
		includes.add(library);
		System.out.println("Including Library: " + library);
	}
}
