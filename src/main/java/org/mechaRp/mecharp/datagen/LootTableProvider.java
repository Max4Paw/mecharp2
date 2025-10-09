package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class LootTableProvider extends FabricBlockLootTableProvider {
    public LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BlockInit.BRONZE_ORE_BLOCK, oreDrops(BlockInit.BRONZE_ORE_BLOCK, ModItems.BRONZE_ORE_MATERIAL));
        addDrop(BlockInit.SILVER_ORE_BLOCK, oreDrops(BlockInit.SILVER_ORE_BLOCK, ModItems.SILVER_ORE_MATERIAL));
        addDrop(BlockInit.PLATINUM_ORE_BLOCK, oreDrops(BlockInit.PLATINUM_ORE_BLOCK, ModItems.PLATINUM_ORE_MATERIAL));
        addDrop(BlockInit.PALLADIUM_ORE_BLOCK, oreDrops(BlockInit.PALLADIUM_ORE_BLOCK, ModItems.PALLADIUM_ORE_MATERIAL));
        addDrop(BlockInit.DEEPSLATE_PALLADIUM_ORE_BLOCK, oreDrops(BlockInit.DEEPSLATE_PALLADIUM_ORE_BLOCK, ModItems.PALLADIUM_ORE_MATERIAL));
        addDrop(BlockInit.DEEPSLATE_PLATINUM_ORE_BLOCK, oreDrops(BlockInit.DEEPSLATE_PLATINUM_ORE_BLOCK, ModItems.PLATINUM_ORE_MATERIAL));
        addDrop(BlockInit.DEEPSLATE_SILVER_ORE_BLOCK, oreDrops(BlockInit.DEEPSLATE_SILVER_ORE_BLOCK, ModItems.SILVER_ORE_MATERIAL));
        addDrop(BlockInit.DEEPSLATE_BRONZE_ORE_BLOCK, oreDrops(BlockInit.DEEPSLATE_BRONZE_ORE_BLOCK, ModItems.BRONZE_ORE_MATERIAL));
        addDrop(BlockInit.MAGMARIUM_ORE_BLOCK, oreDrops(BlockInit.MAGMARIUM_ORE_BLOCK, ModItems.MAGMARIUM_ORE_MATERIAL));

    }
}
