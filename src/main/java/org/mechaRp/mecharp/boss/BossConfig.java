package org.mechaRp.mecharp.boss;

import net.minecraft.server.MinecraftServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BossConfig {
    private static final String FILE_NAME = "mecharp_boss_config.json";
    private static BossConfig instance;

    private final File configFile;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Параметры конфигурации
    public boolean testMode = false;
    public boolean enableBossAbilities = true;
    public boolean enableWorldGenSpawn = true;
    public int leshySpawnWeight = 5;
    public Map<String, Boolean> enabledBosses = new HashMap<>();

    public BossConfig(MinecraftServer server) {
        // Исправление: убрал .toPath() так как getRunDirectory() уже возвращает Path
        this.configFile = server.getRunDirectory().resolve("config").resolve(FILE_NAME).toFile();
        this.enabledBosses.put("leshy", true);
        loadConfig();
    }

    // Статический метод для получения конфигурации
    public static BossConfig getConfig(MinecraftServer server) {
        if (instance == null) {
            instance = new BossConfig(server);
        }
        return instance;
    }

    // Статические геттеры для удобства
    public static boolean isTestMode() {
        return instance != null && instance.testMode;
    }

    public static boolean areBossAbilitiesEnabled() {
        return instance != null && instance.enableBossAbilities;
    }

    public static boolean isWorldGenSpawnEnabled() {
        return instance != null && instance.enableWorldGenSpawn;
    }

    public static int getLeshySpawnWeight() {
        return instance != null ? instance.leshySpawnWeight : 5;
    }

    public static boolean isBossEnabled(String bossId) {
        return instance != null && instance.enabledBosses.getOrDefault(bossId, true);
    }

    // Методы для изменения настроек
    public static void setTestMode(boolean testMode) {
        if (instance != null) {
            instance.testMode = testMode;
            instance.saveConfig();
        }
    }

    public static void setEnableBossAbilities(boolean enableBossAbilities) {
        if (instance != null) {
            instance.enableBossAbilities = enableBossAbilities;
            instance.saveConfig();
        }
    }

    private void loadConfig() {
        if (!configFile.exists()) {
            // Создаем дефолтную конфигурацию
            saveConfig();
            return;
        }

        try (FileReader reader = new FileReader(configFile)) {
            BossConfig loaded = gson.fromJson(reader, BossConfig.class);
            if (loaded != null) {
                this.testMode = loaded.testMode;
                this.enableBossAbilities = loaded.enableBossAbilities;
                this.enableWorldGenSpawn = loaded.enableWorldGenSpawn;
                this.leshySpawnWeight = loaded.leshySpawnWeight;
                if (loaded.enabledBosses != null) {
                    this.enabledBosses = loaded.enabledBosses;
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load boss config: " + e.getMessage());
            // Используем значения по умолчанию
        }
    }

    private void saveConfig() {
        try {
            // Создаем директорию config если не существует
            configFile.getParentFile().mkdirs();

            try (FileWriter writer = new FileWriter(configFile)) {
                gson.toJson(this, writer);
            }
        } catch (IOException e) {
            System.err.println("Failed to save boss config: " + e.getMessage());
        }
    }
}