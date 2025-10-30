package org.mechaRp.mecharp.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.mechaRp.mecharp.screen.BankTerminalScreen;
import org.mechaRp.mecharp.registry.ModScreenHandlers;
import org.mechaRp.mecharp.util.tools.hammer.HammerOverlayRenderer;
import org.mechaRp.mecharp.util.tooltip.TooltipItem;

public class MecharpClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HammerOverlayRenderer.init();

        HandledScreens.register(ModScreenHandlers.BANK_TERMINAL, BankTerminalScreen::new);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list
        ) -> {
            if(itemStack.getItem() instanceof TooltipItem tooltipItem) {
                tooltipItem.appendClientTooltip(itemStack, new TooltipItem.TooltipAccept(list));
            }
        });
    }
}