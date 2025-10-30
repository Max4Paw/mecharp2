package org.mechaRp.mecharp.item;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import org.mechaRp.mecharp.config.MagmariumConfig;
import org.mechaRp.mecharp.config.PalladiumConfig;
import org.mechaRp.mecharp.registry.ModTags;
import org.mechaRp.mecharp.util.ModEquipmentAssets;

import java.util.EnumMap;

public class ModArmorMaterials {
    public static ArmorMaterial MAGMARIUM = new ArmorMaterial(MagmariumConfig.durabilityMagmarium, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, MagmariumConfig.protectionValueMagmariumBoots);
        map.put(EquipmentType.LEGGINGS, MagmariumConfig.protectionValueMagmariumLeggings);
        map.put(EquipmentType.CHESTPLATE, MagmariumConfig.protectionValueMagmariumChestplate);
        map.put(EquipmentType.HELMET, MagmariumConfig.protectionValueMagmariumHelmet);
    }), MagmariumConfig.enchantmentValueMagmariumArmor, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,MagmariumConfig.toughnessValueMagmariumArmor, MagmariumConfig.knockbackResistanceValueMagmariumArmor,
            ModTags.Items.MAGMARIUM_REPAIRS, ModEquipmentAssets.MAGMARIUM);


    public static ArmorMaterial PALLADIUM = new ArmorMaterial(PalladiumConfig.durabilityPalladium, Util.make(new EnumMap<>(EquipmentType.class), map -> {
            map.put(EquipmentType.BOOTS, PalladiumConfig.protectionValuePalladiumBoots);
            map.put(EquipmentType.LEGGINGS, PalladiumConfig.protectionValuePalladiumLeggings);
            map.put(EquipmentType.CHESTPLATE, PalladiumConfig.protectionValuePalladiumChestplate);
            map.put(EquipmentType.HELMET, PalladiumConfig.protectionValuePalladiumHelmet);
        }), PalladiumConfig.enchantmentValuePalladiumArmor, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,PalladiumConfig.toughnessValuePalladiumArmor, PalladiumConfig.knockbackResistanceValuePalladiumArmor,
                ModTags.Items.PALLADIUM_REPAIRS, ModEquipmentAssets.PALLADIUM);

}
