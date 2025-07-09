package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.LargeBagModel;
import net.minecraft.world.item.ItemStack;

public class LargeBagItem extends BagItemBase {

    public LargeBagItem(Properties pProperties) {
        super(pProperties, 6);
    }

    @Override
    public boolean canInstallOn(ItemStack stack) {
        return false;
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(LargeBagModel.LAYER_LOCATION, LargeBagModel::new)
                .setBody(model -> model.bag);
    }
}
