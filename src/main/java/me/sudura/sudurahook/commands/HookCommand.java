package me.sudura.sudurahook.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.sudura.sudurahook.SuduraHook;
import org.bukkit.command.CommandSender;

@CommandAlias("sudurahook")
@CommandPermission("sudura.hook")
public class HookCommand extends BaseCommand {
    SuduraHook plugin;

    public HookCommand(SuduraHook instance){
        plugin = instance;
    }

    @Default
    public void onDefault(CommandSender sender) {
        sender.sendMessage(plugin.getMessages().getString("command.default"));
    }

    @Subcommand("reload")
    @CommandPermission("sudura.hook.reload")
    @Description("Reloads the config and messages files")
    public void onReload(CommandSender sender) {
        plugin.reloadMessages();
        sender.sendMessage(plugin.getMessages().getString("command.reload"));
    }

    @CatchUnknown
    public void onUnknown(CommandSender sender) {
        sender.sendMessage(plugin.getMessages().getString("command.unknown"));
    }
}
