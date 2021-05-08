package io.github.acesjus.scratchmc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class Chat implements Listener {
	static Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void onPlayerChatEvent (AsyncPlayerChatEvent e) throws IOException {
		Player p = e.getPlayer();
		String message = e.getMessage();
		e.setCancelled(true);
		for (Player s : Bukkit.getOnlinePlayers()) {
			
			s.sendMessage(ChatColor.translateAlternateColorCodes('&', 
					Ranks.getRank(p, true) + ChatColor.YELLOW + p.getName() +
					ChatColor.WHITE + " " + filter(message)));
		}
		System.out.println(ChatColor.translateAlternateColorCodes('&', 
				Ranks.getRank(p, true) + ChatColor.YELLOW + p.getName() +
				ChatColor.WHITE + " " + message));
		Files.logToFile("log.txt", p.getName() + ": " + message);
	}
	
	public static String filter(String s) throws IOException {
		String fileName = "filter.txt";
		File saveTo = new File(plugin.getDataFolder(), fileName);
        Path path = saveTo.toPath();
        List<String> lines = java.nio.file.Files.readAllLines(path, StandardCharsets.UTF_8);
		String newString = "";
		boolean b;
    	for (String spl : s.split(" ")) {
    		b = false;
            for (String str : lines) {
	    		if (spl.contains(str.toLowerCase())) {
	    			b = true;
	    		}
	    	}
            if (b) {
            	for (int i = 0; i < spl.length(); i++)
            		newString += "*";
            	newString += " ";
            }
            else {
            	newString += (spl + " ");
            }
    	}
        return newString;
	}
}
