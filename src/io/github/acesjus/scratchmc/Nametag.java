package io.github.acesjus.scratchmc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.iso2013.mlapi.api.MultiLineAPI;
import net.iso2013.mlapi.api.tag.Tag;
import net.iso2013.mlapi.api.tag.TagController;

public class Nametag {
	public static MultiLineAPI mlapi = Main.mlapi;
	public static TagController tc = Main.tc;
	
	public void updateNametag(Player p, String s) {
		Tag tag = mlapi.getOrCreateTag(p, true);
	
		for (TagController tagController : tag.getTagControllers(false)) {
			tag.removeTagController(tagController);
		}
		for (Player all : Bukkit.getOnlinePlayers()) {
			tc.getName(p, all, "hi");
		}
		tag.addTagController(tc);
		tag.update();
	}
}
