package org.mechaRp.mecharp.client.renderer;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.mechaRp.mecharp.block.entity.MyWardrobeBlockEntity;
import org.mechaRp.mecharp.client.MecharpClient;
import org.mechaRp.mecharp.models.WardrobeModel;

public class WardrobeBlockEntityRenderer implements BlockEntityRenderer<MyWardrobeBlockEntity> {
    private final WardrobeModel model;

    public WardrobeBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.model = new WardrobeModel(ctx.getLayerModelPart(MecharpClient.WARDROBE_LAYER));
    }

    @Override
    public void render(MyWardrobeBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d offset) {
        matrices.push();
        matrices.translate(0.5, 2, 0.5);
        matrices.scale(1.001f, 1.001f, 1.001f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(0));

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                RenderLayer.getEntityCutout(MecharpClient.WARDROBE_TEXTURE));

        // Временно закомментируем, пока не реализованы методы
        // if (entity != null) {
        //     float doorAngle = entity.getDoorAngle(tickDelta);
        //     model.setDoorRotation(doorAngle);
        // }

        // Предполагаем, что метод render в WardrobeModel принимает 5 аргументов
        model.render(matrices, vertexConsumer, light, overlay, -1);
        matrices.pop();
    }
}