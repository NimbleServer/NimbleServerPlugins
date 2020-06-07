package de.nimble.server.enchantmentsystem.enchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.nimble.server.NimbleServer;
import de.nimble.server.customtags.NimbleTag;

/** @author master */
public class NimbleEnchantmentCreator {

  private Material material;
  private NimbleEnchantment enchantment;

  public NimbleEnchantmentCreator(int id, Material material) {
    this.material = material;
    this.enchantment = NimbleServer.enchantmentManager.getEnchantment(id);
  }

  /** @return itemstack with finished enchantment */
  public ItemStack createEnchantment() {
    ItemStack item = new ItemStack(material);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(enchantment.getDisplayName());

    // NBT ID
    // NimbleTag tag = new NimbleTag("id", enchantment.getID());
    // item = tag.create(item);
    // System.out.println(tag.getTagName());

    List<String> lore = new ArrayList<>();
    lore.add("Type: " + enchantment.getType());
    lore.add(enchantment.getDescription());
    lore.add("ID: " + enchantment.getID());

    meta.setLore(lore);

    item.setItemMeta(meta);

    return item;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public Material getMaterial() {
    return this.material;
  }

  public void setEnchantment(int id) {
    this.enchantment = NimbleServer.enchantmentManager.getEnchantment(id);
  }

  public NimbleEnchantment getEnchantment() {
    return this.enchantment;
  }
}
