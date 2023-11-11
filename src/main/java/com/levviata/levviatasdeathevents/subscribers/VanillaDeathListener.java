package com.levviata.levviatasdeathevents.subscribers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.levviata.levviatasdeathevents.handlers.DeathListenerHandler.handlePlayerDeath;
import static com.levviata.levviatasdeathevents.LevviatasDeathEvents.HardcoreRevivalIsOn;
import static com.levviata.levviatasdeathevents.LevviatasDeathEvents.PlayerReviveIsOn;

public class VanillaDeathListener {
    @SubscribeEvent
    public void onVanillaPlayerDeath(LivingDeathEvent event) {
        // If the mod PlayerRevive is not loaded and Hardcore Revival is not loaded, proceed with Vanilla's death handling
        if (!PlayerReviveIsOn && !HardcoreRevivalIsOn) {
            if (event.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getEntity();
                handlePlayerDeath(player);
            }

        }
    }
}
