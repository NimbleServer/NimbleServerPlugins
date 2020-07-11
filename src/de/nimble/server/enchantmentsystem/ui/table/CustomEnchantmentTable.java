package de.nimble.server.enchantmentsystem.ui.table;

import de.nimble.server.NimbleLogger;
import de.nimble.server.NimbleServer;
import de.nimble.server.ui.window.NimbleWindow;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomEnchantmentTable extends NimbleWindow {
  /* TODO replace this version with a version that looks more like an enchantment table
   * so you open a window and can insert some custom item and it will be enchanted with some custom enchantment
   * will cost some ingame currency/levels(don't know yet) and you have different kind of levels
   * Level 1:
   *  - cost: 3 ingame currency
   *  - reward: level 1-3 enchantments and different chances to get them
   * Level 2:
   *  - cost: 5 ingame currency:
   *  - reward: 1-10 enchantments and higher chances to get higher levels or more rare enchantments
   * etc
   */
  public CustomEnchantmentTable() {
    super(
        NimbleServer.enchantmentConfig.getEnchantmentTableSize(),
        NimbleServer.enchantmentConfig.getWindowDisplay());
  }
  /**
   * Checks if item on cursor is NimbleItem
   * @param event InventoryClickEvent to detect clicks of the inventory
   */
  @Override
  public void onClick(InventoryClickEvent event) {
    if(event.getCurrentItem() != null) {
      if (NimbleServer.enchantmentConfig.isFillerItem(event.getCurrentItem(), event.getSlot())
          || event.getCurrentItem().getType() == Material.AIR) {
        event.setCancelled(true);
        NimbleLogger.getInstance()
            .log("CustomEnchantmentTable onClick is CANCELLED because it is an filler item");
      } else {
        event.setCancelled(false);
      }
    } else if(event.getCursor() != null) {
      if (NimbleServer.enchantmentConfig.isFillerItem(event.getCursor(), event.getSlot()) || event.getCursor().getType() == Material.AIR) {
        event.setCancelled(true);
        NimbleLogger.getInstance()
            .log("CustomEnchantmentTable onClick is CANCELLED because it is an filler item");
      } else {
        event.setCancelled(false);
      }
    }

  }

  /**
   * Initializes inventory with filler items and doesn't set an Item at given <b>empty slots</b>
   */
  public void initItems() {
    List<Integer> emptySlots = NimbleServer.enchantmentConfig.getEmptySlots();
    for (int i = 0; i < getSize(); i++) {
      if(!(emptySlots.contains(i))) {
        getInventory().setItem(i, getFiller());
      }
    }
  }

  public ItemStack getFiller() {
    ItemStack filler = new ItemStack(NimbleServer.enchantmentConfig.getFillerMaterial());
    ItemMeta fillerMeta = filler.getItemMeta();
    fillerMeta.setDisplayName(NimbleServer.enchantmentConfig.getFillerDisplay());
    filler.setItemMeta(fillerMeta);
    return filler;
  }
}
