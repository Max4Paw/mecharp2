package org.mechaRp.mecharp.boss;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.mechaRp.mecharp.boss.BossRespawnManager;

import java.util.concurrent.CompletableFuture;

public class BossCommands {
    private static final SuggestionProvider<ServerCommandSource> BOSS_SUGGESTIONS =
            (context, builder) -> getBossSuggestions(builder);

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(net.minecraft.server.command.CommandManager.literal("boss")
                .requires(source -> source.hasPermissionLevel(2))
                .then(net.minecraft.server.command.CommandManager.literal("spawn")
                        .then(net.minecraft.server.command.CommandManager.argument("type", StringArgumentType.string())
                                .suggests(BOSS_SUGGESTIONS)
                                .executes(context -> {
                                    String bossType = StringArgumentType.getString(context, "type");
                                    ServerCommandSource source = context.getSource();
                                    BlockPos pos = BlockPos.ofFloored(source.getPosition());

                                    if (BossSpawnManager.spawnBoss(source.getWorld(), pos, bossType)) {
                                        source.sendMessage(Text.literal("Босс " + bossType + " заспавнен!"));
                                    } else {
                                        source.sendMessage(Text.literal("Этот босс еще не возродился!"));
                                    }
                                    return 1;
                                })))
                .then(net.minecraft.server.command.CommandManager.literal("info")
                        .executes(context -> {
                            ServerCommandSource source = context.getSource();
                            BossRespawnManager respawnManager = BossRespawnManager.getServerState(
                                    source.getServer()
                            );

                            for (String bossId : BossSpawnManager.getBossRegistry().keySet()) {
                                BaseBoss boss = BossSpawnManager.getBossRegistry().get(bossId).apply((ServerWorld) source.getWorld());
                                long respawnTime = respawnManager.getTimeUntilRespawn(
                                        bossId, boss.getRespawnTimeMs());

                                if (respawnTime > 0) {
                                    source.sendMessage(Text.literal(bossId + ": возродится через " +
                                            formatTime(respawnTime)));
                                } else {
                                    source.sendMessage(Text.literal(bossId + ": доступен для боя"));
                                }
                            }
                            return 1;
                        })));
    }

    private static CompletableFuture<Suggestions> getBossSuggestions(SuggestionsBuilder builder) {
        for (String bossId : BossSpawnManager.getBossRegistry().keySet()) {
            builder.suggest(bossId);
        }
        return builder.buildFuture();
    }

    private static String formatTime(long millis) {
        long days = millis / (24 * 60 * 60 * 1000);
        long hours = (millis % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
        return days + "д " + hours + "ч";
    }
}