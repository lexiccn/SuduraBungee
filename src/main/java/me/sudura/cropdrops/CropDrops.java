package me.sudura.cropdrops;

import com.gmail.nossr50.mcMMO;
import org.bukkit.plugin.java.JavaPlugin;

public class CropDrops extends JavaPlugin {
    mcMMO mmo;
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new BlockListener(this), this);
        if (this.getServer().getPluginManager().isPluginEnabled("mcMMO")) {
            mmo = (mcMMO) this.getServer().getPluginManager().getPlugin("mcMMO");
        }
    }
}
