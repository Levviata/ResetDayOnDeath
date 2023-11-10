package com.levviata.resetdayondeath.utils;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PlayerCount {

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
