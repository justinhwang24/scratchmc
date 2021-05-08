package io.github.acesjus.scratchmc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Ranks implements CommandExecutor, Listener {
	private static Plugin plugin = Main.getPlugin(Main.class);

	public enum Rank {
		TRAINEE ("Trainee"),
		MOD ("Mod"),
		SRMOD ("Sr.Mod"), 
		ADMIN ("Admin"),
		OP ("OP"), 
		ETERNAL ("Eternal"),
		YOUTUBE ("YouTube"),
		NORANK ("No Rank");
		
		private final String name;
	    private Rank(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("rank")) {
			if (Ranks.getPerm(p) <= 3) {
				Ranks.noPerm(p, "ADMIN");
				return true;
			}
			if (args.length != 2) {
				p.sendMessage(ChatColor.RED + "Usage> " + ChatColor.YELLOW + "/rank <username> <rank>");
				return true;
			}
			if (args.length == 2) {
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				if (!plugin.getConfig().contains("Users." + target.getUniqueId().toString())) {
					p.sendMessage(ChatColor.RED + "Error> " + ChatColor.YELLOW + "The username does not exist!");
					return true;
				}
				
				else {
					switch (args[1].toLowerCase()) {
					case "trainee":
						if (!getRank(target.getPlayer()).equals(Rank.TRAINEE)) {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
									+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
									+ args[1] + ChatColor.GRAY + "!");
							plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
							plugin.saveConfig();
							if (target.isOnline()) {
								Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
										+ ChatColor.GRAY + "Your rank has been updated to "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								CustomScoreboard.updateScoreboard((Player) target);
								for (Player s : Bukkit.getOnlinePlayers()) {
									Tablist.tabListUpdate(s);
								}
							}
							return true;
						}
						else {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
									+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
									+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
							return true;
						}
					
					case "mod":
						if (!getRank(target.getPlayer()).equals(Rank.MOD)) {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
									+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
									+ args[1] + ChatColor.GRAY + "!");
							plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
							plugin.saveConfig();
							if (target.isOnline()) {
								Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
										+ ChatColor.GRAY + "Your rank has been updated to "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								CustomScoreboard.updateScoreboard((Player) target);
								for (Player s : Bukkit.getOnlinePlayers()) {
									Tablist.tabListUpdate(s);
								}
							}
							return true;
						}
						else {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
									+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
									+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
							return true;
						}
					
					case ("srmod"):
						case ("sr.mod"): 
						if (!getRank(target.getPlayer()).equals(Rank.SRMOD)) {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
									+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
									+ args[1] + ChatColor.GRAY + "!");
							plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
							plugin.saveConfig();
							if (target.isOnline()) {
								Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
										+ ChatColor.GRAY + "Your rank has been updated to "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								CustomScoreboard.updateScoreboard((Player) target);
								for (Player s : Bukkit.getOnlinePlayers()) {
									Tablist.tabListUpdate(s);
								}
							}
							return true;
						}
						else {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
									+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
									+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
							return true;
						}
					
					case "admin":
						if (!getRank(target.getPlayer()).equals(Rank.ADMIN)) {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
									+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
									+ args[1] + ChatColor.GRAY + "!");
							plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
							plugin.saveConfig();
							if (target.isOnline()) {
								Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
										+ ChatColor.GRAY + "Your rank has been updated to "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								CustomScoreboard.updateScoreboard((Player) target);
								for (Player s : Bukkit.getOnlinePlayers()) {
									Tablist.tabListUpdate(s);
								}
							}
							return true;
						}
						else {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
									+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
									+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
							return true;
						}
					
					case "op":
						if (!getRank(target.getPlayer()).equals(Rank.OP)) {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
									+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
									+ args[1] + ChatColor.GRAY + "!");
							plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
							plugin.saveConfig();
							if (target.isOnline()) {
								Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
										+ ChatColor.GRAY + "Your rank has been updated to "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								CustomScoreboard.updateScoreboard((Player) target);
								for (Player s : Bukkit.getOnlinePlayers()) {
									Tablist.tabListUpdate(s);
								}
							}
							return true;
						}
						else {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
									+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
									+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
							return true;
						}
					
					case "eternal":
						if (!getRank(target.getPlayer()).equals(Rank.ETERNAL)) {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
									+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
									+ args[1] + ChatColor.GRAY + "!");
							plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
							plugin.saveConfig();
							if (target.isOnline()) {
								Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
										+ ChatColor.GRAY + "Your rank has been updated to "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								CustomScoreboard.updateScoreboard((Player) target);
								for (Player s : Bukkit.getOnlinePlayers()) {
									Tablist.tabListUpdate(s);
								}
							}
							return true;
						}
						else {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
									+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
									+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
							return true;
						}
					
					case "youtube":
						if (!getRank(target.getPlayer()).equals(Rank.YOUTUBE)) {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
									+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
									+ args[1] + ChatColor.GRAY + "!");
							plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
							plugin.saveConfig();
							if (target.isOnline()) {
								Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
										+ ChatColor.GRAY + "Your rank has been updated to "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								CustomScoreboard.updateScoreboard((Player) target);
								for (Player s : Bukkit.getOnlinePlayers()) {
									Tablist.tabListUpdate(s);
								}
							}
							return true;
						}
						else {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
									+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
									+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
							return true;
						}
					
					case "norank":
					case "reset":
						if (!getRank(target.getPlayer()).equals(Rank.NORANK)) {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
									+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
									+ args[1] + ChatColor.GRAY + "!");
							plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", "norank");
							plugin.saveConfig();
							if (target.isOnline()) {
								Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
										+ ChatColor.GRAY + "Your rank has been updated to "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								CustomScoreboard.updateScoreboard((Player) target);
								for (Player s : Bukkit.getOnlinePlayers()) {
									Tablist.tabListUpdate(s);
								}
							}
							return true;
						}
						else {
							p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
									+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
									+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
							return true;
						}
					default:
						p.sendMessage(ChatColor.RED + "Error> " +
					ChatColor.GOLD + args[1] + ChatColor.YELLOW + " is not a valid rank!");
				}
				}
			}
	}
}
		if (sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender p = (ConsoleCommandSender) sender;
			if (cmd.getName().equalsIgnoreCase("rank")) {
				if (args.length != 2) {
					p.sendMessage(ChatColor.RED + "Usage> " + ChatColor.YELLOW + "/rank <username> <rank>");
					return true;
				}
				if (args.length == 2) {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					if (!plugin.getConfig().contains("Users." + target.getUniqueId().toString())) {
						p.sendMessage(ChatColor.RED + "Error> " + ChatColor.YELLOW + "The username does not exist!");
						return true;
					}
					
					else {
						switch (args[1].toLowerCase()) {
						case "trainee":
							if (!getRank(target.getPlayer()).equals(Rank.TRAINEE)) {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
										+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
										+ args[1] + ChatColor.GRAY + "!");
								plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
								plugin.saveConfig();
								if (target.isOnline()) {
									Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
											+ ChatColor.GRAY + "Your rank has been updated to "
											+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
									CustomScoreboard.updateScoreboard((Player) target);
									for (Player s : Bukkit.getOnlinePlayers()) {
										Tablist.tabListUpdate(s);
									}
								}
								return true;
							}
							else {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
										+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								return true;
							}
						
						case "mod":
							if (!getRank(target.getPlayer()).equals(Rank.MOD)) {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
										+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
										+ args[1] + ChatColor.GRAY + "!");
								plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
								plugin.saveConfig();
								if (target.isOnline()) {
									Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
											+ ChatColor.GRAY + "Your rank has been updated to "
											+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
									CustomScoreboard.updateScoreboard((Player) target);
									for (Player s : Bukkit.getOnlinePlayers()) {
										Tablist.tabListUpdate(s);
									}
								}
								return true;
							}
							else {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
										+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								return true;
							}
						
						case ("srmod"):
							case ("sr.mod"): 
							if (!getRank(target.getPlayer()).equals(Rank.SRMOD)) {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
										+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
										+ args[1] + ChatColor.GRAY + "!");
								plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
								plugin.saveConfig();
								if (target.isOnline()) {
									Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
											+ ChatColor.GRAY + "Your rank has been updated to "
											+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
									CustomScoreboard.updateScoreboard((Player) target);
									for (Player s : Bukkit.getOnlinePlayers()) {
										Tablist.tabListUpdate(s);
									}
								}
								return true;
							}
							else {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
										+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								return true;
							}
						
						case "admin":
							if (!getRank(target.getPlayer()).equals(Rank.ADMIN)) {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
										+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
										+ args[1] + ChatColor.GRAY + "!");
								plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
								plugin.saveConfig();
								if (target.isOnline()) {
									Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
											+ ChatColor.GRAY + "Your rank has been updated to "
											+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
									CustomScoreboard.updateScoreboard((Player) target);
									for (Player s : Bukkit.getOnlinePlayers()) {
										Tablist.tabListUpdate(s);
									}
								}
								return true;
							}
							else {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
										+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								return true;
							}
						
						case "op":
							if (!getRank(target.getPlayer()).equals(Rank.OP)) {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
										+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
										+ args[1] + ChatColor.GRAY + "!");
								plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
								plugin.saveConfig();
								if (target.isOnline()) {
									Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
											+ ChatColor.GRAY + "Your rank has been updated to "
											+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
									CustomScoreboard.updateScoreboard((Player) target);
									for (Player s : Bukkit.getOnlinePlayers()) {
										Tablist.tabListUpdate(s);
									}
								}
								return true;
							}
							else {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
										+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								return true;
							}
						
						case "eternal":
							if (!getRank(target.getPlayer()).equals(Rank.ETERNAL)) {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
										+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
										+ args[1] + ChatColor.GRAY + "!");
								plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
								plugin.saveConfig();
								if (target.isOnline()) {
									Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
											+ ChatColor.GRAY + "Your rank has been updated to "
											+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
									CustomScoreboard.updateScoreboard((Player) target);
									for (Player s : Bukkit.getOnlinePlayers()) {
										Tablist.tabListUpdate(s);
									}
								}
								return true;
							}
							else {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
										+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								return true;
							}
						
						case "youtube":
							if (!getRank(target.getPlayer()).equals(Rank.YOUTUBE)) {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
										+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
										+ args[1] + ChatColor.GRAY + "!");
								plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", args[1].toLowerCase());
								plugin.saveConfig();
								if (target.isOnline()) {
									Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
											+ ChatColor.GRAY + "Your rank has been updated to "
											+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
									CustomScoreboard.updateScoreboard((Player) target);
									for (Player s : Bukkit.getOnlinePlayers()) {
										Tablist.tabListUpdate(s);
									}
								}
								return true;
							}
							else {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
										+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								return true;
							}
						
						case "norank":
						case "reset":
							if (!getRank(target.getPlayer()).equals(Rank.NORANK)) {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.YELLOW + args[0]
										+ ChatColor.GRAY + "'s rank has been updated to " + ChatColor.YELLOW
										+ args[1] + ChatColor.GRAY + "!");
								plugin.getConfig().set("Users." + target.getUniqueId() + ".Rank", "norank");
								plugin.saveConfig();
								if (target.isOnline()) {
									Bukkit.getPlayer(args[0]).sendMessage(ChatColor.BLUE + "Rank> "
											+ ChatColor.GRAY + "Your rank has been updated to "
											+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
									CustomScoreboard.updateScoreboard((Player) target);
									for (Player s : Bukkit.getOnlinePlayers()) {
										Tablist.tabListUpdate(s);
									}
								}
								return true;
							}
							else {
								p.sendMessage(ChatColor.BLUE + "Rank> " + ChatColor.GRAY + "Player "
										+ ChatColor.YELLOW + args[0] + ChatColor.GRAY + " already has the rank "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "!");
								return true;
							}
						default:
							p.sendMessage(ChatColor.RED + "Error> " +
						ChatColor.GOLD + args[1] + ChatColor.YELLOW + " is not a valid rank!");
					}
					}
				}
		}
	}
		return true;
}
	
	public static String getRank (OfflinePlayer p, boolean b) {
		if (b == true) {
		switch (plugin.getConfig().getString("Users." + p.getUniqueId() + ".Rank").toLowerCase()) {
		case "trainee":
			return (ChatColor.translateAlternateColorCodes('&', ChatColor.GOLD + "&LTRAINEE "));
			
		case "mod":
			return (ChatColor.translateAlternateColorCodes('&', ChatColor.GOLD + "&LMOD "));
		
		case "srmod":
			return (ChatColor.translateAlternateColorCodes('&', ChatColor.GOLD + "&LSR.MOD "));
		
		case "admin":
			return (ChatColor.translateAlternateColorCodes('&', ChatColor.RED + "&LSTAFF "));
		
		case "op":
			return (ChatColor.translateAlternateColorCodes('&', ChatColor.DARK_RED + "&LOP "));
		
		case "eternal": 
			return (ChatColor.translateAlternateColorCodes('&', ChatColor.DARK_AQUA + "&LETERNAL "));
		
		case "youtube": 
			return (ChatColor.translateAlternateColorCodes('&', ChatColor.RED + "&LYOUTUBE "));
		
		default:
			return "";
		}
		}
		else {
			switch (plugin.getConfig().getString("Users." + p.getUniqueId() + ".Rank").toLowerCase()) {
			case "trainee":
				return "Trainee";
				
			case "mod":
				return "Mod";
			
			case "srmod":
				return "Sr.Mod";
			
			case "admin":
				return "Admin";
			
			case "op":
				return "OP";
			
			case "eternal": 
				return "Eternal";
			
			case "youtube": 
				return "YouTube";
			
			default:
				return "No Rank";
			}
		}
	}
	
	public static Rank getRank (OfflinePlayer p) {
		switch (plugin.getConfig().getString("Users." + p.getUniqueId() + ".Rank").toLowerCase()) {
		case "trainee":
			return Rank.TRAINEE;
			
		case "mod":
			return Rank.MOD;
		
		case "srmod":
			return Rank.SRMOD;
		
		case "admin":
			return Rank.ADMIN;
		
		case "op":
			return Rank.OP;
		
		case "eternal": 
			return Rank.ETERNAL;
		
		case "youtube": 
			return Rank.YOUTUBE;
		
		default:
			return Rank.NORANK;
		}
	}
	
	public static int getPerm (OfflinePlayer p) {
		switch (plugin.getConfig().getString("Users." + p.getUniqueId() + ".Rank").toLowerCase()) {
		case "trainee":
			return 1;
		case "mod":
			return 2;
		case "srmod":
			return 3;
		case "admin":
			return 4;
		case "op":
			return 5;
		case "eternal":
			return 0;
		case "youtube":
			return 0;
		default:
			return 0;
		}
	}
	
	public static void noPerm (Player p, String rank) {
		p.sendMessage(ChatColor.BLUE + "Permissions> "
				+ ChatColor.GRAY + "The following action requires the rank: [" 
				+ ChatColor.YELLOW + rank + ChatColor.GRAY + "]");
	}
}
