package org.mechaRp.mecharp.item;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.config.AsbestitConfig;
import org.mechaRp.mecharp.config.MagmariumConfig;
import org.mechaRp.mecharp.config.PalladiumConfig;
import org.mechaRp.mecharp.item.custom.abestit.AsbestitPickaxe;
import org.mechaRp.mecharp.item.custom.magmarium.*;
import org.mechaRp.mecharp.item.custom.palladium.*;

import java.util.function.Function;

public class ModItems {
    // ===== Методы регистрации =====
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    private static Item register(String name, Item item) {
        Identifier id = Identifier.of(Mecharp.MOD_ID, name);
        return Registry.register(Registries.ITEM, id, item);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Mecharp.MOD_ID, name), item);
    }

    // ===== БАЗОВЫЕ МАТЕРИАЛЫ И РЕСУРСЫ =====
    // Руда и материалы
    public static final Item ASBESTIT = register("asbestit", Item::new, new Item.Settings());
    public static final Item ASBESTIT_ORE_MATERIAL = register("asbestit_ore_material", Item::new, new Item.Settings());
    public static final Item BRONZE_ORE_MATERIAL = register("bronze_ore_material", Item::new, new Item.Settings());
    public static final Item BRONZE_COIN = register("bronze_coin", Item::new, new Item.Settings());
    public static final Item IRON_STICK = register("iron_stick", Item::new, new Item.Settings());
    public static final Item MAGMARIUM = register("magmarium", Item::new, new Item.Settings());
    public static final Item MAGMARIUM_ORE_MATERIAL = register("magmarium_ore_material", Item::new, new Item.Settings());
    public static final Item PALLADIUM = register("palladium", Item::new, new Item.Settings());
    public static final Item PALLADIUM_INGOT = register("palladium_ingot_material", Item::new, new Item.Settings());
    public static final Item PALLADIUM_ORE_MATERIAL = register("palladium_ore_material", Item::new, new Item.Settings());
    public static final Item PLATINUM_COIN = register("platinum_coin", Item::new, new Item.Settings());
    public static final Item PLATINUM_ORE_MATERIAL = register("platinum_ore_material", Item::new, new Item.Settings());
    public static final Item SILVER_COIN = register("silver_coin", Item::new, new Item.Settings());
    public static final Item SILVER_ORE_MATERIAL = register("silver_ore_material", Item::new, new Item.Settings());

    // ===== БАНКОВСКИЕ КАРТЫ =====
    public static final Item BANK_CARD_BLUE = register("bank_card_blue", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_GREEN = register("bank_card_green", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_RED = register("bank_card_red", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_YELLOW = register("bank_card_yellow", Item::new, new Item.Settings().maxCount(1));

    // ===== ИНСТРУМЕНТЫ И ОРУЖИЕ =====
    // Asbestit инструменты
    public static final Item ASBESTIT_PICKAXE = registerItem("asbestit_pickaxe",
            new AsbestitPickaxe(ModToolMaterial.ASBESTIT, AsbestitConfig.attackDamageAsbesitPickaxe, (float) AsbestitConfig.attackSpeedAsbesitPickaxe,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "asbestit_pickaxe")))));

    // Magmarium инструменты
    public static final Item MAGMARIUM_AXE = registerItem("magmarium_axe",
            new MagmariumAxe(ModToolMaterial.MAGMARIUM, MagmariumConfig.attackDamageMagmariumAxe, (float) MagmariumConfig.attackSpeedMagmariumAxe,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_axe")))));

    public static final Item MAGMARIUM_HAMMER = registerItem("magmarium_hammer",
            new MagmariumHammer(ModToolMaterial.MAGMARIUM, MagmariumConfig.attackDamageMagmariumHammer, (float) MagmariumConfig.attackSpeedMagmariumHammer,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_hammer")))));

    public static final Item MAGMARIUM_HOE = registerItem("magmarium_hoe",
            new MagmariumHoe(ModToolMaterial.MAGMARIUM, MagmariumConfig.attackDamageMagmariumHoe, (float) MagmariumConfig.attackSpeedMagmariumHoe,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_hoe")))));

    public static final Item MAGMARIUM_PAXEL = registerItem("magmarium_paxel",
            new MagmariumPaxel(ModToolMaterial.MAGMARIUM, MagmariumConfig.attackDamageMagmariumPaxel, (float) MagmariumConfig.attackSpeedMagmariumPaxel,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_paxel")))));

    public static final Item MAGMARIUM_PICKAXE = registerItem("magmarium_pickaxe",
            new MagmariumPickaxe(ModToolMaterial.MAGMARIUM, MagmariumConfig.attackDamageMagmariumPickaxe, (float) MagmariumConfig.attackSpeedMagmariumPickaxe,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_pickaxe")))));

    public static final Item MAGMARIUM_SHOVEL = registerItem("magmarium_shovel",
            new MagmariumShovel(ModToolMaterial.MAGMARIUM, MagmariumConfig.attackDamageMagmariumShovel, (float) MagmariumConfig.attackSpeedMagmariumShovel,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_shovel")))));

    public static final Item MAGMARIUM_SWORD = registerItem("magmarium_sword",
            new MagmariumSword(ModToolMaterial.MAGMARIUM, MagmariumConfig.attackDamageMagmariumSword, (float) MagmariumConfig.attackSpeedMagmariumSword,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_sword")))));

    // Palladium инструменты
    public static final Item PALLADIUM_AXE = registerItem("palladium_axe",
            new PalladiumAxe(ModToolMaterial.PALLADIUM, PalladiumConfig.attackDamagePalladiumAxe, (float) PalladiumConfig.attackSpeedPalladiumAxe,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_axe")))));

    public static final Item PALLADIUM_HAMMER = registerItem("palladium_hammer",
            new PalladiumHammer(ModToolMaterial.PALLADIUM, PalladiumConfig.attackDamagePalladiumHammer, (float) PalladiumConfig.attackSpeedPalladiumHammer,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_hammer")))));

    public static final Item PALLADIUM_HOE = registerItem("palladium_hoe",
            new PalladiumHoe(ModToolMaterial.PALLADIUM, PalladiumConfig.attackDamagePalladiumHoe, (float) PalladiumConfig.attackSpeedPalladiumHoe,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_hoe")))));

    public static final Item PALLADIUM_PAXEL = registerItem("palladium_paxel",
            new PalladiumPaxel(ModToolMaterial.PALLADIUM, PalladiumConfig.attackDamagePalladiumPaxel, (float) PalladiumConfig.attackSpeedPalladiumPaxel,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_paxel")))));

    public static final Item PALLADIUM_PICKAXE = registerItem("palladium_pickaxe",
            new PalladiumPickaxe(ModToolMaterial.PALLADIUM, PalladiumConfig.attackDamagePalladiumPickaxe, (float) PalladiumConfig.attackSpeedPalladiumPickaxe,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_pickaxe")))));

    public static final Item PALLADIUM_SHOVEL = registerItem("palladium_shovel",
            new PalladiumShovel(ModToolMaterial.PALLADIUM, PalladiumConfig.attackDamagePalladiumShovel, (float) PalladiumConfig.attackSpeedPalladiumShovel,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_shovel")))));

    public static final Item PALLADIUM_SWORD = registerItem("palladium_sword",
            new PalladiumSword(ModToolMaterial.PALLADIUM, PalladiumConfig.attackDamagePalladiumSword, (float) PalladiumConfig.attackSpeedPalladiumSword,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_sword")))));

    // ===== БРОНЯ =====
    // Magmarium броня
    public static final Item MAGMARIUM_BOOTS = registerItem("magmarium_boots",
            new MagmariumArmor(ModArmorMaterials.MAGMARIUM, EquipmentType.BOOTS,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_boots")))));

    public static final Item MAGMARIUM_CHESTPLATE = registerItem("magmarium_chestplate",
            new MagmariumArmor(ModArmorMaterials.MAGMARIUM, EquipmentType.CHESTPLATE,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_chestplate")))));

    public static final Item MAGMARIUM_HELMET = registerItem("magmarium_helmet",
            new MagmariumArmor(ModArmorMaterials.MAGMARIUM, EquipmentType.HELMET,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_helmet")))));

    public static final Item MAGMARIUM_LEGGINGS = registerItem("magmarium_leggings",
            new MagmariumArmor(ModArmorMaterials.MAGMARIUM, EquipmentType.LEGGINGS,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_leggings")))));

    // Palladium броня
    public static final Item PALLADIUM_BOOTS = registerItem("palladium_boots",
            new PalladiumArmor(ModArmorMaterials.PALLADIUM, EquipmentType.BOOTS,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_boots")))));

    public static final Item PALLADIUM_CHESTPLATE = registerItem("palladium_chestplate",
            new PalladiumArmor(ModArmorMaterials.PALLADIUM, EquipmentType.CHESTPLATE,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_chestplate")))));

    public static final Item PALLADIUM_HELMET = registerItem("palladium_helmet",
            new PalladiumArmor(ModArmorMaterials.PALLADIUM, EquipmentType.HELMET,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_helmet")))));

    public static final Item PALLADIUM_LEGGINGS = registerItem("palladium_leggings",
            new PalladiumArmor(ModArmorMaterials.PALLADIUM, EquipmentType.LEGGINGS,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_leggings")))));

    // ===== РИТУАЛЬНЫЕ ПРЕДМЕТЫ =====
    public static final Item A_FRAGMENT_OF_THE_EARTH = register("a_fragment_of_the_earth", Item::new, new Item.Settings().maxCount(1));

    // ===== ПРЕДМЕТЫ С БОССА =====
    public static final Item ANCIENT_ROOT = register("ancient_root", Item::new, new Item.Settings().maxCount(1));
    public static final Item EARTH_CRYSTAL = register("earth_crystal", Item::new, new Item.Settings().maxCount(1));
    public static final Item EARTH_STAFF = register("earth_staff", Item::new, new Item.Settings().maxCount(1));
    public static final Item OAK_LOG = register("oak_log", Item::new, new Item.Settings().maxCount(1));

    public static void initialize() {
        Mecharp.LOGGER.info("✅ Предметы зарегистрированы (ModItems успешно инициализирован)");
    }
}