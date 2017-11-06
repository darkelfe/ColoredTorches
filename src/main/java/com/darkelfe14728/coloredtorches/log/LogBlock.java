package com.darkelfe14728.coloredtorches.log;

import java.util.Collections;

/**
 * A log block.
 * 
 * @author Julien Rosset
 */
final class LogBlock
{
	public static final String INDENT_STR = "  ";
	
	private String name;
	private int indent;
	
	/**
	 * Create a new block
	 * 
	 * @param name	Block name.
	 */
	public LogBlock(String name)
	{
		this.name = name;
		this.indent = 0;
	}
	
	@Override
	public String toString()
	{
		return this.name + " : " + String.join("", Collections.nCopies(this.indent, INDENT_STR)) ;
	}
	
	/**
	 * Start a new indent in the block.
	 */
	public void startIndent()
	{
		this.indent++;
	}
	/**
	 * Stop the current (last) indent of block.
	 */
	public void stopIndent()
	{
		this.indent--;
	}
}
