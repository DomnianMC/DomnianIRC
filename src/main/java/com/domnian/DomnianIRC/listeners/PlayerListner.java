package com.domnian.DomnianIRC.listeners;

import com.domnian.DomnianIRC.DomnianIRC;
import com.domnian.DomnianIRC.IRCBridge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
public class PlayerListner implements Listener {

    IRCBridge irc = DomnianIRC.getIRCBridge();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerJoinEvent pje) {
        irc.conn.doPrivmsg(irc.channel, pje.getPlayer().getName() + " Joined The Game");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent pqe) {
        irc.conn.doPrivmsg(irc.channel, pqe.getPlayer().getName() + " Left The Game");
    }

}