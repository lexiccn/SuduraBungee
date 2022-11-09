package me.sudura.template.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.sudura.template.MainClass;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("template|temp")
public class TemplateCommand extends BaseCommand {
    MainClass plugin;

    public TemplateCommand(MainClass instance){
        plugin = instance;
    }

    @Default
    public void onDefault(CommandSender sender) {
        sender.sendMessage(plugin.getMessages().getString("command.default"));
    }

    @Subcommand("reload")
    @Description("Reloads the config and messages files")
    public void onReload(CommandSender sender) {
        plugin.reloadMessages();
        plugin.reloadConfig();
        sender.sendMessage(plugin.getMessages().getString("command.reload"));
    }

    @CatchUnknown
    public void onUnknown(CommandSender sender) {
        sender.sendMessage(plugin.getMessages().getString("command.unknown"));
    }
}
