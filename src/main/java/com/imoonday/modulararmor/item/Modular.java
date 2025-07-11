package com.imoonday.modulararmor.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Predicate;

public interface Modular {

    List<ItemStack> getInstalledParts(ItemStack stack);

    default ItemStack findPart(ItemStack stack, Predicate<ItemStack> predicate) {
        for (ItemStack part : getInstalledParts(stack)) {
            if (predicate.test(part)) {
                return part;
            }
        }
        return ItemStack.EMPTY;
    }

    default ItemStack findPart(ItemStack stack, ItemLike item) {
        return findPart(stack, itemStack -> itemStack.is(item.asItem()));
    }

    default boolean canInstall(ItemStack stack, ItemStack part) {
        return !part.isEmpty() && part.getItem() instanceof Installable installable && installable.canInstallOn(stack);
    }

    default boolean canUseOnVestCraftTable(ItemStack stack) {
        return true;
    }

    default boolean tryInstallPart(ItemStack stack, ItemStack part) {
        return canInstall(stack, part) && installPart(stack, part);
    }

    default boolean installPart(ItemStack stack, ItemStack part) {
        return installPart(stack, part, -1);
    }

    boolean installPart(ItemStack stack, ItemStack part, int slot);

    ItemStack uninstallPart(ItemStack stack, int slot);

    static void removeEmptyParts(ItemStack stack) {
        if (stack.getItem() instanceof Modular modular) {
            List<ItemStack> parts = modular.getInstalledParts(stack);
            for (int i = 0; i < parts.size(); i++) {
                ItemStack part = parts.get(i);
                if (part.isEmpty()) {
                    modular.uninstallPart(stack, i);
                }
            }
        }
    }
}
