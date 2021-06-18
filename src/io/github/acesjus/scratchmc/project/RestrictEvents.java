package io.github.acesjus.scratchmc.project;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.vehicle.VehicleEnterEvent;

import io.github.acesjus.scratchmc.Servers;

public class RestrictEvents implements Listener {
	
	@EventHandler
	public void onDropEvent(PlayerDropItemEvent e) {
		if (!e.getItemDrop().getItemStack().getType().equals(Material.BLAZE_ROD)) {
			return;
		}
		if (!e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Block Editor")) {
			return;
		}
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onMoveEvent(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		int id = Servers.getCurrentProject.get(p).getId();
		
		if (Math.abs(e.getTo().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getTo().getBlockZ() - 1000 * id) >= 500) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You may not leave the plot!");
			e.setTo(Servers.getCurrentProject.get(p).getSpawn());
			p.setVelocity(p.getLocation().getDirection().multiply(-1));
		}
	}
	
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
		if (Math.abs(e.getTo().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getTo().getBlockZ() - 1000 * id) >= 500) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "I see what you are doing. You may not leave the plot!");
		}
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
		
		if (Math.abs(e.getVehicle().getLocation().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getVehicle().getLocation().getBlockZ() - 1000 * id) >= 500) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "I see what you are doing. You may not leave the plot!");
		}
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
		if (Math.abs(e.getBlockPlaced().getLocation().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getBlockPlaced().getLocation().getBlockZ() - 1000 * id) >= 500) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You may not build outside of your plot!");
		}
		if (e.getBlock().getType().equals(Material.TNT)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "TNT is not allowed!");
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
		if (Math.abs(e.getBlock().getLocation().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getBlock().getLocation().getBlockZ() - 1000 * id) >= 500) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You may not build outside of your plot!");
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
		int id = Servers.getCurrentProject.get(p).getId();
		if (Math.abs(e.getBlock().getLocation().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getBlock().getLocation().getBlockZ() - 1000 * id) >= 500) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You may not build outside of your plot!");
		}
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
		if (!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getItem().getType().equals(Material.LEGACY_MONSTER_EGG))) {
			return;
		}
		int id = Servers.getCurrentProject.get(p).getId();
		if (Math.abs(e.getClickedBlock().getLocation().getBlockX() - 1000 * id) >= 500 || Math.abs(e.getClickedBlock().getLocation().getBlockZ() - 1000 * id) >= 500) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You may not build outside of your plot!");
		}
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
        }
	}
}
