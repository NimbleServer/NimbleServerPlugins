package de.nimble.server.parser;

import java.util.Arrays;
import java.util.List;

public class LoreParser extends Parser<List<String>> {
	
	public LoreParser(List<String> lore) {
		setToParse(lore);
	}
	
	public LoreParser(String... items) {
		setToParse(Arrays.asList(items));
	}
	
	@Override
	public void parse() {
		
	}
	
	public void setLore(String... items) {
		setToParse(Arrays.asList(items));
	}
	
}