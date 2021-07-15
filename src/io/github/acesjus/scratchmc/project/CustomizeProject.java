package io.github.acesjus.scratchmc.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.acesjus.scratchmc.Servers;
import io.github.acesjus.scratchmc.project.settings.Rename;
import io.github.acesjus.scratchmc.project.settings.ToggleDamage;
import io.github.acesjus.scratchmc.project.settings.ToggleHunger;

public class CustomizeProject implements Listener {
	public static HashMap<Player, Inventory> menuHashMap = new HashMap<Player, Inventory>();
	public static HashMap<Player, Inventory> gameToolsHashMap = new HashMap<Player, Inventory>();
	public static HashMap<Player, Inventory> settingsHashMap = new HashMap<Player, Inventory>();
	
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
		
		final Inventory mainMenu = Bukkit.createInventory(null, 27, "Customize Project");
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
			mainMenu.setItem(11, item);
			
			ItemStack item1 = new ItemStack(Material.BLAZE_POWDER);
			ItemMeta meta1 = item1.getItemMeta();
			meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LEvents"));
			List<String> lore1 = new ArrayList<>();
			lore1.add("");
			lore1.add(ChatColor.GRAY + "Event-based tools.");
			lore1.add("");
			lore1.add(ChatColor.RED + "Coming soon!");
			meta1.setLore(lore1);
			item1.setItemMeta(meta1);
			mainMenu.setItem(13, item1);

			ItemStack item2 = new ItemStack(Material.COMPARATOR);
			ItemMeta meta2 = item2.getItemMeta();
			meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LSettings"));
			List<String> lore2 = new ArrayList<>();
			lore2.add("");
			lore2.add(ChatColor.GRAY + "Change settings for your project.");
			lore2.add("");
			lore2.add(ChatColor.GREEN + "Left-Click to View");
			meta2.setLore(lore2);
			item2.setItemMeta(meta2);
			mainMenu.setItem(15, item2);
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
			lore3.add(ChatColor.GRAY + "Gives item(s) to a player");
			lore3.add(ChatColor.GRAY + "upon stepping on a pressure plate.");
			lore3.add("");
			lore3.add(ChatColor.GREEN + "Left-Click to Use");
			meta3.setLore(lore3);
			item3.setItemMeta(meta3);
			gameTools.setItem(13, item3);

			ItemStack item5 = new ItemStack(Material.CRIMSON_PRESSURE_PLATE);
			ItemMeta meta5 = item5.getItemMeta();
			meta5.setDisplayName(ChatColor.GOLD + "Remove Item");
			List<String> lore5 = new ArrayList<>();
			lore5.add("");
			lore5.add(ChatColor.GRAY + "Removes specific item(s)");
			lore5.add(ChatColor.GRAY + "upon stepping on this block.");
			lore5.add("");
			lore5.add(ChatColor.GREEN + "Left-Click to Use");
			meta5.setLore(lore5);
			item5.setItemMeta(meta5);
			gameTools.setItem(14, item5);
			
			ItemStack item6 = new ItemStack(Material.ACACIA_PRESSURE_PLATE);
			ItemMeta meta6 = item6.getItemMeta();
			meta6.setDisplayName(ChatColor.GOLD + "Clear Inventory");
			List<String> lore6 = new ArrayList<>();
			lore6.add("");
			lore6.add(ChatColor.GRAY + "Removes the contents of player");
			lore6.add(ChatColor.GRAY + "upon stepping on this block.");
			lore6.add("");
			lore6.add(ChatColor.GREEN + "Left-Click to Use");
			meta6.setLore(lore6);
			item6.setItemMeta(meta6);
			gameTools.setItem(15, item6);

			ItemStack item4 = new ItemStack(Material.END_PORTAL_FRAME);
			ItemMeta meta4 = item4.getItemMeta();
			meta4.setDisplayName(ChatColor.GOLD + "Teleport");
			List<String> lore4 = new ArrayList<>();
			lore4.add("");
			lore4.add(ChatColor.GRAY + "Teleports to a specific location");
			lore4.add(ChatColor.GRAY + "upon stepping on this block.");
			lore4.add("");
			lore4.add(ChatColor.GREEN + "Left-Click to Use");
			meta4.setLore(lore4);
			item4.setItemMeta(meta4);
			gameTools.setItem(16, item4);
			
			ItemStack item7 = new ItemStack(Material.DARK_OAK_PRESSURE_PLATE);
			ItemMeta meta7 = item7.getItemMeta();
			meta7.setDisplayName(ChatColor.GOLD + "Change Gamemode");
			List<String> lore7 = new ArrayList<>();
			lore7.add("");
			lore7.add(ChatColor.GRAY + "Changes a player's gamemode");
			lore7.add(ChatColor.GRAY + "upon stepping on this block.");
			lore7.add("");
			lore7.add(ChatColor.GREEN + "Left-Click to Use");
			meta7.setLore(lore7);
			item7.setItemMeta(meta7);
			gameTools.setItem(19, item7);

