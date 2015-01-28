package net.galaxygaming.GalaxyIRC;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

public class IRCCommandSender implements ConsoleCommandSender {

	public IRCCommandSender() {
	}

	@Override
	public String getName() {
		return "GalaxyIRC";
	}

	@Override
	public Server getServer() {
		return Bukkit.getServer();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0) {
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, int arg1) {
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1,
			boolean arg2) {
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1,
			boolean arg2, int arg3) {
		return null;
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return null;
	}

	@Override
	public boolean hasPermission(String arg0) {
		return true;
	}

	@Override
	public boolean hasPermission(Permission arg0) {
		return true;
	}

	@Override
	public boolean isPermissionSet(String arg0) {
		return true;
	}

	@Override
	public boolean isPermissionSet(Permission arg0) {
		return true;
	}

	@Override
	public boolean isOp() {
		return true;
	}

	@Override
	public boolean beginConversation(Conversation arg0) {
		return false;
	}

	@Override
	public boolean isConversing() {
		return false;
	}

	@Override
	public void sendRawMessage(String arg0) {
	}

	@Override
	public void acceptConversationInput(String arg0) {
	}

	@Override
	public void setOp(boolean arg0) {
	}

	@Override
	public void abandonConversation(Conversation arg0) {
	}

	@Override
	public void abandonConversation(Conversation arg0,
			ConversationAbandonedEvent arg1) {
	}

	@Override
	public void recalculatePermissions() {
	}

	@Override
	public void removeAttachment(PermissionAttachment arg0) {
	}

	@Override
	public void sendMessage(String msg) {
	}

	@Override
	public void sendMessage(String[] arg0) {
	}
}
