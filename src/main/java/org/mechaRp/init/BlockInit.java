package org.mechaRp.init;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import org.mechaRp.init.blocks.BankTerminalBlock;
import org.mechaRp.init.entity.BankTerminalBlockEntity;
import org.mechaRp.mecharp.Mecharp;

import java.util.function.Function;

public class BlockInit {
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }
        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Mecharp.id(name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Mecharp.id(name));
    }

    public static final Block BRONZE_ORE_BLOCK = register(
            "bronze_ore",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL),
            true
    );
    public static final Block DEEPSLATE_BRONZE_ORE_BLOCK = register(
            "deepslate_bronze_ore",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL),
            true
    );
    public static final Block SILVER_ORE_BLOCK = register(
            "silver_ore",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL),
            true
    );
    public static final Block DEEPSLATE_SILVER_ORE_BLOCK = register(
            "deepslate_silver_ore",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL),
            true
    );
    public static final Block PLATINUM_ORE_BLOCK = register(
            "platinum_ore",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL),
            true
    );
    public static final Block DEEPSLATE_PLATINUM_ORE_BLOCK = register(
            "deepslate_platinum_ore",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL),
            true
    );
    public static final Block PALLADIUM_ORE_BLOCK = register(
            "palladium_ore",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL),
            true
    );
    public static final Block DEEPSLATE_PALLADIUM_ORE_BLOCK = register(
            "deepslate_palladium_ore",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL),
            true
    );


    public static final Block BANK_TERMINAL_BLOCK = register(
            "bank_terminal",
            BankTerminalBlock::new,
            AbstractBlock.Settings.create(),
            true
    );

    public static final BlockEntityType<BankTerminalBlockEntity> BANK_TERMINAL_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Mecharp.id("bank_terminal_entity"),
            FabricBlockEntityTypeBuilder.create(BankTerminalBlockEntity::new, BANK_TERMINAL_BLOCK).build()
    );




    public static void registerBlocks() {
    }
}