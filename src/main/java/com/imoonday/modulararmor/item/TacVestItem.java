package com.imoonday.modulararmor.item;

import net.minecraft.world.entity.EquipmentSlot;

public class TacVestItem extends ArmorItemBase {

    public TacVestItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }
}
