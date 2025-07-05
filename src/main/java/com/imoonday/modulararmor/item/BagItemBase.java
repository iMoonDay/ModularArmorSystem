package com.imoonday.modulararmor.item;

import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;

public abstract class BagItemBase extends Item implements Installable, DyeableLeatherItem {

    private final int containerSize;

    public BagItemBase(Properties pProperties, int pContainerSize) {
        super(pProperties);
        containerSize = pContainerSize;
    }

    public int getContainerSize() {
        return containerSize;
    }
}