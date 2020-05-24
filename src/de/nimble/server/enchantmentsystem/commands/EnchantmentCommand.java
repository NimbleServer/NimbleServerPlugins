package de.nimble.server.enchantmentsystem.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.nimble.server.enchantmentsystem.enchants.EnchantmentBuilder;
import de.nimble.server.enchantmentsystem.ui.EnchantmentHelpPage;

public class EnchantmentCommand implements CommandExecutor {
	
	/**
	 * command for getting enchantments
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		
		if(args.length == 0) {
			EnchantmentHelpPage helpPage = new EnchantmentHelpPage();
			helpPage.send(player);
		} else if(args.length == 2){
			if(args[0].equalsIgnoreCase("get")) {
				EnchantmentBuilder enchantmentBuilder = new EnchantmentBuilder(args[1]).setMaterial(Material.ENCHANTED_BOOK);
				player.getInventory().addItem(enchantmentBuilder.createEnchantmentBook());
				player.updateInventory();
			}
		}
		
		return true;
	}
	
	
	
}