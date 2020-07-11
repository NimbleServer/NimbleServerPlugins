package de.nimble.server.enchantmentsystem.enchants;

import de.nimble.server.parser.LoreParser;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentManager {

  private List<NimbleEnchantment> enchantments;

  private static EnchantmentManager manager = null;

  public static EnchantmentManager getInstance() {
    if (manager == null) {
      manager = new EnchantmentManager();
    }

    return manager;
  }

  private EnchantmentManager() {
    this.enchantments = new ArrayList<NimbleEnchantment>();
  }

  public void addEnchantment(NimbleEnchantment enchantment) {
    this.enchantments.add(enchantment);
  }

  public void deleteEnchantment(NimbleEnchantment enchantment) {
    deleteEnchantment(enchantment.getID());
  }

  public void deleteEnchantment(int id) {
    for (NimbleEnchantment enchantment : enchantments) {
      if (enchantment.getID() == id) {
        enchantments.remove(enchantment);
      }
    }
  }

  public NimbleEnchantment getEnchantment(int id) {
    for (NimbleEnchantment enchantment : enchantments) {
      if (enchantment.getID() == id) {
        return enchantment;
      }
    }
    return null;
  }

  public List<NimbleEnchantment> getEnchantments() {
    return this.enchantments;
  }

  public boolean isNimbleEnchantment(ItemStack item) {
    LoreParser parser = new LoreParser(item);
    return getEnchantment(parser.getID()) != null;
  }

  public void clear() {
    enchantments.clear();
  }
}
