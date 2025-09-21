package org.mechaRp.mecharp.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;

import java.util.function.Function;

public class ModItems {


    public static Item  register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }



    public static Item BRONZE_COIN = register("bronze_coin", Item::new, new Item.Settings());
    public static Item SILVER_COIN = register("silver_coin", Item::new, new Item.Settings());
    public static Item BRONZE_ORE_MATERIAL = register("bronze_ore_material", Item::new, new Item.Settings());
    public static final Item BANK_CARD = register("bank_card", Item::new, new Item.Settings().maxCount(1));
    public static void initialize() {


    }

}
