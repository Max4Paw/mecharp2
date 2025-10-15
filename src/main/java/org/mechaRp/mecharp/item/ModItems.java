package org.mechaRp.mecharp.item;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.config.PalladiumConfig;
import org.mechaRp.mecharp.item.custom.magmarium.MagmariumArmor;
import org.mechaRp.mecharp.item.custom.palladium.PalladiumHammer;
import org.mechaRp.mecharp.item.custom.palladium.PalladiumPaxel;
import org.mechaRp.mecharp.item.custom.palladium.PalladiumPickaxe;
import org.mechaRp.mecharp.toolmaterial.PalladiumSwordMaterial;

import java.util.function.Function;

public class ModItems {

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

    // ===== Основные материалы и предметы =====
    public static final Item BRONZE_COIN = register("bronze_coin", Item::new, new Item.Settings());
    public static final Item SILVER_COIN = register("silver_coin", Item::new, new Item.Settings());
    public static final Item PLATINUM_COIN = register("platinum_coin", Item::new, new Item.Settings());
    public static final Item BRONZE_ORE_MATERIAL = register("bronze_ore_material", Item::new, new Item.Settings());
    public static final Item SILVER_ORE_MATERIAL = register("silver_ore_material", Item::new, new Item.Settings());
    public static final Item PLATINUM_ORE_MATERIAL = register("platinum_ore_material", Item::new, new Item.Settings());
    public static final Item PALLADIUM_ORE_MATERIAL = register("palladium_ore_material", Item::new, new Item.Settings());
    public static final Item PALLADIUM_INGOT = register("palladium_ingot_material", Item::new, new Item.Settings());
    public static final Item PALLADIUM = register("palladium", Item::new, new Item.Settings());
    public static final Item MAGMARIUM = register("magmarium",Item::new, new Item.Settings());
    public static final Item MAGMARIUM_ORE_MATERIAL = register("magmarium_ore_material",Item::new, new Item.Settings());

    // ===== Банковские карты (пока отключены) =====
    public static final Item BANK_CARD_RED = register("bank_card_red", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_BLUE = register("bank_card_blue", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_GREEN = register("bank_card_green", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_YELLOW = register("bank_card_yellow", Item::new, new Item.Settings().maxCount(1));

    // ===== Инструменты (временно ванильные материалы) =====
    public static final Item PALLADIUM_PAXEL = registerItem("palladium_paxel",
            new PalladiumPaxel(ModToolMaterial.PALLADIUM, PalladiumConfig.attackDamagePalladiumPaxel, (float) PalladiumConfig.attackSpeedPalladiumPaxel,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_paxel")))));

    public static final Item PALLADIUM_PICKAXE = registerItem("palladium_pickaxe",
            new PalladiumPickaxe(ModToolMaterial.PALLADIUM, PalladiumConfig.attackDamagePalladiumPickaxe, (float) PalladiumConfig.attackSpeedPalladiumPickaxe,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_pickaxe")))));
    public static final Item PALLADIUM_SWORD = register("palladium_sword", Item::new, new Item.Settings().sword(PalladiumSwordMaterial.PALLADIUM,17.5f,1.6f));
    public static final Item PALLADIUM_HAMMER = registerItem("palladium_hammer",
            new PalladiumHammer(ModToolMaterial.PALLADIUM, PalladiumConfig.attackDamagePalladiumAxe, (float) PalladiumConfig.attackSpeedPalladiumHammer,
                    new Item.Settings().fireproof()
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium_hammer")))));

    // броня магмы
    public static final Item MAGMARIUM_HELMET = registerItem("magmarium_helmet",
            new MagmariumArmor(ModArmorMaterials.MAGMARIUM, EquipmentType.HELMET,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_helmet")))));

    public static final Item MAGMARIUM_CHESTPLATE = registerItem("magmarium_chestplate",
            new MagmariumArmor(ModArmorMaterials.MAGMARIUM, EquipmentType.CHESTPLATE,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_chestplate")))));

    public static final Item MAGMARIUM_LEGGINGS = registerItem("magmarium_leggings",
            new MagmariumArmor(ModArmorMaterials.MAGMARIUM, EquipmentType.LEGGINGS,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_leggings")))));

    public static final Item MAGMARIUM_BOOTS = registerItem("magmarium_boots",
            new MagmariumArmor(ModArmorMaterials.MAGMARIUM, EquipmentType.BOOTS,
                    new Item.Settings().fireproof().maxCount(1)
                            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium_boots")))));
    // ===== Предметы для ритуалов =====
    public static final Item A_FRAGMENT_OF_THE_EARTH = register("a_fragment_of_the_earth", Item::new, new Item.Settings().maxCount(1));

    // ===== Предметы с босса =====
    public static final Item ANCIENT_ROOT = register("ancient_root", Item::new, new Item.Settings().maxCount(1));
    public static final Item EARTH_CRYSTAL = register("earth_crystal", Item::new, new Item.Settings().maxCount(1));
    public static final Item OAK_LOG = register("oak_log", Item::new, new Item.Settings().maxCount(1));
    public static final Item EARTH_STAFF = register("earth_staff", Item::new, new Item.Settings().maxCount(1));

    public static void initialize() {
        Mecharp.LOGGER.info("✅ Предметы зарегистрированы (ModItems успешно инициализирован)");
    }
}
