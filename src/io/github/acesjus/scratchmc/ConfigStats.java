package io.github.acesjus.scratchmc;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.Plugin;

public class ConfigStats implements Listener {
	static Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void beforePlayerJoin (AsyncPlayerPreLoginEvent e) {
	    UUID u = e.getUniqueId();
	    
	    if (plugin.getConfig().getString("Users." + u) == null) {
	    	plugin.getConfig().set("Users." + u + ".Rank", "norank");
	    	plugin.getConfig().set("Users." + u + ".Worlds", "");
			plugin.saveConfig();
	    }
	    if ((plugin.getConfig().getString("Users." + u + ".Rank")) == null) {
	    	plugin.getConfig().set("Users." + u + ".Rank", "norank");
	    }
	    if ((plugin.getConfig().getString("Users." + u + ".Worlds")) == null) {
	    	plugin.getConfig().set("Users." + u + ".Worlds", "");
	    }
	    if ((plugin.getConfig().getString("Server.WorldCount")) == null) {
	    	plugin.getConfig().set("Server.WorldCount", 0);
			plugin.saveConfig();
	    }
	}
	
	public static String getValue (OfflinePlayer p, String s) {
		UUID u = p.getUniqueId();
		
		if (s == null) {
			return "";
		}
		
		if (s.equalsIgnoreCase("worlds")) {
		return plugin.getConfig().getString("Users." + u + ".Worlds");
		}
		
		return "";
	}
	
	public static int getValue (String s) {
		if (s == null) {
			return 0;
		}
		
		if (s.equalsIgnoreCase("worldCount")) {
			return plugin.getConfig().getInt("Server.WorldCount");
		}
		
		return 0;
	}
	
	public static void increment (String s) {
		if (s.equalsIgnoreCase("worldCount")) {
			plugin.getConfig().set("Server.WorldCount", getValue(s) + 1);
			plugin.saveConfig();
		}
	}
}
