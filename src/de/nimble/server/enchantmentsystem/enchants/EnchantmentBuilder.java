package de.nimble.server.enchantmentsystem.enchants;

import de.nimble.server.enchantmentsystem.enchants.types.DefaultNimbleEnchantment;
import de.nimble.server.enchantmentsystem.enchants.types.EnchantmentType;
import de.nimble.server.enchantmentsystem.enchants.types.playereffect.DamageNimbleEnchantment;
import de.nimble.server.enchantmentsystem.enchants.types.playereffect.HealNimbleEnchantment;

/**
 * Builder for creating custom enchantments
 * @author master
 */
public class EnchantmentBuilder {

	private int id;
	private EnchantmentType type;
	private String displayName;
	private String description;

	public NimbleEnchantment build() {
		NimbleEnchantment enchantment = null;

		switch (type) {
			case DAMAGE:
				enchantment = new DamageNimbleEnchantment();
				break;
			case HEAL:
				enchantment = new HealNimbleEnchantment();
				break;
			case NONE:
				enchantment = new DefaultNimbleEnchantment();
				break;
			default:
				break;
		}

		enchantment.setID(id);
		enchantment.setDescription(description);
		enchantment.setDisplayName(displayName);
		enchantment.setDescription(description);
		enchantment.setType(type);

		return enchantment;
	}

	public EnchantmentBuilder id(int id) {
		this.id = id;
		return this;
	}

	public EnchantmentBuilder type(EnchantmentType type) {
		this.type = type;
		return this;
	}

	public EnchantmentBuilder displayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public EnchantmentBuilder description(String description) {
		this.description = description;
		return this;
	}

}