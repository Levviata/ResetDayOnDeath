package com.levviata.levviatasdeathevents.handlers;

import com.levviata.levviatasdeathevents.utils.OverworldPlayerCount;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

import static com.levviata.levviatasdeathevents.utils.CheckSpectators.areAllPlayersInSpectatorMode;

public class DeathListenerHandler {
    public static void handlePlayerDeath(EntityPlayer player) {
        if (!player.world.isRemote) {
            // We get the server to be able to execute commands later
            MinecraftServer server = player.getServer();
            // We get the name of the player to use it in commands
            String playerName = player.getName();
            // Get the number of players in the overworld by using our OverworldPlayerCount class
            int overworldPlayerCount = OverworldPlayerCount.getOverworldPlayerCount(server);
            // We send a command to the server to make the player that died respawn in
            // spectator mode
            server.getCommandManager().executeCommand(player, "gamemode spectator " + playerName);

            if (areAllPlayersInSpectatorMode(server, overworldPlayerCount)) {
                WorldServer world = server.getWorld(0);
                server.getCommandManager().executeCommand(player, "gamemode survival @a");
                server.getCommandManager().executeCommand(player, "kill @a");
                server.getCommandManager().executeCommand(player, "srpevolution setphase 0");
                server.getCommandManager().executeCommand(player, "time set day");
                spreadPlayer(player, world);
            }
        }
    }


    // Code for spreading players to a random location.
    private static void spreadPlayer(EntityPlayer player, WorldServer world) {
        double x = player.posX + world.rand.nextDouble() * 20000 - 10000; // 10,000 blocks radius
        double z = player.posZ + world.rand.nextDouble() * 20000 - 10000; // 10,000 blocks radius
        double y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY(); // Set the Y coordinate to a safe location
        player.setPositionAndUpdate(x, y, z);

        // Set the spawn point to the player's current location
        player.setSpawnPoint(player.getPosition(), true);
    }
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