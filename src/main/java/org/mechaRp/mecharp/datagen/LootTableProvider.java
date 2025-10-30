package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class LootTableProvider extends FabricBlockLootTableProvider {
    private RegistryWrapper.WrapperLookup itemLookup;

    public LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl= this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        // Получаем RegistryWrapper.WrapperLookup

        // Получаем RegistryWrapper.Impl для предметов


        // Обычные дропы руд
        addDrop(BlockInit.BRONZE_ORE_BLOCK, oreDrops(BlockInit.BRONZE_ORE_BLOCK, ModItems.BRONZE_ORE_MATERIAL));
        addDrop(BlockInit.SILVER_ORE_BLOCK, oreDrops(BlockInit.SILVER_ORE_BLOCK, ModItems.SILVER_ORE_MATERIAL));
        addDrop(BlockInit.PLATINUM_ORE_BLOCK, oreDrops(BlockInit.PLATINUM_ORE_BLOCK, ModItems.PLATINUM_ORE_MATERIAL));
        addDrop(BlockInit.PALLADIUM_ORE_BLOCK, oreDrops(BlockInit.PALLADIUM_ORE_BLOCK, ModItems.PALLADIUM_ORE_MATERIAL));
        addDrop(BlockInit.DEEPSLATE_PALLADIUM_ORE_BLOCK, oreDrops(BlockInit.DEEPSLATE_PALLADIUM_ORE_BLOCK, ModItems.PALLADIUM_ORE_MATERIAL));
        addDrop(BlockInit.DEEPSLATE_PLATINUM_ORE_BLOCK, oreDrops(BlockInit.DEEPSLATE_PLATINUM_ORE_BLOCK, ModItems.PLATINUM_ORE_MATERIAL));
        addDrop(BlockInit.DEEPSLATE_SILVER_ORE_BLOCK, oreDrops(BlockInit.DEEPSLATE_SILVER_ORE_BLOCK, ModItems.SILVER_ORE_MATERIAL));
        addDrop(BlockInit.DEEPSLATE_BRONZE_ORE_BLOCK, oreDrops(BlockInit.DEEPSLATE_BRONZE_ORE_BLOCK, ModItems.BRONZE_ORE_MATERIAL));

        addDrop(BlockInit.MAGMARIUM_ORE_BLOCK, multipleOreDrops(BlockInit.MAGMARIUM_ORE_BLOCK,ModItems.MAGMARIUM_ORE_MATERIAL,2,5));
        addDrop(BlockInit.ASBESTIT_ORE_BLOCK, multipleOreDrops(BlockInit.ASBESTIT_ORE_BLOCK,ModItems.ASBESTIT_ORE_MATERIAL,2,5));

        // Специальный дроп для магмариума с требованием инструмента по ТЕ
    }

    private void addOreDropWithToolTag(Block block, Item dropItem, RegistryWrapper.Impl<Item> itemLookup, String namespace, String toolTagPath) {
        // Создаем TagKey для инструмента с указанием namespace
        TagKey<Item> toolTagKey = TagKey.of(RegistryKeys.ITEM, Identifier.of(namespace, toolTagPath));

        // Создаем ItemPredicate.Builder для инструмента по ТЕГУ
        ItemPredicate.Builder toolPredicateBuilder = ItemPredicate.Builder.create().tag(itemLookup, toolTagKey);

        // Создаем LootCondition.Builder через MatchToolLootCondition
        LootCondition.Builder conditionBuilder = MatchToolLootCondition.builder(toolPredicateBuilder);

        // Создаем LootPool и добавляем ItemEntry с условием
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(dropItem)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                        .conditionally(conditionBuilder)
                );

        // Создаем LootTable.Builder и добавляем в него пул
        LootTable.Builder tableBuilder = LootTable.builder().pool(poolBuilder);

        // Используем унаследованный метод addDrop
        addDrop(block, tableBuilder);
    }
    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop,((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops,maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

}