package com.domnian.DomnianIRC;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.schwering.irc.lib.IRCUser;

public class IRCCommandHandler {

	public static void execute(DomnianIRC main, IRCUser user, String cmd) throws Exception {
		//TODO Map Commands
		if ( cmd.startsWith(".mute") ) {
			cmd.replaceFirst(".", "/");
			Bukkit.getServer().broadcastMessage(cmd + " (mute)");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
			logCommand(main, user.getNick(), cmd);
		} else if ( cmd.startsWith(".kick") ) {
			cmd.replaceFirst(".", "/");
			Bukkit.getServer().broadcastMessage(cmd + " (kick)");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
			logCommand(main, user.getNick(), cmd);
		} else if ( cmd.startsWith(".ban") ) {
			cmd.replaceFirst(".", "/");
			Bukkit.getServer().broadcastMessage(cmd + " (ban)");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
			logCommand(main, user.getNick(), cmd);
		} else if ( cmd.startsWith(".ignore") ) {
			cmd.replaceFirst(".", "/");
			Bukkit.getServer().broadcastMessage(cmd + " (ignore)");
		} else {
			cmd.replaceFirst(".", "/");
			Bukkit.getServer().broadcastMessage(cmd + " (unknown)");
			//main.irc.conn.doPrivmsg(user.getNick(), "Unknown Command");
		}
	}
	
	private static void logCommand(DomnianIRC main, String nick, String cmd) throws SQLException {
		main.sql.getStatement().execute("INSERT INTO `GalaxyBot`.`CommandLog` (`nick`, `command`, `timestamp`) VALUES ('" + nick + "', '" + cmd + "', CURRENT_TIMESTAMP);");
	}

}
