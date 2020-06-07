package de.nimble.server.itemsystem.items;

import de.nimble.server.enchantmentsystem.enchants.NimbleEnchantment;
import de.nimble.server.itemsystem.items.types.NimbleItemType;
import org.bukkit.Material;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class NimbleItem {

  private int id;
  private String displayName;
  private String description;

  private List<NimbleEnchantment> enchantments;
  private Material material;

  private NimbleItemType type;

  public NimbleItem() {
    this.enchantments = new ArrayList<>();
  }

  /**
   * onUse method gets implementated in class that extends Enchantment defines what happens when
   * item with enchantment is used
   *
   * @param event specified by what should happen
   */
  public abstract void onUse(Event event);

  public void setID(int id) {
    this.id = id;
  }

  public int getID() {
    return this.id;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void addEnchantment(NimbleEnchantment... enchantment) {
    Arrays.asList(enchantment).stream().forEach(e -> enchantments.add(e));
  }

  public List<NimbleEnchantment> getEnchantments() {
    return this.enchantments;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public void setMaterial(String material) {
    this.material = Material.getMaterial(material);
  }

  public Material getMaterial() {
    return this.material;
  }

  public void setType(NimbleItemType type) {
    this.type = type;
  }

  public NimbleItemType getType() {
    return this.type;
  }
}
