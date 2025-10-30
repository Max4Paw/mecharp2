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
import org.mechaRp.mecharp.item.custom.base.Hammer;
import org.mechaRp.mecharp.util.map.RadiusMap;

import java.util.function.Consumer;

public class MagmariumHammer extends Hammer {

    private static Settings createSettings(Settings base, boolean unbreakable, int durability) {
        base.maxDamage(durability).fireproof();
        if (unbreakable) {
            base.component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
        }
        return base;
    }

    public MagmariumHammer(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed,
                createSettings(settings, MagmariumConfig.unbreakableMagmarium, MagmariumConfig.durabilityMagmarium));
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().formatted(Formatting.DARK_AQUA);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent tooltip, Consumer<Text> textConsumer, TooltipType options) {
        int radius = getRadiusForHammer(stack);
        int widht = radius * 2 + 1;

        if (Screen.hasShiftDown()) {
            textConsumer.accept(Text.translatable("tooltip.mecharp.unbreakable").formatted(Formatting.DARK_AQUA));
            textConsumer.accept(Text.translatable("tooltip.mecharp.immune_to_fire").formatted(Formatting.DARK_AQUA));

            Text text = Text.translatable("tooltip.mecharp.dig_area")
                    .formatted(Formatting.DARK_AQUA)
                    .append(Text.literal(widht + "x3" + "x3").formatted(Formatting.YELLOW));

            textConsumer.accept(text);

            super.appendTooltip(stack, context, tooltip, textConsumer, options);
        } else {
            textConsumer.accept(Text.translatable("tooltip.mecharp.press_shift").formatted(Formatting.DARK_AQUA));
        }
    }

    private int getRadiusForHammer(ItemStack stack) {
        if (RadiusMap.MAGMARIUM_HAMMER_RADIUS.containsKey(stack.getItem())) {
            return RadiusMap.MAGMARIUM_HAMMER_RADIUS.get(stack.getItem());
        }
        return 0;
    }
}