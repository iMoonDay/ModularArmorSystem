package com.imoonday.modulararmor.item;

import net.minecraft.world.entity.EquipmentSlot;

public class KevlarChestplateItem extends ArmorItemBase {

    public KevlarChestplateItem(Properties properties) {
        super(properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }
}
