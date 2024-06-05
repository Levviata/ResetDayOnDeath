package com.levviata.levviatasdeathevents.handlers;

import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class DayHandler {
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			World world = event.world;
			if (!world.isRemote) {
				// Get the total world time
				long worldTime = world.getWorldTime();
				// There are 24000 ticks in a Minecraft day
				long daysPassed = worldTime / 24000;

				// Check if it's the 10th day
				if (daysPassed == 10) {
					// Enable mob spawning
					world.getGameRules().setOrCreateGameRule("doMobSpawning", "true");
				} else {
					// Disable mob spawning
					world.getGameRules().setOrCreateGameRule("doMobSpawning", "false");
				}
			}
		}
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event) {
		World world = event.getWorld();
		if (!world.isRemote) {
			// Ensure the game rule is disabled initially
			world.getGameRules().setOrCreateGameRule("doMobSpawning", "false");
		}
	}
}

