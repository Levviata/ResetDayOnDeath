package com.levviata.levviatasdeathevents.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {

	public static void addNBTTag(ItemStack itemStack, String tagName, float tagValue) {
		NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

		if (nbtTagCompound == null) {
			nbtTagCompound = new NBTTagCompound();
		}

		nbtTagCompound.setFloat(tagName, tagValue);
		itemStack.setTagCompound(nbtTagCompound);
	}
}