package io.github.acesjus.scratchmc.project;

import java.io.IOException;
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
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import io.github.acesjus.scratchmc.Chat;
import io.github.acesjus.scratchmc.CustomScoreboard;
import io.github.acesjus.scratchmc.Files;
import io.github.acesjus.scratchmc.Main;
import io.github.acesjus.scratchmc.anvilgui.AnvilGUI;

public class ProjectMenu implements Listener {
	static Plugin plugin = Main.getPlugin(Main.class);
	static String[] s = {""};
	static String[] clicked = {""};
	public static HashMap<Player, Inventory> menuHashMap = new HashMap<Player, Inventory>();
	public static HashMap<Player, Inventory> selectHashMap = new HashMap<Player, Inventory>();
	
	@EventHandler
	public void onPlayerInteractAtEntity (PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		Entity en = e.getRightClicked();
		
		if (!(en instanceof Villager)) {
			return;
		}
		if (!en.getCustomName().equals(ChatColor.translateAlternateColorCodes('&',
				ChatColor.AQUA + "&LCreate Project"))) {
			return;
		}
		openMenu(p);
	}
	
	@EventHandler
	public static void onClick (InventoryClickEvent e) throws IllegalArgumentException, IOException {
		Player p = (Player) e.getWhoClicked();
		if (!e.getView().getTitle().equals("Project Menu")) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		ItemStack item = e.getCurrentItem();
		e.setCancelled(true);
		
		if (item.getType().equals(Material.PLAYER_HEAD))
			createProject(p);
		else if (item.getType().equals(Material.NETHER_STAR))
			openSelect(p);
		else if (item.getType().equals(Material.PLAYER_HEAD))
			;
			// TODO
	}
	
	public static void createProject(Player p) throws IOException {
		if (Files.getHouses(p).size() == 3) {
			p.sendMessage(ChatColor.RED + "Error: You already have 3 existing projects!");
			p.closeInventory();
		}
		else {
			nameWorld(p);
		}
	}
	
	public static void createProject2(Player p) throws IOException {
		Project pr = new Project(p.getUniqueId(), s[0]);
		p.sendMessage(ChatColor.GRAY + "Creating Project...");
		ProjectSetUp.setUp(pr);
		p.sendMessage(ChatColor.AQUA + s[0] + ChatColor.GREEN + " successfully created.");
		pr.joinProject(p);
		CustomScoreboard.updateScoreboard(p);
	}
	
