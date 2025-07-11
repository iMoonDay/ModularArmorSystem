package com.imoonday.modulararmor.util;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SlotItemSupplierHandler extends Slot {

    private static Container emptyInventory = new SimpleContainer(0);
    private final Supplier<IItemHandler> itemHandler;
    private final int index;

    public SlotItemSupplierHandler(Supplier<IItemHandler> itemHandler, int index, int xPosition, int yPosition) {
        super(emptyInventory, index, xPosition, yPosition);
        this.itemHandler = itemHandler;
        this.index = index;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        return itemHandler.get().isItemValid(index, stack);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return this.getItemHandler().getStackInSlot(index);
    }

    @Override
    public void set(@NotNull ItemStack stack) {
        ((IItemHandlerModifiable) this.getItemHandler()).setStackInSlot(index, stack);
        this.setChanged();
    }

    public void initialize(ItemStack stack) {
        IItemHandler handler = this.getItemHandler();
        if (handler.getSlots() == 0) {
            return;
        }

        ((IItemHandlerModifiable) handler).setStackInSlot(index, stack);
        this.setChanged();
    }

    @Override
    public void onQuickCraft(@NotNull ItemStack oldStackIn, @NotNull ItemStack newStackIn) {

    }

    @Override
    public int getMaxStackSize() {
        return this.itemHandler.get().getSlotLimit(this.index);
    }

    @Override
    public int getMaxStackSize(@NotNull ItemStack stack) {
        ItemStack maxAdd = stack.copy();
        int maxInput = stack.getMaxStackSize();
        maxAdd.setCount(maxInput);

        IItemHandler handler = this.getItemHandler();
        ItemStack currentStack = handler.getStackInSlot(index);
        if (handler instanceof IItemHandlerModifiable) {
            IItemHandlerModifiable handlerModifiable = (IItemHandlerModifiable) handler;

            handlerModifiable.setStackInSlot(index, ItemStack.EMPTY);

            ItemStack remainder = handlerModifiable.insertItem(index, maxAdd, true);

            handlerModifiable.setStackInSlot(index, currentStack);

            return maxInput - remainder.getCount();
        } else {
            ItemStack remainder = handler.insertItem(index, maxAdd, true);

            int current = currentStack.getCount();
            int added = maxInput - remainder.getCount();
            return current + added;
        }
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        return !this.getItemHandler().extractItem(index, 1, true).isEmpty();
    }

    @Override
    @NotNull
    public ItemStack remove(int amount) {
        return this.getItemHandler().extractItem(index, amount, false);
    }

    public IItemHandler getItemHandler() {
        return itemHandler.get();
    }
}
