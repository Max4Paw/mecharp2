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
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.item.ModItems;

public class ArmorGroup {
    public static final ItemGroup ARMOR_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(Mecharp.MOD_ID, "armor_group"),
            ItemGroup.create(ItemGroup.Row.TOP, 3)
                    .displayName(Text.translatable("itemGroup.armor_group"))
                    .icon(() -> new ItemStack(ModItems.MAGMARIUM_CHESTPLATE))
                    .build()
    );
    public static final RegistryKey<ItemGroup> ARMOR_GROUP_KEY = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of(Mecharp.MOD_ID, "armor_group")
    );

    public static void registerArmorGroups() {
        ItemGroupEvents.modifyEntriesEvent(ARMOR_GROUP_KEY).register(entries -> {
            entries.add(ModItems.MAGMARIUM_BOOTS);
            entries.add(ModItems.MAGMARIUM_CHESTPLATE);
            entries.add(ModItems.MAGMARIUM_HELMET);
            entries.add(ModItems.MAGMARIUM_LEGGINGS);
            entries.add(ModItems.PALLADIUM_BOOTS);
            entries.add(ModItems.PALLADIUM_LEGGINGS);
            entries.add(ModItems.PALLADIUM_CHESTPLATE);
            entries.add(ModItems.PALLADIUM_HELMET);
        });
    }
}
