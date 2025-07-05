package com.imoonday.modulararmor.item;

import net.minecraft.world.item.ItemStack;

public class BackBagItem extends BagItemBase {

    public BackBagItem(Properties pProperties) {
        super(pProperties, 9);
    }

    @Override
    public boolean canInstallOn(ItemStack stack) {
        return false;
    }
}
