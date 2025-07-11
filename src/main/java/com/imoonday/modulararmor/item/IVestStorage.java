package com.imoonday.modulararmor.item;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.Optional;

public interface IVestStorage {

    IItemHandler getStorage(ItemStack stack);

    void onContentChanged(ItemStack stack);

    static Optional<IItemHandler> get(ItemStack stack) {
        if (stack.getItem() instanceof IVestStorage storage) {
            return Optional.of(storage.getStorage(stack));
        }
        return Optional.empty();
    }
}
