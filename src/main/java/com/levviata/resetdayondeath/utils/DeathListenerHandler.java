package com.levviata.resetdayondeath.utils;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class DeathListenerHandler {
    // We listen to the LivingDeathEvent event if a player were to die and if so, we give the info to one of the three
    // methods below



    public static void handlePlayerDeath(EntityPlayer player) {
        if (!player.world.isRemote) {
            // We get the server to be able to execute commands later
            MinecraftServer server = player.getServer();
            // Get the number of players in the overworld by using our PlayerCount class
            int overworldPlayerCount = PlayerCount.getOverworldPlayerCount(server);
            // We get the name of the player to use it in players
            String playerName = player.getName();

            // We send a command to the server to make the player that died respawn in
            // spectator mode
            server.getCommandManager().executeCommand(getCommandSender(player), "gamemode spectator " + playerName);

            // We check if all players are in spectator mode and if so we send three commands in an important
            // order to:

            // 1. Set all of the player's gamemode to survival so next time they aren't in spectator mode
            // 2. Kill all of the players to make them forcefully spawn somewhere else
            // 3. Set the time to day to reset the day counter to 0 and also reset the phase so the hordes are
            // set back to normal

            if (areAllPlayersInSpectatorMode(server, overworldPlayerCount)) {
                server.getCommandManager().executeCommand(getCommandSender(player), "gamemode survival @a");
                server.getCommandManager().executeCommand(getCommandSender(player), "kill @a");
                server.getCommandManager().executeCommand(getCommandSender(player), "srpevolution setphase 0");
                server.getCommandManager().executeCommand(getCommandSender(player), "time set day");
            }
        }
    }
    private static ICommandSender getCommandSender(EntityPlayer player) {
        return player.getCommandSenderEntity();
    }
    // Check if all players are in spectator mode
    private static boolean areAllPlayersInSpectatorMode(MinecraftServer server, int playerCount) {
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

    // Code for spreading players to a random location.
    /*private void spreadPlayer(EntityPlayer player, WorldServer world) {
        double x = player.posX + world.rand.nextDouble() * 20000 - 10000; // 10,000 blocks radius
        double z = player.posZ + world.rand.nextDouble() * 20000 - 10000; // 10,000 blocks radius
        double y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY(); // Set the Y coordinate to a safe location
        player.setPositionAndUpdate(x, y, z);

        // Set the spawn point to the player's current location
        player.setSpawnPoint(player.getPosition(), true);
    }

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