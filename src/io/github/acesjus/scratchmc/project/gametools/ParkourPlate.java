package io.github.acesjus.scratchmc.project.gametools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.acesjus.scratchmc.Files;
import io.github.acesjus.scratchmc.Servers;
import io.github.acesjus.scratchmc.project.ProjectEvents;

public class ParkourPlate implements Listener {
	public static HashMap<Player, Inventory> menuHashMap = new HashMap<Player, Inventory>();
	public static ArrayList<Player> placedPlatesPlayer = new ArrayList<Player>();
	public static ArrayList<Block> placedPlatesBlock = new ArrayList<Block>();
	public static ArrayList<Boolean> placedPlatesStart = new ArrayList<Boolean>();
	public static HashMap<Player, Long> stopWatch = new HashMap<Player, Long>();
	public static HashMap<Player, Location> currentParkourStart = new HashMap<Player, Location>();
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (b.getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
			if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Parkour Pressure Plate")) {
				placedPlatesPlayer.add(p);
				placedPlatesBlock.add(b);
				placedPlatesStart.add(true);
				openMenu(p);
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) throws IOException {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		if (!b.getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
			return;
		}
		removePlate(b);
	}
	
	@EventHandler
	public void onBreakBelow(BlockBreakEvent e) throws IOException {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		if (!b.getRelative(BlockFace.UP).getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
			return;
		}
		removePlate(b.getRelative(BlockFace.UP));
	}
	
	public static void removePlate(Block b) throws IOException {
		int i = Files.getLineInFile("parkourPlate.txt", b.getLocation().getBlockX() + ", " 
				+ b.getLocation().getBlockY() + ", " 
				+ b.getLocation().getBlockZ(), 1);
		if (i >= 0) {
			Files.replaceLineInFile("parkourPlate.txt", i, "");
			Files.replaceLineInFile("parkourPlate.txt", i + 1, "");
			ArrayList<ArmorStand> asList = new ArrayList<ArmorStand>();
			ArrayList<Double> asDouble = new ArrayList<Double>();
			ArrayList<Double> asDoubleCopy = new ArrayList<Double>();
			for (ArmorStand as : b.getWorld().getEntitiesByClass(ArmorStand.class)) {
				asList.add(as);
				asDouble.add(b.getLocation().distance(as.getLocation()));
				asDoubleCopy.add(b.getLocation().distance(as.getLocation()));
			}
			Collections.sort(asDoubleCopy);
			int j = asDouble.indexOf(asDoubleCopy.get(0));
			asList.get(j).remove();
		}
	}
	
