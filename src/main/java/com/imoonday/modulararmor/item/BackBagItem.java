package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.BackBagModel;
import net.minecraft.world.item.ItemStack;

public class BackBagItem extends BagItemBase {

    public BackBagItem(Properties pProperties) {
        super(pProperties, 9);
    }

    @Override
    public boolean canInstallOn(ItemStack stack) {
        return false;
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(BackBagModel.LAYER_LOCATION, BackBagModel::new)
                .setBody(model -> model.backpack);
    }
}
