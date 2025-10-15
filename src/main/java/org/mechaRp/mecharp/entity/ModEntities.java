package org.mechaRp.mecharp.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.boss.LeshyBoss;

public class ModEntities {
    private static final Identifier LESHY_BOSS_ID = Identifier.of(Mecharp.MOD_ID, "leshy_boss");

    public static final EntityType<LeshyBoss> LESHY_BOSS = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Mecharp.MOD_ID, "leshy_boss"),
            EntityType.Builder.create(LeshyBoss::new, SpawnGroup.MONSTER)
                    .dimensions(3f, 4.5f)
                    .maxTrackingRange(8)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, LESHY_BOSS_ID)
    ));

    public static void registerModEntities() {
        Mecharp.LOGGER.info("Registering Mod Entities for " + Mecharp.MOD_ID);

    }
}