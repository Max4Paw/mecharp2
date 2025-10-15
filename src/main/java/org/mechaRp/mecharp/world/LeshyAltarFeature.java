package org.mechaRp.mecharp.world;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.world.gen.LeshyAltarGenerator;

public class LeshyAltarFeature extends Feature<LeshyAltarFeatureConfig> {

    public LeshyAltarFeature(Codec<LeshyAltarFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<LeshyAltarFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        // Проверяем, можно ли сгенерировать здесь
        if (!world.isAir(origin) || !world.getBlockState(origin.down()).isOpaque()) {
            return false;
        }

        // Устанавливаем спавнер босса
        world.setBlockState(origin, BlockInit.LESHY_BOSS_SPAWNER.getDefaultState(), 3);

        // Генерируем структуру вокруг спавнера
        LeshyAltarGenerator.generate(world.toServerWorld(), origin);

        return true;
    }
}