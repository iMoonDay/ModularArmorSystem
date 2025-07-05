package com.imoonday.modulararmor.init;

import com.imoonday.modulararmor.ModularArmorSystem;
import com.imoonday.modulararmor.item.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModularArmorSystem.MODID);

    public static final RegistryObject<Item> KEVLAR = ITEMS.register("kevlar", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NYLON = ITEMS.register("nylon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<BlockItem> VEST_CRAFTTABLE = ITEMS.register("vest_crafttable", () -> new BlockItem(ModBlocks.VEST_CRAFTTABLE.get(), new Item.Properties()));

    public static final RegistryObject<KevlarHelmetItem> KEVLAR_HELMET = ITEMS.register("kevlar_helmet", () -> new KevlarHelmetItem(new Item.Properties().durability(256)));
    public static final RegistryObject<KevlarChestplateItem> KEVLAR_CHESTPLATE = ITEMS.register("kevlar_chestplate", () -> new KevlarChestplateItem(new Item.Properties().durability(256)));
    public static final RegistryObject<KevlarLinerItem> KEVLAR_LINER = ITEMS.register("kevlar_liner", () -> new KevlarLinerItem(new Item.Properties().durability(256)));

    public static final RegistryObject<BulletPlateItem> BULLET_PLATE = ITEMS.register("bullet_plate", () -> new BulletPlateItem(new Item.Properties().durability(32)));

    public static final RegistryObject<TacHelmetItem> TAC_HELMET = ITEMS.register("tac_helmet", () -> new TacHelmetItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<TacVestItem> TAC_VEST = ITEMS.register("tac_vest", () -> new TacVestItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<HighcutHelmetItem> HIGHCUT_HELMET = ITEMS.register("highcut_helmet", () -> new HighcutHelmetItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<PlaceCarrierItem> PLACE_CARRIER = ITEMS.register("place_carrier", () -> new PlaceCarrierItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<SmallBagItem> SMALL_BAG = ITEMS.register("small_bag", () -> new SmallBagItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<LargeBagItem> LARGE_BAG = ITEMS.register("large_bag", () -> new LargeBagItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<BackBagItem> BACK_BAG = ITEMS.register("back_bag", () -> new BackBagItem(new Item.Properties().stacksTo(1)));
}
