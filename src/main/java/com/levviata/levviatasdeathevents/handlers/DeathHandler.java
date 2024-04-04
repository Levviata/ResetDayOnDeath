package com.levviata.levviatasdeathevents.handlers;

import com.levviata.levviatasdeathevents.utils.OverworldPlayerCount;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

import java.util.Objects;

import static com.levviata.levviatasdeathevents.handlers.PlayerSpreadHandler.spreadPlayer;
import static com.levviata.levviatasdeathevents.utils.CheckSpectators.areAllPlayersInSpectatorMode;

public class DeathHandler {
    public static void handlePlayerDeath(EntityPlayer player) {
        if (!player.world.isRemote) {
            if (!(player instanceof FakePlayer)) {
                // This check ensures that the player is not a fake player
                String playerName = player.getName();
                MinecraftServer server = player.getServer();
                WorldServer world = Objects.requireNonNull(player.getServer()).getWorld(0);
                int overworldPlayerCount = OverworldPlayerCount.getOverworldPlayerCount(server);

                if (server != null) {
                    spreadPlayer(player, world);
                    server.getCommandManager().executeCommand(server, "srpevolution setphase 0");
                    server.getCommandManager().executeCommand(server, "time set day");
                    server.getCommandManager().executeCommand(server, "weather clear");
                    server.getCommandManager().executeCommand(server, "stopHordeEvent");
                    server.getCommandManager().executeCommand(server, "scalinghealth difficulty set 0 " + playerName);
                    //server.getCommandManager().executeCommand(server, "sereneseasons setseason mid_summer" + playerName);
                    if (areAllPlayersInSpectatorMode(server, overworldPlayerCount)) {
                        for (EntityPlayer entityPlayer : world.playerEntities) {
                            spreadPlayer(entityPlayer, world);
                            server.getCommandManager().executeCommand(server, "gamemode survival " + entityPlayer.getName());
                            server.getCommandManager().executeCommand(server, "scalinghealth difficulty set 0 " + entityPlayer.getName());
                            server.getCommandManager().executeCommand(server, "sereneseasons setseason mid_summer" + entityPlayer.getName());
                        }
                        //server.getCommandManager().executeCommand(server, "sereneseasons setseason mid_summer");
                        server.getCommandManager().executeCommand(server, "srpevolution setphase 0");
                        server.getCommandManager().executeCommand(server, "time set day");
                        server.getCommandManager().executeCommand(server, "weather clear");
                        server.getCommandManager().executeCommand(server, "stopHordeEvent");
                    }
                }
            }
        }
    }


    // Code for spreading players to a random location.

    /*
    private void spawnPlayersNear(EntityPlayer sourcePlayer, int playerCount, WorldServer world) {
        BlockPos sourcePos = sourcePlayer.getPosition();
        BlockPos[] newSpawnPoints = new BlockPos[playerCount - 1]; // Array to store new spawn points

        for (int i = 0; i < playerCount - 1; i++) {
            double angle = 50; // Random angle
            double distance = 10.0; // 10-block radius

            double xOffset = distance * Math.cos(angle);
            double zOffset = distance * Math.sin(angle);

            int newX = sourcePos.getX() + (int) xOffset;
            int newY = sourcePos.getY();
            int newZ = sourcePos.getZ() + (int) zOffset;

            EntityPlayer player = world.playerEntities.get(i);
            player.setPositionAndUpdate(newX, newY, newZ);

            // Store the new spawn point for this player
            newSpawnPoints[i] = new BlockPos(newX, newY, newZ);
        }

        // Reset spawn points for all other players
        for (int i = 0; i < playerCount - 1; i++) {
            EntityPlayer player = world.playerEntities.get(i);
            player.setSpawnPoint(newSpawnPoints[i], true);
        }
    }*/
}