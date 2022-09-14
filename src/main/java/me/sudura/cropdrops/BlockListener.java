package me.sudura.cropdrops;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {
    CropDrops plugin;
    public BlockListener(CropDrops instance) {
        plugin = instance;
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onBreakBlock (BlockBreakEvent event) {
        BlockState blockState = event.getBlock().getState();
        if ((blockState.getType() == Material.MELON || blockState.getType() == Material.PUMPKIN) && !plugin.mmo.getPlaceStore().isTrue(blockState)) {
            event.setExpToDrop(20);
        }
    }
}
