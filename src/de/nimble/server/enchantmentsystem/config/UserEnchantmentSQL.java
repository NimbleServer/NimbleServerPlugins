package de.nimble.server.enchantmentsystem.config;

public class UserEnchantmentSQL {
	
	private static UserEnchantmentSQL config = null; 
	
	public static UserEnchantmentSQL getInstance() {
		if(config == null) {
			config = new UserEnchantmentSQL();
		}
		return config;
	}
	
	private UserEnchantmentSQL() {
		init();
	}
	
	private void init() {
		
	}
	
}