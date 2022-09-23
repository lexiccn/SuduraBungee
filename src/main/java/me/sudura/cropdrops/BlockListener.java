package me.sudura.cropdrops;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.Map;

public class BlockListener implements Listener {
    CropDrops plugin;
    HashMap<Material, Integer> xpMaterials = new HashMap<>();
    public BlockListener(CropDrops instance) {
        plugin = instance;
        for (Map.Entry<String, Object> mat : instance.getConfig().getConfigurationSection("xp").getValues(false).entrySet()) {
            xpMaterials.put(Material.getMaterial(mat.getKey()), (Integer)mat.getValue());
        }
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onBreakBlock (BlockBreakEvent event) {
        BlockState blockState = event.getBlock().getState();
        if ((xpMaterials.containsKey(blockState.getType())) && !plugin.mmo.getPlaceStore().isTrue(blockState)){
            event.setExpToDrop(xpMaterials.get(blockState.getType()));
        }
    }
}
