package me.sudura.sudurahook.listeners;

import com.destroystokyo.paper.event.player.PlayerSetSpawnEvent;
import com.gmail.goosius.siegewar.utils.SiegeWarDistanceUtil;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.event.TownRemoveResidentRankEvent;
import com.palmergames.bukkit.towny.object.*;
import com.palmergames.bukkit.towny.utils.CombatUtil;
import dev.geco.gsit.api.event.PreEntitySitEvent;
import dev.geco.gsit.api.event.PrePlayerCrawlEvent;
import dev.geco.gsit.api.event.PrePlayerPlayerSitEvent;
import dev.geco.gsit.api.event.PrePlayerPoseEvent;
import me.sudura.sudurahook.SuduraHook;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class HookListener implements Listener {
    SuduraHook plugin;
    public HookListener(SuduraHook instance) {
        plugin = instance;
    }

    //Paper
    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onPlayerSetsSpawn (PlayerSetSpawnEvent event) {
        Player player = event.getPlayer();
        if (plugin.getCombatLogX().getCombatManager().isInCombat(player)) {
            player.sendMessage("§cYou can't do this in combat!");
            event.setCancelled(true);
            return;
        }

        Location spawnLoc = event.getLocation();

        if (SiegeWarDistanceUtil.isLocationInActiveSiegeAssembly(spawnLoc)) {
            player.sendMessage("§cYou can't do this in a siege zone!");
            event.setCancelled(true);
            return;
        }

        if (SiegeWarDistanceUtil.isLocationInActiveSiegeZone(spawnLoc)) {
            player.sendMessage("§cYou can't do this in a siege zone!");
            event.setCancelled(true);
            return;
        }

        TownBlock townblock = TownyAPI.getInstance().getTownBlock(spawnLoc);

        if (townblock == null || !townblock.hasTown()) {
            //Wilderness - Camps are included
            return;
        }

        if (!CombatUtil.preventPvP(townblock.getWorld(), townblock)) {
            player.sendMessage("§cYou can't do this in a PVP chunk!");
            event.setCancelled(true);
            return;
        }

        Town town = townblock.getTownOrNull();
        Resident resident = TownyUniverse.getInstance().getResident(player.getUniqueId());

        if (resident == null) {
            player.sendMessage("§cYou do not have a Towny resident associated with you!");
            event.setCancelled(true);
            return;
        }

        if (CombatUtil.isEnemy(resident.getTownOrNull(), townblock.getTownOrNull())) {
            player.sendMessage("§cYou can't do this in a town you're an enemy of!");
            event.setCancelled(true);
            return;
        }

        if (townblock.getTownOrNull() == resident.getTownOrNull()) {
            return;
        }

        if (town.hasOutlaw(resident)) {
            player.sendMessage("§cYou can't do this in a town you're outlawed from!");
            event.setCancelled(true);
            return;
        }

        if (townblock.getType() == TownBlockType.INN) {
            return;
        }

        if (townblock.isOwner(resident)) {
            return;
        }
    }

    //GSit
    @EventHandler
    public void onEntitySit (PreEntitySitEvent event) {
        if (event.getEntity() instanceof Player && plugin.getCombatLogX().getCombatManager().isInCombat((Player)event.getEntity())) {
            event.setCancelled(true);
            return;
        }

        Location playerLoc = event.getEntity().getLocation();
        Location targetLoc = event.getBlock().getLocation();

        if (SiegeWarDistanceUtil.isLocationInActiveSiegeAssembly(playerLoc) || SiegeWarDistanceUtil.isLocationInActiveSiegeAssembly(targetLoc)) {
            event.setCancelled(true);
            return;
        }

        if (SiegeWarDistanceUtil.isLocationInActiveSiegeZone(playerLoc) || SiegeWarDistanceUtil.isLocationInActiveSiegeZone(targetLoc)) {
            event.setCancelled(true);
            return;
        }

        if (TownyAPI.getInstance().isWilderness(playerLoc) || TownyAPI.getInstance().isWilderness(targetLoc)) {
            return;
        }

        if (TownyAPI.getInstance().isPVP(playerLoc) || TownyAPI.getInstance().isPVP(targetLoc)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPose (PrePlayerPoseEvent event) {
        if (plugin.getCombatLogX().getCombatManager().isInCombat(event.getPlayer())) {
            event.setCancelled(true);
            return;
        }

        Location playerLoc = event.getPlayer().getLocation();
        Location targetLoc = event.getBlock().getLocation();

        if (SiegeWarDistanceUtil.isLocationInActiveSiegeAssembly(playerLoc) || SiegeWarDistanceUtil.isLocationInActiveSiegeAssembly(targetLoc)) {
            event.setCancelled(true);
            return;
        }

        if (SiegeWarDistanceUtil.isLocationInActiveSiegeZone(playerLoc) || SiegeWarDistanceUtil.isLocationInActiveSiegeZone(targetLoc)) {
            event.setCancelled(true);
            return;
        }

        if (TownyAPI.getInstance().isWilderness(playerLoc) || TownyAPI.getInstance().isWilderness(targetLoc)) {
            return;
        }

        if (TownyAPI.getInstance().isPVP(playerLoc) || TownyAPI.getInstance().isPVP(targetLoc)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPlayerSit (PrePlayerPlayerSitEvent event) {
        if (plugin.getCombatLogX().getCombatManager().isInCombat(event.getPlayer()) || plugin.getCombatLogX().getCombatManager().isInCombat(event.getTarget())) {
            event.setCancelled(true);
            return;
        }

        Location playerLoc = event.getPlayer().getLocation();
        Location targetLoc = event.getTarget().getLocation();

        if (SiegeWarDistanceUtil.isLocationInActiveSiegeAssembly(playerLoc) || SiegeWarDistanceUtil.isLocationInActiveSiegeAssembly(targetLoc)) {
            event.setCancelled(true);
            return;
        }

        if (SiegeWarDistanceUtil.isLocationInActiveSiegeZone(playerLoc) || SiegeWarDistanceUtil.isLocationInActiveSiegeZone(targetLoc)) {
            event.setCancelled(true);
            return;
        }

        if (TownyAPI.getInstance().isWilderness(playerLoc) || TownyAPI.getInstance().isWilderness(targetLoc)) {
            return;
        }

        if (TownyAPI.getInstance().isPVP(playerLoc) || TownyAPI.getInstance().isPVP(targetLoc)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerCrawl (PrePlayerCrawlEvent event) {
        if (plugin.getCombatLogX().getCombatManager().isInCombat(event.getPlayer())) {
            event.setCancelled(true);
            return;
        }

        Location playerLoc = event.getPlayer().getLocation();

        if (SiegeWarDistanceUtil.isLocationInActiveSiegeAssembly(playerLoc)) {
            event.setCancelled(true);
            return;
        }

        if (SiegeWarDistanceUtil.isLocationInActiveSiegeZone(playerLoc)) {
            event.setCancelled(true);
            return;
        }

        if (TownyAPI.getInstance().isWilderness(playerLoc)) {
            return;
        }

        if (TownyAPI.getInstance().isPVP(playerLoc)) {
            event.setCancelled(true);
        }
    }

    //Towny
    @EventHandler
    public void onRemoveRank (TownRemoveResidentRankEvent event) {
        Player player = event.getResident().getPlayer();
        if (player != null && SiegeWarDistanceUtil.isLocationInActiveSiegeZone(player.getLocation())) {
            event.setCancelMessage("§cYou can't do this to someone in a siege zone!");
            event.setCancelled(true);
        }
    }
}
