package tk.FunkDev.MinecraftTime;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Time extends JavaPlugin {
	/*
	 * This plugin is not finished yet.
	 */
	
	public void onEnable(Plugin p) {
		File file =  new File(getDataFolder(), "config.yml");
		
		if(file.exists()) getLogger().info("Plugin configuration file time.yml: EXISTS");
		
		if(!file.exists()) {
			getLogger().info("Plugin configuration file time.yml: DOES NOT EXIST");
			getLogger().info("Generating time.yml configuration file...");
			Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
			
				public void run() {
					getLogger().info("File time.yml has been generated!");
					getConfig().options().copyDefaults(true);
					saveConfig();
					
				}
				
			}, 40L);
			
			if(getConfig().getString("Enabled").equalsIgnoreCase("false")) getLogger().info("This plugin is disabled! Set the plugin config Enabled string to 'true'!");
		}
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(p, new Runnable() {
			public void run() {
				if(getConfig().getString("Enabled").equalsIgnoreCase("true")) Bukkit.getWorld(getConfig().getString("HubWorld")).setTime(getConfig().getInt("Time_In_Ticks_To_Set"));
			}
		}, 2L, 1000L);
		
		//Registration of other classes will be put here!
	}
		

}
