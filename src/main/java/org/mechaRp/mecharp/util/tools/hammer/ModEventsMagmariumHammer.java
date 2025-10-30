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
import org.mechaRp.mecharp.item.custom.base.Hammer;
import org.mechaRp.mecharp.item.custom.magmarium.MagmariumHammer;
import org.mechaRp.mecharp.util.map.RadiusMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModEventsMagmariumHammer implements PlayerBlockBreakEvents.Before {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos,
                                    BlockState state, @Nullable BlockEntity blockEntity) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return true;

        ItemStack mainHandItem = player.getMainHandStack();
        if (!(mainHandItem.getItem() instanceof MagmariumHammer hammer)) return true;
        if (HARVESTED_BLOCKS.contains(pos)) return true;

        boolean isSneaking = player.isSneaking();

        HARVESTED_BLOCKS.add(pos);

        try {
            int radius = isSneaking ? 0 :RadiusMap.MAGMARIUM_HAMMER_RADIUS.get(mainHandItem.getItem());

            // Явно используем 3D разрушение для MagmariumHammer
            for (BlockPos targetPos : Hammer.getBlocksToBeDestroyed3D(radius, pos, serverPlayer)) {
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

    private static List<BlockPos> getBlocksToBeDestroyed3D(int range, BlockPos initalBlockPos) {
        List<BlockPos> positions = new ArrayList<>();
        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ() + z));
                }
            }
        }
        return positions;
    }
}