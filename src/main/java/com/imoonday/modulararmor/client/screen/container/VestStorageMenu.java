package com.imoonday.modulararmor.client.screen.container;

import com.imoonday.modulararmor.init.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class VestStorageMenu extends AbstractContainerMenu {

    private final IItemHandler items;
    private Runnable updateAction;

    public VestStorageMenu(int containerId, Inventory inv, FriendlyByteBuf data) {
        this(containerId, inv, new ItemStackHandler(data.readInt()), null);
    }

    public VestStorageMenu(int containerId, Inventory inv, IItemHandler items, Runnable updateAction) {
        super(ModMenus.VEST_STORAGE.get(), containerId);
        this.items = items;
        this.updateAction = updateAction;

        int i;
        int j;

        int slots = items.getSlots();
        int startX = getStorageSlotStartX(slots);
        for (j = 0; j < 2; j++) {
            for (i = 0; i < 9; ++i) {
                if (i + j * 9 < slots) {
                    this.addSlot(new SlotItemHandler(items, i + j * 9, startX + i * 18, j * 18 + 20) {
                        @Override
                        public void setChanged() {
                            super.setChanged();
                            if (VestStorageMenu.this.updateAction != null) {
                                VestStorageMenu.this.updateAction.run();
                            }
                        }
                    });
                }
            }
        }

        int offset = slots > 9 ? 18 : 0;

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, i * 18 + 51 + offset));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inv, i, 8 + i * 18, 109 + offset));
        }
    }

    public int getStorageSlotStartX(int slots) {
        if (slots > 6) {
            return 8;
        } else if (slots > 3) {
            return 35;
        } else {
            return 62;
        }
    }

    public int getStorageSlots() {
        return items.getSlots();
    }

    public void setUpdateAction(Runnable updateAction) {
        this.updateAction = updateAction;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();

            int slots = this.items.getSlots();
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
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
