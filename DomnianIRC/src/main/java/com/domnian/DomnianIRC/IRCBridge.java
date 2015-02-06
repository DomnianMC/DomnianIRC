package com.domnian.DomnianIRC;

import java.io.IOException;

import org.schwering.irc.lib.IRCConnection;
import org.schwering.irc.lib.ssl.SSLIRCConnection;

import com.willies952002.WSCore.LogHandler;
import com.willies952002.WSCore.SQLHandler;

public class IRCBridge {

	DomnianIRC main;
	SQLHandler sql;
	LogHandler log;
	IRCConnection conn;
	public String channel;

	public void init(DomnianIRC main) throws IOException {
		this.main = main;
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
		if ( main.getConfig().getBoolean("irc.auto.join") ) {
			System.out.println("AUTO JOIN ENABLED");
			String channel = main.getConfig().getString("irc.auto.channel");
			conn.doJoin(channel);
			System.out.println("SENDING BOT TO " + channel);
		}
		conn.addIRCEventListener(new IRCChatListener(main));
	}
	
	public void msgServerToIRC(String name, String message) {
		conn.doPrivmsg(channel, "[Minecraft] " + name + ": " + message);
	}

	public void msgIRCToServer(String msg) {
		String prefix = main.getConfig().getString("irc.prefix").replace('&', '§');
		main.getServer().broadcastMessage(prefix + msg);
	}

}
