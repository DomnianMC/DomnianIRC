package com.domnian.DomnianIRC;

import org.bukkit.ChatColor;
import org.schwering.irc.lib.IRCEventListener;
import org.schwering.irc.lib.IRCModeParser;
import org.schwering.irc.lib.IRCUser;

public class IRCChatListener implements IRCEventListener {

	DomnianIRC main;

	public IRCChatListener(DomnianIRC main) {
		this.main = main;
	}	

	@Override
	public void onError(String msg) {
		System.out.println("§4IRC ERROR >> " + msg);
	}

	@Override public void onError(int num, String msg) {
		System.out.println("§4IRC ERROR >> " + num + " : " + msg);
	}

	@Override
	public void onJoin(String chan, IRCUser user) {
		String prefix = main.getConfig().getString("irc.prefix").replace('&', '§');
		main.getServer().broadcastMessage(prefix + ChatColor.YELLOW + user.getNick() + " Joined IRC");
	}

	@Override
	public void onPart(String chan, IRCUser user, String msg) {
		String prefix = main.getConfig().getString("irc.prefix").replace('&', '§');
		main.getServer().broadcastMessage(prefix + ChatColor.YELLOW + user.getNick() + " Left IRC");
	}

	@Override
	public void onPrivmsg(String target, IRCUser user, String msg) {
		if ( msg.startsWith(".") || msg.startsWith("!") ) {
			try {
				IRCCommandHandler.execute(main, user, msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ( msg.startsWith("ACTION") ) {
			main.irc.conn.doPrivmsg(main.irc.channel, "ACTION not supported");
		} else if ( msg.startsWith("PING") ) {
			main.irc.conn.doPrivmsg(main.irc.channel, "PING not supported");
		} else if ( msg.startsWith("VERSION") ) {
			main.irc.conn.doPrivmsg(main.irc.channel, "VERSION Reply -> DomnianIRC DEV : Java " + System.getProperty("java.version"));
			return;
		} else {
			if ( !msg.contains(".") ) {
				main.irc.msgIRCToServer(user.getNick().replaceAll("[" + "A" + "]", "") + ": " + msg);
			}
		}
	}

	@Override
	public void onReply(int num, String value, String msg) {
		System.out.println("IRC REPLY >> " + num + " : " + value + " : " + msg);
	}
	
	@Override public void onQuit(IRCUser user, String msg) {
		String prefix = main.getConfig().getString("irc.prefix").replace('&', '§');
		main.getServer().broadcastMessage(prefix + ChatColor.YELLOW + user.getNick() + " Joined IRC");
	}

	@Override public void onTopic(String chan, IRCUser user, String topic) {}
	@Override public void unknown(String prefix, String command, String middle, String trailing) {}
	@Override public void onKick(String chan, IRCUser user, String passiveNick, String msg) {}
	@Override public void onMode(String chan, IRCUser user, IRCModeParser modeParser) {}
	@Override public void onMode(IRCUser user, String passiveNick, String mode) {}
	@Override public void onNick(IRCUser user, String newNick) {}
	@Override public void onInvite(String chan, IRCUser user, String passiveNick) {}
	@Override public void onRegistered() {}
	@Override public void onDisconnected() {}
	@Override public void onPing(String ping) {}
	@Override public void onNotice(String target, IRCUser user, String msg) {}

}