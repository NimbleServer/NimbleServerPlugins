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
			System.out.println("1: " + enchantmentName);
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
		System.out.println("2: " + enchantmentName);
		String displayName = getDisplayName(enchantmentName);
		byte level = getLevel(enchantmentName);
		EnchantmentType type = getType(enchantmentName);
		String description = getDescription(enchantmentName);
			
		Enchantment enchantment = null;
			
		switch(type) {
		case DAMAGE:
			enchantment = new DamageEnchantment();
			((DamageEnchantment) enchantment).setMultiplier(getFileConfiguration().getDouble(enchantmentName + ".multiplier"));
			break;
		case HEAL:
			enchantment = new HealEnchantment();
			((HealEnchantment) enchantment).setMultiplier(getFileConfiguration().getDouble(enchantmentName + ".multiplier"));
			break;
		case NONE:
			break;
		default:
			break;
		}
		
		enchantment.setEnchantmentName(enchantmentName);
		enchantment.setDisplayName(displayName);
		enchantment.setLevel(level);
		enchantment.setDescription(description);
			
		return enchantment;
	}
	
	public void save() {
		try {
			getFileConfiguration().save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		System.out.println("3: " + enchantmentName);
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