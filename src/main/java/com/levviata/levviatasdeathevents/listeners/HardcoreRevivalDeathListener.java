package com.levviata.levviatasdeathevents.listeners;

import net.blay09.mods.hardcorerevival.PlayerKnockedOutEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.levviata.levviatasdeathevents.handlers.DeathHandler.handlePlayerDeath;
import static com.levviata.levviatasdeathevents.LevviatasDeathEvents.PlayerReviveIsOn;

public class HardcoreRevivalDeathListener {
    @SubscribeEvent
    public void onHardcoreRevivalPlayerDeath(PlayerKnockedOutEvent event) {
        // If the mod 'PlayerRevive' is not loaded, proceed with HardcoreRevival's death handling
        if (!PlayerReviveIsOn) {
            event.getPlayer();
            EntityPlayer player = event.getPlayer();
            handlePlayerDeath(player);
        }
    }
}
