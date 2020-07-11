package de.nimble.server.events;

import de.nimble.server.NimbleLogger;
import de.nimble.server.NimbleServer;
import de.nimble.server.enchantmentsystem.enchants.EnchantmentManager;
import de.nimble.server.enchantmentsystem.ui.table.CustomEnchantmentTable;
import de.nimble.server.itemsystem.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NimbleInventoryClickEvent implements Listener {

  private List<Player> avoidDoubleCall = new ArrayList<>();

  @EventHandler
  public void onInvClick(InventoryClickEvent event) {
    if(!(avoidDoubleCall.contains((Player) event.getWhoClicked()))) {
      avoidDoubleCall.add((Player) event.getWhoClicked());
    } else {
      avoidDoubleCall.remove((Player) event.getWhoClicked());
    }

    if (event.getClickedInventory().getType() != InventoryType.ANVIL) {
      return;
    }

    NimbleLogger.getInstance().log("on cursor: " + (event.getCursor().getItemMeta().getDisplayName()));
    NimbleLogger.getInstance().log("not on cursor: " + (event.getCurrentItem().getItemMeta().getDisplayName()));

    AnvilInventory inv = (AnvilInventory) event.getClickedInventory();
    for (ItemStack item : inv.getContents()) {
      if (item == null) {
        return;
      }
      if (inv.getItem(0) == null || inv.getItem(1) == null) {
        return;
      }

      if (ItemManager.getInstance().isNimbleItem(inv.getItem(0))
          && EnchantmentManager.getInstance().isNimbleEnchantment(inv.getItem(1))) {
        NimbleLogger.getInstance().log("None is null");
      }
    }

    /*
       if (event
           .getView()
           .getTitle()
           .equalsIgnoreCase(NimbleServer.enchantmentConfig.getWindowDisplay())) {
         CustomEnchantmentTable table = new CustomEnchantmentTable();
         // table.setInventory(event.getClickedInventory());
         table.onClick(event);
         NimbleLogger.getInstance().log("Clicked Inventory is Enchantment Table");
       }
    */
  }
}
