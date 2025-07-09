package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.SmallBagModel;
import net.minecraft.world.item.ItemStack;

public class SmallBagItem extends BagItemBase {

    public SmallBagItem(Properties pProperties) {
        super(pProperties, 3);
    }

    @Override
    public boolean canInstallOn(ItemStack stack) {
        return false;
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(SmallBagModel.LAYER_LOCATION, SmallBagModel::new)
                .setBody(model -> model.bag);
    }
}
