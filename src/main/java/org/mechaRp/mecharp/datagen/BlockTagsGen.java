package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.registry.ModTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagsGen extends FabricTagProvider.BlockTagProvider {


    public BlockTagsGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
// PICKAXE_MINEABLE
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(BlockInit.MAGMARIUM_ORE_BLOCK)
                .add(BlockInit.BRONZE_ORE_BLOCK)
                .add(BlockInit.DEEPSLATE_BRONZE_ORE_BLOCK)
                .add(BlockInit.PALLADIUM_ORE_BLOCK)
                .add(BlockInit.PLATINUM_ORE_BLOCK)
                .add(BlockInit.DEEPSLATE_PALLADIUM_ORE_BLOCK)
                .add(BlockInit.DEEPSLATE_PLATINUM_ORE_BLOCK)
                .add(BlockInit.DEEPSLATE_SILVER_ORE_BLOCK)
                .add(BlockInit.SILVER_ORE_BLOCK)





        ;

        // NEES_PALLADIUM_TOOL
        valueLookupBuilder(ModTags.Blocks.NEEDS_PALLADIUM_TOOL)
                .add(BlockInit.MAGMARIUM_ORE_BLOCK)



        ;
        valueLookupBuilder(ModTags.Blocks.INCORRECT_FOR_PALLADIUM_TOOL)
                .setReplace(true)



        ;
        valueLookupBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .add(BlockInit.MAGMARIUM_ORE_BLOCK);
        valueLookupBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .add(BlockInit.MAGMARIUM_ORE_BLOCK);
        valueLookupBuilder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .add(BlockInit.MAGMARIUM_ORE_BLOCK);
        valueLookupBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .add(BlockInit.MAGMARIUM_ORE_BLOCK);
        valueLookupBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .add(BlockInit.MAGMARIUM_ORE_BLOCK);
        valueLookupBuilder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .add(BlockInit.MAGMARIUM_ORE_BLOCK);






    }
}
