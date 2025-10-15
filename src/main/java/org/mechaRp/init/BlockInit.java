package org.mechaRp.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.block.BankTerminalBlock;
import org.mechaRp.mecharp.block.CustomBlock;
import org.mechaRp.mecharp.block.LeshyBossSpawnerBlock;

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
    public static final Block MAGMARIUM_ORE_BLOCK = register(
            "magmarium_ore",
            settings -> new Block(settings),
            AbstractBlock.Settings.create()
                    .strength(3.0f, 5.0f)
                    .sounds(BlockSoundGroup.METAL)
                    .requiresTool(),
            true
    );




    public static final Block BANK_TERMINAL = register(
            "bank_terminal",
            BankTerminalBlock::new,
            AbstractBlock.Settings.create(),
            true
    );
    public static final Block LESHY_BOSS_SPAWNER = register("leshy_boss_spawner",
            LeshyBossSpawnerBlock::new,
            AbstractBlock.Settings.copy(Blocks.MOSSY_COBBLESTONE)
                    .strength(3.0f, 6.0f)
                    .requiresTool()
                    .luminance(state -> 7),
            true // не забудьте добавить этот параметр
    );
    public static void registerBlocks() {
        Mecharp.LOGGER.info("Блоки зарегистрированы");
    }
}