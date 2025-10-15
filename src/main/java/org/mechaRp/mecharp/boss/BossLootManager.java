package org.mechaRp.mecharp.boss;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class BossLootManager {
    public static void onBossDeath(BaseBoss boss, DamageSource source) {
        if (!boss.getWorld().isClient()) {
            ServerWorld world = (ServerWorld) boss.getWorld();
            MinecraftServer server = world.getServer();

            // Записываем убийство для респавна
            BossRespawnManager respawnManager = BossRespawnManager.getServerState(server);
            respawnManager.recordBossKill(boss.getBossId());

            // Выдаем уникальный дроп
            for (ItemStack drop : boss.getUniqueDrops()) {
                ItemEntity itemEntity = new ItemEntity(world,
                        boss.getX(), boss.getY(), boss.getZ(), drop);
                world.spawnEntity(itemEntity);
            }

            // Выдаем опыт вручную (например, 100 опыта за босса)
            int experience = 100;
            ExperienceOrbEntity xpOrb = new ExperienceOrbEntity(world, boss.getX(), boss.getY(), boss.getZ(), experience);
            world.spawnEntity(xpOrb);

            // Оповещение сервера
            server.getPlayerManager().broadcast(
                    Text.literal("⚔️ ").append(boss.getBossName())
                            .append(" был побежден!"), false);
        }
    }
}