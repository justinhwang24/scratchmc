package io.github.acesjus.scratchmc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemStackClass {

	public static ItemStack itemStack (Material m, int i, String s, String l) {
		
		ItemStack item = new ItemStack(m, i);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(s);
		String[] set = l.split("\n");
		List<String> lore = new ArrayList<>();
		for (String str : set) {
			lore.add(str);
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack ItemStack (Material m, int i, String s, String l1, String l2, String l3, String l4, String l5, String l6, String l7, String l8, String l9) {
			
			ItemStack item = new ItemStack(m, i);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(s);
			List<String> lore = new ArrayList<>();
			lore.add(l1);
			lore.add(l2);
			lore.add(l3);
			lore.add(l4);
			lore.add(l5);
			lore.add(l6);
			lore.add(l7);
			lore.add(l8);
			lore.add(l9);
			meta.setLore(lore);
			item.setItemMeta(meta);
			
			return item;
		}
	
	public static ItemStack ItemStack (Material m, int i, String s, String l1, String l2, String l3, String l4, String l5, String l6, String l7, String l8) {
		
		ItemStack item = new ItemStack(m, i);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(s);
		List<String> lore = new ArrayList<>();
		lore.add(l1);
		lore.add(l2);
		lore.add(l3);
		lore.add(l4);
		lore.add(l5);
		lore.add(l6);
		lore.add(l7);
		lore.add(l8);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}

	public static ItemStack ItemStack (Material m, int i, String s, String l1, String l2, String l3, String l4, String l5, String l6, String l7) {
		
		ItemStack item = new ItemStack(m, i);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(s);
		List<String> lore = new ArrayList<>();
		lore.add(l1);
		lore.add(l2);
		lore.add(l3);
		lore.add(l4);
		lore.add(l5);
		lore.add(l6);
		lore.add(l7);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack ItemStack (Material m, int i, String s, String l1, String l2, String l3, String l4, String l5, String l6) {
		
		ItemStack item = new ItemStack(m, i);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(s);
		List<String> lore = new ArrayList<>();
		lore.add(l1);
		lore.add(l2);
		lore.add(l3);
		lore.add(l4);
		lore.add(l5);
		lore.add(l6);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack ItemStack (Material m, int i, String s, String l1, String l2, String l3, String l4, String l5) {
		
		ItemStack item = new ItemStack(m, i);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(s);
		List<String> lore = new ArrayList<>();
		lore.add(l1);
		lore.add(l2);
		lore.add(l3);
		lore.add(l4);
		lore.add(l5);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}

	public static ItemStack ItemStack (Material m, int i, String s, String l1, String l2, String l3, String l4) {
	
	ItemStack item = new ItemStack(m, i);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(s);
	List<String> lore = new ArrayList<>();
	lore.add(l1);
	lore.add(l2);
	lore.add(l3);
	lore.add(l4);
	meta.setLore(lore);
	item.setItemMeta(meta);
	
	return item;
	}
	
	public static ItemStack ItemStack (Material m, int i, String s, String l1, String l2, String l3) {
		
		ItemStack item = new ItemStack(m, i);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(s);
		List<String> lore = new ArrayList<>();
		lore.add(l1);
		lore.add(l2);
		lore.add(l3);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
		}

	public static ItemStack ItemStack (Material m, int i, String s, String l1, String l2) {
		
		ItemStack item = new ItemStack(m, i);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(s);
		List<String> lore = new ArrayList<>();
		lore.add(l1);
		lore.add(l2);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack ItemStack (Material m, int i, String s, String l1) {
		
		ItemStack item = new ItemStack(m, i);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(s);
		List<String> lore = new ArrayList<>();
		lore.add(l1);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack ItemStack (Material m, int i, String s) {
		
		ItemStack item = new ItemStack(m, i);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(s);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack ItemStack (Material m, int i) {
		
		ItemStack item = new ItemStack(m, i);
		
		return item;
	}
	
	public static ItemStack ItemStack (Material m) {
		
		ItemStack item = new ItemStack(m);
		
		return item;
	}

	public static ItemStack skull (String name, String s) {
	ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
	SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
	skullMeta.setOwner(name);
	skull.setItemMeta(skullMeta);
	ItemMeta meta = skull.getItemMeta();
	meta.setDisplayName(s);
	skull.setItemMeta(meta);
	
	return skull;
	}
}
