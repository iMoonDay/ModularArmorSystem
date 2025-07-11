package com.imoonday.modulararmor.item;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ArmorItemBase extends ArmorItem implements Modular, DyeableLeatherItem {

    protected final int partSlots;

    public ArmorItemBase(String name, Type type, int partSlots, Properties properties) {
        super(new EmptyArmorMaterial(name), type, properties);
        this.partSlots = partSlots;
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

    public static ItemStackHandler getItems(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ITEM_HANDLER)
                    .filter(ItemStackHandler.class::isInstance)
                    .map(ItemStackHandler.class::cast)
                    .orElse(new ItemStackHandler(0));
    }

    @Override
    public boolean installPart(ItemStack stack, ItemStack part, int slot) {
        return stack.getCapability(ForgeCapabilities.ITEM_HANDLER)
                    .lazyMap(itemHandler -> {
                        boolean installed = installPart(itemHandler, part, slot);
                        saveItemsData(itemHandler, stack);
                        return installed;
                    })
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
                    .lazyMap(itemHandler -> {
                        ItemStack uninstalled = uninstallPart(itemHandler, slot);
                        saveItemsData(itemHandler, stack);
                        return uninstalled;
                    })
                    .orElse(ItemStack.EMPTY);
    }

    protected ItemStack uninstallPart(IItemHandler itemHandler, int slot) {
        if (slot < 0 || slot >= itemHandler.getSlots()) {
            return ItemStack.EMPTY;
        }

        return itemHandler.extractItem(slot, itemHandler.getSlotLimit(slot), false);
    }

    public static void saveItemsData(IItemHandler itemHandler, ItemStack stack) {
        if (itemHandler instanceof INBTSerializable<?> serializable) {
            stack.getOrCreateTag().put("Items", serializable.serializeNBT());
        }
    }

    public boolean hasPartSlots() {
        return partSlots > 0;
    }

    @Override
    public int getColor(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTagElement("display");
        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : -1;
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilityProvider() {

            private ItemStackHandler items;

            @Override
            public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                if (cap == ForgeCapabilities.ITEM_HANDLER && hasPartSlots()) {
                    return LazyOptional.of(this::getItems).cast();
                }
                return LazyOptional.empty();
            }

            private ItemStackHandler getItems() {
                if (items == null) {
                    items = new ItemStackHandler(partSlots) {
                        @Override
                        public boolean isItemValid(int slot, @NotNull ItemStack itemStack) {
                            if (!canInstall(stack, itemStack)) {
                                return false;
                            }

                            Class<? extends Installable> baseType = itemStack.getItem() instanceof Installable installable ? installable.getBaseType() : null;

                            int slots = getSlots();
                            for (int i = 0; i < slots; i++) {
                                if (i == slot) {
                                    continue;
                                }
                                ItemStack stackInSlot = getStackInSlot(i);
                                if (ItemStack.isSameItem(itemStack, stackInSlot) || Installable.isSameBaseType(stackInSlot, baseType)) {
                                    return false;
                                }
                            }

                            return super.isItemValid(slot, itemStack);
                        }

                        @Override
                        protected void onContentsChanged(int slot) {
                            super.onContentsChanged(slot);
                            saveItemsData(this, stack);
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

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(this.getItemExtension());
    }

    @OnlyIn(Dist.CLIENT)
    protected abstract RenderArmorItemExtension<?> getItemExtension();

    public static class EmptyArmorMaterial implements ArmorMaterial {

        private final String name;

        public EmptyArmorMaterial(String name) {
            this.name = name;
        }

        @Override
        public int getDurabilityForType(Type pType) {
            return 0;
        }

        @Override
        public int getDefenseForType(Type pType) {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    }
}
