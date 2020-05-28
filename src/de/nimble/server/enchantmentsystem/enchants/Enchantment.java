package de.nimble.server.enchantmentsystem.enchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Event;

import de.nimble.server.NimbleServer;
import de.nimble.server.customtags.NimbleTag;

public abstract class Enchantment {
	
	private String enchantmentName;
	private List<NimbleTag> tags;
	
	public Enchantment(String enchantmentName) {
		this.enchantmentName = enchantmentName;
		this.tags = new ArrayList<NimbleTag>();
	}
	
	/**
	 * onUse method gets implementated in class that extends Enchantment
	 * defines what happens when item with enchantment is used
	 * @param event specified by what should happen 
	 */
	public abstract void onUse(Event event);
	
	/**
	 * adds a customtag to the list of tags an enchantment has
	 * @param NimbleTag
	 */
	public void addTag(NimbleTag tag) {
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
	
	public String getID() {
		return NimbleServer.userEnchantmentConfig.getID(enchantmentName);
	}
	
	public void setID(String id) {
		NimbleServer.userEnchantmentConfig.setID(enchantmentName, id);
	}
	
	/**
	 * reads displayname from config file with the given enchantmentname 
	 * @return String
	 */
	public String getDisplayName() {
		String name = NimbleServer.userEnchantmentConfig.getDisplayName(enchantmentName);
		if(name == null || name.equals("")) {
			return "";
		} else {
			return name;
		}
	}
	
	/**
	 * saves displayname in config with the given enchantmentname
	 * @param displayName
	 */
	public void setDisplayName(String displayName) {
		NimbleServer.userEnchantmentConfig.setDisplayName(enchantmentName, displayName);
	}
	
	/**
	 * gets the given enchantmentname
	 * @return
	 */
	public String getEnchantmentName() {
		return this.enchantmentName;
	}
	
	/**
	 * saves the enchantmentname locally in ram
	 * @param string enchantmentName
	 */
	public void setEnchantmentName(String enchantmentName) {
		this.enchantmentName = enchantmentName;
	}
	
	/**
	 * reads description from config file with the given enchantmentname
	 * @return
	 */
	public String getDescription() {
		String description = NimbleServer.userEnchantmentConfig.getDescription(enchantmentName);
		if(description == null || description.equals("")) {
			return "";
		} else {
			return description;
		}
	}
	/**
	 * saves the description as string in config with the given enchantmentname
	 * @param description as string
	 */
	public void setDescription(String description) {
		NimbleServer.userEnchantmentConfig.setDescription(enchantmentName, description);
	}
	
}