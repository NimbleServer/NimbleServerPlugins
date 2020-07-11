package de.nimble.server.itemsystem.commands;

import de.nimble.server.NimbleLogger;
import de.nimble.server.enchantmentsystem.ui.table.CustomEnchantmentTable;
import de.nimble.server.ui.window.NimbleWindow;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NimbleEnchantmentTableCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
    if(!(cs instanceof Player)) {
      NimbleLogger.getInstance().log("CommandSender is no Player");
      return true;
    }
    Player player = (Player) cs;
    CustomEnchantmentTable window = new CustomEnchantmentTable();
    window.open(player);

    NimbleLogger.getInstance().log("Opened enchantment table");

    return true;
  }
}
