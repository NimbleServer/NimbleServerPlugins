package de.nimble.server.itemsystem.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import de.nimble.server.NimbleServer;
import de.nimble.server.customtags.NimbleTag;

public abstract class NimbleItem {
	
	private String itemName;
	
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
	
	/**
	 * saves id in config
	 * @param id
	 */
	public void setID(String id) {
		NimbleServer.nimbleItemConfig.setID(itemName, id);
	}
	
	/**
	 * reads id from config by the given itemname
	 * @return String id
	 */
	public String getID() {
		return NimbleServer.nimbleItemConfig.getID(itemName);
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	/**
	 * saves displayname in config by the given itemname
	 * @param displayName
	 */
	public void setDisplayName(String displayName) {
		NimbleServer.nimbleItemConfig.setDisplayName(itemName, displayName);
	}
	
	/**
	 * @return displayname for the item by the given itemname
	 */
	public String getDisplayName() {
		return NimbleServer.nimbleItemConfig.getDisplayName(itemName);
	}
	
	/**
	 * saves material in config by the given itemname
	 * @param material enum
	 */
	public void setMaterial(Material material) {
		NimbleServer.nimbleItemConfig.setMaterial(itemName, material);
	}
	
	/**
	 * saves material in config by the given itemname
	 * @param String material
	 */
	public void setMaterial(String material) {
		NimbleServer.nimbleItemConfig.setMaterial(material, material);
	}
	
	/**
	 * @return material enum by the given itemname
	 */
	public Material getMaterial() {
		return NimbleServer.nimbleItemConfig.getMaterial(itemName);
	}
	
	/**
	 * saves enchantment list in config
	 * @param enchantment array
	 */
	public void addEnchantment(Enchantment... enchantment) {
		Arrays.asList(enchantment).stream().forEach(e -> enchantments.add(e));
		NimbleServer.nimbleItemConfig.setEnchantments(itemName, enchantments);
	}
	
	/**
	 * @return list of enchantments given by the itemname
	 */
	public List<Enchantment> getEnchantments() {
		return NimbleServer.nimbleItemConfig.getEnchantments(itemName);
	}
	
	/**
	 * Initializes item
	 * @param ItemStack item
	 */
	public void setItem(ItemStack item) {
		this.item = item;
	}
	
	/**
	 * @return Item
	 */
	public ItemStack getItem() {
		return item;
	}
	
	/**
	 * adds a customtag to the list of tags an enchantment has
	 * @param NimbleTag
	 */
	public void addTag(String tagName, String tagValue) {
		NimbleTag tag = new NimbleTag(tagName, tagValue);
		setItem(tag.create(getItem()));
		
		tags.add(tag);
		
		NimbleServer.nimbleItemConfig.setTags(tagValue, tags);
	}
	
	/**
	 * searches a tag for its name in the list of tags
	 * 
	 * @param name
	 * @return null or tag
	 */
	public NimbleTag getTag(String name) {
		for(NimbleTag tag : NimbleServer.nimbleItemConfig.getTags(itemName)) {
			if(tag.getTagName().equals(name)) {
				return tag;
			}
		}
		return null;
	}
	
	/**
	 * @return all tags read from config by the given itemname
	 */
	public List<NimbleTag> getTags() {
		return NimbleServer.nimbleItemConfig.getTags(itemName);
	}
	
}