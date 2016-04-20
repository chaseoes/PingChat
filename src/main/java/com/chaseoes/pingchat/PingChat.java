package com.chaseoes.pingchat;

import java.io.IOException;

import net.gravitydevelopment.updater.Updater;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class PingChat extends JavaPlugin implements Listener {

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        getConfig().options().header("PingChat by chaseoes. Go here for a list of sounds: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html");
        saveConfig();
        getServer().getPluginManager().registerEvents(this, this);
        
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit Metrics.
        }
        
        if (getConfig().getBoolean("auto-update")) {
            new Updater(this, 57721, this.getFile(), Updater.UpdateType.DEFAULT, false);
        }
    }

    public void onDisable() {
        reloadConfig();
        saveConfig();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(final AsyncPlayerChatEvent event) {
        getServer().getScheduler().runTaskLater(this, new Runnable() {
            public void run() {
                for (final String word : event.getMessage().split(" ")) {
                    if (getConfig().getBoolean("options.partial-names") && (getServer().getPlayer(word) != null) && word.length() >= getConfig().getInt("options.partial-names-min-length") && (event.getRecipients().contains(getServer().getPlayer(word))) && (!getServer().getPlayer(word).getName().equalsIgnoreCase(event.getPlayer().getName()))) {
                        ping(getServer().getPlayer(word));
                    } else {
                        for (Player player : event.getRecipients()) {
                            if (word.contains(player.getName()) && (!player.getName().equalsIgnoreCase(event.getPlayer().getName()))) {
                                ping(player);
                            }
                        }
                    }
                }
            }
        }, 5L);
    }

    public void ping(Player player) {
        player.playSound(player.getLocation(), Sound.valueOf(getConfig().getString("sound.sound").toUpperCase()), getConfig().getInt("sound.volume"), getConfig().getInt("sound.pitch"));
    }

}
