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
	public void onChat(final AsyncPlayerChatEvent event) {
		getServer().getScheduler().runTaskLater(this, new Runnable() {
			public void run() {
				for (final String word : event.getMessage().split(" ")) {
					if (getConfig().getBoolean("options.partial-names") && (getServer().getPlayer(word) != null) && (event.getRecipients().contains(getServer().getPlayer(word)))) {
						ping(getServer().getPlayer(word));
					} else  {
						for (Player player : event.getRecipients()) {
							if (word.equalsIgnoreCase(player.getName())) {
								ping(player);
							}
						}
					}
				}
			}
		}, 5L);
	}


	public void ping( Player player) {
		player.playSound(player.getLocation(), Sound.valueOf(getConfig().getString("sound.sound").toUpperCase()), getConfig().getInt("sound.volume"), getConfig().getInt("sound.pitch"));
	}

}
