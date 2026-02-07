// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
//public class OakWardorobemodel extends EntityModel<Entity> {
//	private final ModelPart wardrobe;
//	private final ModelPart walls;
//	private final ModelPart shelf;
//	private final ModelPart door;
//	private final ModelPart loops;
//	public model(ModelPart root) {
//		this.wardrobe = root.getChild("wardrobe");
//		this.walls = this.wardrobe.getChild("walls");
//		this.shelf = this.walls.getChild("shelf");
//		this.door = this.wardrobe.getChild("door");
//		this.loops = this.wardrobe.getChild("loops");
//	}
//	public static TexturedModelData getTexturedModelData() {
//		ModelData modelData = new ModelData();
//		ModelPartData modelPartData = modelData.getRoot();
//		ModelPartData wardrobe = modelPartData.addChild("wardrobe", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
//
//		ModelPartData walls = wardrobe.addChild("walls", ModelPartBuilder.create().uv(0, 0).cuboid(7.0F, -32.0F, -7.0F, 1.0F, 32.0F, 15.0F, new Dilation(0.0F))
//		.uv(32, 0).cuboid(-8.0F, -32.0F, -7.0F, 1.0F, 32.0F, 15.0F, new Dilation(0.0F))
//		.uv(34, 77).cuboid(-7.0F, -32.0F, 7.0F, 14.0F, 32.0F, 1.0F, new Dilation(0.0F))
//		.uv(0, 47).cuboid(-7.0F, -1.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F))
//		.uv(56, 47).cuboid(-7.0F, -32.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//		ModelPartData shelf = walls.addChild("shelf", ModelPartBuilder.create().uv(0, 62).cuboid(-7.0F, -7.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F))
//		.uv(56, 62).cuboid(-7.0F, -13.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F))
//		.uv(64, 0).cuboid(-7.0F, -19.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F))
//		.uv(64, 15).cuboid(-7.0F, -25.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//		ModelPartData door = wardrobe.addChild("door", ModelPartBuilder.create().uv(0, 77).cuboid(-8.0F, -32.0F, -8.0F, 16.0F, 32.0F, 1.0F, new Dilation(0.0F))
//		.uv(64, 38).cuboid(6.0F, -17.0F, -9.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//		ModelPartData loops = wardrobe.addChild("loops", ModelPartBuilder.create().uv(64, 30).cuboid(-9.0F, -25.0F, -8.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
//		.uv(64, 34).cuboid(-9.0F, -9.0F, -8.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//		return TexturedModelData.of(modelData, 128, 128);
//	}
//	@Override
//	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//	}
//	@Override
//	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
//		wardrobe.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
//	}
//}