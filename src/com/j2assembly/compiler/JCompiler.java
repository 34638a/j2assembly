package com.j2assembly.compiler;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.blocks.CodeBlockController;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.Tokenizer;
import com.j2assembly.resources.chip.Chip;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jordan Laptop on 9/08/2017.
 */
public class JCompiler {

	private static List<String> includes = new ArrayList<>();
	public static List<CodeBlock> headers = new ArrayList<>();
	private List<CodeBlock> codeBlocks;

	public static List<Token> tokenList;
	private boolean silence;

	public JCompiler(Class MainClass, boolean silence) throws IOException {
		this.silence = silence;

		tokenList = interpret(getClassPath(MainClass.getCanonicalName()));
		codeBlocks = CodeBlockController.processTokens(tokenList);

		if (!silence) {
			System.out.println("\n\n______________________________________________________________\n");
			System.out.println("Code Blocks: " + codeBlocks.size());
			for (CodeBlock block : codeBlocks) {
				System.out.println(block.getClass().getName());
			}
		}

		System.out.println("\n\n______________________________________________________________\n");

		String code = "";
		for (CodeBlock block : headers) {
			code += block.getCode();
		}

		for (CodeBlock block : codeBlocks) {
			code += block.getCode();
		}

		System.out.println(code);
		saveCFile(code, "D:\\j2assembly\\compile", MainClass.getSimpleName());
		compileFile("d/j2assembly/compile", MainClass.getSimpleName(), "atmega328p", 16000000l, "COM7", "arduino", 115200, false);
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


	public static void saveCFile(String data, String path, String name) {
		String pathFull = path + "\\" + name + ".c";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFull))) {
			bw.write(data);
			bw.close();
			System.out.println("Saved data to file: " + pathFull);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String compileFile(String fileSystemPath, String fileName, String chip, long clockSpeed, String port, String flasher, int bitrate, boolean clean) {

		//Compile the file
		String filePath = "/cygdrive/" + fileSystemPath.replace('\\','/') /*FILE PATH GETTER HERE!! TODO*/ ;
		String[] compileCommand = new String[]{
				"C:\\cygwin64\\bin\\bash.exe",
				"-c",
				//Set the directory to compile with
				"cd " + filePath + ";" +
				//build based off the chip
				"C:/WinAVR-20100110/bin/avr-gcc " + fileName + ".c -mmcu=" + chip + " -Os -DF_CPU=" + clockSpeed + "UL -std=gnu99 -o " + fileName + ".out;" +
				//Make into machine code
				"C:/WinAVR-20100110/bin/avr-objcopy -O ihex " + fileName + ".out " + fileName + ".hex;" +
				//install file
				"C:/Progra~2/Arduino/hardware/tools/avr/bin/avrdude -CC:/Progra~2/Arduino/hardware/tools/avr/etc/avrdude.conf -v -p" + chip + " -c" + flasher + " -P" + port + " -b" + bitrate + (clean ? " -D" : "") + " -Uflash:w:./" + fileName + ".hex:i;"
		};

		// build my command as a list of strings
		Runtime rt = Runtime.getRuntime();
		Process proc = null;
		try {
			System.out.println("Executing command:\n");
			System.out.println(Arrays.toString(compileCommand).replace(";", ";\n"));
			System.out.println("\n");
			proc = rt.exec(compileCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}


		BufferedReader stdInput = new BufferedReader(new
				InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new
				InputStreamReader(proc.getErrorStream()));

		// read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		try {
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		try {
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filePath;
	}
}