			ItemStack item8 = new ItemStack(Material.WARPED_PRESSURE_PLATE);
			ItemMeta meta8 = item8.getItemMeta();
			meta8.setDisplayName(ChatColor.GOLD + "Temporary Blocks");
			List<String> lore8 = new ArrayList<>();
			lore8.add("");
			lore8.add(ChatColor.GRAY + "Store temporary blocks and");
			lore8.add(ChatColor.GRAY + "clear them when necessary.");
			lore8.add("");
			lore8.add(ChatColor.GREEN + "Left-Click to Use");
			meta8.setLore(lore8);
			item8.setItemMeta(meta8);
			gameTools.setItem(20, item8);
		}
		p.openInventory(gameToolsHashMap.get(p));
	}
	

	public static void settingsMenu(Player p) {
		
		final Inventory settings = Bukkit.createInventory(null, 54, "Settings");
		settingsHashMap.put(p, settings); 
		{
			ItemStack arrow = new ItemStack(Material.ARROW);
			ItemMeta arrowMeta = arrow.getItemMeta();
			arrowMeta.setDisplayName(ChatColor.GREEN + "<- Back");
			arrow.setItemMeta(arrowMeta);
			settings.setItem(0, arrow);
			
			ItemStack item = new ItemStack(Material.BEACON);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Change Spawn Point");
			List<String> lore = new ArrayList<>();
			lore.add("");
			lore.add(ChatColor.GRAY + "Change default spawn position.");
			lore.add("");
			lore.add(ChatColor.GREEN + "Left-Click to Use");
			meta.setLore(lore);
			item.setItemMeta(meta);
			settings.setItem(10, item);

			ItemStack item1 = new ItemStack(Material.IRON_SWORD);
			ItemMeta meta1 = item1.getItemMeta();
			meta1.setDisplayName(ChatColor.GREEN + "Toggle Damage");
			List<String> lore1 = new ArrayList<>();
			lore1.add("");
			lore1.add(ChatColor.GRAY + "Changes whether players take");
			lore1.add(ChatColor.GRAY + "damage in this project.");
			lore1.add("");
			lore1.add(ChatColor.GREEN + "Left-Click to Use");
			meta1.setLore(lore1);
			item1.setItemMeta(meta1);
			settings.setItem(11, item1);

			ItemStack item2 = new ItemStack(Material.COOKED_BEEF);
			ItemMeta meta2 = item2.getItemMeta();
			meta2.setDisplayName(ChatColor.GREEN + "Toggle Hunger");
			List<String> lore2 = new ArrayList<>();
			lore2.add("");
			lore2.add(ChatColor.GRAY + "Changes whether players lose");
			lore2.add(ChatColor.GRAY + "hunger bars in this project.");
			lore2.add("");
			lore2.add(ChatColor.GREEN + "Left-Click to Use");
			meta2.setLore(lore2);
			item2.setItemMeta(meta2);
			settings.setItem(12, item2);
			
			ItemStack item3 = new ItemStack(Material.NAME_TAG);
			ItemMeta meta3 = item3.getItemMeta();
			meta3.setDisplayName(ChatColor.GREEN + "Rename Project");
			List<String> lore3 = new ArrayList<>();
			lore3.add("");
			lore3.add(ChatColor.GRAY + "Change the name of your project.");
			lore3.add("");
			lore3.add(ChatColor.GREEN + "Left-Click to Use");
			meta3.setLore(lore3);
			item3.setItemMeta(meta3);
			settings.setItem(13, item3);
		}
		p.openInventory(settingsHashMap.get(p));
	}

	
	@EventHandler
	public void onClick (InventoryClickEvent e) throws IOException {
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
			else if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LEvents"))) {
				// TODO
			}
			else if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "&LSettings"))) {
				settingsMenu(p);
			}
		}
		else if (e.getView().getTitle().equals("Game Tools")) {
			e.setCancelled(true);
			if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "<- Back")) {
				customizeMenu(p);
			}
			else {
				p.getInventory().addItem(giveModifiedItem(p, item));
				p.closeInventory();
			}
		}
		else if (e.getView().getTitle().equals("Settings")) {
			e.setCancelled(true);
			if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "<- Back")) {
				customizeMenu(p);
			}
			else if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Toggle Damage")) {
				if (Servers.getCurrentProject.get(p).getOwner().equals(p)) {
					ToggleDamage.apply(p);
				}
				else {
					p.sendMessage(ChatColor.RED + "You are not the owner of this project!");
				}
				p.closeInventory();
			}
			else if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Toggle Hunger")) {
				if (Servers.getCurrentProject.get(p).getOwner().equals(p)) {
					ToggleHunger.apply(p);
				}
				else {
					p.sendMessage(ChatColor.RED + "You are not the owner of this project!");
				}
				p.closeInventory();
			}
			else if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Rename Project")) {
				if (Servers.getCurrentProject.get(p).getOwner().equals(p)) {
					Rename.nameWorld(p);
				}
				else {
					p.sendMessage(ChatColor.RED + "You are not the owner of this project!");
					p.closeInventory();
				}
			}
			else {
				p.getInventory().addItem(giveModifiedItem(p, item));
				p.closeInventory();
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (!Servers.getServer.containsKey(p)) {
			return;
		}
		if (Servers.getServer.get(p).equals("Lobby-1")) {
			return;
		}
		if (e.getHand() == null) {
			return;
		}
		if (!e.getHand().equals(EquipmentSlot.HAND)) {
			return;
		}
		if (e.getItem() == null) {
			return;
		}
		ItemStack item = e.getItem();
		if (!item.getType().equals(Material.NETHER_STAR)) {
			return;
		}
		if (!item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Customize Project")) {
			return;
		}
		p.closeInventory();
		customizeMenu(p);
	}
	
	public ItemStack giveModifiedItem(Player p, ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.setLore(item.getItemMeta().getLore().subList(0, item.getItemMeta().getLore().size() - 2));
		item.setItemMeta(meta);
		return item;
	}
}
