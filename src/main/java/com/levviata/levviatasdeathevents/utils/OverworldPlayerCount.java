package com.levviata.levviatasdeathevents.utils;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class OverworldPlayerCount {

    public static int getOverworldPlayerCount(MinecraftServer server) {
        int playerCount = 0;
        if (server != null) {
            WorldServer overworld = DimensionManager.getWorld(0); // Get the Overworld
            if (overworld != null) {
                playerCount = overworld.playerEntities.size();
            }
        }

        return playerCount;
    }
}
