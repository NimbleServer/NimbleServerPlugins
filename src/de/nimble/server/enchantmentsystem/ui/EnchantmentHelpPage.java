package de.nimble.server.enchantmentsystem.ui;

import org.bukkit.entity.Player;

import de.nimble.server.NimbleServer;
import de.nimble.server.enchantmentsystem.enchants.Enchantment;
import de.nimble.server.ui.chat.ClickableText;

public class EnchantmentHelpPage {
	
	// TODO show help page instead of just 
	
	/**
	 * Shows all the enchantments and adds command to get them
	 * 
	 * @param player message is send to
	 */
	public void send(Player player) {
		ClickableText helpPage = new ClickableText();
		
		for(Enchantment enchantment : NimbleServer.userEnchantmentConfig.getEnchantments()) {
			helpPage.add(
				enchantment.getEnchantmentName(), 
				"/enchantments get " + enchantment.getEnchantmentName(), 
				"Displayname: " + enchantment.getDisplayName() + " - Level: " + enchantment.getLevel()
			);
		}
		helpPage.send(player);
	}
	
}