package org.mechaRp.mecharp.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.mechaRp.init.entity.screen.BankTerminalScreen;
import org.mechaRp.init.entity.screen.BankTerminalScreenHandler;
import org.mechaRp.init.entity.screen.BankTerminalScreenHandlers;

public class MecharpClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.<BankTerminalScreenHandler, BankTerminalScreen>register(
                BankTerminalScreenHandlers.BANK_TERMINAL_SCREEN_HANDLER,
                (handler, inventory, title) -> new BankTerminalScreen(handler, inventory, title)
        );
    }
}