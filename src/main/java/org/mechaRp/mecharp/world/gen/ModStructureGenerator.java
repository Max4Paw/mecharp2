package org.mechaRp.mecharp.world.gen;


import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import net.minecraft.world.World;


public class ModStructureGenerator {

    public static void generateBossArena(ServerWorld world, BlockPos center) {
        LeshyAltarGenerator.generate(world, center.up(2)); // Поднимаем на 2 блока чтобы структура генерировалась правильно
    }
}