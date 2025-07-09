package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.TacVestModel;

public class TacVestItem extends ArmorItemBase {

    public TacVestItem(Properties pProperties) {
        super("tac_vest", Type.CHESTPLATE, 4, pProperties);
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(TacVestModel.LAYER_LOCATION, TacVestModel::new)
                .setBody(model -> model.kj2);
    }
}
