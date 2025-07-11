package com.imoonday.modulararmor.mixin;

import com.imoonday.modulararmor.util.EntityUtil;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @Inject(method = "hasEnoughFoodToStartSprinting", at = @At("RETURN"), cancellable = true)
    private void canStartSprinting(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            LocalPlayer player = (LocalPlayer) (Object) this;
            if (EntityUtil.shouldStopSprinting(player)) {
                cir.setReturnValue(false);
            }
        }
    }
}
