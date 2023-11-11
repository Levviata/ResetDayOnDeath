package com.levviata.levviatasdeathevents.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CheckSpectators {
    public static boolean areAllPlayersInSpectatorMode(MinecraftServer server, int playerCount) {
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
