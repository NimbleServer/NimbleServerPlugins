package de.nimble.server.itemsystem.items;

import de.nimble.server.NimbleServer;
import de.nimble.server.enchantmentsystem.enchants.NimbleEnchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class NimbleItemCreator {

  private NimbleItem nItem;

  public NimbleItemCreator(int id) {
    this.nItem = NimbleServer.itemManager.getItem(id);
  }

  public ItemStack createItem() {
    ItemStack item = new ItemStack(nItem.getMaterial());
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(nItem.getDisplayName());

    List<String> lore = new ArrayList<>();
    lore.add("Type: " + nItem.getType().toString());
    lore.add(nItem.getDescription());
    lore.add("ID: " + nItem.getID());
    System.out.println(nItem.getEnchantments());
    for (NimbleEnchantment enchantment : nItem.getEnchantments()) {
      lore.add(enchantment.getDisplayName());
    }

    meta.setLore(lore);
    item.setItemMeta(meta);
    return item;
  }

  public void setItem(int id) {
    this.nItem = NimbleServer.itemManager.getItem(id);
  }

  public NimbleItem getItem() {
    return nItem;
  }
}
