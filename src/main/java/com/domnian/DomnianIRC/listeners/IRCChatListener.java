package com.domnian.DomnianIRC.listeners;

import com.domnian.DomnianIRC.DomnianIRC;
import com.domnian.DomnianIRC.IRCBridge;
import com.domnian.api.Util;
import org.bukkit.ChatColor;
import org.schwering.irc.lib.IRCEventListener;
import org.schwering.irc.lib.IRCModeParser;
import org.schwering.irc.lib.IRCUser;

public class IRCChatListener implements IRCEventListener {

	IRCBridge irc = DomnianIRC.getIRCBridge();
	String channel = irc.channel;
	String prefix = DomnianIRC.getInstance().getConfig().getString("irc.prefix");

	@Override
	public void onError(String msg) {
		System.out.println("IRC ERROR >> " + msg);
	}

	@Override public void onError(int num, String msg) {
		System.out.println("IRC ERROR >> " + num + " : " + msg);
	}

	@Override
	public void onJoin(String chan, IRCUser user) {
		DomnianIRC.getInstance().getServer().broadcastMessage(Util.color(prefix + "&e" + user.getNick() + " Joined IRC"));
	}

	@Override
	public void onPart(String chan, IRCUser user, String msg) {

		DomnianIRC.getInstance().getServer().broadcastMessage(Util.color(prefix + "&e" + user.getNick() + " Left IRC"));
	}

	@Override
	public void onPrivmsg(String target, IRCUser user, String msg) {
		if ( msg.startsWith(".") || msg.startsWith("!") ) {
			try {
				//TODO Implement Commands
				irc.conn.doPrivmsg(user.getNick(), "Commands Not Yet Implemented");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ( msg.startsWith("ACTION") ) {
			irc.conn.doPrivmsg(channel, "ACTION not supported");
		} else if ( msg.startsWith("PING") ) {
			irc.conn.doPrivmsg(channel, "PONG!!!");
		} else if ( msg.startsWith("VERSION") ) {
			irc.conn.doPrivmsg(channel, "VERSION Reply -> DomnianIRC 0.5 : Java " + System.getProperty("java.version"));
			return;
		} else {
			if ( !(msg.startsWith(".") || msg.startsWith("!")) ) {
				irc.msgIRCToServer(user.getNick() + ": " + msg);
			}
		}
	}

	@Override
	public void onReply(int num, String value, String msg) {
		System.out.println("IRC REPLY >> " + num + " : " + value + " : " + msg);
	}
	
	@Override public void onQuit(IRCUser user, String msg) {
		String prefix = DomnianIRC.getInstance().getConfig().getString("irc.prefix").replace('&', '§');
		DomnianIRC.getInstance().getServer().broadcastMessage(prefix + ChatColor.YELLOW + user.getNick() + " Joined IRC");
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