package com.imoonday.modulararmor.item;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface Modular {

    List<ItemStack> getInstalledParts(ItemStack stack);

    default boolean canInstall(ItemStack stack, ItemStack part) {
        return part.getItem() instanceof Installable installable && installable.canInstallOn(stack);
    }

    default boolean canUseOnVestCraftTable(ItemStack stack) {
        return true;
    }

    default boolean tryInstallPart(ItemStack stack, ItemStack part) {
        return canInstall(stack, part) && installPart(stack, part);
    }

    boolean installPart(ItemStack stack, ItemStack part);

    boolean uninstallPart(ItemStack stack, int slot);
}
