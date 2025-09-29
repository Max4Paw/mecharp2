package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static org.mechaRp.mecharp.item.ModItems.*;
import static org.mechaRp.mecharp.util.ModTags.MONEY_ITEMS;

public class FabricDocsReferenceItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public static final TagKey<Item> BANK_CARDS = TagKey.of(RegistryKeys.ITEM, Identifier.of("mecharp", "bank_cards"));

    public FabricDocsReferenceItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Создаем тег и добавляем карты
        valueLookupBuilder(BANK_CARDS)
                .add(BANK_CARD_GREEN)
                .add(BANK_CARD_BLUE)
                .add(BANK_CARD_RED)
                .add(BANK_CARD_YELLOW);

        valueLookupBuilder(MONEY_ITEMS)
                .add(BRONZE_COIN)
                .add(SILVER_COIN)
                .add(PLATINUM_COIN);
    }
}