	public static void openMenu(Player p) {
		final Inventory gameMenu = Bukkit.createInventory(null, 27, "Parkour Plate Menu");
		menuHashMap.put(p, gameMenu); 
		{
			ItemStack item = new ItemStack(Material.LIME_WOOL, 1);
			ItemMeta meta1 = item.getItemMeta();
			meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LMark as Start"));
			List<String> lore1 = new ArrayList<>();
			lore1.add(ChatColor.GRAY + "");
			lore1.add(ChatColor.GRAY + "Sets this pressure plate");
			lore1.add(ChatColor.GRAY + "as start of parkour.");
			meta1.setLore(lore1);
			item.setItemMeta(meta1);
			gameMenu.setItem(11, item);
			
			ItemStack item2 = new ItemStack(Material.RED_WOOL, 1);
			ItemMeta meta2 = item2.getItemMeta();
			meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.RED + "&LMark as End"));
			List<String> lore2 = new ArrayList<>();
			lore2.add(ChatColor.GRAY + "");
			lore2.add(ChatColor.GRAY + "Sets this pressure plate");
			lore2.add(ChatColor.GRAY + "as end of parkour.");
			meta2.setLore(lore2);
			item2.setItemMeta(meta2);
			gameMenu.setItem(15, item2);
			}
		p.openInventory(menuHashMap.get(p));
	}
	
	@EventHandler
	public void onClick (InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if (e.getCurrentItem() == null) {
			return;
		}
		ItemStack item = e.getCurrentItem();
		
		if (e.getView().getTitle().equals("Parkour Plate Menu")) {
			e.setCancelled(true);
			if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LMark as Start"))) {
				int i = placedPlatesPlayer.lastIndexOf(p);
				placedPlatesStart.set(i, true);
				applyPlate(p, i);
				p.closeInventory();
			}
			else if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', ChatColor.RED + "&LMark as End"))) {
				int i = placedPlatesPlayer.lastIndexOf(p);
				placedPlatesStart.set(i, false);
				applyPlate(p, i);
				p.closeInventory();
			}
		}
	}
	
	public void applyPlate(Player p, int i) {
		Block b = placedPlatesBlock.get(i);
		Files.logToFile("parkourPlate.txt", b.getLocation().getBlockX() + ", " 
				+ b.getLocation().getBlockY() + ", " 
				+ b.getLocation().getBlockZ());
		Files.logToFile("parkourPlate.txt", "" + placedPlatesStart.get(i));
		
		Location location = b.getLocation().add(0.5, 0, 0.5);
		ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND); //Spawn the ArmorStand

        as.setGravity(false);
        as.setCanPickupItems(false); 
        as.setCustomName(ChatColor.translateAlternateColorCodes('&', ChatColor.YELLOW + "&LParkour " + ChatColor.GREEN + (placedPlatesStart.get(i) ? "Start" : "End"))); 
        as.setCustomNameVisible(true);
        as.setVisible(false);
        as.setMarker(true);
	}
	
	@EventHandler
	public void onStep(PlayerInteractEvent e) throws IOException {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		if (b == null) {
			return;
		}
		if (!b.getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
			return;
		}
		if (!e.getAction().equals(Action.PHYSICAL)) {
			return;
		}
		int i = Files.getLineInFile("parkourPlate.txt", b.getLocation().getBlockX() + ", " 
				+ b.getLocation().getBlockY() + ", " 
				+ b.getLocation().getBlockZ(), 1);
		if (!(i >= 0)) {
			return;
		}
		String bool = Files.getNthLineInFile("parkourPlate.txt", i + 1);
		
		ItemStack item = new ItemStack(Material.FEATHER, 1);
		ItemMeta meta1 = item.getItemMeta();
		meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LReset Parkour"));
		List<String> lore1 = new ArrayList<>();
		lore1.add(ChatColor.GRAY + "");
		lore1.add(ChatColor.GRAY + "Go back to start");
		lore1.add(ChatColor.GRAY + "upon right-click.");
		meta1.setLore(lore1);
		item.setItemMeta(meta1);
		
		if (Boolean.parseBoolean(bool.strip())) {
			// start
			if (!stopWatch.containsKey(p)) {
				p.sendMessage(ChatColor.GREEN + "Parkour Started!");
				p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, 10, 1);
			}
			p.setGameMode(GameMode.ADVENTURE);
			p.getInventory().setItem(0, item);
			stopWatch.put(p, System.currentTimeMillis());
			currentParkourStart.put(p, b.getLocation());
		}
		else {
			// finish
			if (stopWatch.containsKey(p)) {
				double time = (System.currentTimeMillis() - stopWatch.get(p)) / 1000.000;
				ProjectEvents.announceMessage(Servers.getCurrentProject.get(p), 
						ChatColor.translateAlternateColorCodes('&', 
						String.format(ChatColor.GREEN + p.getName() + " has finished the parkour in " 
						+ ChatColor.YELLOW + "&L%.3f" + ChatColor.GREEN + " seconds!"
						, time)));
				stopWatch.remove(p);
				currentParkourStart.remove(p);
				p.getInventory().remove(item);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
			}
			else {
				p.sendMessage(ChatColor.RED + "You have not started the parkour!");
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) throws IOException {
		Player p = e.getPlayer();
		ItemStack item = e.getItem();
		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		if (!stopWatch.containsKey(p)) {
			return;
		}
		if (item == null) {
			return;
		}
		ItemStack item1 = new ItemStack(Material.FEATHER, 1);
		ItemMeta meta1 = item1.getItemMeta();
		meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LReset Parkour"));
		List<String> lore1 = new ArrayList<>();
		lore1.add(ChatColor.GRAY + "");
		lore1.add(ChatColor.GRAY + "Go back to start");
		lore1.add(ChatColor.GRAY + "upon right-click.");
		meta1.setLore(lore1);
		item1.setItemMeta(meta1);
		if (!item.equals(item1)) {
			return;
		}
		p.teleport(currentParkourStart.get(p));
		stopWatch.put(p, System.currentTimeMillis());
		p.sendMessage(ChatColor.GREEN + "Reset time to 0:00!");
		p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, 10, 1);
	}
}
