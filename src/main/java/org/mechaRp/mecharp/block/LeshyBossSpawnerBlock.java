package org.mechaRp.mecharp.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.mechaRp.mecharp.boss.LeshyBoss;
import org.mechaRp.mecharp.entity.ModEntities;
import org.mechaRp.mecharp.item.ModItems;
import org.mechaRp.mecharp.world.gen.ModStructureGenerator;

public class LeshyBossSpawnerBlock extends Block {
    private static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);

    public LeshyBossSpawnerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos,
                                 PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }

        ItemStack stack = player.getMainHandStack();

        // ОТЛАДОЧНАЯ ИНФОРМАЦИЯ
        System.out.println("=== DEBUG SPAWNER ACTIVATION ===");
        System.out.println("Item in hand: " + stack.getItem().toString());
        System.out.println("Item name: " + stack.getName().getString());
        System.out.println("Expected item: " + ModItems.A_FRAGMENT_OF_THE_EARTH.toString());
        System.out.println("Is correct item: " + stack.isOf(ModItems.A_FRAGMENT_OF_THE_EARTH));
        System.out.println("Stack empty: " + stack.isEmpty());
        System.out.println("===============================");

        // ПРАВИЛЬНАЯ ПРОВЕРКА
        if (stack.isEmpty() || !stack.isOf(ModItems.A_FRAGMENT_OF_THE_EARTH)) {
            player.sendMessage(Text.literal("Для активации алтаря Лешего нужен Фрагмент Земли").formatted(Formatting.RED), true);
            return ActionResult.FAIL;
        }

        if (!isValidStructure(world, pos)) {
            player.sendMessage(Text.literal("Алтарь Лешего не завершен. Нужно построить ритуальную структуру").formatted(Formatting.RED), true);
            return ActionResult.FAIL;
        }

        if (!canSpawnBoss(world, pos)) {
            player.sendMessage(Text.literal("Леший еще не готов к призыву").formatted(Formatting.YELLOW), true);
            return ActionResult.FAIL;
        }

        // Активация спавнера
        activateSpawner(world, pos, player);

        if (!player.isCreative()) {
            stack.decrement(1);
        }

        player.sendMessage(Text.literal("Алтарь активирован! Леший пробуждается!").formatted(Formatting.DARK_GREEN), true);
        return ActionResult.SUCCESS;
    }

    private boolean isValidStructure(World world, BlockPos center) {
        BlockPos checkPos;

        // Проверка угловых столбов (например, из темного дуба)
        for (int x = -3; x <= 3; x += 6) {
            for (int z = -3; z <= 3; z += 6) {
                for (int y = 1; y <= 3; y++) {
                    checkPos = center.add(x, y, z);
                    if (!isValidPillarBlock(world.getBlockState(checkPos))) {
                        return false;
                    }
                }
            }
        }

        // Проверка ритуального круга (например, из каменных кирпичей)
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) == 2 || Math.abs(z) == 2) {
                    checkPos = center.add(x, -1, z);
                    if (!isValidCircleBlock(world.getBlockState(checkPos))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean isValidPillarBlock(BlockState state) {
        // Столбы из темного дуба или аналогичного материала
        return state.isOf(Blocks.DARK_OAK_LOG) ||
                state.isOf(Blocks.SPRUCE_LOG) ||
                state.isOf(Blocks.MOSSY_COBBLESTONE);
    }

    private boolean isValidCircleBlock(BlockState state) {
        // Круг из каменных кирпичей или аналогичного материала
        return state.isOf(Blocks.STONE_BRICKS) ||
                state.isOf(Blocks.MOSSY_STONE_BRICKS) ||
                state.isOf(Blocks.COBBLESTONE);
    }

    private boolean canSpawnBoss(World world, BlockPos pos) {
        // Здесь можно добавить проверку кулдауна
        // Например, проверка последнего времени спавна в NBT мира
        return true;
    }

    private void activateSpawner(World world, BlockPos pos, PlayerEntity player) {
        // Эффекты при активации
        world.syncWorldEvent(2001, pos, Block.getRawIdFromState(Blocks.MOSSY_COBBLESTONE.getDefaultState()));

        // Спавн босса - для 1.21.8
        LeshyBoss boss = new LeshyBoss(ModEntities.LESHY_BOSS, world);

        BlockPos spawnPos = pos.up(2);
        boss.refreshPositionAndAngles(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5,
                world.random.nextFloat() * 360.0F, 0.0F);

        // Настройка босса
        boss.setCustomName(Text.literal("Леший").formatted(Formatting.DARK_GREEN));
        boss.setCustomNameVisible(true);

        world.spawnEntity(boss);

        player.sendMessage(Text.literal("Леший пробуждается!").formatted(Formatting.DARK_GREEN), true);

        // Установка кулдауна (опционально)
        setSpawnCooldown(world, pos);
    }

    private void setSpawnCooldown(World world, BlockPos pos) {
        // Сохранение времени последнего спавна в NBT мира
    }

    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);

        if (!world.isClient()) {
            // Приводим World к ServerWorld для генерации
            if (world instanceof net.minecraft.server.world.ServerWorld serverWorld) {
                ModStructureGenerator.generateBossArena(serverWorld, pos);
            }
        }
    }
}