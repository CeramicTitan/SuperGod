package me.ceramictitan.SuperGod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;


import me.ceramictitan.SuperGod.Commands.CommandGod;
import me.ceramictitan.SuperGod.Commands.CommandUngod;
import me.ceramictitan.SuperGod.Commands.CommandUpdater;
import me.ceramictitan.SuperGod.Commands.Updater;
import me.ceramictitan.SuperGod.Listeners.MyPlayerListener;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperGod extends JavaPlugin {
	public static Logger log = Logger.getLogger("Minecraft");
	public final MyPlayerListener playerListener = new MyPlayerListener(this);
	 public static boolean update = false;
	 public static String name = "";
	 public static long size = 0;
	 
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
	    log.info(pdfFile.getName() + " is now disabled.");
		
	}
	@Override
	 public void onEnable() {
		    PluginDescriptionFile pdfFile = this.getDescription();
		    SuperGod.log.info(pdfFile.getName() + " Version " + pdfFile.getVersion() +" is now enabled.");
		    PluginManager pm = getServer().getPluginManager();
		    pm.registerEvents(this.playerListener, this);
		    Updater updater = new Updater(this, "super-god", this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
		    update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE; // Determine if there is an update ready for us
		    name = updater.getLatestVersionString(); // Get the latest version
		    size = updater.getFileSize(); // Get latest size
		    getConfig().addDefault("main.update-notify", true);
		    getConfig().addDefault("main.motd", true);
		    getConfig().addDefault("main.GodEffect", true);
		    getConfig().addDefault("metrics.enable", true);
		    getConfig().options().copyDefaults(true);
		    saveConfig();
		    startMetrics();
		    getCommands();
		  }
		public void downloadVersion() {
			Updater updater = new Updater(this, "super-god", this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true); // Go straight to downloading, and announce progress to console.
		}
		public void getCommands(){
		getCommand("god", new CommandGod());
		getCommand("ungod", new CommandUngod());
		getCommand("update", new CommandUpdater(this));
		log.info("Installing Commands");
		
	}
		public void getCommand(String command, CommandExecutor commandexecutor) {
			Bukkit.getServer().getPluginCommand(command).setExecutor(commandexecutor);
		}
		private void startMetrics() {
		        if (getConfig().getBoolean("metrics.enable")) {
		        	try {
		        		log.info("Metrics is starting");
		        	    MetricsLite metrics = new MetricsLite(this);
		        	    metrics.start();
		        	} catch (IOException e) {
		        	    // Failed to submit the stats :-(
		        }}
		        	}
}
