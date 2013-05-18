package me.chaseoes.pingchat;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PingChat extends JavaPlugin implements Listener {
    
    public void onEnable() {
    	getConfig().options().copyDefaults(true);
    	getConfig().options().header("PingChat by chaseoes. Go here for a list of sounds: http://jd.bukkit.org/rb/apidocs/org/bukkit/Sound.html");
    	saveConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    public void onDisable() {
    	reloadConfig();
    	saveConfig();
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event) {
        for (Player player : event.getRecipients()) {
            if (event.getMessage().toLowerCase().contains(player.getName().toLowerCase())) {
                player.playSound(player.getLocation(), Sound.valueOf(getConfig().getString("sound").toUpperCase()), getConfig().getInt("volume"), getConfig().getInt("pitch"));
            }
        }
    }
    
}
