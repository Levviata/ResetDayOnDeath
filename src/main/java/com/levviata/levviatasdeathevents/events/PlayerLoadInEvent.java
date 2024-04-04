package com.levviata.levviatasdeathevents.events;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerLoadInEvent {
    private boolean isTeamStarted = false;
    @SubscribeEvent
    public void onServerStarting(WorldEvent.Load event) {
        // Get the MinecraftServer instance
        MinecraftServer server = event.getWorld().getMinecraftServer();
        if (server != null) {
            // Command to create the team if it doesn't exist
            if (!isTeamStarted) {
                String createTeamCommand = "scoreboard teams add players";

                server.commandManager.executeCommand(server, createTeamCommand);
                isTeamStarted = true;
            }
        }
    }
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.world.isRemote) { // Check if we're on the server side
            // Get the MinecraftServer instance
            MinecraftServer server = event.player.getServer();
            if (server != null) {
                // Prepare the command
                String command = "scoreboard teams join players " + event.player.getName();
                // Execute the command as the server
                server.commandManager.executeCommand(server, command);
            }
        }
    }
}
