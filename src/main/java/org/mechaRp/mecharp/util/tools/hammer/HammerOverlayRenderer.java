package org.mechaRp.mecharp.util.tools.hammer;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.mechaRp.mecharp.item.custom.base.Hammer;
import org.mechaRp.mecharp.item.custom.magmarium.MagmariumHammer;
import org.mechaRp.mecharp.item.custom.palladium.PalladiumHammer;
import org.mechaRp.mecharp.util.map.RadiusMap;

import java.util.HashSet;
import java.util.Set;

public class HammerOverlayRenderer {
    public static void init() {
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();

            if (client.world == null || client.player == null) return;

            ItemStack heldItem = client.player.getMainHandStack();
            if (!(heldItem.getItem() instanceof Hammer hammer)) return;

            if (!(client.crosshairTarget instanceof BlockHitResult blockHit) || blockHit.getType() != HitResult.Type.BLOCK) return;

            Direction side = blockHit.getSide();
            BlockPos origin = blockHit.getBlockPos();
            int range;
            if (client.player.isSneaking()) {
                range = 0;
            } else {
                range = RadiusMap.HAMMERS_RADIUS.get(heldItem.getItem());
            }

            if (!client.world.getBlockState(origin).isIn(BlockTags.PICKAXE_MINEABLE)) return;

            Vec3d cameraPos = context.camera().getPos();
            VertexConsumerProvider.Immediate buffers = client.getBufferBuilders().getEntityVertexConsumers();
            VertexConsumer buffer = buffers.getBuffer(RenderLayer.getLines());
            MatrixStack matrices = context.matrixStack();

            // Определяем, является ли молот 3D (Magmarium) или 2D (Palladium)
            boolean is3D = hammer instanceof MagmariumHammer;

            Set<BlockPos> blocksToRender = new HashSet<>();

            if (is3D) {
                // Для 3D молота - полный куб
                blocksToRender = getBlocksToBeDestroyed3D(range, origin, side);
            } else {
                // Для 2D молота - плоскость
                blocksToRender = getBlocksToBeDestroyed2D(range, origin, side);
            }

            // Рисуем сетку для каждого блока
            for (BlockPos pos : blocksToRender) {
                if (!client.world.getBlockState(pos).isIn(BlockTags.PICKAXE_MINEABLE)) continue;

                Box box = new Box(pos).offset(-cameraPos.x, -cameraPos.y, -cameraPos.z).expand(0.002f);

                // Разные цвета для разных типов молотов
                if (is3D) {
                    drawBox(matrices, buffer, box, 1f, 0.5f, 0f, 1f); // Оранжевый для Magmarium
                } else {
                    drawBox(matrices, buffer, box, 0f, 1f, 1f, 1f); // Голубой для Palladium
                }
            }

            buffers.draw();
        });
    }

    /**
     * Метод для получения блоков для 3D разрушения (полный куб)
     */
    private static Set<BlockPos> getBlocksToBeDestroyed3D(int range, BlockPos initialBlockPos, Direction side) {
        Set<BlockPos> positions = new HashSet<>();
        Direction direction = side.getOpposite();
        Direction[] planeDirections = getPlaneDirections(side);

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                for (int k = 0; k <= range * 2; k++) {
                    BlockPos pos = initialBlockPos.offset(planeDirections[0], i)
                            .offset(planeDirections[1], j)
                            .offset(direction, k);
                    positions.add(pos);
                }
            }
        }
        return positions;
    }

    private static Direction[] getPlaneDirections(Direction side) {
        return switch (side) {
            case UP, DOWN -> new Direction[]{Direction.EAST, Direction.NORTH};
            case NORTH, SOUTH -> new Direction[]{Direction.EAST, Direction.UP};
            case EAST, WEST -> new Direction[]{Direction.NORTH, Direction.UP};
        };
    }

    /**
     * Метод для получения блоков для 2D разрушения (плоскость)
     */
    private static Set<BlockPos> getBlocksToBeDestroyed2D(int range, BlockPos initialBlockPos, Direction side) {
        Set<BlockPos> positions = new HashSet<>();

        if (side == Direction.DOWN || side == Direction.UP) {
            for (int x = -range; x <= range; x++) {
                for (int z = -range; z <= range; z++) {
                    positions.add(initialBlockPos.add(x, 0, z));
                }
            }
        } else if (side == Direction.NORTH || side == Direction.SOUTH) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(initialBlockPos.add(x, y, 0));
                }
            }
        } else if (side == Direction.EAST || side == Direction.WEST) {
            for (int z = -range; z <= range; z++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(initialBlockPos.add(0, y, z));
                }
            }
        }

        return positions;
    }

    /**
     * Рисует контур куба
     */
    private static void drawBox(MatrixStack matrices, VertexConsumer buffer, Box box, float r, float g, float b, float a) {
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        float[][] corners = {
                {(float) box.minX, (float) box.minY, (float) box.minZ},
                {(float) box.maxX, (float) box.minY, (float) box.minZ},
                {(float) box.minX, (float) box.maxY, (float) box.minZ},
                {(float) box.maxX, (float) box.maxY, (float) box.minZ},
                {(float) box.minX, (float) box.minY, (float) box.maxZ},
                {(float) box.maxX, (float) box.minY, (float) box.maxZ},
                {(float) box.minX, (float) box.maxY, (float) box.maxZ},
                {(float) box.maxX, (float) box.maxY, (float) box.maxZ}
        };

        int[][] edges = {
                {0,1},{1,3},{3,2},{2,0},  // нижняя грань
                {4,5},{5,7},{7,6},{6,4},  // верхняя грань
                {0,4},{1,5},{2,6},{3,7}   // вертикальные ребра
        };

        for (int[] e : edges) {
            float[] p1 = corners[e[0]], p2 = corners[e[1]];
            buffer.vertex(matrix, p1[0], p1[1], p1[2]).color(r, g, b, a).light(0xF000F0).normal(1.0F, 0.0F, 0.0F);
            buffer.vertex(matrix, p2[0], p2[1], p2[2]).color(r, g, b, a).light(0xF000F0).normal(1.0F, 0.0F, 0.0F);
        }
    }

    /**
     * Дополнительный метод для рисования сетки внутри куба (опционально)
     */
    private static void drawGrid(MatrixStack matrices, VertexConsumer buffer, Set<BlockPos> blocks, Vec3d cameraPos, float r, float g, float b, float a) {
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        for (BlockPos pos : blocks) {
            // Рисуем дополнительные линии для создания сетки
            Box box = new Box(pos).offset(-cameraPos.x, -cameraPos.y, -cameraPos.z).expand(0.001f);

            // Центральные линии для создания сетки
            float centerX = (float) ((box.minX + box.maxX) / 2);
            float centerY = (float) ((box.minY + box.maxY) / 2);
            float centerZ = (float) ((box.minZ + box.maxZ) / 2);

            // Горизонтальные линии
            buffer.vertex(matrix, (float) box.minX, centerY, (float) box.minZ).color(r, g, b, a * 0.5f).light(0xF000F0).normal(1.0F, 0.0F, 0.0F);
            buffer.vertex(matrix, (float) box.maxX, centerY, (float) box.minZ).color(r, g, b, a * 0.5f).light(0xF000F0).normal(1.0F, 0.0F, 0.0F);

            buffer.vertex(matrix, (float) box.minX, centerY, (float) box.maxZ).color(r, g, b, a * 0.5f).light(0xF000F0).normal(1.0F, 0.0F, 0.0F);
            buffer.vertex(matrix, (float) box.maxX, centerY, (float) box.maxZ).color(r, g, b, a * 0.5f).light(0xF000F0).normal(1.0F, 0.0F, 0.0F);

            // Вертикальные линии
            buffer.vertex(matrix, centerX, (float) box.minY, (float) box.minZ).color(r, g, b, a * 0.5f).light(0xF000F0).normal(1.0F, 0.0F, 0.0F);
            buffer.vertex(matrix, centerX, (float) box.maxY, (float) box.minZ).color(r, g, b, a * 0.5f).light(0xF000F0).normal(1.0F, 0.0F, 0.0F);
        }
    }
}