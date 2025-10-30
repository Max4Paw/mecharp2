package org.mechaRp.mecharp.item.custom.palladium;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.util.Unit;
import org.mechaRp.mecharp.config.PalladiumConfig;

public class PalladiumArmor extends Item {
    public PalladiumArmor(ArmorMaterial material, EquipmentType type, Settings settings) {
        super(computeSettings(material, type, PalladiumConfig.unbreakablePalladium, PalladiumConfig.durabilityPalladium, settings));
    }
    private static Settings computeSettings(ArmorMaterial material, EquipmentType type, boolean unbreakable, int durability, Settings settings) {
        settings.armor(material, EquipmentType.BODY)
                .attributeModifiers(material.createAttributeModifiers(type))
                .enchantable(material.enchantmentValue())
                .component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(type.getEquipmentSlot()).equipSound(material.equipSound()).model(material.assetId()).build())
                .maxDamage(durability);
        if (unbreakable) {
            settings.component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
        }
        return settings;
    }
}
