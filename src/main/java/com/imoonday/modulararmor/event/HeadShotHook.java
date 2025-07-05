package com.imoonday.modulararmor.event;

import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class HeadShotHook {

    private static final ThreadLocal<List<Entity>> HEADSHOT_PROJECTILES = new ThreadLocal<>();

    public static void addHeadshotProjectile(Entity projectile) {
        List<Entity> list = HEADSHOT_PROJECTILES.get();
        if (list == null) {
            HEADSHOT_PROJECTILES.set(new ArrayList<>());
        }

        HEADSHOT_PROJECTILES.get().add(projectile);
    }

    public static void removeHeadshotProjectile(Entity projectile) {
        List<Entity> list = HEADSHOT_PROJECTILES.get();
        if (list == null) {
            return;
        }

        list.remove(projectile);
        if (list.isEmpty()) {
            HEADSHOT_PROJECTILES.remove();
        }
    }

    public static boolean isHeadshotProjectile(Entity projectile) {
        List<Entity> list = HEADSHOT_PROJECTILES.get();
        if (list == null) {
            return false;
        }

        return list.contains(projectile);
    }
}
