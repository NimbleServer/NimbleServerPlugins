package de.nimble.server.enchantmentsystem.enchants.types.playereffect;

import de.nimble.server.NimbleServer;
import de.nimble.server.enchantmentsystem.enchants.NimbleEnchantment;

public abstract class PlayerEffectNimbleEnchantment extends NimbleEnchantment {

  public double getMultiplier() {
    return Double.parseDouble(
        String.valueOf(
            NimbleServer.userEnchantmentsSql.get("customenchantments", "multiplier", getID())));
  }

  public void setMultiplier(double multiplier) {
    NimbleServer.userEnchantmentsSql.update(
        "customenchantments", "multiplier", getID(), String.valueOf(multiplier));
  }
}