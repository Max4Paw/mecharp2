package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import org.mechaRp.mecharp.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FabricDocsReferenceRecipeProvider extends FabricRecipeProvider {
    public FabricDocsReferenceRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                // Список предметов, которые можно переплавить в бронзовую монету
                List<ItemConvertible> bronzeSmeltables = List.of(ModItems.BRONZE_ORE_MATERIAL);
                List<ItemConvertible> silverSmeltables = List.of(ModItems.SILVER_ORE_MATERIAL);
                List<ItemConvertible> platinumSmeltables = List.of(ModItems.PLATINUM_ORE_MATERIAL);
                List<ItemConvertible> palladiumSmeltables = List.of(ModItems.PALLADIUM_ORE_MATERIAL);


                // Генерация рецепта плавки
                offerSmelting(bronzeSmeltables, RecipeCategory.MISC, ModItems.BRONZE_COIN, 0.7f, 200, "bronze_coin");
                offerSmelting(silverSmeltables, RecipeCategory.MISC, ModItems.SILVER_COIN, 0.7f, 200, "silver_coin");
                offerSmelting(platinumSmeltables, RecipeCategory.MISC, ModItems.PLATINUM_COIN, 0.7f, 200, "platinum_coin");
                offerSmelting(palladiumSmeltables, RecipeCategory.MISC, ModItems.PALLADIUM, 0.7f, 200, "palladium");


                // (Дополнительно можно добавить рецепт обжига в печи плавки, если хочешь)
                offerBlasting(bronzeSmeltables, RecipeCategory.MISC, ModItems.BRONZE_COIN, 0.7f, 100, "bronze_coin");
                offerBlasting(silverSmeltables, RecipeCategory.MISC, ModItems.SILVER_COIN, 0.7f, 100, "silver_coin");
                offerBlasting(platinumSmeltables, RecipeCategory.MISC, ModItems.PLATINUM_COIN, 0.7f, 100, "platinum_coin");
                offerBlasting(palladiumSmeltables, RecipeCategory.MISC, ModItems.PALLADIUM, 0.7f, 100, "palladium");

                // крафты
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PALLADIUM_PICKAXE)
                        .pattern("PPP")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('P', ModItems.PALLADIUM)
                        .input('S', Items.STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);


            }


            ;
        };
    }

    @Override
    public String getName() {
        return "FabricDocsReferenceRecipeProvider";
    }
}
