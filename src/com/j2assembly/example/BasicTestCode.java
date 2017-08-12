package com.j2assembly.example;

import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.Tokenizer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Jordan Laptop on 10/08/2017.
 */
public class BasicTestCode {

	private static String testCode = "";


	public static void main(String[] args) {

		try {
			testCode = new String(Files.readAllBytes(
					Paths.get(new File("src/" + ARDUINOJ_BLINK.class.getCanonicalName().replace('.','/') + ".java").getCanonicalPath())
			));
		} catch (IOException e) {
			e.printStackTrace();
		}


		Tokenizer tokenizer = new Tokenizer(testCode);

		while (tokenizer.hasNextToken()) {
			Token t = tokenizer.nextToken();
			System.out.println(t.getTokenType() + ": "+ t.getToken());
		}
	}






}
