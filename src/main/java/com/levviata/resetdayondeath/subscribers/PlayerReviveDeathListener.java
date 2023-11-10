package com.levviata.resetdayondeath.subscribers;

import com.creativemd.playerrevive.api.event.PlayerKilledEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.levviata.resetdayondeath.utils.DeathListenerHandler.handlePlayerDeath;
import static com.levviata.resetdayondeath.ResetDayOnDeath.HardcoreRevivalIsOn;

public class PlayerReviveDeathListener {
    @SubscribeEvent
    public void onPlayerRevivePlayerDeath(PlayerKilledEvent event) {
        // If the mod 'PlayerRevive' is not loaded, proceed with PlayerRevive's death handling
        if (!HardcoreRevivalIsOn) {
            if (event.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getEntity();
                handlePlayerDeath(player);
            }
        }
    }
}
