package io.github.acesjus.scratchmc;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class LoadEntities {
	public static ArrayList<Integer> ids = new ArrayList<Integer>();
	
	public static void loadEntities() {
	World world = Bukkit.getWorld("Lobby");
		
    Entity en = world.spawnEntity(new Location(world, 0.5, 55, -18.5, 0, 0), EntityType.VILLAGER);
    Villager v = (Villager) en;
    v.setSilent(true);
    v.setAI(false);
    v.setInvulnerable(true);
    v.setCollidable(false);
    v.getEquipment().setItemInMainHand(ItemStackClass.ItemStack(Material.REDSTONE_WIRE));
    v.setCustomName(ChatColor.translateAlternateColorCodes('&', ChatColor.AQUA + "&LCreate Project"));
    v.setCustomNameVisible(true);
    v.setRemoveWhenFarAway(false);
    ids.add(v.getEntityId());
    
	}
	
	public static void removeEntities() {
		World world = Bukkit.getWorld("Lobby");
			
	    for (Entity en : world.getEntities()) {
	    	if (ids.contains(en.getEntityId())) {
	    		en.remove();
	    	}
	    }
	}
}