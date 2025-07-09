package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.KevlarHelmetModel;
import net.minecraft.world.item.ItemStack;

public class KevlarHelmetItem extends ArmorItemBase {

    public KevlarHelmetItem(Properties properties) {
        super("kevlar_helmet", Type.HELMET, 0, properties);
    }

    @Override
    public boolean canUseOnVestCraftTable(ItemStack stack) {
        return false;
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(KevlarHelmetModel.LAYER_LOCATION, KevlarHelmetModel::new)
                .setHead(model -> model.tk1)
                .setOffsetY(0.0, -0.15);
    }
}
