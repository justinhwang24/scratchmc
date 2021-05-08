package io.github.acesjus.scratchmc;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class CustomScoreboard implements Listener {
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
		
		Score s3 = o.getScore(ChatColor.translateAlternateColorCodes('&', ChatColor.YELLOW + "&LYour Projects"));
		s3.setScore(i--);
		
		Score s4 = o.getScore("  ");
		s4.setScore(i--);
		
		try {
			for (String str : Files.getHouses(p)) {
				Score sc = o.getScore(str);
				sc.setScore(i--);
			}
		} catch (IllegalArgumentException | IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		Score s5 = o.getScore("  ");
		s5.setScore(i--);
		
		p.setScoreboard(scoreboardHashMap.get(p.getUniqueId()));
		}
}
