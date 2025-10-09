package org.mechaRp.mecharp.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import org.mechaRp.mecharp.Mecharp;
import org.slf4j.Logger;

public class ModOreGeneration {
    private static final Logger LOGGER = Mecharp.LOGGER;

    public static void generateOres() {
        LOGGER.info("[MechaRP] === Запуск генерации руд ===");

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.BRONZE_ORE_PLACED
        );
        LOGGER.info("[MechaRP] Руда BRONZE добавлена в биомы!");

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.SILVER_ORE_PLACED
        );
        LOGGER.info("[MechaRP] Руда SILVER добавлена в биомы!");

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.PLATINUM_ORE_PLACED
        );
        LOGGER.info("[MechaRP] Руда PLATINUM добавлена в биомы!");

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.PALLADIUM_ORE_PLACED
        );
        LOGGER.info("[MechaRP] Руда PALLADIUM добавлена в биомы!");
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.MAGMARIUM_ORE_PLACED
        );
        LOGGER.info("[MechaRP] Руда MAGMARIUM добавлена в биомы!");

        LOGGER.info("[MechaRP] === Генерация руд завершена ===");
    }
}
