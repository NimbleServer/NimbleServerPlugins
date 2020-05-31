package de.nimble.server.enchantmentsystem.enchants.types.playereffect;

import de.nimble.server.NimbleServer;
import de.nimble.server.enchantmentsystem.enchants.Enchantment;

public abstract class PlayerEffectEnchantment extends Enchantment {
	
	public PlayerEffectEnchantment(String enchantmentName) {
		super(enchantmentName);
	}

	/**
	 * reads multiplier from config with the given enchantmentname
	 * @return multiplier as double
	 */
	public double getMultiplier() {
		return NimbleServer.userEnchantmentConfig.getFileConfiguration().getDouble(getEnchantmentName() + ".multiplier");
	}
	
	/**
	 * set multiplier in config with the given enchantmentname
	 * @param multiplier
	 */
	public void setMultiplier(double multiplier) {
		NimbleServer.userEnchantmentConfig.set(getEnchantmentName() + ".multiplier", multiplier);
	}
	
}