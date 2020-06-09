package de.nimble.server.customtags;

import net.minecraft.server.v1_15_R1.NBTBase;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.NBTTagString;

public class NimbleTag {
	
	private String tagName;
	private String value;
	
	public NimbleTag(String tagName, String value) {
		this.tagName = tagName;
		this.value = value;
	}
	
	public ItemStack create(ItemStack item) {
		net.minecraft.server.v1_15_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);

		NBTTagCompound itemCompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		itemCompound.set(tagName, NBTTagString.a(value));
		nmsItem.setTag(itemCompound);
		return CraftItemStack.asBukkitCopy(nmsItem);
	}
	
	public static boolean hasTag(ItemStack item, String name) {
		net.minecraft.server.v1_15_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		if(nmsItem.getTag().getString(name) != null && (!(nmsItem.getTag().getString(name).equals("")))) {
			return true;
		}
		return false;
	}
	
	public static String getTag(ItemStack item, String name) {
		net.minecraft.server.v1_15_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		if(nmsItem.getTag().getString(name) != null && (!(nmsItem.getTag().getString(name).equals("")))) {
			return nmsItem.getTag().getString(name);
		}
		return "-1";
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public String getTagName() {
		return this.tagName;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}