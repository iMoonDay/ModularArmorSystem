package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.HighcutHelmetModel;

public class HighcutHelmetItem extends ArmorItemBase {

    public HighcutHelmetItem(Properties pProperties) {
        super("highcut_helmet", Type.HELMET, 2, pProperties);
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(HighcutHelmetModel.LAYER_LOCATION, HighcutHelmetModel::new)
                .setHead(model -> model.tk3)
                .setOffsetY(0.15, 0.0);
    }
}
