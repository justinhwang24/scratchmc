package io.github.acesjus.scratchmc;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.acesjus.scratchmc.commands.CommandGameMode;
import io.github.acesjus.scratchmc.commands.CommandMessage;
import io.github.acesjus.scratchmc.commands.CommandStaff;
import io.github.acesjus.scratchmc.commands.CommandStaffA;
import io.github.acesjus.scratchmc.commands.CommandVillager;
import io.github.acesjus.scratchmc.commands.CommandVisit;
import io.github.acesjus.scratchmc.project.CustomizeProject;
import io.github.acesjus.scratchmc.project.ProjectEvents;
import io.github.acesjus.scratchmc.project.ProjectMenu;
import io.github.acesjus.scratchmc.project.RestrictEvents;
import io.github.acesjus.scratchmc.project.gametools.AnnouncementPlate;
import io.github.acesjus.scratchmc.project.gametools.ClearInventory;
import io.github.acesjus.scratchmc.project.gametools.GameModePlate;
import io.github.acesjus.scratchmc.project.gametools.GiveItem;
import io.github.acesjus.scratchmc.project.gametools.MessagePlate;
import io.github.acesjus.scratchmc.project.gametools.ParkourPlate;
import io.github.acesjus.scratchmc.project.gametools.RemoveItem;
import io.github.acesjus.scratchmc.project.gametools.Teleport;
import io.github.acesjus.scratchmc.project.gametools.TemporaryBlocks;
import io.github.acesjus.scratchmc.project.settings.ChangeSpawn;
import io.github.acesjus.scratchmc.project.settings.ToggleDamage;
import io.github.acesjus.scratchmc.project.settings.ToggleHunger;

public class Main extends JavaPlugin implements Listener, CommandExecutor {
public static Plugin instance;

@Override
	public void onEnable() {
		instance = this;
		
		PluginManager pm = getServer().getPluginManager();
		System.out.println("(!) ScratchMC Plugin Ready");
		pm.registerEvents(this, this);
		pm.registerEvents(new Chat(), this);
		pm.registerEvents(new ConfigStats(), this);
		pm.registerEvents(new ProjectMenu(), this);
		pm.registerEvents(new RestrictEvents(), this);
		pm.registerEvents(new CustomizeProject(), this);
		pm.registerEvents(new ProjectEvents(), this);
		pm.registerEvents(new ParkourPlate(), this);
		pm.registerEvents(new MessagePlate(), this);
		pm.registerEvents(new AnnouncementPlate(), this);
		pm.registerEvents(new GiveItem(), this);
		pm.registerEvents(new RemoveItem(), this);
		pm.registerEvents(new ClearInventory(), this);
		pm.registerEvents(new Teleport(), this);
		pm.registerEvents(new CommandVisit(), this);
		pm.registerEvents(new ChangeSpawn(), this);
		pm.registerEvents(new GameModePlate(), this);
		pm.registerEvents(new TemporaryBlocks(), this);
		getCommand("rank").setExecutor(new Ranks());
		getCommand("w").setExecutor(new CommandMessage());
		getCommand("hub").setExecutor(new Servers());
		getCommand("staff").setExecutor(new CommandStaff());
		getCommand("sa").setExecutor(new CommandStaffA());
		getCommand("visit").setExecutor(new CommandVisit());
		getCommand("villager").setExecutor(new CommandVillager());
		getCommand("gm").setExecutor(new CommandGameMode());
		
		loadConfig();

		LoadEntities.loadEntities();
	}

	@Override
	public void onDisable() {
		getLogger().info("ScratchMC has been shut down!");
		LoadEntities.removeEntities();
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	@EventHandler
	public void beforePlayerJoin (AsyncPlayerPreLoginEvent e) {
		String name = e.getName();
//		if (!(name.equals("AceSJus") || name.equals("ieightsomepie4"))) {
//			
//			e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatColor.RED + "You have been kicked by " + ChatColor.GOLD + "Minetest Bot"
//				+ ChatColor.YELLOW + "\nReason: " + ChatColor.WHITE + "Not allowed!");
//			return;
//		}
		Player p = Bukkit.getPlayer(name);
		Servers.getServer.put(p, "Lobby-1");
		Tablist.tabListUpdate(p);
	}
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) throws IOException {
		Player p = e.getPlayer();
		p.setDisplayName(Ranks.getRank(p, true) + p.getName());

		Servers.getServer.put(p, "Lobby-1");
		p.setGameMode(GameMode.ADVENTURE);
		p.teleport(new Location(Bukkit.getWorld("Lobby"), 63.5, 171, 64.5, 180, 0));
		p.sendTitle(ChatColor.translateAlternateColorCodes('&', ChatColor.AQUA + "&LMINETEST"), ChatColor.YELLOW + "Welcome!", 20, 50, 20);
		p.getInventory().clear();
		p.setHealth(20);
		p.setFoodLevel(20);
		p.getActivePotionEffects().clear();
		new PotionEffect(PotionEffectType.NIGHT_VISION, 200000000, 0, false, false, false).apply(p);
		e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.BLUE + "Join> " + 
				Ranks.getRank(p, true) + ChatColor.YELLOW + p.getName() + ChatColor.GRAY + " joined the server."));
		CustomScoreboard.updateScoreboard(p);
		Tablist.tabListUpdate(p);
	}
	
	@EventHandler
	public void onQuit (PlayerQuitEvent e) throws IOException {
		Player p = e.getPlayer();
		e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.BLUE + "Quit> " + 
				Ranks.getRank(p, true) + ChatColor.YELLOW + p.getName() + ChatColor.GRAY + " left the server."));
	}
	
	@EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) throws IOException {
		
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getEntity();
        if (Servers.getServer.get(p).equals("Lobby-1")) {
	        if (e.getCause().equals(DamageCause.FALL) || e.getCause().equals(DamageCause.FIRE) ||
	        	e.getCause().equals(DamageCause.SUFFOCATION) || e.getCause().equals(DamageCause.DROWNING) ||
	        	e.getCause().equals(DamageCause.LAVA) || e.getCause().equals(DamageCause.HOT_FLOOR) || 
	        	e.getCause().equals(DamageCause.FIRE_TICK) || e.getCause().equals(DamageCause.ENTITY_EXPLOSION) ||
	        	e.getCause().equals(DamageCause.ENTITY_ATTACK) || e.getCause().equals(DamageCause.ENTITY_SWEEP_ATTACK)) {
	            
	        	e.setCancelled(true);
	        }
        }
        else if (Servers.getServer.containsKey(p)) {
        	if (ToggleDamage.getOption(p)) {
        		return;
        	}
        }
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onHunger (FoodLevelChangeEvent e) throws IOException {
		if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getEntity();
        if (Servers.getServer.get(p).equals("Lobby-1")) {
    		e.setCancelled(true);
        }
        else if (Servers.getServer.containsKey(p)) {
        	if (ToggleHunger.getOption(p)) {
        		return;
        	}
        }
		e.setCancelled(true);
	}
}