package org.mechaRp.mecharp.boss.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import org.mechaRp.mecharp.boss.BossConfig;
import org.mechaRp.mecharp.boss.LeshyBoss;
import org.mechaRp.mecharp.entity.ModEntities;
import org.mechaRp.mecharp.registry.YourEntities;

public class BossSpawnGenerator {

    public static void addBossSpawns() {
        // Проверяем, включен ли спавн боссов в мире через конфиг
        if (!BossConfig.isWorldGenSpawnEnabled()) {
            return;
        }

        // Добавляем спавн Лешего в лесных биомах с весом из конфига
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(
                        BiomeKeys.FOREST,
                        BiomeKeys.DARK_FOREST,
                        BiomeKeys.BIRCH_FOREST,
                        BiomeKeys.OLD_GROWTH_BIRCH_FOREST
                ),
                SpawnGroup.MONSTER,
                ModEntities.LESHY_BOSS,
                BossConfig.getLeshySpawnWeight(), // вес спавна из конфига
                1, // минимальное количество
                1  // максимальное количество
        );

        // Настройка ограничений спавна - используем SpawnLocationTypes.ON_GROUND
        SpawnRestriction.register(
                ModEntities.LESHY_BOSS,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                LeshyBoss::canSpawnInDark
        );
    }
}