package org.mechaRp.mecharp.item.custom.palladium;


import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Unit;
import org.mechaRp.mecharp.config.PalladiumConfig;
import org.mechaRp.mecharp.item.custom.base.Paxel;
import org.mechaRp.mecharp.registry.ModTags;

import java.util.List;
import java.util.function.Consumer;


public class PalladiumPaxel extends Paxel {
    private static Settings createSettings(Settings base, boolean unbreakable, int durability) {
        base.maxDamage(durability).fireproof();
        if (unbreakable) {
            base.component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
        }
        return base;
    }

    public PalladiumPaxel(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, ModTags.Blocks.PALLADIUM_PAXEL_MINEABLE, attackDamage, attackSpeed,
                createSettings(settings, PalladiumConfig.unbreakablePalladium, PalladiumConfig.durabilityPalladium));
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().formatted(Formatting.DARK_AQUA);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent tooltip, Consumer<Text> textConsumer, TooltipType options) {
        if(Screen.hasShiftDown()) {
            if (PalladiumConfig.unbreakablePalladium) {
                textConsumer.accept(Text.translatable("tooltip.mecharp.unbreakble.tooltip").formatted(Formatting.DARK_AQUA));
            }
            textConsumer.accept(Text.translatable("tooltip.mecharp.immunetofire.tooltip").formatted(Formatting.DARK_AQUA));
        } else {
            textConsumer.accept(Text.translatable("tooltip.mecharp.pressshiftformoreinfo.tooltip").formatted(Formatting.DARK_AQUA));
        }
    }
}