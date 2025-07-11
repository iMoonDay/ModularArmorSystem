package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;
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

    @Override
    public boolean canInstallOn(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof PlaceCarrierItem || item instanceof TacVestItem;
    }

    @Override
    public Class<? extends Installable> getBaseType() {
        return BagItemBase.class;
    }

    @Override
    public int getColor(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTagElement("display");
        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : -1;
    }

    public static Optional<IItemHandlerModifiable> getBagItems(ItemStack... stacks) {
        ArrayList<IItemHandlerModifiable> handlers = new ArrayList<>();

        for (ItemStack stack : stacks) {
            if (stack.getItem() instanceof BagItemBase) {
                stack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {
                    if (itemHandler instanceof IItemHandlerModifiable modifiableHandler) {
                        handlers.add(modifiableHandler);
                    }
                });
            }
        }

        return handlers.isEmpty() ? Optional.empty() : Optional.of(new CombinedInvWrapper(handlers.toArray(new IItemHandlerModifiable[0])));
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(this.getItemExtension());
    }

    @OnlyIn(Dist.CLIENT)
    protected abstract RenderArmorItemExtension<?> getItemExtension();

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
                    items = new ItemStackHandler(getContainerSize()) {
                        @Override
                        protected void onContentsChanged(int slot) {
                            super.onContentsChanged(slot);
                            ArmorItemBase.saveItemsData(this, stack);
                        }
                    };
                    if (stack.hasTag() && stack.getOrCreateTag().contains("Items")) {
                        items.deserializeNBT(stack.getOrCreateTag().getCompound("Items"));
                    }
                }
                return items;
            }
        };
    }
}