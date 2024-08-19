package com.levviata.levviatasdeathevents.listeners;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Objects;

import static com.levviata.levviatasdeathevents.handlers.DeathHandler.handlePlayerDeath;
import static com.levviata.levviatasdeathevents.LevviatasDeathEvents.HardcoreRevivalIsOn;
import static com.levviata.levviatasdeathevents.LevviatasDeathEvents.PlayerReviveIsOn;

public class VanillaDeathListener {
    private boolean shouldRun = false;
    private Entity player;
    @SubscribeEvent
    public void onVanillaPlayerDeath(LivingDeathEvent event) {
        // If the mod PlayerRevive is not loaded and Hardcore Revival is not loaded, proceed with Vanilla's death handling
       if (!PlayerReviveIsOn && !HardcoreRevivalIsOn) {
            if (event.getEntity() instanceof EntityPlayer) {
                player = event.getEntity();
                shouldRun = true;
                player.sendMessage(new TextComponentString(String.valueOf(player.isEntityAlive())));
            }
        }

    }

    @SubscribeEvent
    public void tickEvent(TickEvent.ClientTickEvent event)
    {
        if (player != null && shouldRun && player.isEntityAlive()) {
            player.sendMessage(new TextComponentString("Hi I went through and player should be alive ig?"));
            shouldRun = false;
            handlePlayerDeath((EntityPlayer) player);
        }
    }
}
