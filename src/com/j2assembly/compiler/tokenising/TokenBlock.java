package com.j2assembly.compiler.tokenising;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 10/08/2017.
 */
public abstract class TokenBlock {

	private TokenBlock parentBlock;
	private List<TokenBlock> childBlock;

	public TokenBlock(TokenBlock parentBlock) {
		this.parentBlock = parentBlock;
		this.childBlock = new ArrayList<>();
	}

	/**
	 * Getter for property 'parentBlock'.
	 *
	 * @return Value for property 'parentBlock'.
	 */
	public TokenBlock getParentBlock() {
		return parentBlock;
	}

	/**
	 * Getter for property 'childBlock'.
	 *
	 * @return Value for property 'childBlock'.
	 */
	public List<TokenBlock> getChildBlock() {
		return childBlock;
	}

	public abstract boolean executeBlock();

	public abstract void createCode();
}
