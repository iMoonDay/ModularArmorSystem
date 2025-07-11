package com.imoonday.modulararmor.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BulletPlateItem extends Item implements Installable {

    public BulletPlateItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canInstallOn(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof PlaceCarrierItem || item instanceof TacVestItem;
    }

    @Override
    public Class<? extends Installable> getBaseType() {
        return BulletPlateItem.class;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemBySlot(EquipmentSlot.CHEST);
        Item item = stack.getItem();
        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (item instanceof ArmorItemBase armorItem && armorItem.hasPartSlots()) {
            if (pLevel.isClientSide) {
                return InteractionResultHolder.success(itemInHand);
            }

            if (armorItem.tryInstallPart(stack, itemInHand.copyAndClear())) {
                return InteractionResultHolder.consume(itemInHand);
            }
        }

        return InteractionResultHolder.pass(itemInHand);
    }
}
