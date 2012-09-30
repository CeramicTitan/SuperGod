package me.ceramictitan.SuperGod.Listeners;






import me.ceramictitan.SuperGod.SuperGod;
import me.ceramictitan.SuperGod.User.User;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MyPlayerListener implements Listener {
	
private SuperGod plugin;
	
	public MyPlayerListener(SuperGod sg) { 
		plugin = sg; 
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(plugin.getConfig().getBoolean("main.motd")){
			  String prefix = ChatColor.GOLD + "[SG] ";
			  player.sendMessage(prefix + ChatColor.YELLOW + "This server is Running version " + ChatColor.DARK_AQUA + plugin.getDescription().getVersion() + ChatColor.YELLOW + " of " + plugin.getDescription().getName());
		}
		if(player.hasPermission("supergod.update") && SuperGod.update && plugin.getConfig().getBoolean("main.update-notify"))
		  {
		    player.sendMessage(ChatColor.GREEN +"An update is available: " + SuperGod.name + "(" + SuperGod.size + " bytes" + ")");
		    // Will look like - An update is available: AntiCheat v1.3.6 (93738 bytes)
		    player.sendMessage(ChatColor.GREEN +"Type /update if you would like to update.");
		  }
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
			if(event.getEntity() instanceof Player) {
				final Player player = ((Player) event.getEntity());;
				if(User.godlist.containsKey(player.getName())) {
					event.setCancelled(true);
					if(plugin.getConfig().getBoolean("main.GodEffect")) {
					player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 30);
				}
			}
	}
}
}