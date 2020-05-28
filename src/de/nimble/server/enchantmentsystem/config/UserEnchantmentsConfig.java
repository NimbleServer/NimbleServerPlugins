package de.nimble.server.enchantmentsystem.config;

import java.util.ArrayList;
import java.util.List;

import de.nimble.server.config.Config;
import de.nimble.server.enchantmentsystem.enchants.Enchantment;
import de.nimble.server.enchantmentsystem.enchants.types.EnchantmentType;
import de.nimble.server.enchantmentsystem.enchants.types.playereffect.DamageEnchantment;
import de.nimble.server.enchantmentsystem.enchants.types.playereffect.HealEnchantment;

public class UserEnchantmentsConfig extends Config {
	
	public static UserEnchantmentsConfig config = null;
	
	public static UserEnchantmentsConfig getInstance() {
		if(config == null) {
			config = new UserEnchantmentsConfig();
		}
		return config;
	}
	
	private UserEnchantmentsConfig() {
		super("enchantments/NimbleEnchantments");
	}
	
	/**
	 * gets all enchantments from config and adds them to a list
	 * @return EnchantmentList
	 */
	public List<Enchantment> getEnchantments() {
		List<Enchantment> enchantments = new ArrayList<Enchantment>();
		
		for(String enchantmentName : getFileConfiguration().getKeys(false)) {
			enchantments.add(createEnchantment(enchantmentName));
		}
		
		return enchantments;
	}
	
	/**
	 * get enchantment by enchantmentname
	 * @param enchantmentName
	 * @return EnchantmentByName
	 */
	public Enchantment getEnchantmentByName(String enchantmentName) {
		for(String key : getFileConfiguration().getKeys(false)) {
			if(!(key.equals(enchantmentName))) {
				continue;
			}
			return createEnchantment(enchantmentName);
		}
		return null;
	}
	
	/**
	 * function to create enchantment
	 * @param enchantmentName
	 * @return enchantment
	 */
	private Enchantment createEnchantment(String enchantmentName) {
		Enchantment enchantment = null;
		EnchantmentType type = getType(enchantmentName);
		
		switch(type) {
		case DAMAGE:
			enchantment = new DamageEnchantment(enchantmentName);
			break;
		case HEAL:
			enchantment = new HealEnchantment(enchantmentName);
			break;
		case NONE:
			break;
		default:
			break;
		}
		
		return enchantment;
	}
	
	public void setID(String enchantmentName, String id) {
		set(enchantmentName  + ".id", id);
	}
	
	public String getID(String enchantmentName) {
		return getString(enchantmentName + ".id");
	}
	
	public void setDisplayName(String enchantmentName, String displayName) {
		set(enchantmentName + ".displayName", displayName);
	}
	
	public String getDisplayName(String enchantmentName) {
		return getString(enchantmentName + ".displayName");
	}
	
	public void setType(String enchantmentName, EnchantmentType type) {
		set(enchantmentName + ".type", type);
	}
	
	public EnchantmentType getType(String enchantmentName) {
		return EnchantmentType.getTypeByName(getString(enchantmentName + ".type"));
	}
	
	public void setDescription(String enchantmentName, String description) {
		set(enchantmentName + ".description", description);
	}
	
	public String getDescription(String enchantmentName) {
		return getString(enchantmentName + ".description");
	}
	
}