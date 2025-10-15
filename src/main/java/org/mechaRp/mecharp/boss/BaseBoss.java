package org.mechaRp.mecharp.boss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import java.util.List;

public abstract class BaseBoss extends HostileEntity {
    private final String bossId;
    private final long respawnTimeMs;

    protected BaseBoss(EntityType<? extends HostileEntity> entityType, World world, String bossId, long respawnTimeMs) {
        super(entityType, world);
        this.bossId = bossId;
        this.respawnTimeMs = respawnTimeMs;

        // Установка здоровья на максимальное
        this.setHealth(this.getMaxHealth());
    }

    public String getBossId() {
        return bossId;
    }

    public long getRespawnTimeMs() {
        return respawnTimeMs;
    }

    public abstract void performSpecialAbility();
    public abstract List<ItemStack> getUniqueDrops();
    public abstract Text getBossName();
}