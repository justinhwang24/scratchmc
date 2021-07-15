package io.github.acesjus.scratchmc.commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.acesjus.scratchmc.Chat;
import io.github.acesjus.scratchmc.Files;
import io.github.acesjus.scratchmc.Ranks;

public class CommandMessage implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (cmd.getName().equalsIgnoreCase("w")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Usage> " + ChatColor.YELLOW + "/" + cmd.getName().toLowerCase()
						+ " <player> <message>");
				return true;
			}
			if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
				Player target = Bukkit.getPlayer(args[0]);
				String msg = "";
				
				for (int i = 1; i <= args.length-1; i++) {
					msg += (args[i] + " ");
				}
				System.out.println(sender.getName() + " whispers to " + target.getName() + ": " + msg);
				Files.logToFile("log.txt", sender.getName() + " whispers to " + target.getName() + ": " + msg);
				
				try {
					msg = Chat.filter(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
				target.sendMessage(ChatColor.translateAlternateColorCodes('&', 
				ChatColor.DARK_PURPLE + sender.getName() 
				+ ChatColor.LIGHT_PURPLE + " whispers to you: " + ChatColor.GRAY + msg));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
						ChatColor.LIGHT_PURPLE + "You whisper to " 
						+ ChatColor.DARK_PURPLE + target.getName() + 
						ChatColor.LIGHT_PURPLE + ": " + ChatColor.GRAY + msg));
				if (sender instanceof Player) {
				((Player) sender).playSound(((Player) sender).getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
				}
				target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
			}
			else {
				sender.sendMessage(ChatColor.RED + "Error> " + ChatColor.GOLD + args[0] 
						+ ChatColor.YELLOW + " is not online!");
		}
		}
	return true;
}
}
