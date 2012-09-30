package me.ceramictitan.SuperGod.Commands;




import me.ceramictitan.SuperGod.User.User;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUngod implements CommandExecutor {
	
	  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		  if (!(sender instanceof Player)) {
			    if (cmd.getName().equalsIgnoreCase("ungod")) {
			    	if (args.length == 1) {
			          if (sender.getServer().getPlayer(args[0]) != null) {
			            Player targetplayer = sender.getServer().getPlayer(args[0]);
			            User.disableGodMode((Player) targetplayer);
			            sender.sendMessage(ChatColor.AQUA + "God mode remove for " + ChatColor.GOLD + targetplayer.getDisplayName());
			            targetplayer.sendMessage(ChatColor.GOLD + "[" + sender.getName() + "]" + ChatColor.AQUA +" has removed your god mode!");
			          } else {
			        	  sender.sendMessage(ChatColor.RED+ "Can't find that player, maybe they're offline?");
			          }
			        } else if (args.length > 1) {
			        	sender.sendMessage(ChatColor.RED +"Too many arguments!");
				        sender.sendMessage(ChatColor.GREEN + "Usage: /" + commandLabel + " <player>");
			          }
			        }
				return true;
			    }
		Player player = (Player)sender;
		    if (cmd.getName().equalsIgnoreCase("ungod")) {
		    	if (!player.hasPermission("supergod.ungod")) {
				player.sendMessage(ChatColor.RED +"No Permission!");
				return true;
	    }
	        if (args.length == 0) {
      		User.disableGodMode((Player) sender);
				player.sendMessage(ChatColor.AQUA + "You god mode is no londer active!");
	        } else if (args.length == 1) {
		    	if (!player.hasPermission("supergod.ungod.others")) {
				player.sendMessage(ChatColor.RED +"No Permission!");
				return true;
	    }
		          if (player.getServer().getPlayer(args[0]) != null) {
			            Player targetplayer = player.getServer().getPlayer(args[0]);
			            User.disableGodMode((Player) targetplayer);
			            player.sendMessage(ChatColor.AQUA + "God mode no longer active for " + ChatColor.GOLD + targetplayer.getDisplayName());
			            targetplayer.sendMessage(ChatColor.GOLD + "[" + sender.getName() + "]" + ChatColor.AQUA +" has removed your god mode!");
		          } else {
			          player.sendMessage(ChatColor.RED + "Can't find that player, maybe they're offline?");
		          }
			} else if (args.length > 1 || args.length < 1) {
		          player.sendMessage(ChatColor.RED + "Error!");
		          player.sendMessage(ChatColor.GREEN + "Usage: /" + commandLabel + " [player]");
			}
	}
			return true;
}
}

