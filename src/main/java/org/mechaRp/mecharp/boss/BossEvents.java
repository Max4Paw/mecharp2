package org.mechaRp.mecharp.boss;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class BossEvents {

    public static void register() {
        // Обработка смерти боссов
        ServerLivingEntityEvents.AFTER_DEATH.register((LivingEntity entity, DamageSource damageSource) -> {
            if (entity instanceof BaseBoss boss) {
                BossLootManager.onBossDeath(boss, damageSource);
            }
        });
    }
}