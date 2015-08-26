package com.domnian.DomnianIRC;

import java.io.IOException;

import com.domnian.DomnianIRC.listeners.IRCChatListener;
import com.domnian.api.Util;
import org.schwering.irc.lib.IRCConnection;
import org.schwering.irc.lib.ssl.SSLIRCConnection;

public class IRCBridge {

	DomnianIRC main = DomnianIRC.getInstance();
	public IRCConnection conn;
	public String channel;

	public void init() throws IOException {
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
		if ( main.getConfig().getBoolean("irc.auto.join") ) {
			System.out.println("AUTO JOIN ENABLED");
			String channel = main.getConfig().getString("irc.auto.channel");
			conn.doJoin(channel);
			System.out.println("SENDING BOT TO " + channel);
		}
		conn.addIRCEventListener(new IRCChatListener());
	}
	
	public void msgServerToIRC(String name, String message) {
		conn.doPrivmsg(channel, "[Minecraft] " + name + ": " + message);
	}

	public void msgIRCToServer(String msg) {
		String prefix = Util.color(main.getConfig().getString("irc.prefix"));
		main.getServer().broadcastMessage(prefix + msg);
	}

}
