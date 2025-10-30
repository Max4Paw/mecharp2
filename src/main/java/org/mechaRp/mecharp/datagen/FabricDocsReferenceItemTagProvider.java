package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.item.ModItems;
import org.mechaRp.mecharp.registry.ModTags;

import java.util.concurrent.CompletableFuture;

import static org.mechaRp.mecharp.item.ModItems.*;

public class FabricDocsReferenceItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> BANK_CARDS = TagKey.of(RegistryKeys.ITEM, Identifier.of( Mecharp.MOD_ID, "bank_cards"));
    public static final TagKey<Item> MONEY_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Mecharp.MOD_ID,"money_items"));
    public static final TagKey<Item> PALLADIUM_TOOLS = TagKey.of(RegistryKeys.ITEM,
            Identifier.of(Mecharp.MOD_ID, "palladium_tool"));

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

        valueLookupBuilder(PALLADIUM_TOOLS)
                .add(PALLADIUM_PICKAXE)
                .add(PALLADIUM_PAXEL)
                .add(PALLADIUM_HAMMER);

        valueLookupBuilder(ModTags.Items.PALLADIUM_REPAIRS).add(ModItems.PALLADIUM);
        valueLookupBuilder(ModTags.Items.MAGMARIUM_REPAIRS).add(ModItems.MAGMARIUM);
        valueLookupBuilder(ModTags.Items.ASBESTIT_REPAIRS).add(ModItems.ASBESTIT);


    }
}