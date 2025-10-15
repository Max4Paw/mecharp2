package org.mechaRp.mecharp.boss;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.mechaRp.mecharp.entity.ModEntities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BossSpawnManager {
    private static final Map<String, Function<ServerWorld, BaseBoss>> BOSS_REGISTRY = new HashMap<>();

    static {
        BOSS_REGISTRY.put("leshy", (world) -> new LeshyBoss(ModEntities.LESHY_BOSS, world));
    }

    public static boolean spawnBoss(ServerWorld world, BlockPos pos, String bossId) {
        // Проверяем, включен ли босс в конфиге
        if (!BossConfig.isBossEnabled(bossId)) {
            return false;
        }

        Function<ServerWorld, BaseBoss> bossFunction = BOSS_REGISTRY.get(bossId);
        if (bossFunction == null) return false;

        BossRespawnManager respawnManager = BossRespawnManager.getServerState(world.getServer());
        BaseBoss boss = bossFunction.apply(world);

        if (!BossConfig.isTestMode() && !respawnManager.canSpawnBoss(bossId, boss.getRespawnTimeMs())) {
            return false;
        }

        boss.refreshPositionAndAngles(pos, 0, 0);
        world.spawnEntity(boss);
        return true;
    }

    public static Map<String, Function<ServerWorld, BaseBoss>> getBossRegistry() {
        return BOSS_REGISTRY;
    }
}