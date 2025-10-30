package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import org.mechaRp.mecharp.world.ModConfiguredFeatures;
import org.mechaRp.mecharp.world.ModPlacedFeatures;


public class MRPGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelsGen::new);
        pack.addProvider(BlockTagsGen::new);
        pack.addProvider(LootTableProvider::new);
        pack.addProvider(FabricDocsReferenceRecipeProvider::new);
        pack.addProvider(FabricDocsReferenceItemTagProvider::new);
        pack.addProvider(ModRegistryDataGenerator::new);
        pack.addProvider(ModEnglishLanguageProvider::new);
        pack.addProvider(ModRussianLanguageProvider::new);
        pack.addProvider(ModAdvancementProvider::new);
        DataGenerator.Pack secondaryPack = fabricDataGenerator.createPack();
        secondaryPack.addProvider(ModEquipmentAssetProvider::new);
    }
    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
    }

}