package com.imoonday.modulararmor.network;

import com.imoonday.modulararmor.client.screen.container.VestStorageMenu;
import com.imoonday.modulararmor.item.IVestStorage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkHooks;

public record OpenVestStorageC2SRequest() implements NetworkPacket {

    public OpenVestStorageC2SRequest(FriendlyByteBuf buf) {
        this();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {

    }

    @Override
    public void handleServer(ServerPlayer player) {
        ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (stack.isEmpty()) {
            return;
        }

        if (stack.getItem() instanceof IVestStorage storage) {
            IItemHandler items = storage.getStorage(stack);
            NetworkHooks.openScreen(player,
                                    new SimpleMenuProvider((i,
                                                            playerInventory,
                                                            playerEntity) -> new VestStorageMenu(i, playerInventory, items, () -> storage.onContentChanged(stack)),
                                                           stack.getHoverName()),
                                    buf -> buf.writeInt(items.getSlots()));
        }
    }
}
