package me.sudura.sudurahook;

import co.aikar.commands.PaperCommandManager;
import com.github.sirblobman.combatlogx.api.ICombatLogX;
import me.sudura.sudurahook.commands.HookCommand;
import me.sudura.sudurahook.listeners.HookListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SuduraHook extends JavaPlugin {
    private File messagesFile;
    private FileConfiguration messages;
    private ICombatLogX combatLogX;
    public void onEnable() {
        combatLogX = (ICombatLogX) Bukkit.getPluginManager().getPlugin("CombatLogX");
        PaperCommandManager manager = new PaperCommandManager(this);
        this.saveDefaultMessages();
        manager.registerCommand(new HookCommand(this));
        this.getServer().getPluginManager().registerEvents(new HookListener(this), this);
    }

    public FileConfiguration getMessages() {
        return this.messages;
    }
    public ICombatLogX getCombatLogX() { return this.combatLogX; }

    public void reloadMessages() {
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    private void saveDefaultMessages() {
        messagesFile = new File(getDataFolder(), "messages.yml");

        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }

        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }
}
