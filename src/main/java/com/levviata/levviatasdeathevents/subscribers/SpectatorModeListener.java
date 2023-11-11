package com.levviata.levviatasdeathevents.subscribers;

public class SpectatorModeListener
{


    /*@SubscribeEvent
    public static void allPlayersInSpectatorMode(SpectatorModeEvent.EnterSpectatorModeEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        if (!player.world.isRemote) {
            MinecraftServer server = player.getServer();
            // Get the number of players in the overworld by using our OverworldPlayerCount class
            int overworldPlayerCount = OverworldPlayerCount.getOverworldPlayerCount(server);
            // We get the name of the player to use it in players
            String playerName = player.getName();

            if (areAllPlayersInSpectatorMode(server, overworldPlayerCount)) {
                server.getCommandManager().executeCommand(player, "gamemode survival @a");
                server.getCommandManager().executeCommand(player, "kill @a");
                server.getCommandManager().executeCommand(player, "srpevolution setphase 0");
                server.getCommandManager().executeCommand(player, "time set day");
            }
        }
    }*/
}
