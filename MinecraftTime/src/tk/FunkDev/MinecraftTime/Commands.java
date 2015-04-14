package tk.FunkDev.MinecraftTime;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	private Time core;
	public Commands(Time core) {
		this.core = core;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("HubMCTime")) {
			if(args.length == 0) {
				p.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + ChatColor.BOLD + "=============" + ChatColor.GREEN + " HubMCTime Help " + ChatColor.STRIKETHROUGH + ChatColor.BOLD + "=============");
				p.sendMessage(ChatColor.GRAY + "/hubmctime reload           Reload the plugin");
				p.sendMessage(ChatColor.GRAY + "/hubmctime [on/off] Turn the plugin on or off");
				p.sendMessage(ChatColor.GRAY + "/hubmctime day/night      Set it day or night");
				p.sendMessage(ChatColor.GRAY + "/hubmctime world [world]        Set the world");
				p.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + ChatColor.BOLD + "==========================================");
				
			} else {
				if(args[0].equalsIgnoreCase("reload")) {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "HubMCTime" + ChatColor.GRAY + "] " + ChatColor.RED + "Reloading the HubMCTime config.yml...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						
						public void run() {
							p.sendMessage(ChatColor.RED + "1");
						}
						
					}, 20L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						
						public void run() {
							p.sendMessage(ChatColor.GOLD + "2");
						}
						
					}, 40L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						
						public void run() {
							p.sendMessage(ChatColor.YELLOW + "3");
						}
						
					}, 60L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						
						public void run() {
							p.sendMessage(ChatColor.GREEN + "Plugin config.yml file has been reloaded!");
							core.reloadConfig();
						}
						
					}, 70L); 
				}
				if(args[0].equalsIgnoreCase("on")) {
					core.getConfig().set("Enabled", "true");
					core.saveConfig();
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "HubMCTime" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Plugin has enabled!");
					Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						public void run() {
							core.reloadConfig();
						}
					}, 5L);
				}
				if(args[0].equalsIgnoreCase("off")) {
					core.getConfig().set("Enabled", "false");
					core.saveConfig();
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "HubMCTime" + ChatColor.GRAY + "] " + ChatColor.DARK_RED + "Plugin has disabled!");
					Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						public void run() {
							core.reloadConfig();
						}
					}, 5L);
				}
				if(args[0].equalsIgnoreCase("day")) {
					core.getConfig().set("Time_In_Ticks_To_Set", "2000");
					core.saveConfig();
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "HubMCTime" + ChatColor.GRAY + "] " + ChatColor.GOLD + "Time set to day all the time for " + core.getConfig().getString("HubWorld") + "!");
					Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						public void run() {
							core.reloadConfig();
						}
					}, 5L);
				}
				if(args[0].equalsIgnoreCase("night")) {
					core.getConfig().set("Time_In_Ticks_To_Set", "14000");
					core.saveConfig();
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "HubMCTime" + ChatColor.GRAY + "] " + ChatColor.DARK_GRAY + "Time set to night all the time for " + core.getConfig().getString("HubWorld") + "!");
					Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						public void run() {
							core.reloadConfig();
						}
					}, 5L);
				}
				if(args[0].equalsIgnoreCase("world")) {
					if(args.length == 1) p.sendMessage(ChatColor.RED + "Usage: /hubmctime world [worldname]");
					if(args.length == 2) {
						core.getConfig().set("HubWorld", args[0]);
						core.saveConfig();
						Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
							public void run() {
								core.reloadConfig();
							}
						}, 5L);
						p.sendMessage();
					}
				}
			}
		}
		
		return false;
	}

}
