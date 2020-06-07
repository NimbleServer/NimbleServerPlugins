package de.nimble.server.enchantmentsystem.ui;

import org.bukkit.entity.Player;

import de.nimble.server.NimbleServer;
import de.nimble.server.ui.chat.ClickableText;

public class EnchantmentHelpPage {
	
	// TODO show help page instead of just enchantments
	
	/**
	 * Shows all the enchantments and adds command to get them
	 * 
	 * @param player message is send to
	 */
	public void send(Player player) {
		ClickableText helpPage = new ClickableText();
		
		if(NimbleServer.enchantmentManager.getEnchantments().size() == 0) {
			helpPage.add("No enchantments", "", "No enchantments");
		} else {
			NimbleServer.enchantmentManager.getEnchantments().stream().forEach(enchantment -> {
				helpPage.add(
					enchantment.getDisplayName(),
					"/enchantments get " + enchantment.getID(),
					"Displayname: " + enchantment.getDisplayName()
				);
			});
		}
		
		
		
		helpPage.send(player);
	}
	
}