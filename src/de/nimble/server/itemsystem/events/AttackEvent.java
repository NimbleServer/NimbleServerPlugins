package de.nimble.server.itemsystem.events;

import de.nimble.server.NimbleServer;
import de.nimble.server.itemsystem.items.NimbleItem;
import de.nimble.server.parser.LoreParser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AttackEvent implements Listener {

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
      player.sendMessage("" + event.getDamage());
    }
  }
}
