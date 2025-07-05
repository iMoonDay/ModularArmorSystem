package com.imoonday.modulararmor.util;

import com.tacz.guns.config.util.HeadShotAABBConfigRead;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class EntityUtil {

    @Nullable
    public static boolean isHeadshot(EntityHitResult hitResult) {
        Vec3 hitPos = hitResult.getLocation();
        Entity entity = hitResult.getEntity();
        ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        // 有配置的调用配置
        if (entityId != null) {
            AABB aabb = HeadShotAABBConfigRead.getAABB(entityId);
            if (aabb != null) {
                return aabb.contains(hitPos);
            }
        }
        // 没有配置的默认给一个
        float eyeHeight = entity.getEyeHeight();
        return (eyeHeight - 0.25) < hitPos.y && hitPos.y < (eyeHeight + 0.25);
    }
}
