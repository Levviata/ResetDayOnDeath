package com.levviata.levviatasdeathevents;

import com.levviata.levviatasdeathevents.events.PlayerLoadInEvent;
import com.levviata.levviatasdeathevents.handlers.TemperatureHandler;
import com.levviata.levviatasdeathevents.listeners.HardcoreRevivalDeathListener;
import com.levviata.levviatasdeathevents.listeners.PlayerReviveDeathListener;
import com.levviata.levviatasdeathevents.listeners.VanillaDeathListener;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;

@Mod(modid = LevviatasDeathEvents.MODID, name = LevviatasDeathEvents.NAME, version = LevviatasDeathEvents.VERSION)
public class LevviatasDeathEvents
{
    public static final String MODID = "levviatasdeathevents";
    public static final String NAME = "Levviata's Death Events";
    public static final String VERSION = "1.0";

    public static boolean PlayerReviveIsOn = false;
    public static boolean HardcoreRevivalIsOn = false;

    private static SimpleNetworkWrapper networkWrapper;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new PlayerLoadInEvent());
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
       /* networkWrapper = new SimpleNetworkWrapper("levviatasdeathevent");
        // Register your packet handler
        networkWrapper.registerMessage(PacketHandler.PacketDeathSoundHandler.class, PacketHandler.PacketDeathSound.class, 0, Side.CLIENT);
        */
        MinecraftForge.EVENT_BUS.register(new TemperatureHandler());

        MinecraftForge.EVENT_BUS.register(new VanillaDeathListener());
        logger.info("Starting Reset Day On Death");
        if (Loader.isModLoaded("Player Revive")) {
            PlayerReviveIsOn = true;
            MinecraftForge.EVENT_BUS.register(new PlayerReviveDeathListener());
        }
        if (Loader.isModLoaded("Hardcore Revival")) {
            HardcoreRevivalIsOn = true;
            MinecraftForge.EVENT_BUS.register(new HardcoreRevivalDeathListener());
        }
        else if (Loader.isModLoaded("Player Revive") && Loader.isModLoaded("Hardcore Revival"))
        {
            logger.error("Both Player Revive and Hardcore Revival are loaded. This is not supported. Will only catch events from Hardcore Revival.");
            HardcoreRevivalIsOn = true;
            PlayerReviveIsOn = false;
        }

    }
}
