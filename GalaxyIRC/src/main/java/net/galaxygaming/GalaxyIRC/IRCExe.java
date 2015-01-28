package net.galaxygaming.GalaxyIRC;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class IRCExe implements CommandExecutor {

	IRCBridge irc;
	GalaxyIRC main;
	
	public IRCExe(GalaxyIRC main) {
		this.main = main;
		this.irc = main.irc;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ( args.length == 0 ) {
			usage(sender);
			return true;
		} else if ( args.length == 1 ) {
			if ( args[0].equalsIgnoreCase("list") ) {
				irc.conn.doList();
				sender.sendMessage("Server Channel List Dumped To Console");
			}
			if ( args[0].equalsIgnoreCase("connect") ) {
				if ( irc.conn.isConnected() ) {
					try {
						irc.conn.doQuit();
						irc.init(main);
						sender.sendMessage("IRC Bot Reconnected");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						irc.init(main);
						sender.sendMessage("IRC Bot Connected");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			if ( args[0].equalsIgnoreCase("quit") ) {
				if ( irc.conn.isConnected() ) {
					irc.conn.doQuit();
					sender.sendMessage("IRC Bot Disconnected");
				} else {
					sender.sendMessage("IRC Bot Already Disconnected");
				}
			}
			return true;
		} else if ( args.length == 2 ) {
			if ( args[0].equalsIgnoreCase("join") ) {
				sender.sendMessage("Sending Bot to Channel");
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
		sender.sendMessage(ChatColor.RED + "Incorrect Usage: /irc [join|list|quit|connect] [channel]");
		
	}

	private void usage(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "===================[ " + ChatColor.AQUA + "Galaxy" + ChatColor.WHITE + "IRC" + ChatColor.RED + " ]-=-=-=-=-=-=-=-=-=-");
		sender.sendMessage(ChatColor.GREEN + " join " + ChatColor.GOLD + "-" + ChatColor.YELLOW + " used to tell the bot to join a channel");
		sender.sendMessage(ChatColor.GREEN + " list " + ChatColor.GOLD + "-" + ChatColor.YELLOW + " used to list the server's channels");
		sender.sendMessage(ChatColor.GREEN + " connect " + ChatColor.GOLD + "-" + ChatColor.YELLOW + " used to (re)connect to the IRC server");
		sender.sendMessage(ChatColor.GREEN + " quit " + ChatColor.GOLD + "-" + ChatColor.YELLOW + " used to close the connection to the IRC server");
		sender.sendMessage(ChatColor.RED + "===================[ " + ChatColor.AQUA + "Galaxy" + ChatColor.WHITE + "IRC" + ChatColor.RED + " ]-=-=-=-=-=-=-=-=-=-");
	}

}
