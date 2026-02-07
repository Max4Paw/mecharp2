package org.mechaRp.mecharp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.config.AsbestitConfig;
import org.mechaRp.mecharp.config.MagmariumConfig;
import org.mechaRp.mecharp.config.PalladiumConfig;
import org.mechaRp.mecharp.entity.ModEntities;
import org.mechaRp.mecharp.groups.*;
import org.mechaRp.mecharp.registry.ModBlockEntities;
import org.mechaRp.mecharp.registry.ModScreenHandlers;
import org.mechaRp.mecharp.boss.BossCommands;
import org.mechaRp.mecharp.boss.BossEvents;
import org.mechaRp.mecharp.command.BankCardCommands;
import org.mechaRp.mecharp.item.ModItems;
import org.mechaRp.mecharp.util.tools.hammer.ModEventsMagmariumHammer;
import org.mechaRp.mecharp.util.tools.hammer.ModEventsPalladiumHammer;
import org.mechaRp.mecharp.world.ModFeatures;
import org.mechaRp.mecharp.world.ModOreGeneration;
import org.mechaRp.mecharp.world.gen.ModWorldGeneration;
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

        // 1. Сначала регистрируем конфиги
        registerConfigs();

        // 2. Регистрируем блоки и предметы
        registerBlocksAndItems();

        // 3. Регистрируем сущности
        registerEntities();

        // 4. Регистрируем структуры и генерацию мира
        registerWorldGen();

        // 5. Регистрируем группы творческого режима
        registerCreativeTabs();

        // 6. Регистрируем интерфейсы
        registerScreens();

        // 7. Регистрируем события
        registerEvents();

        // 8. Регистрируем команды
        registerCommands();

        // 9. Отладочная информация
        logDebugInfo();

        LOGGER.info("Мод MechaRP успешно загружен!");
    }

    private void registerConfigs() {
        PalladiumConfig.registerConfigs();
        MagmariumConfig.registerConfigs();
        AsbestitConfig.registerConfigs();
        LOGGER.info("Конфигурации зарегистрированы");
    }

    private void registerBlocksAndItems() {
        ModItems.initialize();
        LOGGER.info("Предметы зарегистрированы");

        BlockInit.registerBlocks();
        LOGGER.info("Блоки зарегистрированы");
        ModBlockEntities.register();

       // MyBlockEntityType.register();
    }

    private void registerEntities() {
        ModEntities.registerModEntities();
        LOGGER.info("Сущности зарегистрированы");
    }

    private void registerWorldGen() {
        // Сначала регистрируем структуры
        registerStructures();

        // Затем настраиваем генерацию
        ModWorldGeneration.generateModWorldGen();
        ModOreGeneration.generateOres();
        ModFeatures.initialize();

        LOGGER.info("Генерация мира настроена");
    }

    private void registerCreativeTabs() {
        CoinItemGroup.registerItemGroups();
        OreBlockGroup.registerBlockGroup();
        ToolItemGroup.registerToolItemGroups();
        ArmorGroup.registerArmorGroups();
        ItemBossGroup.registerToolItemGroups();
        LOGGER.info("Группы творческого режима зарегистрированы");
    }

    private void registerScreens() {
        ModScreenHandlers.registerScreenHandlers();
        LOGGER.info("Экраны зарегистрированы");
    }

    private void registerEvents() {
        BossEvents.register();

        // Регистрируем события для инструментов
        PlayerBlockBreakEvents.BEFORE.register(new ModEventsMagmariumHammer());
        PlayerBlockBreakEvents.BEFORE.register(new ModEventsPalladiumHammer());

        LOGGER.info("События зарегистрированы");
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            BossCommands.register(dispatcher);
            BankCardCommands.register(dispatcher);
        });
        LOGGER.info("Команды зарегистрированы");
    }

    private void registerStructures() {
        // Здесь должна быть регистрация ваших структур
        // Пример:
        // ModStructures.registerStructureFeatures();
        // ModStructures.registerStructureSets();

        LOGGER.info("Структуры зарегистрированы");
    }

    private void logDebugInfo() {
        LOGGER.info("=== ДЕБАГ ИНФОРМАЦИЯ ===");
        LOGGER.info("Проверка регистрации предметов:");

        // Проверяем регистрацию ключевых предметов
        checkItemRegistration("bronze_ore_material");
        checkItemRegistration("bronze_coin");

        LOGGER.info("=== КОНЕЦ ДЕБАГ ИНФОРМАЦИИ ===");
    }

    private void checkItemRegistration(String itemName) {
        boolean isRegistered = Registries.ITEM.containsId(Identifier.of(MOD_ID, itemName));
        LOGGER.info("{}: {}", itemName, isRegistered ? "ЗАРЕГИСТРИРОВАН" : "НЕ ЗАРЕГИСТРИРОВАН");
    }
}