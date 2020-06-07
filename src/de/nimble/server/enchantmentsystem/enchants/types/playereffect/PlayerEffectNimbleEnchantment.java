package de.nimble.server.enchantmentsystem.enchants.types.playereffect;

import de.nimble.server.NimbleServer;
import de.nimble.server.enchantmentsystem.enchants.NimbleEnchantment;
import de.nimble.server.sql.NimbleConnection;

public abstract class PlayerEffectNimbleEnchantment extends NimbleEnchantment {

  public PlayerEffectNimbleEnchantment() {
    super();
  }

  /**
   * reads multiplier from config with the given enchantmentname
   *
   * @return multiplier as double
   */
  public double getMultiplier() {
    return Double.parseDouble(
        NimbleServer.userEnchantmentsSql.get(
            NimbleConnection.getConnection("UserEnchantments"), "multiplier", getID()));
  }

  /**
   * set multiplier in config with the given enchantmentname
   *
   * @param multiplier
   */
  public void setMultiplier(double multiplier) {
    NimbleServer.userEnchantmentsSql.update(
        NimbleConnection.getConnection("UserEnchantments"),
        "multiplier",
        getID(),
        String.valueOf(multiplier));
  }
}
