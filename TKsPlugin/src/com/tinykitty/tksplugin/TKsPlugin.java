package com.tinykitty.tksplugin;

import com.tinykitty.tksplugin.events.TKEvents;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class TKsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TKEvents(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[TKsPlugin]: PLUGIN SUCCESSFULLY ENABLED!");
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[TKsPlugin]: Use /tktoggle to enable it!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "[TKsPlugin]: PLUGIN SUCCESSFULLY DISABLED!");
    }
}
