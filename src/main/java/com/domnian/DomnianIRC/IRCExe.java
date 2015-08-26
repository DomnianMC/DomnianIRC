package com.domnian.DomnianIRC;

import java.io.IOException;

import com.domnian.api.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
public class IRCExe implements CommandExecutor {

	IRCBridge irc = DomnianIRC.getIRCBridge();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ( args.length == 0 ) {
			usage(sender);
			return true;
		} else if ( args.length == 1 ) {
			if ( args[0].equalsIgnoreCase("list") ) {
				irc.conn.doList();
				Util.sendMsg(sender, "&bServer Channel List Dumped To Console");
			}
			if ( args[0].equalsIgnoreCase("connect") ) {
				if ( irc.conn.isConnected() ) {
					try {
						irc.conn.doQuit();
						irc.init();
						Util.sendMsg(sender, "&aIRC Bot Reconnected");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						irc.init();
						Util.sendMsg(sender, "&aIRC Bot Connected");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			if ( args[0].equalsIgnoreCase("quit") ) {
				if ( irc.conn.isConnected() ) {
					irc.conn.doQuit();
					Util.sendMsg(sender, "&aIRC Bot Disconnected");
				} else {
					Util.sendMsg(sender, "&cIRC Bot Already Disconnected");
				}
			}
			return true;
		} else if ( args.length == 2 ) {
			if ( args[0].equalsIgnoreCase("join") ) {
				Util.sendMsg(sender, "&bSending Bot to Channel");
				irc.conn.doPart(irc.channel);
				irc.conn.doJoin(args[1]);
				irc.channel = args[1];
				return true;
			} else {
				invalid(sender);
				return true;
			}
		} else {
			invalid(sender);
			return true;
		}
	}

	private void invalid(CommandSender sender) {
		Util.sendMsg(sender, "&cIncorrect Usage: /irc [join|list|quit|connect] [channel]");
	}

	private void usage(CommandSender sender) {
		Util.sendMsg(sender, "&c===================[ &aDomnian&fIRC &c]===================");
		Util.sendMsg(sender, " &ajoin &6- &eUsed to tell the bot to join a channel");
		Util.sendMsg(sender, " &alist &6- &eUsed to list the server's channels");
		Util.sendMsg(sender, " &aconnect &6- &eUsed to (re)connect to the IRC server");
		Util.sendMsg(sender, " &aquit &6- &eUsed to close the connection to the IRC server");
		Util.sendMsg(sender, "&c===================[ &aDomnian&fIRC &c]===================");
	}

}
