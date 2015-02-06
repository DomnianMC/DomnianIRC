package com.domnian.DomnianIRC;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.willies952002.WSCore.LogHandler;
import com.willies952002.WSCore.SQLHandler;

public class DomnianIRC extends JavaPlugin implements Listener {

	LogHandler log;
	SQLHandler sql;
	IRCBridge irc = new IRCBridge();

	public void onEnable() {
		try {
			this.saveDefaultConfig();
			sql = new SQLHandler(this.getConfig().getString("db.host"),
					this.getConfig().getString("db.name"), this.getConfig()
							.getString("db.user"), this.getConfig()
							.getString("db.pass"));
			log = new LogHandler(this.getDataFolder().getAbsolutePath());
			irc.init(this);
			this.getServer().getPluginManager()
					.registerEvents(new ChatListener(irc), this);
			this.getServer().getPluginManager().registerEvents(this, this);
			this.getCommand("irc").setExecutor(new IRCExe(this));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDisable() {
		irc.conn.doQuit("GalaxyIRC Disabled");
		irc.conn.close();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent ple) {
		Bukkit.getServer()
				.getScheduler()
				.scheduleSyncDelayedTask(this,
						new LoginMessage(ple.getPlayer()), 20L);
		irc.conn.doPrivmsg(irc.channel, ple.getPlayer().getName() + "Joined The Game");
	}

}
