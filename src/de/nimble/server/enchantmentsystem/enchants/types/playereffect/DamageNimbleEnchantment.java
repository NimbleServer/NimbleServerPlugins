package de.nimble.server.enchantmentsystem.enchants.types.playereffect;

import org.bukkit.event.Event;

/**
 * DamageEnchantment class
 *
 * @author master
 */
public class DamageNimbleEnchantment extends PlayerEffectNimbleEnchantment {

  private double damage;

  public DamageNimbleEnchantment() {
    super();
  }

  /**
   * Overrides onUse method of base class "NimbleEnchantment"
   *
   * @param EntityDamageByEntityEvent
   */
  @Override
  public void onUse(Event ev) {
    damage = (getMultiplier() + 1.5);
  }

  public void setDamage(double damage) {
    this.damage = damage;
  }

  public double getDamage() {
    return damage;
  }
}
