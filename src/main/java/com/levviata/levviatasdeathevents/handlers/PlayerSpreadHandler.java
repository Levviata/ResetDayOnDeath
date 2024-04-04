package com.levviata.levviatasdeathevents.handlers;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;

public class PlayerSpreadHandler {
    public static void spreadPlayer(EntityPlayer player, WorldServer world) {
        BlockPos pos;
        Biome biome;
        do {
            double x = player.posX + world.rand.nextDouble() * 3000 - 2000; // 10,000 blocks radius
            double z = player.posZ + world.rand.nextDouble() * 3000 - 2000; // 10,000 blocks radius
            pos = new BlockPos(x, 0, z);
            pos = world.getTopSolidOrLiquidBlock(pos); // Set the BlockPos to a safe location
            biome = world.getBiome(pos); // Get the biome at the current position
        } while (isWaterBiome(biome) || isSurroundedByWater(world, pos));

        // Set the player's position to the safe location
        player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());

        // Reset the spawn point to the player's current location, removing any existing bed spawn point
        player.setSpawnPoint(player.getPosition(), false);
    }

    private static boolean isWaterBiome(Biome biome) {
        return biome.isHighHumidity() || biome.getBiomeName().toLowerCase().contains("ocean") || biome.getBiomeName().toLowerCase().contains("river");
    }

    private static boolean isSurroundedByWater(WorldServer world, BlockPos pos) {
        return world.getBlockState(pos).getMaterial() == Material.WATER ||
                world.getBlockState(pos.up()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.down()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.south()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.west()).getMaterial() == Material.WATER;
    }
}