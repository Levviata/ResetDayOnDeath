package com.levviata.levviatasdeathevents.listeners;

import com.levviata.levviatasdeathevents.handlers.PlayerSpreadHandler;
import com.levviata.levviatasdeathevents.utils.OverworldPlayerCount;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.Objects;

import static com.levviata.levviatasdeathevents.handlers.DeathHandler.handlePlayerDeath;
import static com.levviata.levviatasdeathevents.handlers.PlayerSpreadHandler.spreadPlayer;
import static com.levviata.levviatasdeathevents.utils.CheckSpectators.areAllPlayersInSpectatorMode;

public class RespawnListener {
    /*@SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.player instanceof FakePlayer)) {
            EntityPlayer player = event.player;
            handlePlayerDeath(player);
        }
    }*/
}

