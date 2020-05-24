package de.nimble.server.enchantmentsystem.enchants;

import org.bukkit.event.Event;

import de.nimble.server.NimbleServer;

public abstract class Enchantment {
	
	private String enchantmentName;
	
//	public Enchantment() {
//		
//	}
	
	public Enchantment(String enchantmentName) {
		this.enchantmentName = enchantmentName;
	}
	
	/**
	 * onUse method gets implementated in class that extends Enchantment
	 * defines what happens when item with enchantment is used
	 * @param event specified by what should happen 
	 */
	public abstract void onUse(Event event);
	
	/**
	 * reads displayname from config file with the given enchantmentname 
	 * @return String
	 */
	public String getDisplayName() {
		return NimbleServer.userEnchantmentConfig.getDisplayName(enchantmentName);
	}
	
	/**
	 * saves displayname in config with the given enchantmentname
	 * @param displayName
	 */
	public void setDisplayName(String displayName) {
		NimbleServer.userEnchantmentConfig.setDisplayName(enchantmentName, displayName);
	}
	
	/**
	 * gets the given enchantmentname
	 * @return
	 */
	public String getEnchantmentName() {
		return this.enchantmentName;
	}
	
	/**
	 * saves the enchantmentname locally in ram
	 * @param string enchantmentName
	 */
	public void setEnchantmentName(String enchantmentName) {
		this.enchantmentName = enchantmentName;
	}
	
	/**
	 * reads level from config file with the given enchantmentname
	 * @return level as byte
	 */
	public byte getLevel() {
		return NimbleServer.userEnchantmentConfig.getLevel(enchantmentName);
	}
	
	/**
	 * saves the level as byte in config with the given enchantmentname
	 * @param level as byte
	 */
	public void setLevel(byte level) {
		NimbleServer.userEnchantmentConfig.setLevel(enchantmentName, level);
	}
	
	/**
	 * reads description from config file with the given enchantmentname
	 * @return
	 */
	public String getDescription() {
		return NimbleServer.userEnchantmentConfig.getDescription(enchantmentName);
	}
	/**
	 * saves the description as string in config with the given enchantmentname
	 * @param description as string
	 */
	public void setDescription(String description) {
		NimbleServer.userEnchantmentConfig.setDescription(enchantmentName, description);
	}
	
}