package org.mechaRp.mecharp.util;

import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;

import java.util.function.BiConsumer;

public class ModEquipmentAssets {
    private static final RegistryKey<? extends Registry<EquipmentAsset>> ROOT_ID = RegistryKey.ofRegistry(Identifier.of("equipment_asset"));

    public static final RegistryKey<EquipmentAsset> MAGMARIUM = id("magmarium");
   // public static final RegistryKey<EquipmentAsset> VULPUS = id("vulpus");
    public static final RegistryKey<EquipmentAsset> PALLADIUM = id("palladium");

    private static RegistryKey<EquipmentAsset> id(String name) {
        return RegistryKey.of(ROOT_ID, Identifier.of(Mecharp.MOD_ID, name));
    }

    public static void bootstrap(BiConsumer<RegistryKey<EquipmentAsset>, EquipmentModel> consumer) {
        registerAssetWithLayers(consumer, ModEquipmentAssets.MAGMARIUM, "magmarium");
       // registerAssetWithLayers(consumer, ModEquipmentAssets.VULPUS, "vulpus");
        registerAssetWithLayers(consumer, ModEquipmentAssets.PALLADIUM, "palladium");
    }

    private static void registerAssetWithLayers(BiConsumer<RegistryKey<EquipmentAsset>, EquipmentModel> consumer,
                                                RegistryKey<EquipmentAsset> asset, String name) {
        consumer.accept(asset, EquipmentModel.builder()
                .addHumanoidLayers(Identifier.of(Mecharp.MOD_ID, name))
                .addLayers(EquipmentModel.LayerType.HORSE_BODY,
                        new EquipmentModel.Layer(Identifier.of(Mecharp.MOD_ID, name)))
                .build());

    }
}
