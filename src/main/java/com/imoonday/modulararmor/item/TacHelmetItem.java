package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.TacHelmetModel;

public class TacHelmetItem extends ArmorItemBase {

    public TacHelmetItem(Properties pProperties) {
        super("tac_helmet", Type.HELMET, 2, pProperties);
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(TacHelmetModel.LAYER_LOCATION, TacHelmetModel::new)
                .setHead(model -> model.tk2)
                .setOffsetY(0.15, 0.0);
    }
}
