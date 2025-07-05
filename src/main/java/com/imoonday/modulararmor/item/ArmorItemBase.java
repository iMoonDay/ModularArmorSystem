package com.imoonday.modulararmor.item;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class ArmorItemBase extends Item implements Equipable, Modular, DyeableLeatherItem {

    public ArmorItemBase(Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getInstalledParts(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ITEM_HANDLER)
                    .lazyMap(this::getInstalledParts)
                    .orElse(new ArrayList<>());
    }

    protected List<ItemStack> getInstalledParts(IItemHandler itemHandler) {
        ArrayList<ItemStack> parts = new ArrayList<>();
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            parts.add(itemHandler.getStackInSlot(i));
        }
        return parts;
    }

    @Override
    public boolean installPart(ItemStack stack, ItemStack part, int slot) {
        return stack.getCapability(ForgeCapabilities.ITEM_HANDLER)
                    .lazyMap(itemHandler -> installPart(itemHandler, part, slot))
                    .orElse(false);
    }

    protected boolean installPart(IItemHandler itemHandler, ItemStack part, int slot) {
        if (slot == -1) {
            ItemStack result = part;
            int count = result.getCount();
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                result = ItemHandlerHelper.insertItemStacked(itemHandler, result, false);
                if (result.isEmpty()) {
                    return true;
                }
            }
            return result.getCount() < count;
        }

        if (slot < 0 || slot >= itemHandler.getSlots()) {
            return false;
        }

        ItemStack result = part;
        int count = part.getCount();
        result = itemHandler.insertItem(slot, result, false);
        return result.isEmpty() || result.getCount() < count;
    }

    @Override
    public ItemStack uninstallPart(ItemStack stack, int slot) {
        return stack.getCapability(ForgeCapabilities.ITEM_HANDLER)
                    .lazyMap(itemHandler -> uninstallPart(itemHandler, slot))
                    .orElse(ItemStack.EMPTY);
    }

    protected ItemStack uninstallPart(IItemHandler itemHandler, int slot) {
        if (slot < 0 || slot >= itemHandler.getSlots()) {
            return ItemStack.EMPTY;
        }

        return itemHandler.extractItem(slot, itemHandler.getSlotLimit(slot), false);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilityProvider() {

            private ItemStackHandler items;

            @Override
            public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                if (cap == ForgeCapabilities.ITEM_HANDLER) {
                    return LazyOptional.of(this::getItems).cast();
                }
                return LazyOptional.empty();
            }

            private ItemStackHandler getItems() {
                if (items == null) {
                    items = new ItemStackHandler(0);
                }
                return items;
            }
        };
    }
}
