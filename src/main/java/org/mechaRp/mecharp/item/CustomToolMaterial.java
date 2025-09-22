package org.mechaRp.mecharp.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

public class CustomToolMaterial {
    public static final ToolMaterial CUSTOM_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2031,    // durability
            9.0F,    // miningSpeed
            2.0F,    // attackDamage
            15,      // enchantability
            ItemTags.NETHERITE_TOOL_MATERIALS
    );

    public static void registerToolMaterial() {
        // Правильная регистрация ToolMaterial
    }
}