package com.levviata.levviatasdeathevents.events;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SpectatorModeEvent {

    /*private static final int COOLDOWN_TICKS = 10; // 0.5 seconds at 20 ticks per second
    private int cooldown = 0;

    // Subscribe to the event in the constructor
    public SpectatorModeEvent() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Custom event class for entering spectator mode
    public static class EnterSpectatorModeEvent extends PlayerEvent {

        public EnterSpectatorModeEvent(EntityPlayer player) {
            super(player);
        }
    }

    // Event handler for checking if a player entered spectator mode
    @SubscribeEvent
    public void onPlayerTick(PlayerEvent.LivingUpdateEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();

            // Check cooldown
            if (cooldown > 0) {
                cooldown--;
                return; // Skip checking if cooldown is active
            }

            // Check if the player entered spectator mode
            if (player.isSpectator() && player.dimension != -1) {
                // Player entered spectator mode
                MinecraftForge.EVENT_BUS.post(new EnterSpectatorModeEvent(player));

                // Set cooldown
                cooldown = COOLDOWN_TICKS;
            }
        }
    }*/
}
