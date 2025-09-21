package org.mechaRp.mecharp.item;

import net.minecraft.block.Block;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import org.mechaRp.mecharp.util.ModTags;

import java.util.function.Supplier;


public enum ModToolMaterials /*implements /*ToolMaterial*/ {
    PALLADIUM(ModTags.Blocks.INCORRECT_FOR_PALLADIUM_TOOL,
            2500, // Увеличенная прочность
            9.0F, // Скорость копания
            4.0F, // Урон атаки
            15,   // Зачаровываемость
            () -> Ingredient.ofItems(ModItems.PALLADIUM_INGOT));

    private final int durability;
    private final float miningSpeedMultiplier;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(TagKey<Block> inverseTag, int durability, float miningSpeedMultiplier,
                     float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.durability = durability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    public int getDurability() {
        return this.durability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeedMultiplier;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return 4; // Незеритовый уровень
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    // Метод для обратной совместимости
    public TagKey<Block> getInverseTag() {
        return null;
    }
}