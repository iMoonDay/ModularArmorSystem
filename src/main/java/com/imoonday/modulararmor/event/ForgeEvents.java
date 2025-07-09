package com.imoonday.modulararmor.event;

import com.imoonday.modulararmor.ModularArmorSystem;
import com.imoonday.modulararmor.init.ModItems;
import com.imoonday.modulararmor.item.KevlarChestplateItem;
import com.imoonday.modulararmor.item.KevlarHelmetItem;
import com.imoonday.modulararmor.item.Modular;
import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
import com.tacz.guns.api.event.common.GunDamageSourcePart;
import com.tacz.guns.init.ModDamageTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularArmorSystem.MODID)
public class ForgeEvents {

    public static final float BULLET_PLATE_PROTECTION = 12.0F;
    public static final float KEVLAR_PROTECTION = 6.0F;

    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        if (!source.is(DamageTypes.ARROW) && !source.getMsgId().equals("armor_bypass_damage")) {
            return;
        }

        LivingEntity entity = event.getEntity();

        float amount = handleBulletPlateProtection(entity, event.getAmount());
        if (amount <= 0.0F) {
            event.setCanceled(true);
            return;
        }

        boolean headShot = HeadShotHook.isHeadshotProjectile(event.getSource().getDirectEntity());
        EquipmentSlot slot = headShot ? EquipmentSlot.HEAD : EquipmentSlot.CHEST;

        amount = handleKevlarProtection(entity, slot, amount);
        if (amount <= 0.0F) {
            event.setCanceled(true);
        } else {
            event.setAmount(amount);
        }
    }

    @SubscribeEvent
    public static void onEntityHurtByGun(EntityHurtByGunEvent.Pre event) {
        DamageSource source = event.getDamageSource(GunDamageSourcePart.NON_ARMOR_PIERCING);
        if (!source.is(ModDamageTypes.BULLET)) {
            return;
        }

        Entity hurtEntity = event.getHurtEntity();
        if (!(hurtEntity instanceof LivingEntity entity)) {
            return;
        }

        float amount = handleBulletPlateProtection(entity, event.getBaseAmount());
        if (amount <= 0.0F) {
            event.setCanceled(true);
            return;
        }

        boolean headShot = event.isHeadShot();
        EquipmentSlot slot = headShot ? EquipmentSlot.HEAD : EquipmentSlot.CHEST;

        amount = handleKevlarProtection(entity, slot, amount);
        if (amount <= 0.0F) {
            event.setCanceled(true);
        } else {
            event.setBaseAmount(amount);
        }
    }

    private static float handleKevlarProtection(LivingEntity entity, EquipmentSlot slot, float amount) {
        ItemStack slotItem = entity.getItemBySlot(slot);
        ItemStack kevlarArmor = ItemStack.EMPTY;

        if (!slotItem.isEmpty()) {
            Item item = slotItem.getItem();
            if (item instanceof KevlarHelmetItem || item instanceof KevlarChestplateItem) {
                kevlarArmor = slotItem;
            } else if (item instanceof Modular modular) {
                kevlarArmor = modular.findPart(slotItem, ModItems.KEVLAR_LINER.get());
            }
        }

        if (kevlarArmor.isEmpty()) {
            return amount;
        }

        amount -= KEVLAR_PROTECTION;
        kevlarArmor.hurtAndBreak(1, entity, (entity1) -> entity1.broadcastBreakEvent(slot));

        return amount;
    }

    private static float handleBulletPlateProtection(LivingEntity entity, float amount) {
        ItemStack bulletPlate = ItemStack.EMPTY;

        for (ItemStack stack : entity.getArmorSlots()) {
            if (stack.getItem() instanceof Modular modular) {
                bulletPlate = modular.findPart(stack, ModItems.BULLET_PLATE.get());
                if (!bulletPlate.isEmpty()) {
                    break;
                }
            }
        }

        if (bulletPlate.isEmpty()) {
            return amount;
        }

        if (amount < BULLET_PLATE_PROTECTION) {
            entity.level().playSound(null, entity, SoundEvents.SHIELD_BLOCK, entity.getSoundSource(), 1.0F, 1.0F);
        }

        amount -= BULLET_PLATE_PROTECTION;
        return amount;
    }
}
