package io.github.acesjus.scratchmc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
import org.bukkit.plugin.Plugin;

public class Files {
	static Plugin plugin = Main.getPlugin(Main.class);
	
	public static void newFile(OfflinePlayer p) throws IOException {
		String fileName = p.getUniqueId().toString().concat(".txt");
		File saveTo = new File(plugin.getDataFolder(), fileName);
		if (!saveTo.exists()) {
			saveTo.createNewFile(); 
	        FileWriter fw = new FileWriter(saveTo, true);
	        PrintWriter pw = new PrintWriter(fw);
	        pw.println(p.getName());
	        pw.println();
	        pw.println();
	        pw.println();
	        pw.println("-------\n");
	        pw.println("-------\n");
	        pw.println("-------\n");
	        pw.println("-------");
	        pw.flush();
	        pw.close();
	     }
	}
	
	public static void saveData(Project pr) throws IOException {
		OfflinePlayer p = pr.getOwner();
		String fileName = p.getUniqueId().toString().concat(".txt");
		File saveTo = new File(plugin.getDataFolder(), fileName);
		if (!saveTo.exists()) {
			newFile(p);
			return;
		}
        FileWriter fw = new FileWriter(saveTo, true);
        PrintWriter pw = new PrintWriter(fw);
        replaceLine(p, 0, p.getName());
        replaceLine(p, pr.getSlot(), pr.getName());
        int n = getLine(p, "-------", pr.getSlot());
        insertLine(p, n++, "Name: " + pr.getName());
        insertLine(p, n++, "Id: " + pr.getId());
        insertLine(p, n++, "SpawnX: " + pr.getSpawn().getX());
        insertLine(p, n++, "SpawnY: " + pr.getSpawn().getY());
        insertLine(p, n++, "SpawnZ: " + pr.getSpawn().getZ());
        insertLine(p, n++, "SpawnYaw: " + pr.getSpawn().getYaw());
        insertLine(p, n++, "SpawnPitch: " + pr.getSpawn().getPitch());
        pw.flush();
        pw.close();
        return;
	}
	
	public static Project fetchProject(OfflinePlayer p, String name) throws IOException {
		if (!Files.getHouses(p).contains(name)) {
			return null;
		}
		String fileName = p.getUniqueId().toString().concat(".txt");
		File saveTo = new File(plugin.getDataFolder(), fileName);
		if (!saveTo.exists()) {
			newFile(p);
			return null;
		}
        Path path = saveTo.toPath();
		int slot = ArrayUtils.indexOf(Files.getHouses(p).toArray(), name) + 1;
        FileWriter fw = new FileWriter(saveTo, true);
        PrintWriter pw = new PrintWriter(fw);
		int n = Files.getLine(p, "-------", slot);
        List<String> lines = java.nio.file.Files.readAllLines(path, StandardCharsets.UTF_8);
        try (BufferedReader br = new BufferedReader(new FileReader(saveTo))) {
            int i = 1;
	        int id = Integer.parseInt(strip(lines.get(n + i++).split(":"))[1]);
            double x = Double.parseDouble(strip(lines.get(n + i++).split(":"))[1]);
            double y = Double.parseDouble(strip(lines.get(n + i++).split(":"))[1]);
            double z = Double.parseDouble(strip(lines.get(n + i++).split(":"))[1]);
            float yaw = Float.parseFloat(strip(lines.get(n + i++).split(":"))[1]);
            float pitch = Float.parseFloat(strip(lines.get(n + i++).split(":"))[1]);
	        Project pr = new Project(p.getUniqueId(), name, id, slot, new Location(Bukkit.getWorld("Lobby"), x, y, z, yaw, pitch));
	        pw.flush();
	        pw.close();
	        pw.flush();
	        pw.close();
	        return pr;
	     }
	}
	
