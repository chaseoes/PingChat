package com.chaseoes.pingchat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PingChat extends JavaPlugin {
    
    private static PingChat instance;
    
    public static PingChat getInstance() {
        return instance;
    }
	
	public void onEnable() {
	    instance = this;
	}
	
	public void onDisable() {
		instance = null;
	}
	
	public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
		return true;
	}

}
