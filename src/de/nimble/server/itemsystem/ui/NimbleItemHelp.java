package de.nimble.server.itemsystem.ui;

import de.nimble.server.NimbleServer;
import de.nimble.server.ui.chat.ClickableText;
import org.bukkit.entity.Player;

public class NimbleItemHelp {

	public void send(Player player) {
		ClickableText helpPage = new ClickableText();

		if(NimbleServer.itemManager.getItems().size() == 0) {
			helpPage.add("No items", "", "No items");
		} else {
			NimbleServer.itemManager.getItems().stream().forEach(enchantment -> {
				helpPage.add(
						enchantment.getDisplayName(),
						"/items get " + enchantment.getID(),
						"Displayname: " + enchantment.getDisplayName()
				);
			});
		}



		helpPage.send(player);
	}

}