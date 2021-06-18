package io.github.acesjus.scratchmc.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomizeProject implements Listener {
	public static HashMap<Player, Inventory> menuHashMap = new HashMap<Player, Inventory>();
	public static HashMap<Player, Inventory> gameToolsHashMap = new HashMap<Player, Inventory>();
	
	@EventHandler
	public void onPlayerInteractAtEntity (PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		Entity en = e.getRightClicked();
		
		if (!(en instanceof Villager)) {
			return;
		}
		
		if (!en.getCustomName().equals(ChatColor.translateAlternateColorCodes('&',
				ChatColor.GOLD + "&LCustomize Project"))) {
			return;
		}
		customizeMenu(p);
	}
	
	public static void customizeMenu(Player p) {
		
		final Inventory mainMenu = Bukkit.createInventory(null, 54, "Customize Project");
		menuHashMap.put(p, mainMenu); 
		{
			ItemStack item = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LGame Tools"));
			List<String> lore = new ArrayList<>();
			lore.add("");
			lore.add(ChatColor.GRAY + "Tools for making a game.");
			lore.add("");
			lore.add(ChatColor.GREEN + "Left-Click to View");
			meta.setLore(lore);
			item.setItemMeta(meta);
			mainMenu.setItem(10, item);
			
			ItemStack item1 = new ItemStack(Material.BLAZE_POWDER);
			ItemMeta meta1 = item1.getItemMeta();
			meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LParticle Effects"));
			List<String> lore1 = new ArrayList<>();
			lore1.add("");
			lore1.add(ChatColor.GRAY + "Customizable event-based blocks.");
			lore1.add("");
			lore1.add(ChatColor.GREEN + "Left-Click to View");
			meta1.setLore(lore1);
			item1.setItemMeta(meta1);
			mainMenu.setItem(12, item1);
		}
		p.openInventory(menuHashMap.get(p));
	}
	

	public static void gameToolsMenu(Player p) {
		final Inventory gameTools = Bukkit.createInventory(null, 54, "Game Tools");
		gameToolsHashMap.put(p, gameTools); 
		{
			ItemStack arrow = new ItemStack(Material.ARROW);
			ItemMeta arrowMeta = arrow.getItemMeta();
			arrowMeta.setDisplayName(ChatColor.GREEN + "<- Back");
			arrow.setItemMeta(arrowMeta);
			gameTools.setItem(0, arrow);
			
			ItemStack item = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Parkour Pressure Plate");
			List<String> lore = new ArrayList<>();
			lore.add("");
			lore.add(ChatColor.GRAY + "Can start, stop, and record time.");
			lore.add("");
			lore.add(ChatColor.GREEN + "Left-Click to Use");
			meta.setLore(lore);
			item.setItemMeta(meta);
			gameTools.setItem(10, item);
			
			ItemStack item1 = new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
			ItemMeta meta1 = item1.getItemMeta();
			meta1.setDisplayName(ChatColor.GOLD + "Message Pressure Plate");
			List<String> lore1 = new ArrayList<>();
			lore1.add("");
			lore1.add(ChatColor.GRAY + "Sends a message to a player");
			lore1.add(ChatColor.GRAY + "upon stepping on a pressure plate.");
			lore1.add("");
			lore1.add(ChatColor.GREEN + "Left-Click to Use");
			meta1.setLore(lore1);
			item1.setItemMeta(meta1);
			gameTools.setItem(11, item1);
			
			ItemStack item2 = new ItemStack(Material.BIRCH_PRESSURE_PLATE);
			ItemMeta meta2 = item2.getItemMeta();
			meta2.setDisplayName(ChatColor.GOLD + "Announcement Pressure Plate");
			List<String> lore2 = new ArrayList<>();
			lore2.add("");
			lore2.add(ChatColor.GRAY + "Sends a message to everyone");
			lore2.add(ChatColor.GRAY + "that is online in your project");
			lore2.add(ChatColor.GRAY + "upon stepping on a pressure plate.");
			lore2.add(ChatColor.GRAY + "Has a cooldown of 10 seconds.");
			lore2.add("");
			lore2.add(ChatColor.GREEN + "Left-Click to Use");
			meta2.setLore(lore2);
			item2.setItemMeta(meta2);
			gameTools.setItem(12, item2);

			ItemStack item3 = new ItemStack(Material.OAK_PRESSURE_PLATE);
			ItemMeta meta3 = item3.getItemMeta();
			meta3.setDisplayName(ChatColor.GOLD + "Give Item");
			List<String> lore3 = new ArrayList<>();
			lore3.add("");
			lore3.add(ChatColor.GRAY + "Gives an item to a player");
			lore3.add(ChatColor.GRAY + "upon stepping on a pressure plate.");
			lore3.add("");
			lore3.add(ChatColor.GREEN + "Left-Click to Use");
			meta3.setLore(lore3);
			item3.setItemMeta(meta3);
			gameTools.setItem(13, item3);
		}
		p.openInventory(gameToolsHashMap.get(p));
	}
	
	@EventHandler
	public void onClick (InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if (e.getCurrentItem() == null) {
			return;
		}
		ItemStack item = e.getCurrentItem();
		
		if (e.getView().getTitle().equals("Customize Project")) {
			e.setCancelled(true);
			if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LGame Tools"))) {
				gameToolsMenu(p);
			}
		}
		else if (e.getView().getTitle().equals("Game Tools")) {
			e.setCancelled(true);
			if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Arrow")) {
				gameToolsMenu(p);
			}
			else {
				p.getInventory().addItem(giveModifiedItem(p, item));
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1);
				p.closeInventory();
			}
		}
	}
	
	public ItemStack giveModifiedItem(Player p, ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.setLore(item.getItemMeta().getLore().subList(0, item.getItemMeta().getLore().size() - 2));
		item.setItemMeta(meta);
		return item;
	}
}
