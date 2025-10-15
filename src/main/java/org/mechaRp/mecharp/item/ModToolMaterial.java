package org.mechaRp.mecharp.item;

import net.minecraft.item.ToolMaterial;
import org.mechaRp.mecharp.config.PalladiumConfig;
import org.mechaRp.mecharp.registry.ModTags;

public class ModToolMaterial {
    public static final ToolMaterial PALLADIUM = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_PALLADIUM_TOOL, PalladiumConfig.durabilityPalladium, PalladiumConfig.speedPalladiumTier,
            PalladiumConfig.attackDamageBonusPalladiumTier, PalladiumConfig.enchantmentValuePalladiumTier, ModTags.Items.PALLADIUM_REPAIRS);
}
