package de.nimble.server.enchantmentsystem.config;

import de.nimble.server.NimbleLogger;
import de.nimble.server.config.Config;
import de.nimble.server.enchantmentsystem.enchants.NimbleEnchantment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Config class for <b>general</b> enchantment settings
 *
 * @author nimble
 */
public class NimbleEnchantmentConfig extends Config {

  private static NimbleEnchantmentConfig config;

  public static NimbleEnchantmentConfig getInstance() {
    if (config == null) {
      config = new NimbleEnchantmentConfig();
    }
    return config;
  }

  private NimbleEnchantmentConfig() {
    super("EnchantmentsConfig");
  }

  public void setEnchantmentTableSize(int size) {
    set("table.size", size);
  }

  public int getEnchantmentTableSize() {
    return getInt("table.size");
  }

  public void setFillerMaterial(Material material) {
    set("table.filler.material", material.toString());
  }

  public void setFillerMaterial(String material) {
    set("table.filler.material", material);
  }

  public Material getFillerMaterial() {
    // NimbleLogger.getInstance().log("Enchantment Table Filler Material: " +
    // getString("table.filler.material"));
    // return Material.getMaterial(getString("table.filler.material"));
    return Material.GRAY_STAINED_GLASS;
  }

  public void setFillerDisplay(String display) {
    set("table.filler.display", display);
  }

  public String getFillerDisplay() {
    return getString("table.filler.display");
  }

  public void setWindowDisplay(String display) {
    set("table.display", display);
  }

  public String getWindowDisplay() {
    return getString("table.display");
  }

  public void setEmptySlots(int... emptySlots) {
    StringBuilder slots = new StringBuilder();
    Arrays.asList(emptySlots).stream()
        .forEach(
            slot -> {
              slots.append(slot + ";");
            });
    set("table.emptySlots", slots.toString());
  }

  public List<Integer> getEmptySlots() {
    List<Integer> emptySlots = new ArrayList<>();
    String[] splittedSlots = getString("table.emptySlots").split(";");
    Arrays.asList(splittedSlots).stream()
        .forEach(
            slot -> {
              emptySlots.add(Integer.valueOf(slot));
            });

    return emptySlots;
  }

  public boolean isFillerItem(ItemStack item, int slot) {
    if (item.getType() == getFillerMaterial()
        && item.getItemMeta().getDisplayName().equals(getFillerDisplay())
        && (!(getEmptySlots().contains(slot)))) {
      return true;
    }
    return false;
  }
}
