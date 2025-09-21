package org.mechaRp.init;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
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

                // Генерация рецепта плавки
                offerSmelting(bronzeSmeltables, RecipeCategory.MISC, ModItems.BRONZE_COIN, 0.7f, 200, "bronze_coin");


                // (Дополнительно можно добавить рецепт обжига в печи плавки, если хочешь)
                 offerBlasting(bronzeSmeltables, RecipeCategory.MISC, ModItems.BRONZE_COIN, 0.7f, 100, "bronze_coin");
            }
        };
    }

    @Override
    public String getName() {
        return "FabricDocsReferenceRecipeProvider";
    }
}
