package org.mechaRp.mecharp.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;

public class ModTags {
    public static final TagKey<Item> BANK_CARDS = TagKey.of(RegistryKeys.ITEM, Identifier.of( Mecharp.MOD_ID, "bank_cards"));
    public static final TagKey<Item> MONEY_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID,"money_items"));

    public static class Blocks {
        public static final TagKey<Block> PALLADIUM_PAXEL_MINEABLE = createTag("palladium_paxel_mineable");
        public static final TagKey<Block> MAGMARIUM_PAXEL_MINEABLE = createTag("magmarium_paxel_mineable");
        public static final TagKey<Block> NEEDS_PALLADIUM_TOOL = createTag("needs_palladium_tool");
        public static final TagKey<Block> NEEDS_MAGMARIUM_TOOL = createTag("needs_magmarium_tool");
        public static final TagKey<Block> NEEDS_ASBESTIT_TOOL = createTag("needs_asbestit_tool");
        public static final TagKey<Block> INCORRECT_FOR_PALLADIUM_TOOL = createTag("incorrect_for_palladium_tool");
        public static final TagKey<Block> INCORRECT_FOR_MAGMARIUM_TOOL = createTag("incorrect_for_magmarium_tool");
        public static final TagKey<Block> INCORRECT_FOR_ASBESTIT_TOOL = createTag("incorrect_for_asbestit_tool");
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Mecharp.MOD_ID, name));
        }

}

    public static class Items {
        public static final TagKey<Item> PALLADIUM_TOOLS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of(Mecharp.MOD_ID, "palladium_tool"));
        public static final TagKey<Item> MAGMARIUM_TOOLS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of(Mecharp.MOD_ID, "magmarium_tool"));
        public static final TagKey<Item> PALLADIUM_REPAIRS =
                TagKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "palladium"));
        public static final TagKey<Item> ASBESTIT_REPAIRS =
                TagKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "asbestit"));
        public static final TagKey<Item> MAGMARIUM_REPAIRS =
                TagKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, "magmarium"));


        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID, name));
        }
    }
}

