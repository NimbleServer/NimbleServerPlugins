package de.nimble.server.ui.chat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Class to create custom clickable text components 
 * @author master
 */
public class ClickableText {
	
	private List<TextComponent> components;
	
	public ClickableText() {
		this.components = new ArrayList<TextComponent>();
		
	}
	
	/**
	 * adds TextComponents to list 
	 * checks if parameters are null(Optional.
	 * 
	 * @param text to show
	 * @param command to execute when clicked
	 * @param hoverText showed when hovers over text
	 */
	public ClickableText add(String text, String command, String hoverText) {
		
		TextComponent component = null;
		if(text != null && (!(text.equals("")))) {
			component = new TextComponent(text);
		}
		
		if(command != null && (!(command.equals("")))) {
			component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		}
		if(hoverText != null && (!(hoverText.equals("")))) {
			component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverText).create()));
		}
		
		components.add(component);
		return this;
	}
	
	/**
	 * sends all the clickable components to one specific player
	 * 
	 * @param player you want the message to send to
	 */
	public void send(Player player) {
		// TODO send it in single message
		for(TextComponent comp : components) {
			player.spigot().sendMessage(comp);
		}
	}
	
}