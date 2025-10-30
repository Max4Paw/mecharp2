package org.mechaRp.mecharp.item.custom.magmarium;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Unit;
import org.mechaRp.mecharp.config.MagmariumConfig;

import org.mechaRp.mecharp.item.custom.base.Paxel;
import org.mechaRp.mecharp.registry.ModTags;

import java.util.function.Consumer;

public class MagmariumPaxel extends Paxel {
    private static Settings createSettings(Settings base, boolean unbreakable, int durability) {
        base.maxDamage(durability).fireproof();
        if (unbreakable) {
            base.component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
        }
        return base;
    }

    public MagmariumPaxel(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, ModTags.Blocks.MAGMARIUM_PAXEL_MINEABLE, attackDamage, attackSpeed,
                createSettings(settings, MagmariumConfig.unbreakableMagmarium, MagmariumConfig.durabilityMagmarium));
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().formatted(Formatting.DARK_AQUA);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent tooltip, Consumer<Text> textConsumer, TooltipType options) {
        if(Screen.hasShiftDown()) {
            if (MagmariumConfig.unbreakableMagmarium) {
                textConsumer.accept(Text.translatable("tooltip.mecharp.unbreakble.tooltip").formatted(Formatting.DARK_AQUA));
            }
            textConsumer.accept(Text.translatable("tooltip.mecharp.immunetofire.tooltip").formatted(Formatting.DARK_AQUA));
        } else {
            textConsumer.accept(Text.translatable("tooltip.mecharp.pressshiftformoreinfo.tooltip").formatted(Formatting.DARK_AQUA));
        }
    }
}