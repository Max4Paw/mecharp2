package org.mechaRp.mecharp.item.custom.palladium;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Unit;
import org.mechaRp.mecharp.config.PalladiumConfig;

import java.util.function.Consumer;

public class PalladiumPickaxe extends Item {
    private static TagKey<Block> pickaxeMineable;

    public PalladiumPickaxe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(computeSettings(material, BlockTags.PICKAXE_MINEABLE, settings, attackDamage, attackSpeed, PalladiumConfig.unbreakablePalladium, PalladiumConfig.durabilityPalladium));
    }

    private static Settings computeSettings(ToolMaterial material, TagKey<Block> pickaxeMineable, Settings settings, float attackDamage, float attackSpeed, boolean unbreakable, int durability) {
        PalladiumPickaxe.pickaxeMineable = pickaxeMineable;
        settings.pickaxe(wrapMaterial(material, material.durability()), attackDamage, attackSpeed).maxDamage(durability);
        if (unbreakable) {
            settings.component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
        }
        return settings;
    }

    private static ToolMaterial wrapMaterial(ToolMaterial toolMaterial, int durability) {
        return new ToolMaterial(
                toolMaterial.incorrectBlocksForDrops(),
                durability,
                toolMaterial.speed(),
                toolMaterial.attackDamageBonus(),
                toolMaterial.enchantmentValue(),
                toolMaterial.repairItems()
        );
    }
    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().formatted(Formatting.DARK_AQUA);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        if(Screen.hasShiftDown()) {
            textConsumer.accept(Text.translatable("tooltip.mecharp.unbreakble.tooltip").formatted(Formatting.DARK_AQUA));
            textConsumer.accept(Text.translatable("tooltip.mecharp.immunetofire.tooltip").formatted(Formatting.DARK_AQUA));
        } else {
            textConsumer.accept(Text.translatable("tooltip.mecharp.pressshiftformoreinfo.tooltip").formatted(Formatting.DARK_AQUA));
        }
    }


}