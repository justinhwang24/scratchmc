package io.github.acesjus.scratchmc.project;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import io.github.acesjus.scratchmc.ItemStackClass;

public class ProjectSetUp {
	
	public static void setUp(Project pr) {
		spawnNpc(pr);
	}
	
	public static void spawnNpc(Project pr) {
		Location loc = new Location(Bukkit.getWorld("Lobby"), 
				pr.getSpawn().getBlockX(), pr.getSpawn().getBlockY(), pr.getSpawn().getBlockZ());
		
		Entity en = loc.getWorld().spawnEntity(loc.add(0.5, 0, -9.5), EntityType.VILLAGER);
	    Villager v = (Villager) en;
	    v.setSilent(true);
	    v.setAI(false);
	    v.setInvulnerable(true);
	    v.setCollidable(false);
	    v.getEquipment().setItemInMainHand(ItemStackClass.ItemStack(Material.REDSTONE_WIRE));
	    v.setCustomName(ChatColor.translateAlternateColorCodes('&', ChatColor.GOLD + "&LCustomize Project"));
	    v.setCustomNameVisible(true);
	    v.setRemoveWhenFarAway(false);
	    
	    loc = new Location(Bukkit.getWorld("Lobby"), 
				pr.getSpawn().getBlockX(), pr.getSpawn().getBlockY(), pr.getSpawn().getBlockZ());
	    loc.add(0, 0, -10);
	    
	    for (int x = loc.getBlockX() - 1; x <= loc.getBlockX() + 1; x++) {
	    	for (int z = loc.getBlockZ() - 1; z <= loc.getBlockZ() + 1; z++) {
		    	loc.getWorld().getBlockAt(x, 54, z).setType(Material.SMOOTH_STONE_SLAB);
		    }
	    }
	    loc.getWorld().getBlockAt(loc.add(0, -1, 0)).setType(Material.GOLD_BLOCK);
	}
	
}
