package org.mechaRp.mecharp.world;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.mechaRp.mecharp.Mecharp;

public class ModFeatures {
    public static final Feature<LeshyAltarFeatureConfig> LESHY_ALTAR_FEATURE =
            registerFeature("leshy_altar", new LeshyAltarFeature(LeshyAltarFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
        return Registry.register(Registries.FEATURE, Identifier.of(Mecharp.MOD_ID, name), feature);
    }

    public static void initialize() {
        // Инициализация фич (вызывается в основном классе мода)
        Mecharp.LOGGER.info("Registering Mod Features for " + Mecharp.MOD_ID);
    }
}
