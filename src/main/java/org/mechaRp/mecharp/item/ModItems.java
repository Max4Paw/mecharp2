package org.mechaRp.mecharp.item;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.armormaterial.MagmariumArmorMaterial;
import org.mechaRp.mecharp.toolmaterial.PalladiumSwordMaterial;
import org.mechaRp.mecharp.toolmaterial.PalladiumToolMaterial;

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

   // public static final Item BANK_CARD = register("bank_card", Item::new, new Item.Settings().maxCount(1));


    // Цветные банковские карты
    public static final Item BANK_CARD_RED = register("bank_card_red", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_BLUE = register("bank_card_blue", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_GREEN = register("bank_card_green", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_YELLOW = register("bank_card_yellow", Item::new, new Item.Settings().maxCount(1));
    public static final Item PALLADIUM_PICKAXE = register(
            "palladium_pickaxe",
            Item::new,
            new Item.Settings().pickaxe(PalladiumToolMaterial.PALLADIUM, 1.0f, -2.8f)
    );
    public static final Item PALLADIUM_SWORD = register("palladium_sword", Item::new, new Item.Settings().sword(PalladiumSwordMaterial.PALLADIUM,17.5f,1.6f));

    // броня магмы
    public static final Item MAGMARIUM_HELMET = register(
            "magmarium_helmet",
            Item::new,
            new Item.Settings().armor(MagmariumArmorMaterial.INSTANCE, EquipmentType.HELMET)
                    .maxDamage(EquipmentType.HELMET.getMaxDamage(MagmariumArmorMaterial.BASE_DURABILITY))
    );
    public static final Item MAGMARIUM_CHESTPLATE = register("magmarium_chestplate",
            Item::new,
            new Item.Settings().armor(MagmariumArmorMaterial.INSTANCE, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(MagmariumArmorMaterial.BASE_DURABILITY))
    );

    public static final Item MAGMARIUM_LEGGINGS = register(
            "magmarium_leggings",
            Item::new,
            new Item.Settings().armor(MagmariumArmorMaterial.INSTANCE, EquipmentType.LEGGINGS)
                    .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(MagmariumArmorMaterial.BASE_DURABILITY))
    );

    public static final Item MAGMARIUM_BOOTS = register(
            "magmarium_boots",
            Item::new,
            new Item.Settings().armor(MagmariumArmorMaterial.INSTANCE, EquipmentType.BOOTS)
                    .maxDamage(EquipmentType.BOOTS.getMaxDamage(MagmariumArmorMaterial.BASE_DURABILITY))
    );





    public static void initialize() {
         // Здесь можно добавить код инициализации, если потребуется
    }


}
