package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.client.model.PlaceCarrierModel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.EmptyHandler;

public class PlaceCarrierItem extends ArmorItemBase implements IVestStorage {

    public PlaceCarrierItem(Properties pProperties) {
        super("place_carrier", Type.CHESTPLATE, 3, pProperties);
    }

    @Override
    protected RenderArmorItemExtension<?> getItemExtension() {
        return new RenderArmorItemExtension<>(PlaceCarrierModel.LAYER_LOCATION, PlaceCarrierModel::new)
                .setBody(model -> model.kj3);
    }

    @Override
    public IItemHandler getStorage(ItemStack stack) {
        return BagItemBase.getBagItems(getInstalledParts(stack).toArray(ItemStack[]::new)).orElse((IItemHandlerModifiable) EmptyHandler.INSTANCE);
    }

    @Override
    public void onContentChanged(ItemStack stack) {
        saveItemsData(getItems(stack), stack);
    }
}
