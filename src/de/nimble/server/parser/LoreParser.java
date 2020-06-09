package de.nimble.server.parser;

import de.nimble.server.NimbleLogger;
import de.nimble.server.enchantmentsystem.enchants.types.EnchantmentType;
import org.bukkit.Material;
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

  /**
   * Initializes the <b>HashMap</b> for the values and loads the values of the <b>Lore</b> to the
   * Map <br>
   * TODO: Produces error because something is null
   *
   * @param item ItemStack you want to read the lore of
   */
  public LoreParser(ItemStack item) {

    this.values = new HashMap<>();
    if (item == null || item.getType() == Material.AIR) {
      NimbleLogger.getInstance().log("Item is null or material is air");
    } else {
      if (item.getItemMeta().getLore() != null) {
        this.lore = item.getItemMeta().getLore();
        loadComponentsToMap();
      } else {
        NimbleLogger.getInstance().log("LoreParser: Lore is null");
      }
    }
  }

  /** parses lore into its different components */
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
    String idStr = String.valueOf(values.get("id"));
    if (idStr.equals("null")) {
      return -1;
    } else {
      int id = Integer.parseInt(idStr);
      return id; // ugly af
    }
  }

  public String getDescription() {
    return (String) values.get("description");
  }

  public EnchantmentType getType() {
    return (EnchantmentType) values.get("type");
  }
}
