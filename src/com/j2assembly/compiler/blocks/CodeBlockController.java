package com.j2assembly.compiler.blocks;

import com.j2assembly.compiler.JCompiler;
import com.j2assembly.compiler.blocks.blocks.*;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 12/08/2017.
 */
public class CodeBlockController {

	public static List<CodeBlock> processTokens(List<Token> tokens) {

		List<CodeBlock> blocks = new ArrayList<>();

		while (tokens.size() != 0) {
			int size = tokens.size();

			//Check for reserved classes first
			CodeBlock block = new MainCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check if there are compiler hints
			block = new JCompilerIncludeCodeBlock(tokens);
			if (size != tokens.size()) {
				JCompiler.headers.add(block);
				continue;
			}

			//check for portData
			block = new PortCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check for pinData
			block = new PinSetCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check for clock
			block = new ClockDefineCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check for clock
			block = new ClockDelayCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check for import
			block = new ImportCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check for package
			block = new PackageHostCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check for protection
			block = new ProtectionCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check for class
			block = new ClassCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}



			//Check for normal scripts

			block = new WhileLoopBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check for function call
			block = new FunctionCallCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check for function
			block = new FunctionCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}

			//check for boolean
			block = new BooleanCodeBlock(tokens);
			if (size != tokens.size()) {
				blocks.add(block);
				continue;
			}
		}

		return blocks;
	}
}
