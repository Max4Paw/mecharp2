package org.mechaRp.mecharp.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.YOffset;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.Mecharp;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> BRONZE_ORE_PLACED = registerKey("bronze_ore_placed");
    public static final RegistryKey<PlacedFeature> SILVER_ORE_PLACED = registerKey("silver_ore_placed");
    public static final RegistryKey<PlacedFeature> PLATINUM_ORE_PLACED = registerKey("platinum_ore_placed");
    public static final RegistryKey<PlacedFeature> PALLADIUM_ORE_PLACED = registerKey("palladium_ore_placed");
    public static final RegistryKey<PlacedFeature> MAGMARIUM_ORE_PLACED = registerKey("magmarium_ore_placed");




    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configured = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        context.register(BRONZE_ORE_PLACED, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.BRONZE_ORE),
                orePlacement(20, -16, 112)
        ));

        context.register(SILVER_ORE_PLACED, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.SILVER_ORE),
                orePlacement(15, -32, 64)
        ));

        context.register(PLATINUM_ORE_PLACED, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.PLATINUM_ORE),
                orePlacement(10, -48, 32)
        ));

        context.register(PALLADIUM_ORE_PLACED, new PlacedFeature(
                configured.getOrThrow(ModConfiguredFeatures.PALLADIUM_ORE),
                orePlacement(6, -64, 16)
        ));
        context.register(
                MAGMARIUM_ORE_PLACED,
                new PlacedFeature(
                        configured.getOrThrow(ModConfiguredFeatures.MAGMARIUM_ORE),
                        orePlacement(6, 22, 119)
                )
        );
    }

    private static List<PlacementModifier> orePlacement(int count, int minY, int maxY) {
        return List.of(
                CountPlacementModifier.of(count),
                SquarePlacementModifier.of(),
                // правильно: два аргумента YOffset, а не один UniformHeightProvider
                HeightRangePlacementModifier.uniform(YOffset.fixed(minY), YOffset.fixed(maxY))
        );
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Mecharp.MOD_ID, name));
    }
}
