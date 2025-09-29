package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.item.ModItems;

public class ModelsGen extends FabricModelProvider {
    public ModelsGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.BRONZE_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.SILVER_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.PLATINUM_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.PALLADIUM_ORE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BRONZE_ORE_MATERIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_ORE_MATERIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLATINUM_ORE_MATERIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLATINUM_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PALLADIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.PALLADIUM_ORE_MATERIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PALLADIUM_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PALLADIUM_PICKAXE, Models.GENERATED);


        // Регистрация банковских карт с отдельными текстурами (цвет уже встроен в текстуру)
        itemModelGenerator.register(ModItems.BANK_CARD_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.BANK_CARD_BLUE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BANK_CARD_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BANK_CARD_YELLOW, Models.GENERATED);
    }

    @Override
    public String getName() {
        return "FabricDocsReference Model Provider";
    }
}
