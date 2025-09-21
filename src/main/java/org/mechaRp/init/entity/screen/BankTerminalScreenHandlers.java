// BankTerminalScreenHandlers.java
package org.mechaRp.init.entity.screen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;

public class BankTerminalScreenHandlers {
    public static final ScreenHandlerType<BankTerminalScreenHandler> BANK_TERMINAL_SCREEN_HANDLER =
            new ScreenHandlerType<>(BankTerminalScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    public static void registerScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER,
                Identifier.of(Mecharp.MOD_ID, "bank_terminal"),
                BANK_TERMINAL_SCREEN_HANDLER);
    }
}