package io.github.acesjus.scratchmc.project;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;

import io.github.acesjus.scratchmc.Files;
import io.github.acesjus.scratchmc.Servers;

public class RestrictEvents implements Listener {
	
	@EventHandler
	public void onDropEvent(PlayerDropItemEvent e) {
		if (!e.getItemDrop().getItemStack().getType().equals(Material.NETHER_STAR)) {
			return;
		}
		if (!e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Customize Project")) {
			return;
		}
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null) {
			return;
		}
		ItemStack item = e.getCurrentItem();
		if (!item.getType().equals(Material.NETHER_STAR)) {
			return;
		}
		if (!item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Customize Project")) {
			return;
		}
		e.setCancelled(true);
	}
	
//	@EventHandler
//	public void onMove(PlayerMoveEvent e) {
//		Player p = e.getPlayer();
//		if (!Servers.getServer.containsKey(p) || !Servers.getCurrentProject.containsKey(p)) 
//			return;
//		if (Servers.getServer.get(p).equals("Lobby-1"))
//			return;
//		int id = Servers.getCurrentProject.get(p).getId();
//		if (!(e.getTo().getBlockX() - (id - 1) * 1000 - 500 > 0 
//				&& e.getTo().getBlockX() - (id - 1) * 1000 - 500 < 1000)) {
//			
//		}
//	}
	
//	@EventHandler
//	public void onMoveEvent(PlayerMoveEvent e) {
//		Player p = e.getPlayer();
//		if (!Servers.getServer.containsKey(p)) {
//			return;
//		}
//		if (Servers.getServer.get(p).equals("Lobby-1")) {
//			return;
//		}
//		int id = Servers.getCurrentProject.get(p).getId();
//		
//		if (Math.abs(e.getTo().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getTo().getBlockZ() - 1000 * id) >= 500) {
//			e.setCancelled(true);
//			p.sendMessage(ChatColor.RED + "You may not leave the plot!");
//			e.setTo(Servers.getCurrentProject.get(p).getSpawn());
//			p.setVelocity(p.getLocation().getDirection().multiply(-1));
//		}
//	}
//	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		if (!(e.getCause().equals(TeleportCause.ENDER_PEARL) || e.getCause().equals(TeleportCause.CHORUS_FRUIT))) {
			return;
		}
		int id = Servers.getCurrentProject.get(p).getId();
