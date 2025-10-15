package org.mechaRp.mecharp.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.mechaRp.mecharp.screen.BankTerminalScreen;
import org.mechaRp.mecharp.registry.ModScreenHandlers;

public class MecharpClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.BANK_TERMINAL, BankTerminalScreen::new);
    }
}