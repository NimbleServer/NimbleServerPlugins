package de.nimble.server.events;

import de.nimble.server.NimbleServer;
import de.nimble.server.itemsystem.items.NimbleItem;
import de.nimble.server.parser.LoreParser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AttackEvent implements Listener {

  /**
   * Only fires when Entity is attacked by a player and contains some checks<br>
   * e.g. if item in Hand is NimbleItem => call onUse()
   *
   * @param event EntityDamageByEntityEvent
   */
  @EventHandler
  public void onAttack(EntityDamageByEntityEvent event) {
    if (!(event.getDamager() instanceof Player)) {
      return;
    }

    Player player = (Player) event.getDamager();

    if (NimbleServer.itemManager.isNimbleItem(player.getItemInHand())) {
      LoreParser parser = new LoreParser(player.getItemInHand());
      NimbleItem item = NimbleServer.itemManager.getItem(parser.getID());
      item.onUse(event);
    }
  }
}
