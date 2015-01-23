package net.galaxygaming.GalaxyIRC;

import org.bukkit.plugin.java.JavaPlugin;

import com.willies952002.WSCore.LogHandler;
import com.willies952002.WSCore.SQLHandler;

public class GalaxyIRC extends JavaPlugin {

	LogHandler log;
	SQLHandler sql;
	IRCBridge irc = new IRCBridge();

	public void onEnable() {
		try {
			sql = new SQLHandler(this.getConfig().getString("db.host"), this
					.getConfig().getString("db.name"), this.getConfig()
					.getString("db.user"), this.getConfig().getString(
					"db.pass"));
			log = new LogHandler(this.getDataFolder().getAbsolutePath());
			irc.init(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
		
	}

}
