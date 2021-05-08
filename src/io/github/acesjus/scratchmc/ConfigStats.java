package io.github.acesjus.scratchmc;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class ConfigStats implements Listener {
	static Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin (PlayerJoinEvent e) {
			Player p = e.getPlayer();
		    UUID u = p.getUniqueId();
		    
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
		    for (Player s : Bukkit.getOnlinePlayers()) {
				Tablist.tabListUpdate(s);
			}
		    CustomScoreboard.updateScoreboard(p);
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
}
