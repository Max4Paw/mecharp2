package org.mechaRp.mecharp.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public record LeshyAltarFeatureConfig(int searchRadius) implements FeatureConfig {
    public static final Codec<LeshyAltarFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("search_radius").forGetter(LeshyAltarFeatureConfig::searchRadius)
            ).apply(instance, LeshyAltarFeatureConfig::new)
    );
}
