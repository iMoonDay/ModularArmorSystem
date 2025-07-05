package com.imoonday.modulararmor.item;

import net.minecraft.world.item.ItemStack;

public class LargeBagItem extends BagItemBase {

    public LargeBagItem(Properties pProperties) {
        super(pProperties, 6);
    }

    @Override
    public boolean canInstallOn(ItemStack stack) {
        return false;
    }
}
