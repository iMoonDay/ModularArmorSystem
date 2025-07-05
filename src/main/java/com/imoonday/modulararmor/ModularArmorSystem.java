package com.imoonday.modulararmor;

import com.imoonday.modulararmor.init.ModBlocks;
import com.imoonday.modulararmor.init.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(ModularArmorSystem.MODID)
public class ModularArmorSystem {

    public static final String MODID = "modulararmor";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT).title(Component.translatable("itemGroup.modularArmor")).icon(() -> ModItems.KEVLAR.get().getDefaultInstance()).displayItems((parameters, output) -> {
        ModItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
    }).build());

    public ModularArmorSystem() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

        @SubscribeEvent
        public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
            Item[] items = ModItems.ITEMS.getEntries().stream()
                                         .filter(item -> item.get() instanceof DyeableArmorItem)
                                         .map(RegistryObject::get)
                                         .toArray(Item[]::new);
            event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableArmorItem) stack.getItem()).getColor(stack), items);
        }
    }
}
