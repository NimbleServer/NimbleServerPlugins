package de.nimble.server.enchantmentsystem.enchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.nimble.server.NimbleServer;

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
		ItemStack book = new ItemStack(material);
		ItemMeta meta = book.getItemMeta();
		meta.setDisplayName(enchantment.getDisplayName());
		
		List<String> lore = new ArrayList<String>();
		lore.add("Level: " + enchantment.getLevel());
		lore.add(enchantment.getDescription());
		
		meta.setLore(lore);
		
		book.setItemMeta(meta);
		
		return book;
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