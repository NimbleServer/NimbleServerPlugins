package de.nimble.server.itemsystem.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import de.nimble.server.customtags.NimbleTag;
import de.nimble.server.enchantmentsystem.enchants.Enchantment;

public abstract class NimbleItem {
	
	private byte id;
	
	private Material material;
	private ItemStack item;
	
	private List<Enchantment> enchantments;
	private List<NimbleTag> tags;
	
	public NimbleItem() {
		this.enchantments = new ArrayList<Enchantment>();
		this.tags = new ArrayList<NimbleTag>();
	}
	
	/**
	 * onUse method gets implementated in class that extends Enchantment
	 * defines what happens when item with enchantment is used
	 * @param event specified by what should happen 
	 */
	public abstract void onUse(Event event);
	
	public void addEnchantment(Enchantment enchantment) {
		this.enchantments.add(enchantment);
	}
	
	public List<Enchantment> getEnchantments() {
		return this.enchantments;
	}
	
	public ItemStack getItem() {
		ItemStack item = new ItemStack(material);
		return item;
	}
	
	public void setItem(ItemStack item) {
		this.item = item;
	}
	
	/**
	 * adds a customtag to the list of tags an enchantment has
	 * @param NimbleTag
	 */
	public void addTag(String tagName, String tagValue) {
		NimbleTag tag = new NimbleTag(tagName, tagValue);
		setItem(tag.create(getItem()));
		
		tags.add(tag);
	}
	
	/**
	 * searches a tag for its name in the list of tags
	 * 
	 * @param name
	 * @return null or tag
	 */
	public NimbleTag getTag(String name) {
		for(NimbleTag tag : tags) {
			if(tag.getTagName().equals(name)) {
				return tag;
			}
		}
		return null;
	}
	
	/**
	 * returns all tags
	 * @return tags
	 */
	public List<NimbleTag> getTags() {
		return this.tags;
	}
	
}