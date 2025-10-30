package org.mechaRp.mecharp.util.map;

import net.minecraft.item.Item;
import org.mechaRp.mecharp.config.MagmariumConfig;
import org.mechaRp.mecharp.config.PalladiumConfig;
import org.mechaRp.mecharp.item.ModItems;

import java.util.Map;

public class RadiusMap {
    public static final Map<Item, Integer> HAMMERS_RADIUS = Map.of(
            ModItems.PALLADIUM_HAMMER, PalladiumConfig.radiusPalladiumHammer,
            ModItems.MAGMARIUM_HAMMER, MagmariumConfig.radiusMagmariumHammer
    );
    public static final Map<Item, Integer> PALLADIUM_HAMMER_RADIUS = Map.of(
            ModItems.PALLADIUM_HAMMER, PalladiumConfig.radiusPalladiumHammer
    );
    public static final Map<Item, Integer> MAGMARIUM_HAMMER_RADIUS = Map.of(
            ModItems.MAGMARIUM_HAMMER, MagmariumConfig.radiusMagmariumHammer
    );

}
