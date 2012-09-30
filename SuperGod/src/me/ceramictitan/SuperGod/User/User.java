package me.ceramictitan.SuperGod.User;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class User {
	
	public static HashMap<String, Boolean> godlist = new HashMap<String, Boolean>();
	public static void enableGodMode(Player p) {
		godlist.put(p.getName(), true);
	}
	
	public static void disableGodMode(Player p) {
		godlist.remove(p.getName());
	}

}
