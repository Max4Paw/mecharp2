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

public class ToolItemGroup {
    public static final ItemGroup TOOL_ITEM_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(Mecharp.MOD_ID, "tool_item_group"),
            ItemGroup.create(ItemGroup.Row.TOP, 2)
                    .displayName(Text.translatable("itemGroup.tools"))
                    .icon(() -> new ItemStack(ModItems.PALLADIUM_PAXEL))
                    .build()
    );
    public static final RegistryKey<ItemGroup> TOOL_ITEM_KEY = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of(Mecharp.MOD_ID, "tool_item_group")
    );

    public static void registerToolItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(TOOL_ITEM_KEY).register(entries -> {
            // Asbestit инструменты
            entries.add(ModItems.ASBESTIT_PICKAXE);

            // Magmarium инструменты
            entries.add(ModItems.MAGMARIUM_PICKAXE);
            entries.add(ModItems.MAGMARIUM_AXE);
            entries.add(ModItems.MAGMARIUM_SHOVEL);
            entries.add(ModItems.MAGMARIUM_HOE);
            entries.add(ModItems.MAGMARIUM_HAMMER);
            entries.add(ModItems.MAGMARIUM_SWORD);
            entries.add(ModItems.MAGMARIUM_PAXEL);

            // Palladium инструменты
            entries.add(ModItems.PALLADIUM_PICKAXE);
            entries.add(ModItems.PALLADIUM_AXE);
            entries.add(ModItems.PALLADIUM_SHOVEL);
            entries.add(ModItems.PALLADIUM_HOE);
            entries.add(ModItems.PALLADIUM_HAMMER);
            entries.add(ModItems.PALLADIUM_SWORD);
            entries.add(ModItems.PALLADIUM_PAXEL);

            // Базовый материал для крафта инструментов
            entries.add(ModItems.IRON_STICK);
            entries.add(BlockInit.WARDROBE);

        });
    }
}
