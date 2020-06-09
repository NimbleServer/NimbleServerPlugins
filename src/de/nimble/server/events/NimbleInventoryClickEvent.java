package de.nimble.server.events;

import de.nimble.server.NimbleLogger;
import de.nimble.server.NimbleServer;
import de.nimble.server.enchantmentsystem.ui.table.CustomEnchantmentTable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NimbleInventoryClickEvent implements Listener {

  @EventHandler
  public void onInvClick(InventoryClickEvent event) {
    if (event.getView().getTitle().equalsIgnoreCase(NimbleServer.enchantmentConfig.getWindowDisplay())) {
      CustomEnchantmentTable table = new CustomEnchantmentTable();
      // table.setInventory(event.getClickedInventory());
      table.onClick(event);
      NimbleLogger.getInstance().log("Clicked Inventory is Enchantment Table");
    }
  }
}
