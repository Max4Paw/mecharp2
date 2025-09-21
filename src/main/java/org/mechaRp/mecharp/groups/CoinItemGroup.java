package org.mechaRp.mecharp.groups;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.item.ModItems;


public class CoinItemGroup {

    public static final ItemGroup COIN_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(Mecharp.MOD_ID, "coin_group"),
            ItemGroup.create(ItemGroup.Row.TOP, 0)
                    .displayName(Text.translatable("itemGroup.coins"))
                    .icon(() -> new ItemStack(ModItems.BRONZE_COIN))
                    .build()
    );
    public static final RegistryKey<ItemGroup> COIN_GROUP_KEY = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of(Mecharp.MOD_ID, "coin_group")
    );

    public static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(COIN_GROUP_KEY).register(entries -> {
            entries.add(ModItems.BRONZE_COIN);
            entries.add(ModItems.SILVER_COIN);
            entries.add(ModItems.PLATINUM_COIN);
        });
    }
}