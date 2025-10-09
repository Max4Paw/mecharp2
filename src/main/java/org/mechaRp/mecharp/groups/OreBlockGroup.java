package org.mechaRp.mecharp.groups;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.item.ModItems;

public class OreBlockGroup {

    public static final RegistryKey<ItemGroup> BLOCK_GROUP_KEY = RegistryKey.of(
        RegistryKeys.ITEM_GROUP,
        Identifier.of(Mecharp.MOD_ID, "ore_group") // ← лучше ore_group, а не block_group
);

    public static final ItemGroup BLOCK_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            BLOCK_GROUP_KEY.getValue(),
            ItemGroup.create(ItemGroup.Row.TOP, 1) // ← column = 1, чтобы не перекрывать coin_group
                    .displayName(Text.translatable("itemGroup.ore_group"))
                    .icon(() -> new ItemStack(BlockInit.BRONZE_ORE_BLOCK))
                    .build()
    );

    public static void registerBlockGroup() {
        ItemGroupEvents.modifyEntriesEvent(BLOCK_GROUP_KEY).register(entries -> {
            entries.add(BlockInit.BRONZE_ORE_BLOCK);
            entries.add(BlockInit.SILVER_ORE_BLOCK);
                entries.add(ModItems.BRONZE_ORE_MATERIAL);
                entries.add(ModItems.SILVER_ORE_MATERIAL);
                entries.add(ModItems.PLATINUM_ORE_MATERIAL);
            entries.add(BlockInit.PLATINUM_ORE_BLOCK);
            entries.add(BlockInit.PALLADIUM_ORE_BLOCK);
            entries.add(ModItems.PALLADIUM_ORE_MATERIAL);
            entries.add(ModItems.PALLADIUM);
            entries.add(ModItems.PALLADIUM_INGOT);
            entries.add(BlockInit.DEEPSLATE_PLATINUM_ORE_BLOCK);
            entries.add(BlockInit.DEEPSLATE_PALLADIUM_ORE_BLOCK);
            entries.add(BlockInit.DEEPSLATE_BRONZE_ORE_BLOCK);
            entries.add(BlockInit.DEEPSLATE_SILVER_ORE_BLOCK);
            entries.add(BlockInit.MAGMARIUM_ORE_BLOCK);
            entries.add(ModItems.MAGMARIUM);
            entries.add(ModItems.MAGMARIUM_ORE_MATERIAL
            );
           // entries.add(BlockInit.);
//            // Добавьте другие предметы при необходимости
        });
    }
}
