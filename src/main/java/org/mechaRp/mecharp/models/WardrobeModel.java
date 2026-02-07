package org.mechaRp.mecharp.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import org.mechaRp.mecharp.block.entity.MyWardrobeBlockEntity;

public class WardrobeModel {
    private final ModelPart root;
    private final ModelPart wardrobe;
    private final ModelPart body;
    private final ModelPart bone;
    private final ModelPart door;
    private final ModelPart loops;

    public WardrobeModel(ModelPart root) {
        this.root = root;
        this.wardrobe = root.getChild("wardrobe");
        this.body = this.wardrobe.getChild("body");
        this.bone = this.body.getChild("bone");
        this.door = this.wardrobe.getChild("door");
        this.loops = this.wardrobe.getChild("loops");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        // Используем ModelTransform.of() вместо ModelTransform.pivot()
        ModelPartData wardrobe = modelPartData.addChild("wardrobe", ModelPartBuilder.create(),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        ModelPartData body = wardrobe.addChild("body", ModelPartBuilder.create()
                        .uv(0, 0).cuboid(7.0F, -32.0F, -7.0F, 1.0F, 32.0F, 15.0F, new Dilation(0.0F))
                        .uv(32, 0).cuboid(-8.0F, -32.0F, -7.0F, 1.0F, 32.0F, 15.0F, new Dilation(0.0F))
                        .uv(34, 77).cuboid(-7.0F, -32.0F, 7.0F, 14.0F, 32.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 47).cuboid(-7.0F, -1.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F))
                        .uv(56, 47).cuboid(-7.0F, -32.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        ModelPartData bone = body.addChild("bone", ModelPartBuilder.create()
                        .uv(0, 62).cuboid(-7.0F, -7.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F))
                        .uv(56, 62).cuboid(-7.0F, -13.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F))
                        .uv(64, 0).cuboid(-7.0F, -19.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F))
                        .uv(64, 15).cuboid(-7.0F, -25.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        ModelPartData door = wardrobe.addChild("door", ModelPartBuilder.create()
                        .uv(0, 77).cuboid(-8.0F, -32.0F, -8.0F, 16.0F, 32.0F, 1.0F, new Dilation(0.0F))
                        .uv(64, 38).cuboid(6.0F, -17.0F, -9.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        ModelPartData loops = wardrobe.addChild("loops", ModelPartBuilder.create()
                        .uv(64, 30).cuboid(-9.0F, -25.0F, -8.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
                        .uv(64, 34).cuboid(-9.0F, -9.0F, -8.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 128, 128);
    }

    public void setAngles() {
        // Здесь может быть анимация для блока, если нужно
    }


    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        this.root.render(matrices, vertexConsumer, light, overlay, color );
    }
}