package org.mechaRp.mecharp.world.gen;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import org.mechaRp.init.BlockInit;

public class LeshyAltarGenerator {

    public static void generate(StructureWorldAccess world, BlockPos center) {
        // Используем StructureWorldAccess вместо World для совместимости с Feature

        BlockPos pos;

        // Проверяем, что в центре действительно спавнер
        if (!world.getBlockState(center).isOf(BlockInit.LESHY_BOSS_SPAWNER)) {
            return; // Не генерируем структуру если нет спавнера
        }

        // Очистка области вокруг спавнера (не трогая сам спавнер)
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                for (int y = -2; y <= 3; y++) {
                    // Пропускаем позицию спавнера
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }
                    pos = center.add(x, y, z);
                    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                }
            }
        }

        // Остальной код генерации...
        // Установка пола
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                pos = center.add(x, -2, z);
                world.setBlockState(pos, Blocks.STONE.getDefaultState(), 3);
            }
        }

        // Создание угловых столбов
        for (int x = -3; x <= 3; x += 6) {
            for (int z = -3; z <= 3; z += 6) {
                for (int y = -1; y <= 2; y++) {
                    pos = center.add(x, y, z);
                    world.setBlockState(pos, Blocks.DARK_OAK_LOG.getDefaultState(), 3);
                }
            }
        }

        // Создание ритуального круга
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) == 2 || Math.abs(z) == 2) {
                    pos = center.add(x, -1, z);
                    world.setBlockState(pos, Blocks.STONE_BRICKS.getDefaultState(), 3);
                }
            }
        }
    }
}