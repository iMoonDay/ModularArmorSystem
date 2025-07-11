package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.BackBagModel;

public class BackBagItem extends BagItemBase {

    public BackBagItem(Properties pProperties) {
        super(pProperties, 9);
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(BackBagModel.LAYER_LOCATION, BackBagModel::new)
                .setBody(model -> model.backpack);
    }

    @Override
    public Class<? extends Installable> getBaseType() {
        return BackBagItem.class;
    }
}
