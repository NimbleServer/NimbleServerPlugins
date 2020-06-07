package de.nimble.server;

import org.bukkit.Server;

public class NimbleLogger {

	private final String PREFIX = "§8[§6Nimble§8] §a";
	private Server server;

	private static NimbleLogger logger = null;

	public static NimbleLogger getInstance() {
		if(logger == null) {
			logger = new NimbleLogger();
		}
		return logger;
	}

	private NimbleLogger() {

	}

	public void log(String msg) {
		this.server.getConsoleSender().sendMessage(PREFIX + msg);
	}

	public void setServer(Server server) {
		this.server = server;
	}

}