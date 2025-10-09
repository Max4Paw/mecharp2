package org.mechaRp.mecharp.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.registry.tag.BlockTags;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.Mecharp;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> BRONZE_ORE =
            registryKey("bronze_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SILVER_ORE =
            registryKey("silver_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PLATINUM_ORE =
            registryKey("platinum_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALLADIUM_ORE =
            registryKey("palladium_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MAGMARIUM_ORE =
            RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Mecharp.MOD_ID, "magmarium_ore"));

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // Определяем RuleTest для замещения
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacebles = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);

        // Bronze
        register(context, BRONZE_ORE, Feature.ORE,
                new OreFeatureConfig(
                        List.of(
                                OreFeatureConfig.createTarget(stoneReplaceables, BlockInit.BRONZE_ORE_BLOCK.getDefaultState()),
                                OreFeatureConfig.createTarget(deepslateReplaceables, BlockInit.DEEPSLATE_BRONZE_ORE_BLOCK.getDefaultState())
                        ),
                        9
                )
        );

        // Silver
        register(context, SILVER_ORE, Feature.ORE,
                new OreFeatureConfig(
                        List.of(
                                OreFeatureConfig.createTarget(stoneReplaceables, BlockInit.SILVER_ORE_BLOCK.getDefaultState()),
                                OreFeatureConfig.createTarget(deepslateReplaceables, BlockInit.DEEPSLATE_SILVER_ORE_BLOCK.getDefaultState())
                        ),
                        8
                )
        );

        // Platinum
        register(context, PLATINUM_ORE, Feature.ORE,
                new OreFeatureConfig(
                        List.of(
                                OreFeatureConfig.createTarget(stoneReplaceables, BlockInit.PLATINUM_ORE_BLOCK.getDefaultState()),
                                OreFeatureConfig.createTarget(deepslateReplaceables, BlockInit.DEEPSLATE_PLATINUM_ORE_BLOCK.getDefaultState())
                        ),
                        6
                )
        );

        // Palladium
        register(context, PALLADIUM_ORE, Feature.ORE,
                new OreFeatureConfig(
                        List.of(
                                OreFeatureConfig.createTarget(stoneReplaceables, BlockInit.PALLADIUM_ORE_BLOCK.getDefaultState()),
                                OreFeatureConfig.createTarget(deepslateReplaceables, BlockInit.DEEPSLATE_PALLADIUM_ORE_BLOCK.getDefaultState())
                        ),
                        4
                )
        );
        register( context, MAGMARIUM_ORE,  Feature.ORE,
                new OreFeatureConfig(
                        List.of(
                                OreFeatureConfig.createTarget(netherrackReplacebles, BlockInit.MAGMARIUM_ORE_BLOCK.getDefaultState())

                        ),
                        6
                )
        );
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Mecharp.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context,
            RegistryKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
