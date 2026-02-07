package org.mechaRp.mecharp.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.client.renderer.WardrobeBlockEntityRenderer;
import org.mechaRp.mecharp.models.WardrobeModel;
import org.mechaRp.mecharp.registry.ModBlockEntities;
import org.mechaRp.mecharp.screen.BankTerminalScreen;
import org.mechaRp.mecharp.registry.ModScreenHandlers;
import org.mechaRp.mecharp.screen.MyWardrobeScreen;
import org.mechaRp.mecharp.util.tools.hammer.HammerOverlayRenderer;
import org.mechaRp.mecharp.util.tooltip.TooltipItem;

public class MecharpClient implements ClientModInitializer {
    public static final EntityModelLayer WARDROBE_LAYER = new EntityModelLayer(Identifier.of("mecharp", "wardrobe"), "main");
    public static final Identifier WARDROBE_TEXTURE = Identifier.of("mecharp", "textures/entity/wardrobe.png");
    @Override
    public void onInitializeClient() {
        HammerOverlayRenderer.init();

        HandledScreens.register(ModScreenHandlers.BANK_TERMINAL, BankTerminalScreen::new);
        HandledScreens.register(ModScreenHandlers.WARDROBE, MyWardrobeScreen::new);
        // Регистрируем слой модели
        EntityModelLayerRegistry.registerModelLayer(WARDROBE_LAYER, WardrobeModel::getTexturedModelData);

        // Регистрируем рендерер для блок-сущности
        BlockEntityRendererRegistry.register(ModBlockEntities.WARDROBE_BLOCK_ENTITY, WardrobeBlockEntityRenderer::new);


        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list
        ) -> {
            if(itemStack.getItem() instanceof TooltipItem tooltipItem) {
                tooltipItem.appendClientTooltip(itemStack, new TooltipItem.TooltipAccept(list));
            }
        });
        System.out.println("Mecharp Client initialized!");
    }



}