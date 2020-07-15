package de.nimble.server.player;

import org.bukkit.Bukkit;

import java.util.UUID;

public class NimblePlayerManager {

	public static NimblePlayer getPlayerByUUID(UUID uuid) {
		NimblePlayer player = new NimblePlayer();
    // TODO load backpack, experience etc from DB
    player.setPlayer(Bukkit.getPlayer(uuid));
		return player;
	}

}
