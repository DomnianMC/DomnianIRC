package net.galaxygaming.GalaxyIRC;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	IRCBridge irc;
	
	public ChatListener(IRCBridge bridge) {
		this.irc = bridge; 
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent apce) {
		Player p = apce.getPlayer();
		String msg = apce.getMessage();
		irc.sendMessage(p.getDisplayName(), msg);
	}

}
