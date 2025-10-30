package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRussianLanguageProvider extends FabricLanguageProvider {
    public ModRussianLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "ru_ru", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
//        translationBuilder.add(ModItems.MAGMARIUM_BOOTS, "Магмариевые ботинки");
//        translationBuilder.add(ModItems.MAGMARIUM_CHESTPLATE, "Магмариевый нагрудник");
//        translationBuilder.add(ModItems.MAGMARIUM_LEGGINGS, "Магмариевые поножи");
//        translationBuilder.add(ModItems.MAGMARIUM_HELMET, "Магмариевый шлем");
//        translationBuilder.add(ModItems.MAGMARIUM_ORE_MATERIAL, "материал Магмариевой руды");
//        translationBuilder.add(ModItems.MAGMARIUM, "Магмарий");
//        translationBuilder.add(ModItems.PALLADIUM_SWORD, "Палладиевый меч");
//        translationBuilder.add(ModItems.PALLADIUM_HAMMER, "Палладиевый молот");
//        translationBuilder.add(ModItems.PALLADIUM_PAXEL, "Палладиевый киркомолот");
//        translationBuilder.add(BlockInit.MAGMARIUM_ORE_BLOCK, "Магмариевая руда");

    }
}