//		if (Math.abs(e.getTo().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getTo().getBlockZ() - 1000 * id) >= 500) {
//			e.setCancelled(true);
//			p.sendMessage(ChatColor.RED + "I see what you are doing. You may not leave the plot!");
//		}
	}
	
	@EventHandler
	public void onVehicle(VehicleEnterEvent e) {
		if (!(e.getEntered() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntered();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		int id = Servers.getCurrentProject.get(p).getId();
		
//		if (Math.abs(e.getVehicle().getLocation().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getVehicle().getLocation().getBlockZ() - 1000 * id) >= 500) {
//			e.setCancelled(true);
//			p.sendMessage(ChatColor.RED + "I see what you are doing. You may not leave the plot!");
//		}
	}
	
	@EventHandler
	public void onPortal(PlayerPortalEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		int id = Servers.getCurrentProject.get(p).getId();
		Block b = e.getBlockPlaced();
//		if (Math.abs(b.getLocation().getBlockX() - 1000 * id) >= 500 || Math.abs(b.getLocation().getBlockZ() - 1000 * id) >= 500) {
//			e.setCancelled(true);
//			p.sendMessage(ChatColor.RED + "You may not build outside of your plot!");
//		}
		if (b.getType().equals(Material.TNT)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "TNT is not allowed!");
		}
		if ((b.getLocation().getBlockX() % 1000 == 999 
				|| b.getLocation().getBlockX() % 1000 == 0
				|| b.getLocation().getBlockX() % 1000 == 1)
				&& (b.getLocation().getBlockZ() % 1000 == 989 
						|| b.getLocation().getBlockZ() % 1000 == 990
						|| b.getLocation().getBlockZ() % 1000 == 991)
				&& (b.getLocation().getBlockY() == 54 || b.getLocation().getBlockY() == 55)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You may not cover the platform!");
			return;
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		int id = Servers.getCurrentProject.get(p).getId();
//		if (Math.abs(e.getBlock().getLocation().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getBlock().getLocation().getBlockZ() - 1000 * id) >= 500) {
//			e.setCancelled(true);
//			p.sendMessage(ChatColor.RED + "You may not build outside of your plot!");
//			return;
//		}
		Block b = e.getBlock();
		if ((b.getLocation().getBlockX() % 1000 == 999 
				|| b.getLocation().getBlockX() % 1000 == 0
				|| b.getLocation().getBlockX() % 1000 == 1)
				&& (b.getLocation().getBlockZ() % 1000 == 989 
						|| b.getLocation().getBlockZ() % 1000 == 990
						|| b.getLocation().getBlockZ() % 1000 == 991)
				&& b.getLocation().getBlockY() == 54) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You may not destroy this platform!");
			return;
		}
	}
	
	@EventHandler
	public void onEntityPlace(EntityPlaceEvent e) {
		Player p = e.getPlayer();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		if (e.getEntity().getType().equals(EntityType.MINECART_TNT)) {
			e.setCancelled(true);
		}
//		int id = Servers.getCurrentProject.get(p).getId();
//		if (Math.abs(e.getBlock().getLocation().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getBlock().getLocation().getBlockZ() - 1000 * id) >= 500) {
//			e.setCancelled(true);
//			p.sendMessage(ChatColor.RED + "You may not build outside of your plot!");
//		}
	}
	
	@EventHandler
	public void onCreatureSpawn(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		if (e.getItem() == null || e.getAction() == null) {
			return;
		}
		if (!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getItem().getType().equals(Material.LEGACY_MONSTER_EGG))) {
			return;
		}
//		int id = Servers.getCurrentProject.get(p).getId();
//		if (Math.abs(e.getClickedBlock().getLocation().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getClickedBlock().getLocation().getBlockZ() - 1000 * id) >= 500) {
//			e.setCancelled(true);
//			p.sendMessage(ChatColor.RED + "You may not build outside of your plot!");
//		}
	}
	@EventHandler
	public void onEntityExplodeEvent(EntityExplodeEvent e) {
		e.blockList().clear();
        e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlow(BlockExplodeEvent e) {
		e.blockList().clear();
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		if (e.getDamage() >= p.getHealth()) {
        	e.setCancelled(true);
        	for (Player s : Bukkit.getOnlinePlayers()) {
        		if (Servers.getCurrentProject.get(s).equals(Servers.getCurrentProject.get(p))) {
        			s.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.RED + " died!");
        		}
        	}
        	p.teleport(Servers.getCurrentProject.get(p).getSpawn());
    		p.setHealth(20);
    		p.setFoodLevel(20);
        }
	}
	
	@EventHandler
	public void onDamageVillager(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Villager && e.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		if (!e.getEntity().getName().equals(ChatColor.translateAlternateColorCodes('&', ChatColor.GOLD + "&LCustomize Project"))) {
			return;
		}
		e.setDamage(0);
    	e.setCancelled(true);
      }
	
	@EventHandler
	public void onPiston(BlockPistonExtendEvent e) throws IOException {
		List<Block> blocks = e.getBlocks();
		for (Block block : blocks) {
			if ((block.getLocation().getBlockX() % 1000 == 999 
					|| block.getLocation().getBlockX() % 1000 == 0
					|| block.getLocation().getBlockX() % 1000 == 1)
					&& (block.getLocation().getBlockZ() % 1000 == 989 
							|| block.getLocation().getBlockZ() % 1000 == 990
							|| block.getLocation().getBlockZ() % 1000 == 991)
					&& block.getLocation().getBlockY() == 54) {
				e.setCancelled(true);
				return;
			}
			String[] fileNames = {"parkourPlate.txt", "messagePlate.txt", "announcementPlate.txt",
					"giveItem.txt", "teleport.txt", "removeItem.txt", "clearInventory.txt"};
			for (int i = 0; i < fileNames.length; i++) {
				if (Files.getLineInFile(fileNames[i], block.getLocation().getBlockX() + ", " 
						+ block.getLocation().getBlockY() + ", " 
						+ block.getLocation().getBlockZ(), 1) >= 0) {
					e.setCancelled(true);
					return;
				}
			}
		}
    }
	
	@EventHandler
	public void onSpawn(CreatureSpawnEvent e) {
		if (e.getEntityType().equals(EntityType.WITHER))
			e.setCancelled(true);
    }
}
