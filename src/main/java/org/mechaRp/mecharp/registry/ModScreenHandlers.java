package org.mechaRp.mecharp.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.screen.BankTerminalScreenHandler;

public class ModScreenHandlers {
    public static final ScreenHandlerType<BankTerminalScreenHandler> BANK_TERMINAL =
            new ScreenHandlerType<>(BankTerminalScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    public static void registerScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Mecharp.MOD_ID, "bank_terminal"), BANK_TERMINAL);
    }
}