package de.nimble.server.parser;

import de.nimble.server.enchantmentsystem.enchants.types.EnchantmentType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * parser to get the different components of the lore
 *
 * @author master
 */
public class LoreParser {

  private List<String> lore;
  private HashMap<String, Object> values;

  public LoreParser(List<String> lore) {
    this.values = new HashMap<>();
    this.lore = lore;
    loadComponentsToMap();
  }

  public LoreParser(String... items) {
    this.values = new HashMap<>();
    this.lore = Arrays.asList(items);
    loadComponentsToMap();
  }

  public LoreParser(ItemStack item) {
    if (item != null) {
      this.values = new HashMap<>();
      if (item.getItemMeta().getLore() != null) {
        this.lore = item.getItemMeta().getLore();
        loadComponentsToMap();
      }
    }
  }

  private void loadComponentsToMap() {
    for (String line : lore) {
      if (line.contains("ID")) {
        String[] splittedLine = line.split(": ");
        String id = splittedLine[1];
        values.put("id", id);
      } else if (line.contains("Type")) {
        String[] splittedLine = line.split(": ");
        values.put("type", splittedLine[1]);
      } else {
        values.put("description", line);
      }
    }
  }

  public int getID() {
    return Integer.parseInt(String.valueOf(values.get("id"))); // ugly af
  }

  public String getDescription() {
    return (String) values.get("description");
  }

  public EnchantmentType getType() {
    return (EnchantmentType) values.get("type");
  }
}
