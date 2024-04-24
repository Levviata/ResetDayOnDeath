package com.levviata.levviatasdeathevents.handlers;


import com.charles445.simpledifficulty.api.SDPotions;
import com.charles445.simpledifficulty.config.ModConfig;
import com.levviata.levviatasdeathevents.utils.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.charles445.simpledifficulty.potion.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.levviata.levviatasdeathevents.utils.NTMArmorList.*;

public class TemperatureHandler {

	//Goal: Add full temp resist to certain armors
	private final Minecraft mc = Minecraft.getMinecraft();
	@SubscribeEvent
	public void onTemperatureChange(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END && mc.player != null) {
			EntityPlayer player = mc.player;
			boolean completeSteelArmorSet = true;
			PotionEffect effect = new PotionEffect(SDPotions.cold_resist, 120, 0);
			for (String armorPiece : steelArmorSet) {
				ItemStack armorItem = getArmorItemByRegistryName(player, armorPiece);
				if (armorItem.isEmpty()) {
					completeSteelArmorSet = false;
					break;
				}
			}
			if (completeSteelArmorSet) {
				// Check if the player does not already have the cold_resist potion effect
				if (player.getActivePotionEffect(SDPotions.cold_resist) == null) {
					// If the player does not have the cold_resist potion effect, apply it
					player.addPotionEffect(effect);
					player.removePotionEffect(SDPotions.hypothermia);
				}
			}
			else player.removePotionEffect(SDPotions.cold_resist);
		}
	}
	private ItemStack getArmorItemByRegistryName(EntityPlayer player, String armorName) {
		if (player == null) {
			return ItemStack.EMPTY;
		}

		for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
			ItemStack itemStack = player.getItemStackFromSlot(slot);
			if (!itemStack.isEmpty() && itemStack.getItem().getRegistryName().toString().equals(armorName)) {
				return itemStack;
			}
		}
		return ItemStack.EMPTY;
	}
}