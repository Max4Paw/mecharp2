package org.mechaRp.mecharp.toolmaterial;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.item.ModItems;

public class PalladiumSwordMaterial {

    // Тег для починки
    public static final TagKey<Item> REPAIR_PALLADIUM =
            TagKey.of(RegistryKeys.ITEM,  Identifier.of(Mecharp.MOD_ID, "palladium"));

    // ✅ Создаём ToolMaterial корректно для 1.21.8
    public static final ToolMaterial PALLADIUM = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, // ⚙️ исправлено
            10031,      // durability / прочность
            17.0f,      // attack damage bonus / бонус урона
            1.6f,      // attack speed / скорость атаки (типичное значение для мечей)
            22,        // enchantability / зачаровываемость
            REPAIR_PALLADIUM // Предмет для починки
    );
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.PALLADIUM);
    }
}
