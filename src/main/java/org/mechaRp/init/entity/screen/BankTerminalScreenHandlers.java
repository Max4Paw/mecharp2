package org.mechaRp.init.entity.screen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureFlags; // импорт для FeatureFlags
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;

public class BankTerminalScreenHandlers {
    public static final ScreenHandlerType<BankTerminalScreenHandler> BANK_TERMINAL_SCREEN_HANDLER =
            new ScreenHandlerType<>(BankTerminalScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    private static final RegistryKey<ScreenHandlerType<?>> BANK_TERMINAL_SCREEN_HANDLER_KEY =
            RegistryKey.of(RegistryKeys.SCREEN_HANDLER, Identifier.of(Mecharp.MOD_ID, "bank_terminal"));

    public static void registerScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER,
                BANK_TERMINAL_SCREEN_HANDLER_KEY.getValue(),
                BANK_TERMINAL_SCREEN_HANDLER);
    }
}
