package de.nimble.server.enchantmentsystem.enchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.nimble.server.NimbleServer;
import de.nimble.server.customtags.NimbleTag;

/**
 * class to get custom enchantment books
 * @author master
 *
 */
public class EnchantmentBuilder {
	
	private Enchantment enchantment;
	private Material material;
	
	private String enchantmentName;
	
	public EnchantmentBuilder(String enchantmentName) {
		this.enchantmentName = enchantmentName;
		this.enchantment = NimbleServer.userEnchantmentConfig.getEnchantmentByName(enchantmentName);
	}
	
	/**
	 * enchantment in item shape
	 * @return itemstack with finished enchantment
	 */
	public ItemStack createEnchantmentBook() {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(enchantment.getDisplayName());
		
		// NBT ID
		NimbleTag tag = new NimbleTag("id", enchantment.getID());
		item = tag.create(item);
		enchantment.addTag(tag);
		
		List<String> lore = new ArrayList<String>();
		lore.add("Type: " + NimbleServer.userEnchantmentConfig.getType(enchantmentName));
		lore.add(enchantment.getDescription());
		
		// String id = NimbleServer.userEnchantmentConfig.getEnchantmentByName(enchantmentName).getTag("id").getValue();
		lore.add("ID: " + tag.getValue());
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public EnchantmentBuilder setEnchantment() {
		if(this.enchantmentName != null && (!(this.enchantmentName.equals("")))) {
			this.enchantment = NimbleServer.userEnchantmentConfig.getEnchantmentByName(this.enchantmentName);
		}
		return this;
	}
	
	public EnchantmentBuilder setEnchantment(String enchantmentName) {
		this.enchantment = NimbleServer.userEnchantmentConfig.getEnchantmentByName(enchantmentName);
		return this;
	}
	
	public Enchantment getEnchantment() {
		return this.enchantment;
	}
	
	public EnchantmentBuilder setMaterial(Material material) {
		this.material = material;
		return this;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public EnchantmentBuilder setEnchantmentName(String enchantmentName) {
		this.enchantmentName = enchantmentName;
		return this;
	}
	
	public String getEnchantmentName() {
		return this.enchantmentName;
	}
	
}