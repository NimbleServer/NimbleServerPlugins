package de.nimble.server.itemsystem.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import de.nimble.server.NimbleServer;
import de.nimble.server.config.Config;
import de.nimble.server.customtags.NimbleTag;
import de.nimble.server.enchantmentsystem.enchants.Enchantment;

public class NimbleItemConfig extends Config {

	private NimbleItemConfig(String fileName) {
		super("items/NimbleItems");
	}
	
	public void setID(String itemName, byte id) {
		set(itemName + ".id", id);
	}
	
	public byte getID(String itemName) {
		return get(itemName + ".id");
	}
	
	public void setDisplayName(String itemName, String displayName) {
		set(itemName + ".displayName", displayName);
	}
	
	public String getDisplayName(String itemName) {
		return get(itemName + ".displayName");
	}
	
	public void setMaterial(String itemName, Material material) {
		set(itemName + ".material", material);
	}
	
	public void setMaterial(String itemName, String material) {
		set(itemName + ".material", material);
	}
	
	public Material getMaterial(String itemName) {
		return Material.getMaterial(get(itemName + ".material"));
	}
	
	public void setEnchantments(String itemName, List<Enchantment> enchantments) {
		set(itemName + ".enchantments", enchantments);
	}
	
	public List<Enchantment> getEnchantments(String itemName) {
		List<Enchantment> enchantments = new ArrayList<Enchantment>();
		List<String> names = getFileConfiguration().getStringList(itemName + ".enchantments");
		
		for(String name : names) {
			Enchantment enchantment = NimbleServer.userEnchantmentConfig.getEnchantmentByName(name);
			enchantments.add(enchantment);
		}
		
		return enchantments;
	}
	
	public void setTags(String itemName, List<NimbleTag> tags) {
		set(itemName + ".tags", tags);
	}
	
	public void setTags(String itemName, NimbleTag... tags) {
		set(itemName + ".tags", Arrays.asList(tags));
	}
	
	public List<NimbleTag> getTags(String itemName) {
		List<String> configTags = getFileConfiguration().getStringList(itemName + ".tags");
		List<NimbleTag> tags = new ArrayList<>();
		
		configTags.stream().forEach(tag -> {
			String[] split = tag.split(";");
			tags.add(new NimbleTag(split[0], split[1]));
		});
		
		
		return tags;
	}
	
}