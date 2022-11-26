package me.sudura.sudurabungee;

import me.sudura.sudurabungee.commands.GretiaCommand;
import me.sudura.sudurabungee.commands.LobbyCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class SuduraBungee extends Plugin {
    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new LobbyCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new GretiaCommand());
    }
}