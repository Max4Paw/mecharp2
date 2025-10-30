package org.mechaRp.mecharp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.item.ModItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {

    protected ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {

        AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(ModItems.PALLADIUM,
                        Text.translatable("advancement.revivalrp.root.title").formatted(Formatting.LIGHT_PURPLE), Text.translatable("advancement.revivalrp.root.description").formatted(Formatting.LIGHT_PURPLE),
                        Identifier.of(Mecharp.MOD_ID, "textures/icon.png"),
                        AdvancementFrame.TASK, true, true, false)
                .criterion("has_palladium", InventoryChangedCriterion.Conditions.items(ModItems.PALLADIUM))
                .build(consumer, String.valueOf(Identifier.of(Mecharp.MOD_ID, "root")));

        AdvancementEntry palladiumTools = Advancement.Builder.create()
                .parent(rootAdvancement)
                .display(ModItems.PALLADIUM_PICKAXE,
                        Text.translatable("advancement.revivalrp.palladiumtools.title").formatted(Formatting.LIGHT_PURPLE), Text.translatable("advancement.revivalrp.palladiumtools.description").formatted(Formatting.LIGHT_PURPLE),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("has_palladium_pickaxe", InventoryChangedCriterion.Conditions.items(ModItems.PALLADIUM_PICKAXE))
              //  .criterion("has_palladium_axe", InventoryChangedCriterion.Conditions.items(ModItems.VIBRANIUM_AXE))
                .criterion("has_palladium_sword", InventoryChangedCriterion.Conditions.items(ModItems.PALLADIUM_SWORD))
             //   .criterion("has_palladium_shovel", InventoryChangedCriterion.Conditions.items(ModItems.VIBRANIUM_SHOVEL))
              //  .criterion("has_palladium_hoe", InventoryChangedCriterion.Conditions.items(ModItems.VIBRANIUM_HOE))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .build(consumer, String.valueOf(Identifier.of(Mecharp.MOD_ID, "palladiumtools")));

        AdvancementEntry palladiumArmor = Advancement.Builder.create()
                .parent(rootAdvancement)
                .display(ModItems.PALLADIUM_CHESTPLATE,
                        Text.translatable("advancement.revivalrp.palladiumarmor.title").formatted(Formatting.LIGHT_PURPLE), Text.translatable("advancement.revivalrp.palladiumarmor.description").formatted(Formatting.LIGHT_PURPLE),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("has_palladium_helmet", InventoryChangedCriterion.Conditions.items(ModItems.PALLADIUM_HELMET))
                .criterion("has_palladium_chestplate", InventoryChangedCriterion.Conditions.items(ModItems.PALLADIUM_CHESTPLATE))
                .criterion("has_palladium_leggings", InventoryChangedCriterion.Conditions.items(ModItems.PALLADIUM_LEGGINGS))
                .criterion("has_palladium_boots", InventoryChangedCriterion.Conditions.items(ModItems.PALLADIUM_BOOTS))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .build(consumer, String.valueOf(Identifier.of(Mecharp.MOD_ID, "palladiumarmor")));

        AdvancementEntry palladiumSpecialTools = Advancement.Builder.create()
                .parent(palladiumTools)
                .display(ModItems.PALLADIUM_PAXEL,
                        Text.translatable("advancement.revivalrp.palladiumspecialtools.title").formatted(Formatting.LIGHT_PURPLE), Text.translatable("advancement.revivalrp.palladiumspecialtools.description").formatted(Formatting.LIGHT_PURPLE),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("has_palladium_hammer", InventoryChangedCriterion.Conditions.items(ModItems.PALLADIUM_HAMMER))
                .criterion("has_palladium_paxel", InventoryChangedCriterion.Conditions.items(ModItems.PALLADIUM_PAXEL))
            //    .criterion("has_palladium_excavator", InventoryChangedCriterion.Conditions.items(ModItems.VIBRANIUM_EXCAVATOR))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .build(consumer, String.valueOf(Identifier.of(Mecharp.MOD_ID, "palladiumspecialtools")));

        AdvancementEntry magmarium = Advancement.Builder.create()
                .parent( rootAdvancement)
                .display(ModItems.MAGMARIUM,
                        Text.translatable("advancement.revivalrp.magmarium.title").formatted(Formatting.RED), Text.translatable("advancement.revivalrp.magmarium.description").formatted(Formatting.RED),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("has_magmarium", InventoryChangedCriterion.Conditions.items(ModItems.MAGMARIUM))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .build(consumer, String.valueOf(Identifier.of(Mecharp.MOD_ID, "magmarium")));

        AdvancementEntry magmariumTools = Advancement.Builder.create()
                .parent(magmarium)
                .display(ModItems.MAGMARIUM_PICKAXE,
                        Text.translatable("advancement.revivalrp.magmariumtools.title").formatted(Formatting.RED), Text.translatable("advancement.revivalrp.magmariumtools.description").formatted(Formatting.RED),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("has_magmarium_pickaxe", InventoryChangedCriterion.Conditions.items(ModItems.MAGMARIUM_PICKAXE))
               // .criterion("has_magmarium_axe", InventoryChangedCriterion.Conditions.items(ModItems.VULPUS_AXE))
                .criterion("has_magmarium_sword", InventoryChangedCriterion.Conditions.items(ModItems.MAGMARIUM_SWORD))
            //    .criterion("has_magmarium_shovel", InventoryChangedCriterion.Conditions.items(ModItems.VULPUS_SHOVEL))
              //  .criterion("has_magmarium_hoe", InventoryChangedCriterion.Conditions.items(ModItems.VULPUS_HOE))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .build(consumer, String.valueOf(Identifier.of(Mecharp.MOD_ID, "magmariumtools")));

        AdvancementEntry magmariumArmor = Advancement.Builder.create()
                .parent(magmarium)
                .display(ModItems.MAGMARIUM_CHESTPLATE,
                        Text.translatable("advancement.revivalrp.magmariumarmor.title").formatted(Formatting.RED), Text.translatable("advancement.revivalrp.magmariumarmor.description").formatted(Formatting.RED),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("has_magmarium_helmet", InventoryChangedCriterion.Conditions.items(ModItems.MAGMARIUM_HELMET))
                .criterion("has_magmarium_chestplate", InventoryChangedCriterion.Conditions.items(ModItems.MAGMARIUM_CHESTPLATE))
                .criterion("has_magmarium_leggings", InventoryChangedCriterion.Conditions.items(ModItems.MAGMARIUM_LEGGINGS))
                .criterion("has_magmarium_boots", InventoryChangedCriterion.Conditions.items(ModItems.MAGMARIUM_BOOTS))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .build(consumer, String.valueOf(Identifier.of(Mecharp.MOD_ID, "magmariumarmor")));

        AdvancementEntry magmariumSpecialTools = Advancement.Builder.create()
                .parent(magmariumTools)
                .display(ModItems.MAGMARIUM_HAMMER,
                        Text.translatable("advancement.revivalrp.magmariumspecialtools.title").formatted(Formatting.RED), Text.translatable("advancement.revivalrp.magmariumtools.description").formatted(Formatting.RED),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("has_magmarium_hammer", InventoryChangedCriterion.Conditions.items(ModItems.MAGMARIUM_HAMMER))
              //  .criterion("has_magmarium_paxel", InventoryChangedCriterion.Conditions.items(ModItems.VULPUS_PAXEL))
             //   .criterion("has_magmarium_excavator", InventoryChangedCriterion.Conditions.items(ModItems.VULPUS_EXCAVATOR))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .build(consumer, String.valueOf(Identifier.of(Mecharp.MOD_ID, "magmariumspecialtools")));

//        AdvancementEntry asbestit = Advancement.Builder.create()
//                .parent(asbestitTools)
//                .display(ModItems.ASBESTIT,
//                        Text.translatable("advancement.immersiveores.enderium.title").formatted(Formatting.DARK_AQUA), Text.translatable("advancement.immersiveores.enderium.descrption").formatted(Formatting.DARK_AQUA),
//                        null,
//                        AdvancementFrame.TASK, true, true, false)
//                .criterion("has_enderium_ingot", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_INGOT))
//                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
//                .build(consumer, String.valueOf(Identifier.of(ImmersiveOres.MOD_ID, "enderium")));
//
//        AdvancementEntry asbestitTools = Advancement.Builder.create()
//                .parent(asbestit)
//                .display(ModItems.ENDERIUM_PICKAXE,
//                        Text.translatable("advancement.immersiveores.enderiumtools.title").formatted(Formatting.DARK_AQUA), Text.translatable("advancement.immersiveores.enderiumtools.descrption").formatted(Formatting.DARK_AQUA),
//                        null,
//                        AdvancementFrame.TASK, true, true, false)
//                .criterion("has_enderium_pickaxe", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_PICKAXE))
//                .criterion("has_enderium_axe", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_AXE))
//                .criterion("has_enderium_sword", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_SWORD))
//                .criterion("has_enderium_shovel", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_SHOVEL))
//                .criterion("has_enderium_hoe", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_HOE))
//                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
//                .build(consumer, String.valueOf(Identifier.of(ImmersiveOres.MOD_ID, "enderiumtools")));
//
//        AdvancementEntry asbestitArmor = Advancement.Builder.create()
//                .parent(asbestit)
//                .display(ModItems.ENDERIUM_CHESTPLATE,
//                        Text.translatable("advancement.immersiveores.enderiumarmor.title").formatted(Formatting.DARK_AQUA), Text.translatable("advancement.immersiveores.enderiumarmor.descrption").formatted(Formatting.DARK_AQUA),
//                        null,
//                        AdvancementFrame.TASK, true, true, false)
//                .criterion("has_enderium_helmet", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_HELMET))
//                .criterion("has_enderium_chestplate", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_CHESTPLATE))
//                .criterion("has_enderium_leggings", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_LEGGINGS))
//                .criterion("has_enderium_boots", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_BOOTS))
//                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
//                .build(consumer, String.valueOf(Identifier.of(ImmersiveOres.MOD_ID, "enderiumarmor")));
//
//        AdvancementEntry asbestitSpecialTools = Advancement.Builder.create()
//                .parent(asbestitTools)
//                .display(ModItems.ENDERIUM_PAXEL,
//                        Text.translatable("advancement.immersiveores.enderiumspecialtools.title").formatted(Formatting.DARK_AQUA), Text.translatable("advancement.immersiveores.enderiumspecialtools.descrption").formatted(Formatting.DARK_AQUA),
//                        null,
//                        AdvancementFrame.TASK, true, true, false)
//                .criterion("has_enderium_hammer", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_HAMMER))
//                .criterion("has_enderium_paxel", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_PAXEL))
//                .criterion("has_enderium_excavator", InventoryChangedCriterion.Conditions.items(ModItems.ENDERIUM_EXCAVATOR))
//                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
//                .build(consumer, String.valueOf(Identifier.of(ImmersiveOres.MOD_ID, "enderiumspecialtools")))
//
//                ;
    }
}