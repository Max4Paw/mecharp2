//package org.mechaRp.mecharp;
//
//import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
//import net.minecraft.block.entity.BlockEntityType;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.util.Identifier;
//import org.mechaRp.init.BlockInit;
//import org.mechaRp.mecharp.block.entity.MyWardrobeBlockEntity;
//
//public class MyBlockEntityType {
//    public static final BlockEntityType<MyWardrobeBlockEntity> WARDROBE = FabricBlockEntityTypeBuilder.create(
//                    MyWardrobeBlockEntity::new, BlockInit.WARDROBE)
//            .build();
//
//    public static void register() {
//        // Используем "wardrobe" вместо "my_wardrobe" для консистентности
//        Registry.register(Registries.BLOCK_ENTITY_TYPE,
//                Identifier.of(Mecharp.MOD_ID, "wardrobe"),
//                WARDROBE
//        );
//    }
//}