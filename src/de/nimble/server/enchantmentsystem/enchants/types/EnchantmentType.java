package de.nimble.server.enchantmentsystem.enchants.types;

public enum EnchantmentType {
  DAMAGE,
  HEAL,
  NONE;

  public static EnchantmentType getTypeByName(String name) {
    for (EnchantmentType type : values()) {
      if (name.equals(type.toString())) {
        return type;
      }
    }
    return NONE;
  }
}
