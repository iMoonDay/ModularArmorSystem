package com.imoonday.modulararmor.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BulletPlateItem extends Item implements Installable{

    public BulletPlateItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canInstallOn(ItemStack stack) {
        return false;
    }
}
