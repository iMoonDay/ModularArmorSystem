package com.imoonday.modulararmor.mixin;

import com.imoonday.modulararmor.event.HeadShotHook;
import com.imoonday.modulararmor.util.EntityUtil;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Projectile.class)
public class ProjectileMixin {

    @Inject(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V"))
    public void beforeOnHitEntity(HitResult pResult, CallbackInfo ci) {
        if (EntityUtil.isHeadshot((EntityHitResult) pResult)) {
            HeadShotHook.addHeadshotProjectile((Projectile) (Object) this);
        }
    }

    @Inject(method = "onHit", at = @At("RETURN"))
    public void afterOnHitEntity(HitResult pResult, CallbackInfo ci) {
        HeadShotHook.removeHeadshotProjectile((Projectile) (Object) this);
    }
}