	public static void insertLine (OfflinePlayer p, int n, String s) throws IOException {
		String fileName = p.getUniqueId().toString().concat(".txt");
		File saveTo = new File(plugin.getDataFolder(), fileName);
        if (!saveTo.exists()) {
            newFile(p);
            return;
        }
        Path path = saveTo.toPath();
        List<String> lines = java.nio.file.Files.readAllLines(path, StandardCharsets.UTF_8);
        if (n > lines.size() - 1) {
        	return;
        }
        List<String> newLines = new ArrayList<String>();
        lines.add("");
        for (int i = 0; i < n; i++) {
        	newLines.add(lines.get(i));
        }
        newLines.add(s);
        for (int i = n; i < lines.size(); i++) {
        	newLines.add(lines.get(i));
        }
		java.nio.file.Files.write(path, newLines, StandardCharsets.UTF_8);
	}
	
	public static void replaceLine (OfflinePlayer p, int n, String s) throws IOException {
		String fileName = p.getUniqueId().toString().concat(".txt");
		File saveTo = new File(plugin.getDataFolder(), fileName);
        if (!saveTo.exists()) {
            newFile(p);
        }
        Path path = saveTo.toPath();
        List<String> lines = java.nio.file.Files.readAllLines(path, StandardCharsets.UTF_8);
		lines.set(n, s);
		java.nio.file.Files.write(path, lines, StandardCharsets.UTF_8);
	}
	
	public static String getNthLine (OfflinePlayer p, int n) throws IOException {
		String fileName = p.getUniqueId().toString().concat(".txt");
		File saveTo = new File(plugin.getDataFolder(), fileName);
        if (!saveTo.exists()) {
            newFile(p);
            return null;
        }
		BufferedReader br = new BufferedReader(new FileReader(saveTo));
		for (int i = 1; i < n; i++) {
        	br.readLine();
        }
		return br.readLine();
	}
	
	public static void logToFile (String fileName, String message) {
        try {
           File dataFolder = plugin.getDataFolder();
           if (!dataFolder.exists()) {
               dataFolder.mkdir();
           }
           File saveTo = new File(plugin.getDataFolder(), fileName);
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }
           FileWriter fw = new FileWriter(saveTo, true);
           PrintWriter pw = new PrintWriter(fw);
           pw.println(message);
           pw.flush();
           pw.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static List<String> getHouses (OfflinePlayer p) throws IOException {
		String fileName = p.getUniqueId() + ".txt";
		File saveTo = new File(plugin.getDataFolder(), fileName);
        if (!saveTo.exists()) {
            Files.newFile(p);
            return null;
        }
        Path path = saveTo.toPath();
        List<String> lines = java.nio.file.Files.readAllLines(path, StandardCharsets.UTF_8);
        List<String> houses = new ArrayList<String>();
        int i = 1;
        while (i <= 3 && !lines.get(i).isBlank()) {
        	houses.add(lines.get(i));
        	i++;
        }
		return houses;
    }
	
	public static int getLine (OfflinePlayer p, String s, int instance) throws IOException {
		String fileName = p.getUniqueId() + ".txt";
		File saveTo = new File(plugin.getDataFolder(), fileName);
        if (!saveTo.exists()) {
           newFile(p);
        }
        try {
        	int i = 1;
        	int times = 0;
            BufferedReader br = new BufferedReader(new FileReader(saveTo));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if (line.equals(s)) {
                	times++;
                	if (times == instance) {
	                    br.close();
	                	return i;
                	}
                }
                i++;
             }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return -1;
    }
	
	public static int getLength(OfflinePlayer p) throws IOException {
		String fileName = p.getUniqueId() + ".txt";
		File saveTo = new File(plugin.getDataFolder(), fileName);
        if (!saveTo.exists()) {
            newFile(p);
         }
		BufferedReader reader = new BufferedReader(new FileReader(saveTo));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		return lines;
	}
	
	public static String[] strip (String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].strip();
		}
		return arr;
	}
}
