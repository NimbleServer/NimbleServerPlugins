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
