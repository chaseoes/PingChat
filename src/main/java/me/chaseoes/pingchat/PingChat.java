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
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event) {
        for (Player player : event.getRecipients()) {
            if (event.getMessage().contains(player.getName())) {
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 60f, 0f);
            }
        }
    }
    
}
