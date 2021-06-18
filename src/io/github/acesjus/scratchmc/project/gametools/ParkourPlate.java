package io.github.acesjus.scratchmc.project.gametools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.acesjus.scratchmc.Files;

public class ParkourPlate implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (b.getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
			if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Parkour Pressure Plate")) {
				Files.logToFile("log.txt", b.getLocation().getBlockX() + ", " 
						+ b.getLocation().getBlockY() + ", " 
						+ b.getLocation().getBlockZ());
				
			}
		}
	}
	
	@EventHandler
	public void onStep(PlayerInteractEvent e) {
		
	}
}
