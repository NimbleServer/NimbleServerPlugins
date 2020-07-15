package de.nimble.server;

import de.nimble.server.enchantmentsystem.config.NimbleEnchantmentConfig;
import de.nimble.server.events.AttackEvent;
import de.nimble.server.events.NimbleInventoryClickEvent;
import de.nimble.server.itemsystem.commands.NimbleItemCommand;
import de.nimble.server.itemsystem.config.NimbleItemSql;
import de.nimble.server.itemsystem.items.ItemManager;
import de.nimble.server.quests.NimbleQuestConfig;
import de.nimble.server.quests.objectives.NimbleQuestObjectivesConfig;
import de.nimble.server.quests.rewards.NimbleQuestRewardsConfig;
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
  public static NimbleEnchantmentConfig enchantmentConfig = null;

  // Item configs
  public static NimbleItemSql itemSql = null;

  // Quest configs
  public static NimbleQuestConfig questConfig = null;
  public static NimbleQuestObjectivesConfig questObjectivesConfig = null;
  public static NimbleQuestRewardsConfig questRewardsConfig = null;

  public void onEnable() {
    init();
    NimbleLogger.getInstance().log("Successfully enabled");
  }

  public void onDisable() {
    NimbleLogger.getInstance().log("Disabled");
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
    pm.registerEvents(new NimbleInventoryClickEvent(), this);
  }

  private void loadCommands() {
    getCommand("enchantments").setExecutor(new EnchantmentCommand());
    getCommand("items").setExecutor(new NimbleItemCommand());
    // getCommand("enchantmenttable").setExecutor(new NimbleEnchantmentTableCommand());
  }

  private void loadConfig() {
    getConfig().options().copyDefaults(true);
    saveConfig();
  }

  private void loadConfigFiles() {
    // enchantment configs
    userEnchantmentsSql = UserEnchantmentSQL.getInstance();
    enchantmentConfig = NimbleEnchantmentConfig.getInstance();
    // item configs
    itemSql = NimbleItemSql.getInstance();
    // quests
    questConfig = NimbleQuestConfig.getInstance();
    questObjectivesConfig = NimbleQuestObjectivesConfig.getInstance();
    questRewardsConfig = NimbleQuestRewardsConfig.getInstance();
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
              itemManager.addItem(i);
            });
  }
}
