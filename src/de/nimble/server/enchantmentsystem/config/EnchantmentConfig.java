package de.nimble.server.enchantmentsystem.config;

import org.bukkit.Material;

import de.nimble.server.config.Config;

public class EnchantmentConfig extends Config {
	
	private static EnchantmentConfig config;
	
	/**
	 * @return only instance of EnchantmentConfig
	 */
	public static EnchantmentConfig getInstance() {
		if(config == null) {
			config = new EnchantmentConfig("EnchantmentConfig");
		}
		return config;
	}
	
	private EnchantmentConfig(String fileName) {
		super(fileName);
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