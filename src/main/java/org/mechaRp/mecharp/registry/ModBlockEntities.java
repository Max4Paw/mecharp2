package org.mechaRp.mecharp.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.Mecharp;
import org.mechaRp.mecharp.block.entity.MyWardrobeBlockEntity;
import org.mechaRp.init.BlockInit;

public class ModBlockEntities {
    public static BlockEntityType<MyWardrobeBlockEntity> WARDROBE_BLOCK_ENTITY;

    public static void register() {
        WARDROBE_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(Mecharp.MOD_ID, "wardrobe"),
                FabricBlockEntityTypeBuilder.create(
                        MyWardrobeBlockEntity::new,
                        BlockInit.WARDROBE // используем блок из BlockInit
                ).build()
        );
    }
}