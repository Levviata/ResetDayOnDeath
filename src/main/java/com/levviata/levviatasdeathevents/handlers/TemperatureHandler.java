package com.levviata.levviatasdeathevents.handlers;


import com.charles445.simpledifficulty.api.SDPotions;
import com.levviata.levviatasdeathevents.utils.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

import static com.levviata.levviatasdeathevents.utils.NTMArmorList.*;

public class TemperatureHandler {
	private final Minecraft mc = Minecraft.getMinecraft();
	private static final float defaultResistance = 0.0f;
	private static final float helmetRatio = 0.2f;
	private static final float chestplateRatio = 0.3f;
	private static final float leggingsRatio = 0.25f;
	private static final float bootsRatio = 0.25f;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			if (mc != null && mc.player != null) { // Check if Minecraft instance and player object are not null
				EntityPlayer player = mc.player; // Assign player object

				// Check if the player's ItemStack objects are not null and not empty before accessing them
				ItemStack helmet = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
				if (helmet != null && !helmet.isEmpty()) {
					NBTHelper.addNBTTag(helmet, "temperature_resistance", handleHelmetTemperature(helmet, helmetRatio));
				}

				ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
				if (chestplate != null && !chestplate.isEmpty()) {
					NBTHelper.addNBTTag(chestplate, "temperature_resistance", handleChestplateTemperature(chestplate, chestplateRatio));
				}

				ItemStack leggings = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
				if (leggings != null && !leggings.isEmpty()) {
					NBTHelper.addNBTTag(leggings, "temperature_resistance", handleLeggingsTemperature(leggings, leggingsRatio));
				}

				ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
				if (boots != null && !boots.isEmpty()) {
					NBTHelper.addNBTTag(boots, "temperature_resistance", handleBootsTemperature(boots, bootsRatio));
				}
				
				manageArmorEffects(player, ajrArmorSet, SDPotions.cold_resist, SDPotions.heat_resist);
				manageArmorEffects(player, ajroArmorSet, SDPotions.cold_resist, SDPotions.heat_resist);
				manageArmorEffects(player, hevArmorSet, SDPotions.cold_resist, SDPotions.heat_resist);
				manageArmorEffects(player, bjArmorSet, SDPotions.cold_resist, SDPotions.heat_resist);
				manageArmorEffects(player, schrabidiumArmorSet, SDPotions.cold_resist, SDPotions.heat_resist);
				manageArmorEffects(player, rpaArmorSet, SDPotions.cold_resist, SDPotions.heat_resist);
				manageArmorEffects(player, fauArmorSet, SDPotions.cold_resist, SDPotions.heat_resist);
				manageArmorEffects(player, dnsArmorSet, SDPotions.cold_resist, SDPotions.heat_resist);
				manageArmorEffects(player, euphemiumArmorSet, SDPotions.cold_resist, SDPotions.heat_resist);
			}
		}
	}
	private void manageArmorEffects(EntityPlayer player, List<String> armorSet, Potion coldResistPotion, Potion heatResistPotion) {
		boolean isSetComplete = true;
		PotionEffect effectCold = new PotionEffect(coldResistPotion, 240, 0);
		PotionEffect effectHeat = new PotionEffect(heatResistPotion, 240, 0);

		// Check if the player has all pieces of the armor set
		for (String armorPiece : armorSet) {
			ItemStack armorItem = getArmorItemByRegistryName(player, armorPiece);
			if (armorItem.isEmpty()) {
				isSetComplete = false;
				break;
			}
		}

		if (isSetComplete) {
			// Apply cold resistance if not present
			if (player.getActivePotionEffect(coldResistPotion) == null) {
				player.addPotionEffect(effectCold);
				player.removePotionEffect(SDPotions.hypothermia);
			}

			// Apply heat resistance if not present
			if (player.getActivePotionEffect(heatResistPotion) == null) {
				player.addPotionEffect(effectHeat);
				player.removePotionEffect(SDPotions.hyperthermia);
			}
		} else {
			// Remove effects if the set is not complete
			player.removePotionEffect(heatResistPotion);
			player.removePotionEffect(coldResistPotion);
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
	public Float handleHelmetTemperature(ItemStack helmet, float ratio) {
		if (helmet != null && helmet.getItem() instanceof ItemArmor) {
			ItemArmor itemArmor = (ItemArmor) helmet.getItem();
			return getTemperatureResistance(itemArmor) * ratio;
		}
		return defaultResistance;
	}

	public Float handleChestplateTemperature(ItemStack chestplate, float ratio) {
		if (chestplate != null && chestplate.getItem() instanceof ItemArmor) {
			ItemArmor itemArmor = (ItemArmor) chestplate.getItem();
			return getTemperatureResistance(itemArmor) * ratio;
		}
		return defaultResistance;
	}

	public Float handleLeggingsTemperature(ItemStack leggings, float ratio) {
		if (leggings != null && leggings.getItem() instanceof ItemArmor) {
			ItemArmor itemArmor = (ItemArmor) leggings.getItem();
			return getTemperatureResistance(itemArmor) * ratio;
		}
		return defaultResistance;
	}

	public Float handleBootsTemperature(ItemStack boots, float ratio) {
		if (boots != null && boots.getItem() instanceof ItemArmor) {
			ItemArmor itemArmor = (ItemArmor) boots.getItem();
			return getTemperatureResistance(itemArmor) * ratio;
		}
		return defaultResistance;
	}

	private static float getTemperatureResistance(ItemArmor armor) {
		String registryName = Objects.requireNonNull(armor.getRegistryName()).toString();
		float maxResistance = 100f; // Maximum resistance for the best armor
		int totalTiers = 16; // Total number of armor tiers
		float incrementPerTier = maxResistance / totalTiers;
		// Format the incrementPerTier to display only the first three digits
		DecimalFormat df = new DecimalFormat("#.###");
		String formattedIncrement = df.format(incrementPerTier);

		// Parse the formatted string back to a float if needed
		float truncatedIncrement = Float.parseFloat(formattedIncrement);

		float baseResistance = 0.05f; // Starting base resistance
		switch (registryName) {
			case "hbm:steel_helmet":
			case "hbm:steel_plate":
			case "hbm:steel_legs":
			case "hbm:steel_boots":
				return baseResistance;
			case "hbm:titanium_helmet":
			case "hbm:titanium_plate":
			case "hbm:titanium_legs":
			case "hbm:titanium_boots":
				baseResistance += truncatedIncrement;
				return baseResistance;
			case "hbm:alloy_helmet":
			case "hbm:alloy_plate":
			case "hbm:alloy_legs":
			case "hbm:alloy_boots":
				baseResistance += 2 * truncatedIncrement; // Increment twice for alloy
				return baseResistance;
			case "hbm:cobalt_helmet":
			case "hbm:cobalt_plate":
			case "hbm:cobalt_legs":
			case "hbm:cobalt_boots":
				baseResistance += 3 * truncatedIncrement; // Increment thrice for cobalt
				return baseResistance;
			case "hbm:security_helmet":
			case "hbm:security_plate":
			case "hbm:security_legs":
			case "hbm:security_boots":
				baseResistance += 4 * truncatedIncrement; // Increment four times for security
				return baseResistance;
			case "hbm:starmetal_helmet":
			case "hbm:starmetal_plate":
			case "hbm:starmetal_legs":
			case "hbm:starmetal_boots":
				baseResistance += 5 * truncatedIncrement; // Increment four times for security
				return baseResistance;
			case "hbm:t45_helmet":
			case "hbm:t45_plate":
			case "hbm:t45_legs":
			case "hbm:t45_boots":
				baseResistance += 6 * truncatedIncrement; // Increment four times for security
				return baseResistance;
			case "hbm:cmb_helmet":
			case "hbm:cmb_plate":
			case "hbm:cmb_legs":
			case "hbm:cmb_boots":
				baseResistance += 7 * truncatedIncrement; // Increment four times for security
				return baseResistance;
			case "hbm:ajr_helmet":
			case "hbm:ajr_plate":
			case "hbm:ajr_legs":
			case "hbm:ajr_boots":
				baseResistance += 8 * truncatedIncrement;
				return baseResistance;
			case "hbm:ajro_helmet":
			case "hbm:ajro_plate":
			case "hbm:ajro_legs":
			case "hbm:ajro_boots":
				baseResistance += 9 * truncatedIncrement;
				return baseResistance;
			case "hbm:hev_helmet":
			case "hbm:hev_plate":
			case "hbm:hev_legs":
			case "hbm:hev_boots":
				baseResistance += 10 * truncatedIncrement;
				return baseResistance;
			case "hbm:bj_helmet":
			case "hbm:bj_plate":
			case "hbm:bj_plate_jetpack":
			case "hbm:bj_legs":
			case "hbm:bj_boots":
				baseResistance += 11 * truncatedIncrement;
				return baseResistance;
			case "hbm:schrabidium_helmet":
			case "hbm:schrabidium_plate":
			case "hbm:schrabidium_legs":
			case "hbm:schrabidium_boots":
				baseResistance += 12 * truncatedIncrement;
				return baseResistance;
			case "hbm:rpa_helmet":
			case "hbm:rpa_plate":
			case "hbm:rpa_legs":
			case "hbm:rpa_boots":
				baseResistance += 13 * truncatedIncrement;
				return baseResistance;
			case "hbm:fau_helmet":
			case "hbm:fau_plate":
			case "hbm:fau_legs":
			case "hbm:fau_boots":
				baseResistance += 14 * truncatedIncrement;
				return baseResistance;
			case "hbm:dns_helmet":
			case "hbm:dns_plate":
			case "hbm:dns_legs":
			case "hbm:dns_boots":
				baseResistance += 15 * truncatedIncrement;
				return baseResistance;
			case "hbm:euphemium_helmet":
			case "hbm:euphemium_plate":
			case "hbm:euphemium_legs":
			case "hbm:euphemium_boots":
				baseResistance += 16 * truncatedIncrement;
				return baseResistance;
			// Add more cases for other armor entries
			default:
				return defaultResistance;
		}
	}

}