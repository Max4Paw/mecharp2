package org.mechaRp.mecharp.world.gen;


import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import org.mechaRp.mecharp.world.ModConfiguredFeatures;
import org.mechaRp.mecharp.world.ModPlacedFeatures;

public class ModWorldGeneration {
    public static void generateModWorldGen(){

    }
    public static void bootstrap(RegistryBuilder registryBuilder) {
        // Регистрируем конфигурации руд
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);

        // Регистрируем расположение руд
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
    }
}