package com.imoonday.modulararmor.item;

import net.minecraft.world.item.ItemStack;

public interface Installable {

    boolean canInstallOn(ItemStack stack);
}
