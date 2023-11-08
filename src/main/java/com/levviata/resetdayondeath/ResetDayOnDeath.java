package com.levviata.resetdayondeath;

import net.minecraft.init.Blocks;
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

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("Starting Reset Day On Death");
    }
}
