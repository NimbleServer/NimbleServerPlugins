package de.nimble.server.enchantmentsystem.enchants.types.playereffect;

import de.nimble.server.NimbleServer;
import de.nimble.server.enchantmentsystem.enchants.Enchantment;

public abstract class PlayerEffectEnchantment extends Enchantment {
	
	/**
	 * reads multiplier from config with the given enchantmentname
	 * gets multiplied with the incoming damage which is added to the level times 1.5 
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
		NimbleServer.userEnchantmentConfig.getFileConfiguration().set(getEnchantmentName() + ".multiplier", multiplier);
	}
	
}