	public static String nameWorld(Player p) {
		Boolean[] b = new Boolean[1];
		b[0] = false;
		new AnvilGUI.Builder()
	    .onClose(player -> {                                        //called when the inventory is closing
	    	if (b[0]) {
	    		try {
	    			boolean containsSearchStr = Files.getHouses(p).stream().anyMatch(s[0]::equalsIgnoreCase);
					if (!containsSearchStr)
						createProject2(p);
					else
						p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + s[0] + ChatColor.RED + " already exists!");
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    })
	    .onComplete((player, text) -> {
	    	try {
				s[0] = Chat.filter(text).strip();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	b[0] = true;
	    	p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1000000000, 1);
	    	return AnvilGUI.Response.close();
	    })
	    .text("Insert name...")                       //sets the text the GUI should start with
	    .itemLeft(new ItemStack(Material.PAPER))               //use a custom item for the first slot
	    .onLeftInputClick(player -> {
	    	// nothing
	    })    //called when the left input slot is clicked
	    .title("Name your project")                                //set the title of the GUI (only works in 1.14+)
	    .plugin(plugin)                                   //set the plugin instance
	    .open(p);
		
		return "";
	}
	
	public static void openMenu(Player p) {
		final Inventory gameMenu = Bukkit.createInventory(null, 27, "Project Menu");
		menuHashMap.put(p, gameMenu); 
		{
			ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
			SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
			skullMeta.setOwner(p.getName());
			skull.setItemMeta(skullMeta);
			ItemMeta meta = skull.getItemMeta();
			List<String> lore = new ArrayList<>();
			lore.add(ChatColor.GRAY + "");
			lore.add(ChatColor.GRAY + "Click to create a new project.");
			meta.setLore(lore);
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.AQUA + "Create New"));
			skull.setItemMeta(meta);
			gameMenu.setItem(11, skull);
			
			ItemStack bed = new ItemStack(Material.NETHER_STAR, 1);
			ItemMeta meta1 = bed.getItemMeta();
			meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.YELLOW + "Select Project..."));
			List<String> lore1 = new ArrayList<>();
			lore1.add(ChatColor.GRAY + "");
			lore1.add(ChatColor.GRAY + "Browse your current projects.");
			meta1.setLore(lore1);
			bed.setItemMeta(meta1);
			gameMenu.setItem(13, bed);
			
			ItemStack door = new ItemStack(Material.DARK_OAK_DOOR, 1);
			ItemMeta meta2 = door.getItemMeta();
			meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GOLD + "Visit Projects"));
			List<String> lore2 = new ArrayList<>();
			lore2.add(ChatColor.GRAY + "");
			lore2.add(ChatColor.GRAY + "Visit someone else's project.");
			lore2.add(" ");
			lore2.add(ChatColor.RED + "Coming soon!");
			meta2.setLore(lore2);
			door.setItemMeta(meta2);
			gameMenu.setItem(15, door);
			}
		p.openInventory(menuHashMap.get(p));
	}
	
	public static void openSelect(Player p) throws IllegalArgumentException, IOException {
		switch (Files.getHouses(p).size()) {
		case 0:
			p.closeInventory();
			p.sendMessage(ChatColor.RED + "You do not have any current projects!");
			break;
		case 1:
			final Inventory gameMenu = Bukkit.createInventory(null, 27, "Select Project...");
			selectHashMap.put(p, gameMenu); 
			{
				int i = 13;
				for (String s : Files.getHouses(p)) {
					ItemStack item = new ItemStack(Material.GOLD_BLOCK);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GOLD + s));
					List<String> lore = new ArrayList<>();
					lore.add("");
					lore.add(ChatColor.GREEN + "Click to join!");
					meta.setLore(lore);
					item.setItemMeta(meta);
					gameMenu.setItem(i, item);
				}
			}
			p.openInventory(selectHashMap.get(p));
			break;
		case 2:
			final Inventory gameMenu2 = Bukkit.createInventory(null, 27, "Select Project...");
			selectHashMap.put(p, gameMenu2); 
			{
				int i = 12;
				for (String s : Files.getHouses(p)) {
					ItemStack item = new ItemStack(Material.GOLD_BLOCK);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GOLD + s));
					List<String> lore = new ArrayList<>();
					lore.add("");
					lore.add(ChatColor.GREEN + "Click to join!");
					meta.setLore(lore);
					item.setItemMeta(meta);
					gameMenu2.setItem(i, item);
					i += 2;
				}
			}
			p.openInventory(selectHashMap.get(p));
			break;
		case 3: 
			final Inventory gameMenu3 = Bukkit.createInventory(null, 27, "Select Project...");
			selectHashMap.put(p, gameMenu3); 
			{
				int i = 11;
				for (String s : Files.getHouses(p)) {
					ItemStack item = new ItemStack(Material.GOLD_BLOCK);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.GOLD + s));
					List<String> lore = new ArrayList<>();
					lore.add("");
					lore.add(ChatColor.GREEN + "Click to join!");
					meta.setLore(lore);
					item.setItemMeta(meta);
					gameMenu3.setItem(i, item);
					i += 2;
				}
			}
			p.openInventory(selectHashMap.get(p));
			break;
		default:
			break;
		}
	}
	
	@EventHandler
	public static void onClick2 (InventoryClickEvent e) throws IllegalArgumentException, IOException {
		Player p = (Player) e.getWhoClicked();
		if (!e.getView().getTitle().equals("Select Project...")) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		ItemStack item = e.getCurrentItem();
		e.setCancelled(true);
		
		if (item.getType().equals(Material.GOLD_BLOCK)) {
			Project pr = Files.fetchProject(p, item.getItemMeta().getDisplayName().substring(2));
			pr.joinProject(p);
		}
	}
}
