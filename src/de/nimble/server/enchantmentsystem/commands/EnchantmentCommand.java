package de.nimble.server.enchantmentsystem.commands;

import java.sql.Connection;

import de.nimble.server.parser.LoreParser;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.nimble.server.NimbleServer;
import de.nimble.server.enchantmentsystem.config.UserEnchantmentSQL;
import de.nimble.server.enchantmentsystem.enchants.NimbleEnchantmentCreator;
import de.nimble.server.enchantmentsystem.enchants.types.EnchantmentType;
import de.nimble.server.enchantmentsystem.ui.EnchantmentHelpPage;
import de.nimble.server.sql.NimbleConnection;

public class EnchantmentCommand implements CommandExecutor {

  /** command for getting enchantments */
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      return true;
    }
    Player player = (Player) sender;

    if (args.length == 0) {
      EnchantmentHelpPage helpPage = new EnchantmentHelpPage();
      helpPage.send(player);
    } else if (args.length == 2) {
      if (args[0].equalsIgnoreCase("get")) {
        NimbleEnchantmentCreator creator =
            new NimbleEnchantmentCreator(Integer.parseInt(args[1]), Material.ENCHANTED_BOOK);

        player.getInventory().addItem(creator.createEnchantment());
        player.updateInventory();
      }
    } else if (args.length > 2) {
      if (args[0].equalsIgnoreCase("create")) {
        // solve with book and quirrel maybe?
        Connection con = NimbleConnection.getConnection("UserEnchantments");
        NimbleServer.userEnchantmentsSql.createNewEnchantment(
            con,
            args[1], // displayName
            args[2], // description
            EnchantmentType.getTypeByName(args[3]), // type
            Double.parseDouble(args[4])); // multiplier

        NimbleServer.enchantmentManager.clear();
        NimbleServer.userEnchantmentsSql.getEnchantments().stream()
            .forEach(e -> NimbleServer.enchantmentManager.addEnchantment(e));
      }
    }

    return true;
  }
}
