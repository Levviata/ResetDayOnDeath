package com.levviata.levviatasdeathevents.handlers;


import com.levviata.levviatasdeathevents.utils.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.lang.reflect.Field;
import java.util.Objects;
@Deprecated
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
					ItemArmor itemArmor = (ItemArmor) helmet.getItem();
					NBTHelper.addNBTTag(helmet, "temperature_resistance", handleHelmetTemperature(helmet, helmetRatio));
				}

				ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
				if (chestplate != null && !chestplate.isEmpty()) {
					ItemArmor itemArmor = (ItemArmor) chestplate.getItem();
					NBTHelper.addNBTTag(chestplate, "temperature_resistance", handleChestplateTemperature(chestplate, helmetRatio));
				}

				ItemStack leggings = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
				if (leggings != null && !leggings.isEmpty()) {
					ItemArmor itemArmor = (ItemArmor) leggings.getItem();
					NBTHelper.addNBTTag(leggings, "temperature_resistance", handleLeggingsTemperature(leggings, helmetRatio));
				}

				ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
				if (boots != null && !boots.isEmpty()) {
					ItemArmor itemArmor = (ItemArmor) boots.getItem();
					NBTHelper.addNBTTag(boots, "temperature_resistance", handleBootsTemperature(boots, helmetRatio));
				}

			}
		}
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
		float maxResistance = 0.999f; // Maximum resistance for the best armor
		int totalTiers = 16; // Total number of armor tiers
		float incrementPerTier = maxResistance / totalTiers;
		Minecraft.getMinecraft().player.sendChatMessage(registryName);
		float baseResistance = 0.0f; // Starting base resistance
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
				baseResistance += incrementPerTier;
				return baseResistance;
			case "hbm:alloy_helmet":
			case "hbm:alloy_plate":
			case "hbm:alloy_legs":
			case "hbm:alloy_boots":
				baseResistance += 2 * incrementPerTier; // Increment twice for alloy
				return baseResistance;
			case "hbm:cobalt_helmet":
			case "hbm:cobalt_plate":
			case "hbm:cobalt_legs":
			case "hbm:cobalt_boots":
				baseResistance += 3 * incrementPerTier; // Increment thrice for cobalt
				return baseResistance;
			case "hbm:security_helmet":
			case "hbm:security_plate":
			case "hbm:security_legs":
			case "hbm:security_boots":
				baseResistance += 4 * incrementPerTier; // Increment four times for security
				return baseResistance;
			case "hbm:starmetal_helmet":
			case "hbm:starmetal_plate":
			case "hbm:starmetal_legs":
			case "hbm:starmetal_boots":
				baseResistance += 5 * incrementPerTier; // Increment four times for security
				return baseResistance;
			case "hbm:t45_helmet":
			case "hbm:t45_plate":
			case "hbm:t45_legs":
			case "hbm:t45_boots":
				baseResistance += 6 * incrementPerTier; // Increment four times for security
				return baseResistance;
			case "hbm:cmb_helmet":
			case "hbm:cmb_plate":
			case "hbm:cmb_legs":
			case "hbm:cmb_boots":
				baseResistance += 7 * incrementPerTier; // Increment four times for security
				return baseResistance;
			case "hbm:ajr_helmet":
			case "hbm:ajr_plate":
			case "hbm:ajr_legs":
			case "hbm:ajr_boots":
				baseResistance += 8 * incrementPerTier;
				return baseResistance;
			case "hbm:ajro_helmet":
			case "hbm:ajro_plate":
			case "hbm:ajro_legs":
			case "hbm:ajro_boots":
				baseResistance += 9 * incrementPerTier;
				return baseResistance;
			case "hbm:hev_helmet":
			case "hbm:hev_plate":
			case "hbm:hev_legs":
			case "hbm:hev_boots":
				baseResistance += 10 * incrementPerTier;
				return baseResistance;
			case "hbm:bj_helmet":
			case "hbm:bj_plate":
			case "hbm:bj_plate_jetpack":
			case "hbm:bj_legs":
			case "hbm:bj_boots":
				baseResistance += 11 * incrementPerTier;
				return baseResistance;
			case "hbm:schrabidium_helmet":
			case "hbm:schrabidium_plate":
			case "hbm:schrabidium_legs":
			case "hbm:schrabidium_boots":
				baseResistance += 12 * incrementPerTier;
				return baseResistance;
			case "hbm:rpa_helmet":
			case "hbm:rpa_plate":
			case "hbm:rpa_legs":
			case "hbm:rpa_boots":
				baseResistance += 13 * incrementPerTier;
				return baseResistance;
			case "hbm:fau_helmet":
			case "hbm:fau_plate":
			case "hbm:fau_legs":
			case "hbm:fau_boots":
				baseResistance += 14 * incrementPerTier;
				return baseResistance;
			case "hbm:dns_helmet":
			case "hbm:dns_plate":
			case "hbm:dns_legs":
			case "hbm:dns_boots":
				baseResistance += 15 * incrementPerTier;
				return baseResistance;
			case "hbm:euphemium_helmet":
			case "hbm:euphemium_plate":
			case "hbm:euphemium_legs":
			case "hbm:euphemium_boots":
				baseResistance += 16 * incrementPerTier;
				return baseResistance;
			// Add more cases for other armor entries
			default:
				return defaultResistance;
		}
	}

	/*public static void modifyHelmetResist(float helmet) {
		modifyPotionThermia(helmet, PotionThermia.chestplateResist, PotionThermia.leggingsResist, PotionThermia.bootsResist);
	}

	public static void modifyChestplateResist(float chestplate) {
		modifyPotionThermia(PotionThermia.helmetResist, chestplate, PotionThermia.leggingsResist, PotionThermia.bootsResist);
	}

	public static void modifyLeggingsResist(float leggings) {
		modifyPotionThermia(PotionThermia.helmetResist, PotionThermia.chestplateResist, leggings, PotionThermia.bootsResist);
	}

	public static void modifyBootsResist(float boots) {
		modifyPotionThermia(PotionThermia.helmetResist, PotionThermia.chestplateResist, PotionThermia.leggingsResist, boots);
	}

	private static void modifyPotionThermia(float helmetResist, float chestplateResist, float leggingsResist, float bootsResist) {
		try {
			// Get the field object for the static variables in PotionThermia
			Field helmetResistField = PotionThermia.class.getDeclaredField("helmetResist");
			Field chestplateResistField = PotionThermia.class.getDeclaredField("chestplateResist");
			Field leggingsResistField = PotionThermia.class.getDeclaredField("leggingsResist");
			Field bootsResistField = PotionThermia.class.getDeclaredField("bootsResist");

			// Enable modification of private fields
			helmetResistField.setAccessible(true);
			chestplateResistField.setAccessible(true);
			leggingsResistField.setAccessible(true);
			bootsResistField.setAccessible(true);

			// Modify the static variables with your own values
			helmetResistField.setFloat(null, helmetResist);
			chestplateResistField.setFloat(null, chestplateResist);
			leggingsResistField.setFloat(null, leggingsResist);
			bootsResistField.setFloat(null, bootsResist);

		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace(); // Handle the exception accordingly
		}
	}*/
}