package com.imoonday.modulararmor.item;

import net.minecraft.world.item.ItemStack;

public class SmallBagItem extends BagItemBase {

    public SmallBagItem(Properties pProperties) {
        super(pProperties, 3);
    }

    @Override
    public boolean canInstallOn(ItemStack stack) {
        return false;
    }
}
