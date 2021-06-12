package io.github.acesjus.scratchmc;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import io.github.acesjus.scratchmc.project.Project;

public class Servers implements Listener {
	public static HashMap<Player, String> getServer = new HashMap<Player, String>();
	public static HashMap<Player, Project> getCurrentProject = new HashMap<Player, Project>();
}