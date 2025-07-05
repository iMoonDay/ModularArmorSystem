package com.imoonday.modulararmor.init;

import com.imoonday.modulararmor.ModularArmorSystem;
import com.imoonday.modulararmor.block.VestCraftTableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModularArmorSystem.MODID);

    public static final RegistryObject<VestCraftTableBlock> VEST_CRAFTTABLE = BLOCKS.register("vest_crafttable", () -> new VestCraftTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
}
