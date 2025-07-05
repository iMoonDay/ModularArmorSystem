package com.imoonday.modulararmor.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class KevlarChestplateItem extends ArmorItemBase {

    public KevlarChestplateItem(Properties properties) {
        super(properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }

    @Override
    public boolean canUseOnVestCraftTable(ItemStack stack) {
        return false;
    }
}
