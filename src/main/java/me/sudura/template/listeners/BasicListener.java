package me.sudura.template.listeners;

import me.sudura.template.MainClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BasicListener implements Listener {
    MainClass plugin;
    public BasicListener(MainClass instance) {
        plugin = instance;
    }

    @EventHandler
    public void onBreakBlock (BlockBreakEvent event) {
        plugin.getLogger().info(plugin.getConfig().getString("messages.error"));
    }
}
