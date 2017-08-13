package com.j2assembly.compiler.blocks;

import com.j2assembly.compiler.blocks.blocks.FunctionCodeBlock;
import com.j2assembly.compiler.blocks.blocks.JCompilerIncludeCodeBlock;
import com.j2assembly.compiler.blocks.blocks.PinSetCodeBlock;
import com.j2assembly.compiler.blocks.blocks.PortCodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 12/08/2017.
 */
public class CodeBlockController {

	private List<CodeBlock> codeBlocks;

	private List<CodeBlock> includeTokens;

	public CodeBlockController() {
		this.codeBlocks = new ArrayList<>();
		this.includeTokens = new ArrayList<>();

	}

	public void processTokens(List<Token> tokens) {
		while (tokens.size() != 0) {
			int size = tokens.size();

			//check if there are compiler hints
			CodeBlock block = new JCompilerIncludeCodeBlock(tokens);
			if (size != tokens.size()) {
				includeTokens.add(block);
				continue;
			}

			//check for portData
			block = new PortCodeBlock(tokens);
			if (size != tokens.size()) {
				codeBlocks.add(block);
				continue;
			}

			//check for pinData
			block = new PinSetCodeBlock(tokens);
			if (size != tokens.size()) {
				codeBlocks.add(block);
				continue;
			}

			//check for function
			block = new FunctionCodeBlock(tokens);
			if (size != tokens.size()) {
				codeBlocks.add(block);
				continue;
			}


		}
	}
}
