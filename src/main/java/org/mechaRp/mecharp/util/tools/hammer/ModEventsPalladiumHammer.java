package org.mechaRp.mecharp.util.tools.hammer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.mechaRp.mecharp.item.custom.palladium.PalladiumHammer;
import org.mechaRp.mecharp.util.map.RadiusMap;

import java.util.HashSet;
import java.util.Set;

public class ModEventsPalladiumHammer implements PlayerBlockBreakEvents.Before {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos,
                                    BlockState state, @Nullable BlockEntity blockEntity) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return true;

        ItemStack mainHandItem = player.getMainHandStack();
        if (!(mainHandItem.getItem() instanceof PalladiumHammer hammer)) return true;
        if (HARVESTED_BLOCKS.contains(pos)) return true;

        boolean isSneaking = player.isSneaking();

        HARVESTED_BLOCKS.add(pos);

        try {
            // Используем радиус из самого молота вместо RadiusMap
            int radius = isSneaking ? 0 : RadiusMap.PALLADIUM_HAMMER_RADIUS.get(mainHandItem.getItem());

            // Используем нестатический метод для получения блоков для разрушения
            for (BlockPos targetPos : PalladiumHammer.getBlocksToBeDestroyed(radius, pos, serverPlayer)) {
                if (targetPos.equals(pos)) continue;
                if (HARVESTED_BLOCKS.contains(targetPos)) continue;
                if (!hammer.isCorrectForDrops(mainHandItem, world.getBlockState(targetPos))) continue;

                HARVESTED_BLOCKS.add(targetPos);
                serverPlayer.interactionManager.tryBreakBlock(targetPos);
                HARVESTED_BLOCKS.remove(targetPos);
            }
        } finally {
            HARVESTED_BLOCKS.remove(pos);
        }

        return true;
    }
}