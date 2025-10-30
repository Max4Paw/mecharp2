package org.mechaRp.mecharp.item.custom.abestit;

import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Unit;
import org.mechaRp.mecharp.config.AsbestitConfig;
import org.mechaRp.mecharp.config.MagmariumConfig;
import org.mechaRp.mecharp.item.custom.magmarium.MagmariumPickaxe;

public class AsbestitPickaxe extends Item{
    private static TagKey<Block> pickaxeMineable;

    public AsbestitPickaxe(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
        super(computeSettings(material, BlockTags.PICKAXE_MINEABLE, settings, attackDamage, attackSpeed, AsbestitConfig.unbreakableAsbesit, AsbestitConfig.durabilityAsbesit));
    }

    private static Item.Settings computeSettings(ToolMaterial material, TagKey<Block> pickaxeMineable, Item.Settings settings, float attackDamage, float attackSpeed, boolean unbreakable, int durability) {
        AsbestitPickaxe.pickaxeMineable = pickaxeMineable;
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

}
