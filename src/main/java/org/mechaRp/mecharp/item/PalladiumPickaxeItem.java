package org.mechaRp.mecharp.item;

import net.minecraft.item.Item;

public class PalladiumPickaxeItem extends Item {
    private final ModToolMaterials material;

    public PalladiumPickaxeItem(ModToolMaterials material, Settings settings) {
        super(settings);
        this.material = material;
    }

    public ModToolMaterials getMaterial() {
        return material;
    }
}