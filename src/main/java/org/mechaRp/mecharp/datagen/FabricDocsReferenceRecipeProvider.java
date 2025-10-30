package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
//import net.minecraft.item.Items;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import org.mechaRp.mecharp.Mecharp;
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
                List<ItemConvertible> magmariumSmeltables = List.of(ModItems.MAGMARIUM_ORE_MATERIAL);
                List<ItemConvertible> asbestitSmeltables = List.of(ModItems.ASBESTIT_ORE_MATERIAL);


                // Генерация рецепта плавки
                offerSmelting(bronzeSmeltables, RecipeCategory.MISC, ModItems.BRONZE_COIN, 0.7f, 200, "bronze_coin");
                offerSmelting(silverSmeltables, RecipeCategory.MISC, ModItems.SILVER_COIN, 0.7f, 200, "silver_coin");
                offerSmelting(platinumSmeltables, RecipeCategory.MISC, ModItems.PLATINUM_COIN, 0.7f, 200, "platinum_coin");
                offerSmelting(palladiumSmeltables, RecipeCategory.MISC, ModItems.PALLADIUM, 0.7f, 200, "palladium");
                offerSmelting(magmariumSmeltables, RecipeCategory.MISC, ModItems.MAGMARIUM, 1.7f, 6000, "magmarium");
                offerSmelting(asbestitSmeltables, RecipeCategory.MISC, ModItems.ASBESTIT, 1.7f, 6000, "asbestit");


                // (Дополнительно можно добавить рецепт обжига в печи плавки, если хочешь)
                offerBlasting(bronzeSmeltables, RecipeCategory.MISC, ModItems.BRONZE_COIN, 0.7f, 100, "bronze_coin");
                offerBlasting(silverSmeltables, RecipeCategory.MISC, ModItems.SILVER_COIN, 0.7f, 100, "silver_coin");
                offerBlasting(platinumSmeltables, RecipeCategory.MISC, ModItems.PLATINUM_COIN, 0.7f, 100, "platinum_coin");
                offerBlasting(palladiumSmeltables, RecipeCategory.MISC, ModItems.PALLADIUM, 0.7f, 100, "palladium");
                offerBlasting(magmariumSmeltables, RecipeCategory.MISC, ModItems.MAGMARIUM, 1.7f, 4800, "magmarium");
                offerBlasting(asbestitSmeltables, RecipeCategory.MISC, ModItems.ASBESTIT, 1.7f, 4900, "asbestit");

                // крафты

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.MAGMARIUM_AXE)
                        .pattern("MM ")
                        .pattern("MS ")
                        .pattern(" S ")
                        .input('M', ModItems.MAGMARIUM)
                        .input('S', ModItems.IRON_STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.MAGMARIUM_HOE)
                        .pattern("MM ")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('M', ModItems.MAGMARIUM)
                        .input('S', ModItems.IRON_STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.MAGMARIUM_SHOVEL)
                        .pattern(" M ")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('M', ModItems.MAGMARIUM)
                        .input('S', ModItems.IRON_STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.MAGMARIUM_HAMMER)
                        .pattern("MMM")
                        .pattern("MSM")
                        .pattern(" S ")
                        .input('M', ModItems.MAGMARIUM)
                        .input('S', ModItems.IRON_STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.IRON_STICK)
                        .pattern("   ")
                        .pattern(" I ")
                        .pattern(" I ")
                        .input('I', Items.IRON_INGOT)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PALLADIUM_PICKAXE)
                        .pattern("PPP")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('P', ModItems.PALLADIUM)
                        .input('S', Items.STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PALLADIUM_AXE)
                        .pattern("PP ")
                        .pattern("PS ")
                        .pattern(" S ")
                        .input('P', ModItems.PALLADIUM)
                        .input('S', Items.STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PALLADIUM_HOE)
                        .pattern("PP ")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('P', ModItems.PALLADIUM)
                        .input('S', Items.STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PALLADIUM_SHOVEL)
                        .pattern(" P ")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('P', ModItems.PALLADIUM)
                        .input('S', Items.STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PALLADIUM_PAXEL)
                        .pattern("PS ")
                        .pattern("A  ")
                        .pattern("   ")
                        .input('P', ModItems.PALLADIUM_PICKAXE)
                        .input('A', ModItems.PALLADIUM_AXE)
                        .input('S', ModItems.PALLADIUM_SHOVEL)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.MAGMARIUM_PAXEL)
                        .pattern("PS ")
                        .pattern("A  ")
                        .pattern("   ")
                        .input('P', ModItems.MAGMARIUM_PICKAXE)
                        .input('A', ModItems.MAGMARIUM_AXE)
                        .input('S', ModItems.MAGMARIUM_SHOVEL)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.MAGMARIUM_PICKAXE)
                        .pattern("MMM")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('M', ModItems.MAGMARIUM)
                        .input('S', ModItems.IRON_STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.ASBESTIT_PICKAXE)
                        .pattern("AAA")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('A', ModItems.ASBESTIT)
                        .input('S', ModItems.IRON_STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PALLADIUM_HAMMER)
                        .pattern("PPP")
                        .pattern("PSP")
                        .pattern(" S ")
                        .input('P', ModItems.PALLADIUM)
                        .input('S', Items.STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PALLADIUM_SWORD)
                        .pattern(" P ")
                        .pattern(" P ")
                        .pattern(" S ")
                        .input('P', ModItems.PALLADIUM)
                        .input('S', Items.STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.MAGMARIUM_SWORD)
                        .pattern(" M ")
                        .pattern(" M ")
                        .pattern(" S ")
                        .input('M', ModItems.MAGMARIUM)
                        .input('S', ModItems.IRON_STICK)
                        .criterion("has_stick", conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModItems.PALLADIUM)
                        .pattern("III")
                        .pattern("III")
                        .pattern("III")
                        .input('I', ModItems.PALLADIUM_INGOT)
                        .criterion("has_palladium_ingot", conditionsFromItem(ModItems.PALLADIUM_INGOT))
                        .offerTo(exporter);
                // 1 слиток → 9 кусочков
                ShapelessRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModItems.PALLADIUM_INGOT, 9)
                        .input(ModItems.PALLADIUM)
                        .criterion("has_palladium", conditionsFromItem(ModItems.PALLADIUM))
                        .offerTo(exporter);
                ShapelessRecipeJsonBuilder.create(Registries.ITEM,RecipeCategory.TOOLS,ModItems.PALLADIUM_PICKAXE )
                        .input(ModItems.PALLADIUM_PICKAXE)
                        .input(ModItems.PALLADIUM_PICKAXE)
                        .criterion("has_palladium_pickaxe", conditionsFromItem(ModItems.PALLADIUM_PICKAXE))
                        .offerTo(exporter, Mecharp.id("palladium_pickaxe_repair").toString());
                ShapelessRecipeJsonBuilder.create(Registries.ITEM,RecipeCategory.TOOLS,ModItems.MAGMARIUM_PICKAXE )
                        .input(ModItems.MAGMARIUM_PICKAXE)
                        .input(ModItems.MAGMARIUM_PICKAXE)
                        .criterion("has_magmarium_pickaxe", conditionsFromItem(ModItems.MAGMARIUM_PICKAXE))
                        .offerTo(exporter, Mecharp.id("magmarium_pickaxe_repair").toString());

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModItems.MAGMARIUM_BOOTS)
                        .pattern("   ")
                        .pattern("MNM")
                        .pattern("M M")
                        .input('M', ModItems.MAGMARIUM)
                        .input('N', Items.NETHER_STAR)
                        .criterion("magmarium", conditionsFromItem(ModItems.MAGMARIUM))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModItems.MAGMARIUM_LEGGINGS)
                        .pattern("MMM")
                        .pattern("MNM")
                        .pattern("M M")
                        .input('M', ModItems.MAGMARIUM)
                        .input('N', Items.NETHER_STAR)
                        .criterion("magmarium", conditionsFromItem(ModItems.MAGMARIUM))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModItems.MAGMARIUM_HELMET)
                        .pattern("MMM")
                        .pattern("MNM")
                        .pattern("   ")
                        .input('M', ModItems.MAGMARIUM)
                        .input('N', Items.NETHER_STAR)
                        .criterion("magmarium", conditionsFromItem(ModItems.MAGMARIUM))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModItems.MAGMARIUM_CHESTPLATE)
                        .pattern("MNM")
                        .pattern("MMM")
                        .pattern("MMM")
                        .input('M', ModItems.MAGMARIUM)
                        .input('N', Items.NETHER_STAR)
                        .criterion("magmarium", conditionsFromItem(ModItems.MAGMARIUM))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModItems.PALLADIUM_CHESTPLATE)
                        .pattern("P P")
                        .pattern("PPP")
                        .pattern("PPP")
                        .input('P', ModItems.PALLADIUM)
                        .criterion("palladium", conditionsFromItem(ModItems.PALLADIUM))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModItems.PALLADIUM_HELMET)
                        .pattern("PPP")
                        .pattern("P P")
                        .pattern("   ")
                        .input('P', ModItems.PALLADIUM)
                        .criterion("palladium", conditionsFromItem(ModItems.PALLADIUM))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModItems.PALLADIUM_LEGGINGS)
                        .pattern("PPP")
                        .pattern("P P")
                        .pattern("P P")
                        .input('P', ModItems.PALLADIUM)
                        .criterion("palladium", conditionsFromItem(ModItems.PALLADIUM))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModItems.PALLADIUM_BOOTS)
                        .pattern("P P")
                        .pattern("P P")
                        .pattern("   ")
                        .input('P', ModItems.PALLADIUM)
                        .criterion("palladium", conditionsFromItem(ModItems.PALLADIUM))
                        .offerTo(exporter);



            }



        };
    }

    @Override
    public String getName() {
        return "FabricDocsReferenceRecipeProvider";
    }
}
