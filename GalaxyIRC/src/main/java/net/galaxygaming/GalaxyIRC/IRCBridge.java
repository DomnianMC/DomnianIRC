package net.galaxygaming.GalaxyIRC;

import java.io.IOException;

import org.schwering.irc.lib.IRCConnection;
import org.schwering.irc.lib.ssl.SSLIRCConnection;

import com.willies952002.WSCore.LogHandler;
import com.willies952002.WSCore.SQLHandler;

public class IRCBridge {

	static SQLHandler sql;
	static LogHandler log;
	IRCConnection conn;

	public void init(GalaxyIRC main) throws IOException {
		sql = main.sql;
		log = main.log;
		if (main.getConfig().getBoolean("irc.ssl")) {
			conn = new SSLIRCConnection(main.getConfig().getString("irc.host"),
					main.getConfig().getInt("irc.port.min"), main.getConfig()
							.getInt("irc.port.max"), main.getConfig()
							.getString("irc.pass"), main.getConfig().getString(
							"irc.name.nick"), main.getConfig().getString(
							"irc.name.user"), main.getConfig().getString(
							"irc.name.real"));
		} else {
			conn = new IRCConnection(main.getConfig().getString("irc.host"),
					main.getConfig().getInt("irc.port.min"), main.getConfig()
							.getInt("irc.port.max"), main.getConfig()
							.getString("irc.pass"), main.getConfig().getString(
							"irc.name.nick"), main.getConfig().getString(
							"irc.name.user"), main.getConfig().getString(
							"irc.name.real"));
		}
		conn.connect();
		conn.addIRCEventListener(new IRCChatListener());
	}
	
	public void sendMessage(String name, String message) {
		conn.send("[Minecraft] " + name + ": " + message);
	}

}
