package org.mechaRp.init;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.item.ModItems;

public class ModelsGen extends FabricModelProvider {
    public ModelsGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.BRONZE_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.SILVER_ORE_BLOCK);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BRONZE_ORE_MATERIAL, Models.GENERATED);
    }

    @Override
    public String getName() {
        return "FabricDocsReference Model Provider";
    }
}
