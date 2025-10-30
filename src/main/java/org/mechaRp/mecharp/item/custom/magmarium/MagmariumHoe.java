package org.mechaRp.mecharp.item.custom.magmarium;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Unit;
import org.mechaRp.mecharp.config.MagmariumConfig;

import java.util.function.Consumer;

public class MagmariumHoe extends HoeItem {
    private static Item.Settings createSettings(Item.Settings base, boolean unbreakable, int durability) {
        base.maxDamage(durability).fireproof();
        if (unbreakable) {
            base.component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
        }
        return base;
    }

    public MagmariumHoe(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(material, attackDamage, attackSpeed,
                createSettings(settings, MagmariumConfig.unbreakableMagmarium, MagmariumConfig.durabilityMagmarium));
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().formatted(Formatting.DARK_AQUA);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent tooltip, Consumer<Text> textConsumer, TooltipType options) {
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