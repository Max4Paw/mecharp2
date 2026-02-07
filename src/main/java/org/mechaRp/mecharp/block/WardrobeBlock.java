package org.mechaRp.mecharp.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.mechaRp.mecharp.block.entity.MyWardrobeBlockEntity;

public class WardrobeBlock extends BlockWithEntity {
    public static final MapCodec<WardrobeBlock> CODEC = createCodec(WardrobeBlock::new);
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty OPEN = Properties.OPEN;

    private static final VoxelShape SHAPE = VoxelShapes.fullCube();

    public WardrobeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
                .with(HALF, DoubleBlockHalf.LOWER)
                .with(OPEN, false));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();

        // Заменим getTopY() на getHeight()
        if (blockPos.getY() < world.getHeight() - 1 &&
                world.getBlockState(blockPos.up()).canReplace(ctx)) {
            return this.getDefaultState()
                    .with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                    .with(HALF, DoubleBlockHalf.LOWER)
                    .with(OPEN, false);
        }

        return null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        if (!world.isClient) {
            BlockPos upperPos = pos.up();
            world.setBlockState(upperPos, state.with(HALF, DoubleBlockHalf.UPPER), 3);
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);

        if (direction.getAxis() == Direction.Axis.Y) {
            if (doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.UP && !neighborState.isOf(this)) {
                return Blocks.AIR.getDefaultState();
            }
            if (doubleBlockHalf == DoubleBlockHalf.UPPER && direction == Direction.DOWN && !neighborState.isOf(this)) {
                return Blocks.AIR.getDefaultState();
            }
        }

        // Исправим вызов super, передав все аргументы
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos checkPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos : pos.down();
        BlockPos checkPosUp = state.get(HALF) == DoubleBlockHalf.UPPER ? pos : pos.up();

        return world.getBlockState(checkPos).isAir() && world.getBlockState(checkPosUp).isAir();
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            BlockPos otherPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
            BlockState otherState = world.getBlockState(otherPos);

            if (otherState.isOf(this)) {
                world.setBlockState(otherPos, Blocks.AIR.getDefaultState(), 3);
                world.syncWorldEvent(player, 2001, otherPos, Block.getRawIdFromState(otherState));
            }
        }

        super.onBreak(world, pos, state, player);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        // Находим нижнюю часть шкафа для доступа к инвентарю
        BlockPos wardrobePos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos : pos.down();
        BlockEntity blockEntity = world.getBlockEntity(wardrobePos);

        // Проверяем, что blockEntity является NamedScreenHandlerFactory (это уже реализовано в MyWardrobeBlockEntity)
        if (blockEntity instanceof MyWardrobeBlockEntity wardrobeEntity) {
            // Открываем интерфейс инвентаря
            player.openHandledScreen(wardrobeEntity);

            // Анимируем открытие/закрытие двери
            boolean isOpen = state.get(OPEN);
            world.setBlockState(pos, state.with(OPEN, !isOpen), 3);

            // Синхронизируем состояние для обеих частей
            BlockPos otherPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
            BlockState otherState = world.getBlockState(otherPos);
            if (otherState.isOf(this)) {
                world.setBlockState(otherPos, otherState.with(OPEN, !isOpen), 3);
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, HALF, OPEN);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        // Создаем BlockEntity только для нижней части
        if (state.get(HALF) == DoubleBlockHalf.LOWER) {
            return new MyWardrobeBlockEntity(pos, state);
        }
        return null;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}