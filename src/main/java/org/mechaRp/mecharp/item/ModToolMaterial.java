package org.mechaRp.mecharp.item;

import net.minecraft.item.ToolMaterial;
import org.mechaRp.mecharp.config.AsbestitConfig;
import org.mechaRp.mecharp.config.MagmariumConfig;
import org.mechaRp.mecharp.config.PalladiumConfig;
import org.mechaRp.mecharp.registry.ModTags;

public class ModToolMaterial {
    public static final ToolMaterial PALLADIUM = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_PALLADIUM_TOOL, PalladiumConfig.durabilityPalladium, PalladiumConfig.speedPalladiumTier,
            PalladiumConfig.attackDamageBonusPalladiumTier, PalladiumConfig.enchantmentValuePalladiumTier, ModTags.Items.PALLADIUM_REPAIRS);

    public static final ToolMaterial MAGMARIUM = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_MAGMARIUM_TOOL, MagmariumConfig.durabilityMagmarium, MagmariumConfig.speedMagmariumTier,
            MagmariumConfig.attackDamageBonusMagmariumTier, MagmariumConfig.enchantmentValueMagmariumTier, ModTags.Items.MAGMARIUM_REPAIRS);

    public static final ToolMaterial ASBESTIT = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_ASBESTIT_TOOL, AsbestitConfig.durabilityAsbesit, AsbestitConfig.speedAsbesitTier,
            AsbestitConfig.attackDamageBonusAsbesitTier, AsbestitConfig.enchantmentValueAsbesitTier, ModTags.Items.ASBESTIT_REPAIRS);
}
