package com.imoonday.modulararmor;

import com.imoonday.modulararmor.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = ModularArmorSystem.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGen {

    @SubscribeEvent
    public static void onDataGen(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
    }

    private static class ModRecipeProvider extends RecipeProvider {

        public ModRecipeProvider(PackOutput packOutput) {
            super(packOutput);
        }

        @Override
        protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.KEVLAR_HELMET.get())
                               .define('K', ModItems.KEVLAR.get())
                               .pattern("KKK")
                               .pattern("K K")
                               .unlockedBy("has_kevlar", has(ModItems.KEVLAR.get()))
                               .save(consumer);

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.KEVLAR_CHESTPLATE.get())
                               .define('K', ModItems.KEVLAR.get())
                               .define('L', Items.LEATHER_CHESTPLATE)
                               .pattern("KLK")
                               .pattern("KKK")
                               .pattern("KKK")
                               .unlockedBy("has_kevlar", has(ModItems.KEVLAR.get()))
                               .unlockedBy("has_leather_chestplate", has(Items.LEATHER_CHESTPLATE))
                               .save(consumer);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VEST_CRAFTTABLE.get())
                                  .requires(Items.CRAFTING_TABLE)
                                  .requires(Items.LEATHER_CHESTPLATE)
                                  .unlockedBy("has_crafting_table", has(Items.CRAFTING_TABLE))
                                  .unlockedBy("has_leather_chestplate", has(Items.LEATHER_CHESTPLATE))
                                  .save(consumer);

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.KEVLAR_LINER.get())
                               .define('K', ModItems.KEVLAR.get())
                               .pattern("K K")
                               .pattern("KKK")
                               .pattern("KKK")
                               .unlockedBy("has_kevlar", has(ModItems.KEVLAR.get()))
                               .save(consumer);

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SMALL_BAG.get())
                               .define('S', Tags.Items.STRING)
                               .define('N', ModItems.NYLON.get())
                               .pattern(" S ")
                               .pattern("NNN")
                               .unlockedBy("has_string", has(Tags.Items.STRING))
                               .unlockedBy("has_nylon", has(ModItems.NYLON.get()))
                               .save(consumer);

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.LARGE_BAG.get())
                               .define('S', Tags.Items.STRING)
                               .define('N', ModItems.NYLON.get())
                               .pattern(" S ")
                               .pattern("NNN")
                               .pattern("NNN")
                               .unlockedBy("has_string", has(Tags.Items.STRING))
                               .unlockedBy("has_nylon", has(ModItems.NYLON.get()))
                               .save(consumer);

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.BACK_BAG.get())
                               .define('N', ModItems.NYLON.get())
                               .define('C', Tags.Items.CHESTS)
                               .define('L', Tags.Items.LEATHER)
                               .pattern("NNN")
                               .pattern("NCN")
                               .pattern("LLL")
                               .unlockedBy("has_nylon", has(ModItems.NYLON.get()))
                               .unlockedBy("has_chest", has(Tags.Items.CHESTS))
                               .unlockedBy("has_leather", has(Tags.Items.LEATHER))
                               .save(consumer);
        }
    }
}