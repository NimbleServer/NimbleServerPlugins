package de.nimble.server;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.nimble.server.enchantmentsystem.commands.EnchantmentCommand;
import de.nimble.server.enchantmentsystem.config.UserEnchantmentSQL;
import de.nimble.server.enchantmentsystem.enchants.EnchantmentManager;
import de.nimble.server.itemsystem.config.ItemConfig;
import de.nimble.server.itemsystem.config.NimbleItemConfig;

public class NimbleServer extends JavaPlugin {

  // Manager
  public static EnchantmentManager enchantmentManager = null;

  // Enchantment configs
  public static UserEnchantmentSQL userEnchantmentsSql = null;

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
    loadManager();
    initManagerContent();
  }

  private void registerEvents(PluginManager pm) {}

  private void loadCommands() {
    getCommand("enchantments").setExecutor(new EnchantmentCommand());
  }

  private void loadConfig() {
    getConfig().options().copyDefaults(true);
    saveConfig();
  }

  private void loadConfigFiles() {
    // enchantment configs
    userEnchantmentsSql = UserEnchantmentSQL.getInstance();
    // item configs
    nimbleItemConfig = NimbleItemConfig.getInstance();
  }

  private void loadManager() {
    enchantmentManager = EnchantmentManager.getInstance();
  }

  private void initManagerContent() {
    // custom enchantments
    userEnchantmentsSql.getEnchantments().forEach(e -> enchantmentManager.addEnchantment(e));
  }
}
