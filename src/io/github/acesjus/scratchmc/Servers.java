package io.github.acesjus.scratchmc;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.acesjus.scratchmc.project.Project;
import io.github.acesjus.scratchmc.project.ProjectEvents;

public class Servers implements Listener, CommandExecutor {
	public static HashMap<Player, String> getServer = new HashMap<Player, String>();
	public static HashMap<Player, Project> getCurrentProject = new HashMap<Player, Project>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		
		if (sender instanceof Player) {
			if (cmd.getName().equals("hub")) {
				Player p = (Player) sender;
				ProjectEvents.quitMessage(p);
				Servers.getServer.put(p, "Lobby-1");
				Servers.getCurrentProject.remove(p);
				p.setGameMode(GameMode.ADVENTURE);
				p.teleport(new Location(Bukkit.getWorld("Lobby"), 63.5, 171, 64.5, 180, 0));
				p.getInventory().clear();
				p.setHealth(20);
				p.setFoodLevel(20);
				p.getActivePotionEffects().clear();
				new PotionEffect(PotionEffectType.NIGHT_VISION, 200000000, 0, false, false, false).apply(p);
				CustomScoreboard.updateScoreboard(p);
				Tablist.tabListUpdate(p);
			}
		}
		return true;
	}
	
}