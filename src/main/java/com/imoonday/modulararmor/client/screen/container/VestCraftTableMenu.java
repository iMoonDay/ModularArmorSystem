package com.imoonday.modulararmor.client.screen.container;

import com.imoonday.modulararmor.init.ModBlocks;
import com.imoonday.modulararmor.init.ModMenus;
import com.imoonday.modulararmor.item.ArmorItemBase;
import com.imoonday.modulararmor.item.Modular;
import com.imoonday.modulararmor.util.SlotItemSupplierHandler;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class VestCraftTableMenu extends AbstractContainerMenu {

    private final Container inputSlots = new SimpleContainer(1);
    private final ContainerLevelAccess access;

    private IItemHandler items = new ItemStackHandler(0);

    public VestCraftTableMenu(int containerId, Inventory inv) {
        this(containerId, inv, ContainerLevelAccess.NULL);
    }

    public VestCraftTableMenu(int containerId, Inventory inv, ContainerLevelAccess access) {
        super(ModMenus.VEST_CRAFTTABLE.get(), containerId);
        this.access = access;
        int i;
        int j;

        this.addSlot(new Slot(inputSlots, 0, 44, 35) {
            @Override
            public void setChanged() {
                super.setChanged();
                VestCraftTableMenu.this.slotsChanged(this.container);
            }

            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof Modular modular && modular.canUseOnVestCraftTable(stack) && super.mayPlace(stack);
            }
        });

        ItemStackHandler emptyInventory = new ItemStackHandler(9);

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 3; ++j) {
                int index = j + i * 3;
                this.addSlot(new SlotItemSupplierHandler(() -> items.getSlots() > index ? items : emptyInventory, index, 80 + j * 18, 17 + i * 18) {
                    @Override
                    public boolean isActive() {
                        return items.getSlots() > index;
                    }
                });
            }
        }

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, i * 18 + 84));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();

            int slots = this.items.getSlots() + 1;
            if (pIndex < slots) {
                if (!this.moveItemStackTo(itemStack1, slots, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack1, 0, slots, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        if (container == this.inputSlots) {
            ItemStack stack = this.inputSlots.getItem(0);
            this.items = ArmorItemBase.getItems(stack);
        }
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.access.execute((level, blockPos) -> {
            this.clearContainer(pPlayer, this.inputSlots);
        });
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(access, pPlayer, ModBlocks.VEST_CRAFTTABLE.get());
    }
}
