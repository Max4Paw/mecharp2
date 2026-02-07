package org.mechaRp.mecharp;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.screen.MyWardrobeScreenHandler;

public class MyScreenHandlerType {

    public static final ScreenHandlerType<MyWardrobeScreenHandler> WARDROBE =
            new ScreenHandlerType<>(MyWardrobeScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    public static void register() {
        Registry.register(
                Registries.SCREEN_HANDLER,
                Identifier.of(Mecharp.MOD_ID, "wardrobe"),
                WARDROBE
        );
    }
}