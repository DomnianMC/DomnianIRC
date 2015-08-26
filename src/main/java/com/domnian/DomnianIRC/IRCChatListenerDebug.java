package com.domnian.DomnianIRC;

import org.schwering.irc.lib.IRCEventListener;
import org.schwering.irc.lib.IRCModeParser;
import org.schwering.irc.lib.IRCUser;

public class IRCChatListenerDebug implements IRCEventListener {

	DomnianIRC main;

	public IRCChatListenerDebug(DomnianIRC main) {
		this.main = main;
	}

	@Override
	public void onRegistered() {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Registered");
	}

	@Override
	public void onDisconnected() {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Disconnect");
	}

	@Override
	public void onError(String msg) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: §4ERROR");
		main.getServer().broadcastMessage("  - Message: " + msg);
	}

	@Override
	public void onError(int num, String msg) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: §4ERROR");
		main.getServer().broadcastMessage("  - Number: " + num);
		main.getServer().broadcastMessage("  - Message: " + msg);
	}

	@Override
	public void onInvite(String chan, IRCUser user, String passiveNick) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Invite");
		main.getServer().broadcastMessage("  - Channel: " + chan);
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
		main.getServer().broadcastMessage("  - Passive Nick: " + passiveNick);
	}

	@Override
	public void onJoin(String chan, IRCUser user) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Join");
		main.getServer().broadcastMessage("  - Channel: " + chan);
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
	}

	@Override
	public void onKick(String chan, IRCUser user, String passiveNick, String msg) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Kick");
		main.getServer().broadcastMessage("  - Channel: " + chan);
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
		main.getServer().broadcastMessage("  - Passive Nick: " + passiveNick);
		main.getServer().broadcastMessage("  - Message: " + msg);
	}

	@Override
	public void onMode(String chan, IRCUser user, IRCModeParser modeParser) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Mode A");
		main.getServer().broadcastMessage("  - Channel: " + chan);
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
		main.getServer().broadcastMessage("  - IRC Mode Parser:");
		main.getServer().broadcastMessage("    - Mode Count: " + modeParser.getCount());
		main.getServer().broadcastMessage("    - Line: " + modeParser.getLine());
	}

	@Override
	public void onMode(IRCUser user, String passiveNick, String mode) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Mode B");
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
		main.getServer().broadcastMessage("  - Passive Nick: " + passiveNick); 
		main.getServer().broadcastMessage("  - Mode: " + mode);
	}

	@Override
	public void onNick(IRCUser user, String newNick) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Nick");
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
		main.getServer().broadcastMessage("  - New Nick: " + newNick); 
	}

	@Override
	public void onNotice(String target, IRCUser user, String msg) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Notice");
		main.getServer().broadcastMessage("  - Target: " + target);
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
		main.getServer().broadcastMessage("  - Message: " + msg); 
	}

	@Override
	public void onPart(String chan, IRCUser user, String msg) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Part");
		main.getServer().broadcastMessage("  - Channel: " + chan);
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
		main.getServer().broadcastMessage("  - Message: " + msg); 
	}

	@Override
	public void onPing(String ping) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Ping");
		main.getServer().broadcastMessage("  - Ping: " + ping);
	}

	@Override
	public void onPrivmsg(String target, IRCUser user, String msg) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Private Message");
		main.getServer().broadcastMessage("  - Target: " + target);
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
		main.getServer().broadcastMessage("  - Message: " + msg); 
	}

	@Override
	public void onQuit(IRCUser user, String msg) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Quit");
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
		main.getServer().broadcastMessage("  - Message: " + msg); 
	}

	@Override
	public void onReply(int num, String value, String msg) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Reply");
		main.getServer().broadcastMessage("  - Num: " + num);
		main.getServer().broadcastMessage("  - Value: " + value);
		main.getServer().broadcastMessage("  - Message: " + msg); 
	}

	@Override
	public void onTopic(String chan, IRCUser user, String topic) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Topic");
		main.getServer().broadcastMessage("  - Channel: " + chan);
		main.getServer().broadcastMessage("  - User:");
		main.getServer().broadcastMessage("    - Nick: " + user.getNick());
		main.getServer().broadcastMessage("    - Username: " + user.getUsername());
		main.getServer().broadcastMessage("    - Server Name: " + user.getServername());
		main.getServer().broadcastMessage("    - Host: " + user.getHost());
		main.getServer().broadcastMessage("  - Topic: " + topic);
		
	}

	@Override
	public void unknown(String prefix, String command, String middle,
			String trailing) {
		main.getServer().broadcastMessage("IRC Event Fired:");
		main.getServer().broadcastMessage("  - Type: Unknown");
		main.getServer().broadcastMessage("  - Prefix: " + prefix);
		main.getServer().broadcastMessage("  - Command: " + command);
		main.getServer().broadcastMessage("  - Middle: " + middle);
		main.getServer().broadcastMessage("  - Trailing: " + trailing);
	}

}