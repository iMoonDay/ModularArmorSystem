package com.imoonday.modulararmor;

import com.imoonday.modulararmor.client.ModKeys;
import com.imoonday.modulararmor.client.model.*;
import com.imoonday.modulararmor.client.screen.VestCraftTableScreen;
import com.imoonday.modulararmor.client.screen.VestStorageScreen;
import com.imoonday.modulararmor.init.ModBlocks;
import com.imoonday.modulararmor.init.ModItems;
import com.imoonday.modulararmor.init.ModMenus;
import com.imoonday.modulararmor.network.Network;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
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
        ModMenus.MENU_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        Network.init();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                ModKeys.handleInput();
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                MenuScreens.register(ModMenus.VEST_STORAGE.get(), VestStorageScreen::new);
                MenuScreens.register(ModMenus.VEST_CRAFTTABLE.get(), VestCraftTableScreen::new);
            });
        }

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event) {
            ModKeys.register(event);
        }

        @SubscribeEvent
        public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
            Item[] items = ModItems.ITEMS.getEntries().stream()
                                         .filter(item -> item.get() instanceof DyeableLeatherItem)
                                         .map(RegistryObject::get)
                                         .toArray(Item[]::new);
            event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), items);
        }

        @SubscribeEvent
        public static void registerRenderLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(KevlarHelmetModel.LAYER_LOCATION, KevlarHelmetModel::createBodyLayer);
            event.registerLayerDefinition(KevlarChestplateModel.LAYER_LOCATION, KevlarChestplateModel::createBodyLayer);
            event.registerLayerDefinition(TacHelmetModel.LAYER_LOCATION, TacHelmetModel::createBodyLayer);
            event.registerLayerDefinition(TacVestModel.LAYER_LOCATION, TacVestModel::createBodyLayer);
            event.registerLayerDefinition(HighcutHelmetModel.LAYER_LOCATION, HighcutHelmetModel::createBodyLayer);
            event.registerLayerDefinition(PlaceCarrierModel.LAYER_LOCATION, PlaceCarrierModel::createBodyLayer);
            event.registerLayerDefinition(SmallBagModel.LAYER_LOCATION, SmallBagModel::createBodyLayer);
            event.registerLayerDefinition(LargeBagModel.LAYER_LOCATION, LargeBagModel::createBodyLayer);
            event.registerLayerDefinition(BackBagModel.LAYER_LOCATION, BackBagModel::createBodyLayer);
        }
    }
}
