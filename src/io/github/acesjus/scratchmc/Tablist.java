package io.github.acesjus.scratchmc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Tablist implements Listener {

	public static void tabListUpdate (Player p) {
//		String server = (CommandServer.getServer(p).getName());
		String server = Servers.getServer.get(p);
		String header = (ChatColor.translateAlternateColorCodes('&', ChatColor.LIGHT_PURPLE + "&LMINETEST"
				+ ChatColor.WHITE + " - " + ChatColor.GREEN + server));
		p.setPlayerListHeader(header);
		String footer = (ChatColor.GRAY + "Server address: " + ChatColor.GREEN
				+ "ace.minetest.land");
		p.setPlayerListFooter(footer);
		p.setPlayerListName(Ranks.getRank(p, true) + ChatColor.RESET + p.getName());
	}
}
