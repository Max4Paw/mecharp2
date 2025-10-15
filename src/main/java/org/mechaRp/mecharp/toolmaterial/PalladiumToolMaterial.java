package org.mechaRp.mecharp.toolmaterial;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.item.ModItems;



public final class PalladiumToolMaterial {
    public static final TagKey<Block> NEEDS_PALLADIUM_TOOL =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of(Mecharp.MOD_ID, "needs_palladium_tool"));

    // TagKey для предметов, которые можно использовать для починки (например, mecharp:palladium)
    public static final TagKey<Item> REPAIR_PALLADIUM =
            TagKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_ingot"));

    // Сам материал
    public static final ToolMaterial PALLADIUM = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,  // блоки, которые кирка не может добывать
            5031,   // прочность
            9.0f,   // скорость копания
            4.0f,   // бонус урона
            22,     // зачаровываемость
            REPAIR_PALLADIUM // предмет для починки
    );

    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.PALLADIUM_INGOT);
    }
}
