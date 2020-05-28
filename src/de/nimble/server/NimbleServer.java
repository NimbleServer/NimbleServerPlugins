package de.nimble.server;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.nimble.server.enchantmentsystem.commands.EnchantmentCommand;
import de.nimble.server.enchantmentsystem.config.EnchantmentConfig;
import de.nimble.server.enchantmentsystem.config.UserEnchantmentsConfig;
import de.nimble.server.itemsystem.config.ItemConfig;
import de.nimble.server.itemsystem.config.NimbleItemConfig;

public class NimbleServer extends JavaPlugin {
	
	/*
	 * Enchantment configs
	 */
	public static UserEnchantmentsConfig userEnchantmentConfig = null;
	public static EnchantmentConfig enchantmentConfig = null;
	
	/*
	 * Item configs
	 */
	public static NimbleItemConfig nimbleItemConfig = null;
	public static ItemConfig itemConfig = null;
	
	public void onEnable() {
		init();
		System.out.println("EnchantmentSystem loaded");
	}
	
	public void onDisable() {
		System.out.println("EnchantmentSystem disabled");
	}
	
	private void init() {
		registerEvents(getServer().getPluginManager());
		loadCommands();
		loadConfig();
		loadConfigFiles();
	}
	
	private void registerEvents(PluginManager pm) {
	}
	
	private void loadCommands() {
		getCommand("enchantments").setExecutor(new EnchantmentCommand());
	}
	
	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void loadConfigFiles() {
		// enchantment configs
		userEnchantmentConfig = (UserEnchantmentsConfig) UserEnchantmentsConfig.getInstance();
		enchantmentConfig = (EnchantmentConfig) EnchantmentConfig.getInstance();
		
		// item configs
		nimbleItemConfig = (NimbleItemConfig) NimbleItemConfig.getInstance();
	}
	
}