package com.imoonday.modulararmor.client;

import com.imoonday.modulararmor.network.OpenVestStorageC2SRequest;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class ModKeys {

    public static final Lazy<KeyMapping> VEST_STORAGE = Lazy.of(() -> new KeyMapping("key.modulararmor.vest_storage", GLFW.GLFW_KEY_V, "key.categories.modulararmor"));

    public static void register(RegisterKeyMappingsEvent event) {
        event.register(VEST_STORAGE.get());
    }

    public static void handleInput() {
        while (VEST_STORAGE.get().consumeClick()) {
            new OpenVestStorageC2SRequest().sendToServer();
        }
    }
}
