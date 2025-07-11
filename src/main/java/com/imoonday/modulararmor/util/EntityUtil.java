package com.imoonday.modulararmor.util;

import com.imoonday.modulararmor.init.ModItems;
import com.imoonday.modulararmor.item.Modular;
import com.tacz.guns.config.util.HeadShotAABBConfigRead;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class EntityUtil {

    @Nullable
    public static boolean isHeadshot(EntityHitResult hitResult, Entity source) {
        Vec3 hitPos = source.position();
        Entity entity = hitResult.getEntity();
        ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());

        if (entityId != null) {
            AABB aabb = HeadShotAABBConfigRead.getAABB(entityId);
            if (aabb != null) {
                return aabb.contains(hitPos);
            }
        }

        double eyeHeight = entity.getEyeY();
        return (eyeHeight - 0.25) < hitPos.y && hitPos.y < (eyeHeight + 0.25);
    }

    public static boolean shouldStopSprinting(LivingEntity entity) {
        ItemStack stack = entity.getItemBySlot(EquipmentSlot.CHEST);
        return !stack.isEmpty() && stack.getItem() instanceof Modular modular &&
               !modular.findPart(stack, ModItems.KEVLAR_LINER.get()).isEmpty() &&
               !modular.findPart(stack, ModItems.BULLET_PLATE.get()).isEmpty();
    }
}
