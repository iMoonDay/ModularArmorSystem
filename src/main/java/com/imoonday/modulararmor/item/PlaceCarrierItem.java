package com.imoonday.modulararmor.item;

import net.minecraft.world.entity.EquipmentSlot;

public class PlaceCarrierItem extends ArmorItemBase {

    public PlaceCarrierItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }
}
