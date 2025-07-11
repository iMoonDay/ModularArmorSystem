package com.imoonday.modulararmor.init;

import com.imoonday.modulararmor.ModularArmorSystem;
import com.imoonday.modulararmor.client.screen.container.VestCraftTableMenu;
import com.imoonday.modulararmor.client.screen.container.VestStorageMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ModularArmorSystem.MODID);

    public static final RegistryObject<MenuType<VestStorageMenu>> VEST_STORAGE = MENU_TYPES.register("vest_storage", () -> IForgeMenuType.create(VestStorageMenu::new));
    public static final RegistryObject<MenuType<VestCraftTableMenu>> VEST_CRAFTTABLE = MENU_TYPES.register("vest_crafttable", () -> IForgeMenuType.create((windowId, inv, data) -> new VestCraftTableMenu(windowId, inv)));
}
