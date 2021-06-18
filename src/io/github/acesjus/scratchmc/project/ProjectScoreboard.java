package io.github.acesjus.scratchmc.project;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import io.github.acesjus.scratchmc.Main;
import io.github.acesjus.scratchmc.Servers;

public class ProjectScoreboard {
	public static HashMap<UUID, Scoreboard> scoreboardHashMap = new HashMap<UUID, Scoreboard>();
	private static Plugin plugin = Main.getPlugin(Main.class);

	public static void updateScoreboard (Player p) {
		ScoreboardManager m = Bukkit.getScoreboardManager();
		Scoreboard b = m.getNewScoreboard();
		
		@SuppressWarnings("deprecation")
		Objective o = b.registerNewObjective("Gold", "");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.LIGHT_PURPLE + "      &LMINETEST     "));
		scoreboardHashMap.put(p.getUniqueId(), b);
		
		int i = 12;
				
		Score s = o.getScore(ChatColor.translateAlternateColorCodes('&', ChatColor.AQUA + "&LServer"));
		s.setScore(i--);
		
		Score s1 = o.getScore(Servers.getServer.get(p));
		s1.setScore(i--);
		
		Score s2 = o.getScore(" ");
		s2.setScore(i--);
		
		Score s3 = o.getScore(ChatColor.translateAlternateColorCodes('&', ChatColor.YELLOW + "&LProject Name"));
		s3.setScore(i--);
		
		Score s4 = o.getScore(Servers.getCurrentProject.get(p).getName());
		s4.setScore(i--);
		
		Score s5 = o.getScore("  ");
		s5.setScore(i--);
		
		Score s6 = o.getScore(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LPlayers"));
		s6.setScore(i--);
		
		String serverName = Servers.getServer.get(p);
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (Servers.getServer.get(player).equals(serverName)) {
				Score s7 = o.getScore(player.getName());
				s7.setScore(i--);
			}
		}
		
		Score s8 = o.getScore("   ");
		s8.setScore(i--);
		
		p.setScoreboard(scoreboardHashMap.get(p.getUniqueId()));
	}
}
