package com.levviata.resetdayondeath;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.levviata.resetdayondeath.PlayerCount.getOverworldPlayerCount;

public class PlayerDeathListener {
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (!player.world.isRemote) {
                player.getServer().getCommandManager().executeCommand(player, "gamemode spectator");
                int overworldPlayerCount = PlayerCount.getOverworldPlayerCount(player.getServer());

                if (areAllPlayersInSpectatorMode(player.getServer(), overworldPlayerCount)) {
                    player.getServer().getCommandManager().executeCommand(player, "kill");
                }
            }
        }
    }
    private boolean areAllPlayersInSpectatorMode(MinecraftServer server, int playerCount) {
        WorldServer overworld = server.getWorld(0);

        if (overworld != null) {
            int spectatorCount = 0;

            for (EntityPlayer player : overworld.playerEntities) {
                if (player.isSpectator()) {
                    spectatorCount++;
                }
            }

            return spectatorCount == playerCount;
        }

        return false;
    }
}