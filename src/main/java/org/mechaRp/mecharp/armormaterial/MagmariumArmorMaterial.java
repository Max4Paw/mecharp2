package org.mechaRp.mecharp.armormaterial;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.item.ModItems;

import java.util.Map;

public class MagmariumArmorMaterial {
    public static final int BASE_DURABILITY = 1500;
    public static final RegistryKey<EquipmentAsset> MGMARIUM_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(Mecharp.MOD_ID, "magmariun"));

    private static TagKey<Item> REPAIRS_MAGMARIUM_ARMOR;
    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    EquipmentType.HELMET, 50,
                    EquipmentType.CHESTPLATE, 80,
                    EquipmentType.LEGGINGS, 65,
                    EquipmentType.BOOTS, 54
            ),
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            7.0F,
            0.6F,
            REPAIRS_MAGMARIUM_ARMOR,
            MGMARIUM_ARMOR_MATERIAL_KEY
    );
}
