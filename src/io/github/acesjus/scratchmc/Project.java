package io.github.acesjus.scratchmc;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

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
		p.sendMessage(ChatColor.GREEN + "Sending you to " + this.getOwner().getName() + "-" + slot + "...");
		p.teleport(spawn);
	}

	public int generateId() {
		return ConfigStats.getValue("worldCount") + 1;
	}
	
	public int generateSlot() throws IOException {
		return Files.getHouses(this.getOwner()).size() + 1;
	}
	
	public Location generateSpawn() {
		return new Location(Bukkit.getWorld("Lobby"), id * 1000, 56, id * 1000);
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
