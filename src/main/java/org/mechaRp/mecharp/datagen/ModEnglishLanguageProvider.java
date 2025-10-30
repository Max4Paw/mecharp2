package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModEnglishLanguageProvider extends FabricLanguageProvider {
    public ModEnglishLanguageProvider(FabricDataOutput dataOutput,  CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput,"en_us" , registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
//        translationBuilder.add(ModItems.MAGMARIUM_BOOTS,"magmarium_boots");
//        translationBuilder.add(ModItems.MAGMARIUM_CHESTPLATE,"magmarium_chestplate");
//        translationBuilder.add(ModItems.MAGMARIUM_LEGGINGS,"magmarium_leggings");
//        translationBuilder.add(ModItems.MAGMARIUM_HELMET,"magmarium_helmet");
//        translationBuilder.add(ModItems.MAGMARIUM_ORE_MATERIAL,"magmarium_ore_material");
//        translationBuilder.add(ModItems.MAGMARIUM,"magmarium");
//        translationBuilder.add(ModItems.PALLADIUM_SWORD,"magmarium_boots");
//        translationBuilder.add(ModItems.PALLADIUM_HAMMER,"magmarium_boots");
//        translationBuilder.add(ModItems.PALLADIUM_PAXEL,"magmarium_boots");
//        translationBuilder.add(BlockInit.MAGMARIUM_ORE_BLOCK,"magmarium_ore");
//        //translationBuilder.add(ModItems.MAGMARIUM_BOOTS,"magmarium_boots");


    }
}
