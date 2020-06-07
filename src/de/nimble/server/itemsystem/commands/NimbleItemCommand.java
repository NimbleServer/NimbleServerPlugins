package de.nimble.server.itemsystem.commands;

import de.nimble.server.NimbleServer;
import de.nimble.server.itemsystem.items.NimbleItemCreator;
import de.nimble.server.itemsystem.items.types.NimbleItemType;
import de.nimble.server.itemsystem.ui.NimbleItemHelp;
import de.nimble.server.sql.NimbleConnection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;

public class NimbleItemCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
    if (!(cs instanceof Player)) {
      return true;
    }

    Player player = (Player) cs;
    if (args.length == 0) {
      NimbleItemHelp itemHelp = new NimbleItemHelp();
      itemHelp.send(player);
    } else if (args.length == 2) {
      if (args[0].equalsIgnoreCase("get")) {
        NimbleItemCreator creator = new NimbleItemCreator(Integer.parseInt(args[1]));
        player.getInventory().addItem(creator.createItem());
        player.updateInventory();
      }
    } else if (args.length > 2) {
      if (args[0].equalsIgnoreCase("create")) {
        Connection con = NimbleConnection.getConnection("CustomItems");
        NimbleServer.itemSql.createNewItem(
            con,
            args[1], // displayName
            args[2], // description
            NimbleItemType.getTypeByName(args[3]), // type
            args[4], // enchantment ids,
            args[5] // material
            );

        NimbleServer.itemManager.clear();
        NimbleServer.itemSql.getItems().stream().forEach(i -> NimbleServer.itemManager.addItem(i));
      }
    }

    return true;
  }
}
