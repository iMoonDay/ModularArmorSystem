package com.imoonday.modulararmor.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public abstract class ArmorItemBase extends Item implements Equipable, Modular, DyeableLeatherItem {

    public ArmorItemBase(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public List<ItemStack> getInstalledParts(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return List.of();
    }

    @Override
    public boolean installPart(ItemStack stack, ItemStack part) {
        return false;
    }

    @Override
    public boolean uninstallPart(ItemStack stack, int slot) {
        return false;
    }
}
