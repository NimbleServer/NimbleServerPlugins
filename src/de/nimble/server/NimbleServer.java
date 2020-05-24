package de.nimble.server;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.nimble.server.enchantmentsystem.commands.EnchantmentCommand;
import de.nimble.server.enchantmentsystem.config.EnchantmentConfig;
import de.nimble.server.enchantmentsystem.config.UserEnchantmentsConfig;

public class NimbleServer extends JavaPlugin {
	
	/**
	 * config variable that is available the entire time
	 */
	public static UserEnchantmentsConfig userEnchantmentConfig = null;
	public static EnchantmentConfig enchantmentConfig = null;
	
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
		 userEnchantmentConfig = UserEnchantmentsConfig.getInstance();
		 enchantmentConfig = EnchantmentConfig.getInstance();
	}
	
}