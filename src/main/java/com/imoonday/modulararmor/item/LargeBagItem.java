package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.LargeBagModel;

public class LargeBagItem extends BagItemBase {

    public LargeBagItem(Properties pProperties) {
        super(pProperties, 6);
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(LargeBagModel.LAYER_LOCATION, LargeBagModel::new)
                .setBody(model -> model.bag);
    }
}
