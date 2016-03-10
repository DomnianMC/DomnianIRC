package com.domnian.api;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Util {

    public static String color (String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void printException(Exception e) {
        printException(e.getMessage(), e);
    }

    public static void printException(String msg, Throwable e) {
        Logger.getGlobal().severe(msg);
        String ex = ExceptionUtils.getFullStackTrace(e);
        for (String exl : ex.split("\n")) {
            Logger.getGlobal().severe(exl);
        }
    }

    public static void printException(Throwable dbg, int lines) {
        if (dbg == null) {
            return;
        }
        Logger.getGlobal().severe(dbg.getMessage());
        final StackTraceElement current = new Throwable().getStackTrace()[1];
        Logger.getGlobal().severe("c: "+ current.getClassName()+":" + current.getLineNumber());

        final StackTraceElement[] stack = dbg.getStackTrace();
        for (int i = 0; i < lines && i < stack.length; i++) {
            final StackTraceElement cur = stack[i];
            Logger.getGlobal().severe("  " + cur);
        }
    }

    public static void sendMsg(CommandSender s, String m) {
        s.sendMessage(Util.color(m));
    }

    public static void sendMsg(Player p, String m) {
        p.sendMessage(Util.color(m));
    }

    public static void printArgs(String[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println("Argument " + i + ": " + a[i]);
        }
    }

    public static boolean isNumeric(String s) {
        return s.matches("-?\\d+(\\.\\d+)?");
    }

}
