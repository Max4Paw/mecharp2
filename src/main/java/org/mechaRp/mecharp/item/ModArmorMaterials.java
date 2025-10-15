package org.mechaRp.mecharp.item;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import org.mechaRp.mecharp.config.MagmariumConfig;
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
}
