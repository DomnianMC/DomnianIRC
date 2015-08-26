package com.domnian.DomnianIRC;

import com.domnian.DomnianIRC.listeners.ChatListener;
import com.domnian.DomnianIRC.listeners.PlayerListner;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ==================================================================
 * Copyright willies952002 (c) 2015. All Rights Reserved
 * Any Code contained within this document, and any associated APIs
 * with similar branding are the sole property of willies952002.
 * Distribution, reproduction, taking snippets, or claiming any
 * contents as your own will break the terms of the license, and
 * void any agreements with you, the third party. Thanks
 * ==================================================================
 */
public class DomnianIRC extends JavaPlugin implements Listener {

	private static IRCBridge irc = new IRCBridge();
	private static DomnianIRC instance;

	public void onEnable() {
		instance = this;
		try {
			this.saveDefaultConfig();
			irc.init();
			this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
			this.getServer().getPluginManager().registerEvents(new PlayerListner(), this);
			this.getCommand("irc").setExecutor(new IRCExe());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDisable() {
		irc.conn.doQuit("GalaxyIRC Disabled");
		irc.conn.close();
	}

	public static DomnianIRC getInstance() {
		return instance;
	}

	public static IRCBridge getIRCBridge() {
		return irc;
	}

}