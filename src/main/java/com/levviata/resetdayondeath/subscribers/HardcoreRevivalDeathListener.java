package com.levviata.resetdayondeath.subscribers;

import net.blay09.mods.hardcorerevival.PlayerKnockedOutEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.levviata.resetdayondeath.utils.DeathListenerHandler.handlePlayerDeath;
import static com.levviata.resetdayondeath.ResetDayOnDeath.PlayerReviveIsOn;

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
