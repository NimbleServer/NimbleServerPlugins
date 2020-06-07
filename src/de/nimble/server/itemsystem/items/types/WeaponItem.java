package de.nimble.server.itemsystem.items.types;

import de.nimble.server.NimbleLogger;
import de.nimble.server.enchantmentsystem.enchants.NimbleEnchantment;
import de.nimble.server.enchantmentsystem.enchants.types.playereffect.DamageNimbleEnchantment;
import de.nimble.server.itemsystem.items.NimbleItem;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class WeaponItem extends NimbleItem {

  private double damage;

  @Override
  public void onUse(Event ev) {
    EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) ev;

    NimbleLogger.getInstance().log("DAMAGE: " + event.getDamage());
    damage = 0;
    for (NimbleEnchantment enchantment : getEnchantments()) {
      if (enchantment instanceof DamageNimbleEnchantment) {
        enchantment.onUse(event);
        damage += ((DamageNimbleEnchantment) enchantment).getDamage();
      }
    }

    event.setDamage(event.getDamage() * (getDamage() + 1.5));
  }

  public void setDamage(double damage) {
    this.damage = damage;
  }

  public double getDamage() {
    return damage;
  }
}
