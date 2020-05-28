package de.nimble.server.enchantmentsystem.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.nimble.server.config.Config;
import de.nimble.server.enchantmentsystem.enchants.Enchantment;
import de.nimble.server.enchantmentsystem.enchants.types.EnchantmentType;
import de.nimble.server.enchantmentsystem.enchants.types.playereffect.DamageEnchantment;
import de.nimble.server.enchantmentsystem.enchants.types.playereffect.HealEnchantment;

public class UserEnchantmentsConfig extends Config {
	
	private static UserEnchantmentsConfig config;
	
	/**
	 *  
	 * @return only instance of UserEnchantmentConfig
	 */
	public static UserEnchantmentsConfig getInstance() {
		if(config == null) {
			config = new UserEnchantmentsConfig("CustomEnchantments");
		}
		return config;
	}
	
	private UserEnchantmentsConfig(String fileName) {
		super(fileName);
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
	
	public void save() {
		try {
			getFileConfiguration().save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setID(String enchantmentName, String id) {
		getFileConfiguration().set(enchantmentName  + ".id", id);
		save();
	}
	
	public String getID(String enchantmentName) {
		return getFileConfiguration().getString(enchantmentName + ".id");
	}
	
	public void setDisplayName(String enchantmentName, String displayName) {
		getFileConfiguration().set(enchantmentName + ".displayName", displayName);
		save();
	}
	
	public String getDisplayName(String enchantmentName) {
		return getFileConfiguration().getString(enchantmentName + ".displayName");
	}
	
	public void setLevel(String enchantmentName, byte level) {
		getFileConfiguration().set(enchantmentName + ".level", level);
		save();
	}
	
	public byte getLevel(String enchantmentName) {
		return (byte) getFileConfiguration().getInt(enchantmentName + ".level");
	}
	
	public void setType(String enchantmentName, EnchantmentType type) {
		getFileConfiguration().set(enchantmentName + ".type", type);
		save();
	}
	
	public EnchantmentType getType(String enchantmentName) {
		return EnchantmentType.getTypeByName(getFileConfiguration().getString(enchantmentName + ".type"));
	}
	
	public void setDescription(String enchantmentName, String description) {
		getFileConfiguration().set(enchantmentName + ".description", description);
		save();
	}
	
	public String getDescription(String enchantmentName) {
		return getFileConfiguration().getString(enchantmentName + ".description");
	}
	
}