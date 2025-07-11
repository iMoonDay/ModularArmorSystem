package com.imoonday.modulararmor.item;

import net.minecraft.world.item.ItemStack;

public interface Installable {

    boolean canInstallOn(ItemStack stack);

    Class<? extends Installable> getBaseType();

    static boolean isSameBaseType(ItemStack stack1, ItemStack stack2) {
        if (stack1.isEmpty() || stack2.isEmpty()) {
            return false;
        }

        if (!(stack1.getItem() instanceof Installable installable1)) {
            return false;
        }

        if (!(stack2.getItem() instanceof Installable installable2)) {
            return false;
        }

        return installable1.getBaseType() == installable2.getBaseType();
    }

    static boolean isSameBaseType(ItemStack stack, Class<? extends Installable> baseType) {
        if (baseType == null) {
            return false;
        }

        if (stack.isEmpty()) {
            return false;
        }

        if (!(stack.getItem() instanceof Installable installable)) {
            return false;
        }

        return installable.getBaseType() == baseType;
    }
}
