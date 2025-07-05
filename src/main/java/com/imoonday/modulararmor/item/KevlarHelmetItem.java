package com.imoonday.modulararmor.item;

import net.minecraft.world.entity.EquipmentSlot;

public class KevlarHelmetItem extends ArmorItemBase {

    public KevlarHelmetItem(Properties properties) {
        super(properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
