package de.nimble.server.itemsystem.config;

import de.nimble.server.config.Config;

public class ItemConfig extends Config {
	
	private static ItemConfig config = null;
	
	public static ItemConfig getInstance() {
		if(config == null) {
			config = new ItemConfig();
		}
		
		return config;
	}
	
	private ItemConfig() {
		super("ItemConfig");
	}
	
	
	
}