package org.mechaRp.mecharp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.config.MagmariumConfig;
import org.mechaRp.mecharp.config.PalladiumConfig;
import org.mechaRp.mecharp.entity.ModEntities;
import org.mechaRp.mecharp.groups.*;
import org.mechaRp.mecharp.registry.ModScreenHandlers;
import org.mechaRp.mecharp.boss.BossCommands;
import org.mechaRp.mecharp.boss.BossEvents;
import org.mechaRp.mecharp.command.BankCardCommands;
import org.mechaRp.mecharp.item.ModItems;

import org.mechaRp.mecharp.world.ModFeatures;
import org.mechaRp.mecharp.world.ModOreGeneration;
import org.mechaRp.mecharp.world.gen.ModWorldGeneration;
// ДОБАВЬТЕ ЭТИ ИМПОРТЫ
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mecharp implements ModInitializer {
    public static final String MOD_ID = "mecharp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String name) {
        return Identifier.of(Mecharp.MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Начало инициализации мода MechaRP...");
        PalladiumConfig.registerConfigs();
        MagmariumConfig.registerConfigs();

        // 1. Сначала регистрируем компоненты данных
        //  MyDataComponents.register();
        //LOGGER.info("Компоненты данных зарегистрированы");

        // 2. Затем регистрируем предметы и блоки
        ModItems.initialize();
        LOGGER.info("Предметы зарегистрированы");

        BlockInit.registerBlocks();
        LOGGER.info("Блоки зарегистрированы");

        // 3. РЕГИСТРИРУЕМ СТРУКТУРЫ (ДОБАВЬТЕ ЭТО!)
        registerStructures();
        LOGGER.info("Структуры зарегистрированы");

        ModWorldGeneration.generateModWorldGen();
        ModOreGeneration.generateOres();

        // 4. Регистрируем группы творческого режима
        CoinItemGroup.registerItemGroups();
        OreBlockGroup.registerBlockGroup();
        ToolItemGroup.registerToolItemGroups();
        ArmorGroup.registerArmorGroups();
        ItemBossGroup.registerToolItemGroups();
        LOGGER.info("Группы творческого режима зарегистрированы");

        // 5. Регистрируем сущности
        ModEntities.registerModEntities();
        LOGGER.info("Сущности зарегистрированы");

        // 6. Регистрируем интерфейсы
        ModScreenHandlers.registerScreenHandlers();
        LOGGER.info("Экраны зарегистрированы");

        // 7. Регистрируем события
        BossEvents.register();
        LOGGER.info("События зарегистрированы");

        // 8. Регистрируем команды
        registerCommands();
        LOGGER.info("Команды зарегистрированы");

        // 9. Генерация мира (должна быть после регистрации структур)
        ModFeatures.initialize();
        LOGGER.info("Генерация мира настроена");

        // Отладочная информация
        LOGGER.info("=== ДЕБАГ ИНФОРМАЦИЯ ===");
        LOGGER.info("Проверка регистрации предметов:");
        LOGGER.info("bronze_ore_material: " +
                Registries.ITEM.containsId(Identifier.of(MOD_ID, "bronze_ore_material")));
        LOGGER.info("bronze_coin: " +
                Registries.ITEM.containsId(Identifier.of(MOD_ID, "bronze_coin")));

        LOGGER.info("Мод MechaRP успешно загружен!");
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            // Регистрация команд боссов
            BossCommands.register(dispatcher);
            // Регистрация команд банковских карт
            BankCardCommands.register(dispatcher);
        });
    }

    // ДОБАВЬТЕ ЭТОТ МЕТОД ДЛЯ РЕГИСТРАЦИИ СТРУКТУР
    private void registerStructures() {
        // Регистрируем структуры в правильном порядке


        LOGGER.info("Structures зарегистрированы");

        // Инициализируем генерацию мира для структур
        ModWorldGeneration.initialize();
        LOGGER.info("Генерация структур настроена");
    }
}