package com.domnian.DomnianIRC.listeners;

import com.domnian.DomnianIRC.DomnianIRC;
import com.domnian.DomnianIRC.IRCBridge;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	IRCBridge irc = DomnianIRC.getIRCBridge();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent apce) {
		Player p = apce.getPlayer();
		String msg = apce.getMessage();
		irc.msgServerToIRC(ChatColor.stripColor(p.getDisplayName()), msg);
	}

}
