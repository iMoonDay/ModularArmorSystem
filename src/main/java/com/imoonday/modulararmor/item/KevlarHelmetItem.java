package com.imoonday.modulararmor.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class KevlarHelmetItem extends ArmorItemBase {

    public KevlarHelmetItem(Properties properties) {
        super(properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public boolean canUseOnVestCraftTable(ItemStack stack) {
        return false;
    }
}
