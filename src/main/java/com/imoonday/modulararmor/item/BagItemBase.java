package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public abstract class BagItemBase extends Item implements Installable, DyeableLeatherItem {

    private final int containerSize;

    public BagItemBase(Properties pProperties, int pContainerSize) {
        super(pProperties);
        containerSize = pContainerSize;
    }

    public int getContainerSize() {
        return containerSize;
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(this.getItemExtension());
    }

    @OnlyIn(Dist.CLIENT)
    protected abstract RenderArmorItemExtension<?> getItemExtension();

}