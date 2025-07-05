package com.imoonday.modulararmor.item;

import net.minecraft.world.entity.EquipmentSlot;

public class TacHelmetItem extends ArmorItemBase {

    public TacHelmetItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
