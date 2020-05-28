package de.nimble.server.enchantmentsystem.config;

import org.bukkit.Material;

import de.nimble.server.config.Config;

public class EnchantmentConfig extends Config {
	
	public static EnchantmentConfig config = null;
	
	public static EnchantmentConfig getInstance() {
		if(config == null) {
			config = new EnchantmentConfig();
		}
		return config;
	}
	
	private EnchantmentConfig() {
		super("enchantments/EnchantmentConfig");
	}
	
	public void setMaxLevel(byte maxLevel) {
		
	}
	
	public byte getMaxLevel() {
		return (byte) 0;
	}
	
	public void setItemMaterial(Material material) {
		
	}
	
	public Material getItemMaterial() {
		return null;
	}
	
	
	
}