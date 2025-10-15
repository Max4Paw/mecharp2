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

public class BossRespawnManager {
    private static final String FILE_NAME = "mecharp_boss_respawn.json";
    private final Map<String, Long> bossLastKillTimes = new HashMap<>();
    private final File saveFile;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public BossRespawnManager(MinecraftServer server) {
        this.saveFile = server.getRunDirectory().resolve("boss_respawn").resolve(FILE_NAME).toFile();
        loadData();
    }

    public static BossRespawnManager getServerState(MinecraftServer server) {
        return new BossRespawnManager(server);
    }

    public boolean canSpawnBoss(String bossId, long respawnTime) {
        // Используем конфиг вместо статического поля
        if (BossConfig.isTestMode()) {
            return true;
        }

        Long lastKillTime = bossLastKillTimes.get(bossId);
        if (lastKillTime == null) {
            return true;
        }

        long currentTime = System.currentTimeMillis();
        return (currentTime - lastKillTime) >= respawnTime;
    }

    public void recordBossKill(String bossId) {
        bossLastKillTimes.put(bossId, System.currentTimeMillis());
        saveData();
    }

    public long getTimeUntilRespawn(String bossId, long respawnTime) {
        Long lastKillTime = bossLastKillTimes.get(bossId);
        if (lastKillTime == null) return 0;

        long timePassed = System.currentTimeMillis() - lastKillTime;
        return Math.max(0, respawnTime - timePassed);
    }

    private void loadData() {
        if (!saveFile.exists()) return;

        try (FileReader reader = new FileReader(saveFile)) {
            @SuppressWarnings("unchecked")
            Map<String, Long> loaded = gson.fromJson(reader, HashMap.class);
            if (loaded != null) {
                bossLastKillTimes.clear();
                bossLastKillTimes.putAll(loaded);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try (FileWriter writer = new FileWriter(saveFile)) {
            gson.toJson(bossLastKillTimes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}