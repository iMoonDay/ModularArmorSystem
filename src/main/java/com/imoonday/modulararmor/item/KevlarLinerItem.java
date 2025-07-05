package com.imoonday.modulararmor.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class KevlarLinerItem extends Item implements Installable {

    public KevlarLinerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canInstallOn(ItemStack stack) {
        return false;
    }
}
