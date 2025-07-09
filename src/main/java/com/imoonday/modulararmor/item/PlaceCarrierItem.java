package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.PlaceCarrierModel;

public class PlaceCarrierItem extends ArmorItemBase {

    public PlaceCarrierItem(Properties pProperties) {
        super("place_carrier", Type.CHESTPLATE, 3, pProperties);
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(PlaceCarrierModel.LAYER_LOCATION, PlaceCarrierModel::new)
                .setBody(model -> model.kj3);
    }
}
