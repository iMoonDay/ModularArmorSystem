package com.imoonday.modulararmor.event;

import com.imoonday.modulararmor.ModularArmorSystem;
import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularArmorSystem.MODID)
public class ForgeEvents {

    @SubscribeEvent
    public static void onEntityHurtByGun(EntityHurtByGunEvent event) {

    }
}
