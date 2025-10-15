package org.mechaRp.mecharp.boss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.ServerWorldAccess;
import org.mechaRp.mecharp.entity.ModEntities;
import org.mechaRp.mecharp.item.ModItems;

import java.util.ArrayList;
import java.util.List;

public class LeshyBoss extends BaseBoss {
    private static final String BOSS_ID = "leshy";
    private static final long RESPAWN_TIME = 7 * 24 * 60 * 60 * 1000L; // 7 дней

    public LeshyBoss(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world, BOSS_ID, RESPAWN_TIME);
    }

    @Override
    public void performSpecialAbility() {
        // Способность лешего - призывать деревья и корни
        World world = getWorld();
        if (!world.isClient()) {
            // Создание корней вокруг игроков
            world.getEntitiesByClass(PlayerEntity.class, getBoundingBox().expand(10),
                    player -> true).forEach(player -> {
                BlockPos playerPos = player.getBlockPos();
                // Здесь можно добавить создание специальных блоков
                // world.setBlockState(playerPos.down(), ModBlocks.ROOTED_EARTH.getDefaultState());
            });
        }
    }

    @Override
    public List<ItemStack> getUniqueDrops() {
        List<ItemStack> drops = new ArrayList<>();

        // Предметы для посоха земли
        drops.add(new ItemStack(ModItems.ANCIENT_ROOT, 1));
        drops.add(new ItemStack(ModItems.EARTH_CRYSTAL, 2));
        drops.add(new ItemStack(Items.OAK_LOG, 8));

        // Рандомный дроп посоха (10% шанс)
        if (random.nextFloat() < 0.1f) {
            drops.add(new ItemStack(ModItems.EARTH_STAFF, 1));
        }

        return drops;
    }

    @Override
    public Text getBossName() {
        return Text.literal("Древний Леший").formatted(Formatting.DARK_GREEN);
    }

    @Override
    public void tick() {
        super.tick();
        // Каждые 5 секунд используем способность
        if (this.age % 100 == 0 && BossConfig.areBossAbilitiesEnabled()) {
            performSpecialAbility();
        }
    }

    // Можно переопределить другие методы для настройки поведения
    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false; // Босс не должен деспавниться
    }



    // Убраны методы shouldDropLoot() и shouldDropXp(), так как они больше не существуют в новых версиях
    // Вместо этого управление дропом и опытом осуществляется через другие механизмы
}