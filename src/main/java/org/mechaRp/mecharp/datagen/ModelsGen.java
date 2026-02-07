package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.item.ModItems;
import org.mechaRp.mecharp.util.ModEquipmentAssets;

public class ModelsGen extends FabricModelProvider {
    public ModelsGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Блоки руд в алфавитном порядке
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.ASBESTIT_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.BRONZE_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.DEEPSLATE_BRONZE_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.DEEPSLATE_PALLADIUM_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.DEEPSLATE_PLATINUM_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.DEEPSLATE_SILVER_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.LESHY_BOSS_SPAWNER);
        blockStateModelGenerator.registerSingleton(BlockInit.MAGMARIUM_ORE_BLOCK, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.PALLADIUM_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.PLATINUM_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.SILVER_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.WARDROBE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // ===== БАЗОВЫЕ МАТЕРИАЛЫ И РЕСУРСЫ =====

        itemModelGenerator.register(ModItems.ASBESTIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ASBESTIT_ORE_MATERIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_ORE_MATERIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGMARIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGMARIUM_ORE_MATERIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PALLADIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.PALLADIUM_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PALLADIUM_ORE_MATERIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLATINUM_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLATINUM_ORE_MATERIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_ORE_MATERIAL, Models.GENERATED);

        // ===== БАНКОВСКИЕ КАРТЫ =====
        itemModelGenerator.register(ModItems.BANK_CARD_BLUE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BANK_CARD_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BANK_CARD_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.BANK_CARD_YELLOW, Models.GENERATED);

        // ===== ИНСТРУМЕНТЫ И ОРУЖИЕ =====
        // Asbestit инструменты
        itemModelGenerator.register(ModItems.ASBESTIT_PICKAXE, Models.HANDHELD);

        // Magmarium инструменты
        itemModelGenerator.register(ModItems.MAGMARIUM_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAGMARIUM_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAGMARIUM_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAGMARIUM_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAGMARIUM_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAGMARIUM_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAGMARIUM_PAXEL, Models.HANDHELD);

        // Palladium инструменты
        itemModelGenerator.register(ModItems.PALLADIUM_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PALLADIUM_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PALLADIUM_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PALLADIUM_PAXEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PALLADIUM_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PALLADIUM_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PALLADIUM_SWORD, Models.HANDHELD);

        // ===== РИТУАЛЬНЫЕ ПРЕДМЕТЫ И ПРЕДМЕТЫ С БОССА =====
        itemModelGenerator.register(ModItems.A_FRAGMENT_OF_THE_EARTH, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANCIENT_ROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.EARTH_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.EARTH_STAFF, Models.GENERATED);
        itemModelGenerator.register(ModItems.OAK_LOG, Models.GENERATED);

        // ===== БРОНЯ =====
        // Magmarium броня
        registerArmor(ModItems.MAGMARIUM_BOOTS, itemModelGenerator, ModEquipmentAssets.MAGMARIUM);
        registerArmor(ModItems.MAGMARIUM_CHESTPLATE, itemModelGenerator, ModEquipmentAssets.MAGMARIUM);
        registerArmor(ModItems.MAGMARIUM_HELMET, itemModelGenerator, ModEquipmentAssets.MAGMARIUM);
        registerArmor(ModItems.MAGMARIUM_LEGGINGS, itemModelGenerator, ModEquipmentAssets.MAGMARIUM);

        // Palladium броня
        registerArmor(ModItems.PALLADIUM_BOOTS, itemModelGenerator, ModEquipmentAssets.PALLADIUM);
        registerArmor(ModItems.PALLADIUM_CHESTPLATE, itemModelGenerator, ModEquipmentAssets.PALLADIUM);
        registerArmor(ModItems.PALLADIUM_HELMET, itemModelGenerator, ModEquipmentAssets.PALLADIUM);
        registerArmor(ModItems.PALLADIUM_LEGGINGS, itemModelGenerator, ModEquipmentAssets.PALLADIUM);
    }
    public void registerArmor(Item item, ItemModelGenerator itemModels, RegistryKey<EquipmentAsset> equipmentKey)
    {
        Identifier id = Registries.ITEM.getId(item);
        Identifier armorType = null;
        if (id.getPath().contains("helmet"))
            armorType = ItemModelGenerator.HELMET_TRIM_ID_PREFIX;
        else if (id.getPath().contains("chestplate"))
            armorType = ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX;
        else if (id.getPath().contains("leggings"))
            armorType = ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX;
        else if (id.getPath().contains("boots"))
            armorType = ItemModelGenerator.BOOTS_TRIM_ID_PREFIX;
        itemModels.registerArmor(item, equipmentKey, armorType, false);
    }

    @Override
    public String getName() {
        return "FabricDocsReference Model Provider";
    }
}
