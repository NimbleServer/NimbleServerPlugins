package de.nimble.server.itemsystem.items;

import de.nimble.server.parser.LoreParser;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

	private List<NimbleItem> items;

	private static ItemManager itemManager;

	public static ItemManager getInstance() {
		if(itemManager == null) {
			itemManager = new ItemManager();
		}
		return itemManager;
	}

	private ItemManager() {
		this.items = new ArrayList<>();
	}

	public void addItem(NimbleItem item) {
		this.items.add(item);
	}

	public NimbleItem getItem(int id) {
		for(NimbleItem item : items) {
			if(item.getID() == id) {
				return item;
			}
		}
		return null;
	}

	public void deleteItem(int id) {
		items.stream().filter(i -> i.getID() == id).forEach(i -> items.remove(i));
	}

	public List<NimbleItem> getItems() {
		return this.items;
	}

	public void clear() {
		items.clear();
	}

	public boolean isNimbleItem(ItemStack item) {
		LoreParser parser = new LoreParser(item);
		return getItem(parser.getID()) != null;
	}

}