package de.nimble.server.enchantmentsystem.enchants;

import de.nimble.server.enchantmentsystem.enchants.types.EnchantmentType;
import org.bukkit.event.Event;

public abstract class NimbleEnchantment {

  private int id;
  private String displayName;
  private String description;
  private EnchantmentType type;

  public NimbleEnchantment() {}

  /**
   * onUse method gets implemented in class that extends Enchantment<br>
   * defines what happens when item with enchantment is used
   *
   * @param event specified by what should happen(e.g. EntityDamageByEntityEvent)
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
    return this.description;
  }

  public void setType(EnchantmentType type) {
    this.type = type;
  }

  public EnchantmentType getType() {
    return this.type;
  }
}
