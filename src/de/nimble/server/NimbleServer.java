package de.nimble.server;

import de.nimble.server.itemsystem.commands.NimbleItemCommand;
import de.nimble.server.itemsystem.config.NimbleItemSql;
import de.nimble.server.itemsystem.events.AttackEvent;
import de.nimble.server.itemsystem.items.ItemManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.nimble.server.enchantmentsystem.commands.EnchantmentCommand;
import de.nimble.server.enchantmentsystem.config.UserEnchantmentSQL;
import de.nimble.server.enchantmentsystem.enchants.EnchantmentManager;

public class NimbleServer extends JavaPlugin {

  // Manager
  public static EnchantmentManager enchantmentManager = null;
  public static ItemManager itemManager = null;

  // Enchantment configs
  public static UserEnchantmentSQL userEnchantmentsSql = null;

  // Item configs
  public static NimbleItemSql itemSql = null;

  public void onEnable() {
    init();
    System.out.println("NimbleServer loaded");
  }

  public void onDisable() {
    System.out.println("NimbleServer disabled");
  }

  private void init() {
    registerEvents(getServer().getPluginManager());
    loadCommands();
    loadConfig();
    loadConfigFiles();
    loadManager();
    initManagerContent();
    NimbleLogger.getInstance().setServer(this.getServer());
  }

  private void registerEvents(PluginManager pm) {
    pm.registerEvents(new AttackEvent(), this);
  }

  private void loadCommands() {
    getCommand("enchantments").setExecutor(new EnchantmentCommand());
    getCommand("items").setExecutor(new NimbleItemCommand());
  }

  private void loadConfig() {
    getConfig().options().copyDefaults(true);
    saveConfig();
  }

  private void loadConfigFiles() {
    // enchantment configs
    userEnchantmentsSql = UserEnchantmentSQL.getInstance();
    // item configs
    itemSql = NimbleItemSql.getInstance();
  }

  private void loadManager() {
    enchantmentManager = EnchantmentManager.getInstance();
    itemManager = ItemManager.getInstance();
  }

  private void initManagerContent() {
    // custom enchantments
    userEnchantmentsSql.getEnchantments().forEach(e -> enchantmentManager.addEnchantment(e));
    // custom items
    itemSql
        .getItems()
        .forEach(
            i -> {
              System.out.println(i.getID());
              itemManager.addItem(i);
            });
  }
}
