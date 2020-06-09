package de.nimble.server.ui.window;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import de.nimble.server.itemsystem.items.NimbleItem;

import java.util.HashMap;

/**
 * Base class to create new custom inventories/windows(e.g. custom crafing inventory)
 *
 * @author nimble
 */
public abstract class NimbleWindow {

  private int size;
  private String title;
  private ItemStack[] items;
  private Inventory inventory;

  public NimbleWindow(int size, String title) {
    init(size, title);
  }

  private void init(int size, String title) {
    if (size < 9) {
      this.size = 9;
    } else {
      this.size = size;
    }
    this.title = title;
    this.items = new ItemStack[size];
  }

  /**
   * Defines what happens when an item in the Inventory is clicked and checks if the <b>clicked item</b> is an
   * <b>NimbleItem</b>
   * @param event InventorClickEvent
   * @see NimbleItem
   */
  public abstract void onClick(InventoryClickEvent event);

  /**
   * Initializes the ItemStack array
   */
  public abstract void initItems();

  /**
   * Opens a <b>Players</b> inventory
   * @param player Player the inventory is opened for
   */
  public void open(Player player) {
    setInventory();
    initItems();
    player.openInventory(getInventory());
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getSize() {
    return this.size;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  public void addItem(int pos, ItemStack item) {
    if (items.length > pos) {
      items[pos] = item;
    }
  }

  public void addItems(ItemStack... items) {
    this.items = items;
  }

  public ItemStack[] getItems() {
    return this.items;
  }

  public void setInventory() {
    this.inventory = Bukkit.createInventory(null, this.size, this.title);
  }

  public void setInventory(Inventory inv) {
    this.inventory = inv;
  }

  public Inventory getInventory() {
    return this.inventory;
  }
}
