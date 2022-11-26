package me.sudura.sudurabungee.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LobbyCommand extends Command {
    public LobbyCommand() {
        super("lobby", "sudura.lobby");
    }

    public void execute(CommandSender sender, String[] args) {
        if ((sender instanceof ProxiedPlayer player)) {
            player.sendMessage(new ComponentBuilder("Sending you to the ").color(ChatColor.DARK_AQUA)
                    .append("lobby").color(ChatColor.AQUA)
                    .append("!").color(ChatColor.DARK_AQUA)
                    .create());
            player.connect(ProxyServer.getInstance().getServerInfo("lobby"));
        }
    }
}
