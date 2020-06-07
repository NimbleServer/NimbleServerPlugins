package de.nimble.server.enchantmentsystem.enchants.types.playereffect;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * DamageEnchantment class
 *
 * @author master
 */
public class DamageNimbleEnchantment extends PlayerEffectNimbleEnchantment {

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
    EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) ev;
    event.setDamage(event.getDamage() * (getMultiplier() + 1.5));
  }
}
