package net.galaxygaming.GalaxyIRC;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.willies952002.WSCore.LogHandler;
import com.willies952002.WSCore.SQLHandler;

public class GalaxyIRC extends JavaPlugin implements Listener {

	LogHandler log;
	SQLHandler sql;
	IRCBridge irc = new IRCBridge();

	public void onEnable() {
		try {
			if (this.doDMCACheck()) {
				this.saveDefaultConfig();
				sql = new SQLHandler(this.getConfig().getString("db.host"),
						this.getConfig().getString("db.name"), this.getConfig()
								.getString("db.user"), this.getConfig()
								.getString("db.pass"));
				log = new LogHandler(this.getDataFolder().getAbsolutePath());
				irc.init(this);
				this.getServer().getPluginManager()
						.registerEvents(new ChatListener(irc), this);
				this.getServer().getPluginManager().registerEvents(this, this);
				this.getCommand("irc").setExecutor(new IRCExe(this));
			} else {
				System.out
						.println("You Must Accept The DMCA Agreement Before This Plugin Will Function.");
				this.getServer().getPluginManager().disablePlugin(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean doDMCACheck() throws Exception {
		this.exportResource("dmca.txt");
		File file = new File(this.getDataFolder() + File.separator + "dmca.txt");
		if ( file.createNewFile() ) {
			this.exportResource("dmca.txt");
			return false;
		} else {
			Properties dmca = new Properties();
			dmca.load(new FileReader(file.getAbsolutePath()));
			if ( Boolean.getBoolean(dmca.getProperty("dmca")) ) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void onDisable() {
		irc.conn.doQuit("GalaxyIRC Disabled");
		irc.conn.close();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent ple) {
		Bukkit.getServer()
				.getScheduler()
				.scheduleSyncDelayedTask(this,
						new LoginMessage(ple.getPlayer()), 20L);
	}

	public String exportResource(String resourceName) throws Exception {
		InputStream stream = null;
		OutputStream resStreamOut = null;
		String jarFolder;
		try {
			stream = GalaxyIRC.class.getResourceAsStream(resourceName);
			if (stream == null) {
				throw new Exception("Cannot get resource \"" + resourceName
						+ "\" from Jar file.");
			}
			int readBytes;
			byte[] buffer = new byte[4096];
			jarFolder = new File(GalaxyIRC.class.getProtectionDomain()
					.getCodeSource().getLocation().toURI().getPath())
					.getParentFile().getPath().replace('\\', '/');
			resStreamOut = new FileOutputStream(jarFolder + resourceName);
			while ((readBytes = stream.read(buffer)) > 0) {
				resStreamOut.write(buffer, 0, readBytes);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			stream.close();
			resStreamOut.close();
		}
		return jarFolder + resourceName;
	}

}
