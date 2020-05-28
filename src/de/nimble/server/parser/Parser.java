package de.nimble.server.parser;

public abstract class Parser<T> {
	
	private T toParse;
	
	public abstract void parse();
	
	public void setToParse(T toParse) {
		this.toParse = toParse;
	}
	
	public T getToParse() {
		return this.toParse;
	}
	
}