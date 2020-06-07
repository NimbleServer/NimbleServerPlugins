package de.nimble.server.enchantmentsystem.enchants.types.playereffect;

import de.nimble.server.NimbleServer;
import de.nimble.server.enchantmentsystem.config.UserEnchantmentSQL;
import de.nimble.server.enchantmentsystem.enchants.EnchantmentSql;
import de.nimble.server.mysql.NimbleConnection;

public abstract class PlayerEffectEnchantment extends EnchantmentSql {
	
	public PlayerEffectEnchantment(String enchantmentName) {
		super(enchantmentName);
	}

	/**
	 * reads multiplier from config with the given enchantmentname
	 * @return multiplier as double
	 */
	public double getMultiplier() {
		return Double.parseDouble(NimbleServer.userEnchantmentsSql.get(NimbleConnection.getConnection(UserEnchantmentSQL.DB_NAME), "multiplier", getEnchantmentName()));
	}
	
	/**
	 * set multiplier in config with the given enchantmentname
	 * @param multiplier
	 */
	public void setMultiplier(double multiplier) {
		NimbleServer.userEnchantmentsSql.update(NimbleConnection.getConnection(UserEnchantmentSQL.DB_NAME), "multiplier", getEnchantmentName(), String.valueOf(multiplier));
	}
	
}