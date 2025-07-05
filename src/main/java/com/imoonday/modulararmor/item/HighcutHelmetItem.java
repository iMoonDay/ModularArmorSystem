package com.imoonday.modulararmor.item;

import net.minecraft.world.entity.EquipmentSlot;

public class HighcutHelmetItem extends ArmorItemBase {

    public HighcutHelmetItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
