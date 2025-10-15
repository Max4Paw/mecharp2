package org.mechaRp.mecharp.world.gen;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import org.mechaRp.mecharp.Mecharp;

public class ModStructureKeys {
    public static final RegistryKey<Structure> LESHY_ALTAR =
            RegistryKey.of(RegistryKeys.STRUCTURE, Identifier.of(Mecharp.MOD_ID, "leshy_altar"));
}