package io.github.acesjus.scratchmc.project;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import io.github.acesjus.scratchmc.Files;
import io.github.acesjus.scratchmc.ItemStackClass;
import io.github.acesjus.scratchmc.Main;
import io.github.acesjus.scratchmc.Servers;
import io.github.acesjus.scratchmc.Tablist;

public class Project {
	private static OfflinePlayer owner;
	public static String name;
	private static int id;
	private static int slot;
	public static Location spawn;
	static Plugin plugin = Main.getPlugin(Main.class);
	
	public Project(UUID u, String name) throws IOException {
		Project.owner = Bukkit.getOfflinePlayer(u);
		Project.name = name;
		Project.id = generateId();
		Project.slot = generateSlot();
		Project.spawn = generateSpawn();
		Files.saveData(this);
	}
	
	public Project(UUID u, String name, int id, int slot, Location spawn) throws IOException {
		Project.owner = Bukkit.getOfflinePlayer(u);
		Project.name = name;
		Project.id = id;
		Project.slot = slot;
		Project.spawn = spawn;
	}
	
	public Project fetchProject(String s) throws IOException {
		return Files.fetchProject(this.getOwner(), s);
	}
	
	public void joinProject(Player p) {
		String serverId = this.getOwner().getName() + "-" + slot;
		p.sendMessage(ChatColor.GREEN + "Sending you to " + serverId + "...");
		Servers.getServer.put(p, serverId);
		Servers.getCurrentProject.put(p, this);
		p.teleport(spawn);
		p.getInventory().clear();
		ProjectEvents.joinMessage(p);
		Tablist.tabListUpdate(p);
		p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 10);
		if (owner.equals(p)) {
			p.setGameMode(GameMode.CREATIVE);
			p.getInventory().setItem(8, ItemStackClass.ItemStack(Material.NETHER_STAR, 1, ChatColor.GOLD + "Customize Project"));
		}
		else {
			p.setGameMode(GameMode.ADVENTURE);
		}
	}

	public int generateId() {
		int i = plugin.getConfig().getInt("Server.WorldCount");
		plugin.getConfig().set("Server.WorldCount", i + 1);
		plugin.saveConfig();
		return i + 1;
	}
	
	private int generateSlot() throws IOException {
		return Files.getHouses(this.getOwner()).size() + 1;
	}
	
	private Location generateSpawn() {
		return new Location(Bukkit.getWorld("Lobby"), id * 1000 + 0.5, 155, id * 1000 + 0.5);
	}

	public OfflinePlayer getOwner() {
		return owner;
	}

	public void setOwner(OfflinePlayer owner) {
		Project.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Project.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		Project.id = id;
	}

	public Location getSpawn() {
		return spawn;
	}

	public void setSpawn(Location spawn) {
		Project.spawn = spawn;
	}
	
	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		Project.slot = slot;
	}
}
