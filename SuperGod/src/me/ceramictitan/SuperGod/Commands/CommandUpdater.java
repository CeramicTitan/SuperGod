package me.ceramictitan.SuperGod.Commands;




import me.ceramictitan.SuperGod.SuperGod;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CommandUpdater implements CommandExecutor {
	public SuperGod plugin;
	
	public CommandUpdater(SuperGod sg) { 
		plugin = sg; 
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		  if(cmd.getName().equalsIgnoreCase("update")){
			  if(sender.hasPermission("supergod.update")){
				  sender.sendMessage(ChatColor.GREEN + "Download has started!");
				  sender.sendMessage(ChatColor.GREEN + "Check console for status!");
				  plugin.downloadVersion();
			  }else {
				  sender.sendMessage(ChatColor.RED+"No Permission!");
			  }
		  }
		return true;
	}
}

