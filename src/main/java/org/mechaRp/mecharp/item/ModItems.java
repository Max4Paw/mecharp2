package org.mechaRp.mecharp.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;

import java.util.function.Function;

public class ModItems {

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
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

    public static final Item BANK_CARD = register("bank_card", Item::new, new Item.Settings().maxCount(1));

    // Цветные банковские карты
    public static final Item BANK_CARD_RED = register("bank_card_red", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_BLUE = register("bank_card_blue", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_GREEN = register("bank_card_green", Item::new, new Item.Settings().maxCount(1));
    public static final Item BANK_CARD_YELLOW = register("bank_card_yellow", Item::new, new Item.Settings().maxCount(1));

    public static final Item PALLADIUM_PICKAXE_ITEM = register("palladium_pickaxe",Item::new, new Item.Settings().maxCount(1));


    public static void initialize() {
        // Здесь можно добавить код инициализации, если потребуется
    }
}
