package net.galaxygaming.GalaxyIRC;

import org.bukkit.entity.Player;

public class LoginMessage implements Runnable {

	Player p;
	
	public LoginMessage(Player player) {
		this.p = player;
	}

	@Override
	public void run() {
		p.sendMessage("§4[§bGalaxy§fIRC§4] This server is running §bGalaxy§fIRC §4by §2willies952002");
		p.sendMessage("§4[§bGalaxy§fIRC§4] This plugin is protected under §cDMCA.");
	}

}
