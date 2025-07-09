package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.KevlarChestplateModel;
import net.minecraft.world.item.ItemStack;

public class KevlarChestplateItem extends ArmorItemBase {

    public KevlarChestplateItem(Properties properties) {
        super("kevlar_chestplate", Type.CHESTPLATE, 0, properties);
    }

    @Override
    public boolean canUseOnVestCraftTable(ItemStack stack) {
        return false;
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(KevlarChestplateModel.LAYER_LOCATION, KevlarChestplateModel::new)
                .setBody(model -> model.armor);
    }
}
