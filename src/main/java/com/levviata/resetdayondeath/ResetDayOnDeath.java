package com.levviata.resetdayondeath;

import com.levviata.resetdayondeath.subscribers.HardcoreRevivalDeathListener;
import com.levviata.resetdayondeath.subscribers.PlayerReviveDeathListener;
import com.levviata.resetdayondeath.subscribers.VanillaDeathListener;
import com.levviata.resetdayondeath.utils.DeathListenerHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ResetDayOnDeath.MODID, name = ResetDayOnDeath.NAME, version = ResetDayOnDeath.VERSION)
public class ResetDayOnDeath
{
    public static final String MODID = "resetdayondeath";
    public static final String NAME = "Reset Day On Death";
    public static final String VERSION = "1.0";

    public static boolean PlayerReviveIsOn = false;
    public static boolean HardcoreRevivalIsOn = false;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
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
