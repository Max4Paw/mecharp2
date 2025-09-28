package org.mechaRp.init;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MRPGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelsGen::new);
        pack.addProvider(BlockTagsGen::new);
        pack.addProvider(LootTableProvider::new);
        pack.addProvider(FabricDocsReferenceRecipeProvider::new);
        pack.addProvider(FabricDocsReferenceItemTagProvider::new);
    }

}