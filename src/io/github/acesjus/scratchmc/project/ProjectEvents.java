package io.github.acesjus.scratchmc.project;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.acesjus.scratchmc.Servers;

public class ProjectEvents implements Listener {
	
	public static void joinMessage(Player p) {
		for (Player s : Bukkit.getOnlinePlayers()) {
			if (Servers.getServer.get(s).equals(Servers.getServer.get(p))) {
				s.sendMessage(ChatColor.DARK_GRAY + "Join> " + ChatColor.GRAY + p.getName());
				ProjectScoreboard.updateScoreboard(s);
			}
		}
	}
	
	public static void quitMessage(Player p) {
		if (!Servers.getServer.get(p).equals("Lobby-1")) {
			for (Player s : Bukkit.getOnlinePlayers()) {
				if (Servers.getServer.get(s).equals(Servers.getServer.get(p))) {
					s.sendMessage(ChatColor.DARK_GRAY + "Quit> " + ChatColor.GRAY + p.getName());
					ProjectScoreboard.updateScoreboard(s);
				}
			}
		}
	}

	public static void announceMessage(Project pr, String s) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (Servers.getServer.containsKey(p) && !Servers.getServer.get(p).equals("Lobby-1")) {
				if (Servers.getCurrentProject.containsKey(p)) {
					if (Servers.getCurrentProject.get(p).getId() == pr.getId()) {
						p.sendMessage(s);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		Servers.getServer.remove(p);
		quitMessage(p);
	}
}
