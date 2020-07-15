package de.nimble.server.player;

import de.nimble.server.NimbleLogger;
import org.bukkit.entity.Player;

public class NimblePlayer {

	private Player player;
	private NimbleBackpack backpack;
	private NimbleExperience experience;

	public void sendMessage(String message) {
		if(this.player == null) {
			NimbleLogger.getInstance().error("Player in NimblePlayer is null");
			return;
		}
		this.player.sendMessage(message);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setBackpack(NimbleBackpack backpack) {
		this.backpack = backpack;
	}

	public NimbleBackpack getBackpack() {
		return this.backpack;
	}

	public void setExperience(NimbleExperience experience) {
		this.experience = experience;
	}

	public NimbleExperience getExperience() {
		return this.experience;
	}

}