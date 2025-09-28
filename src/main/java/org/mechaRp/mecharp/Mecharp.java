package org.mechaRp.mecharp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mechaRp.init.BlockInit;
import org.mechaRp.init.entity.screen.BankTerminalScreenHandlers;
import org.mechaRp.mecharp.groups.CoinItemGroup;
import org.mechaRp.mecharp.groups.OreBlockGroup;
import org.mechaRp.mecharp.groups.ToolItemGroup;
import org.mechaRp.mecharp.item.BankCardItem;
import org.mechaRp.mecharp.item.ModItems;
import org.mechaRp.mecharp.item.MyDataComponents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class Mecharp implements ModInitializer {
    public static final String MOD_ID = "mecharp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String name) {
        return Identifier.of(Mecharp.MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        MyDataComponents.register();
        ModItems.initialize();
        BlockInit.registerBlocks();
        CoinItemGroup.registerItemGroups();
        OreBlockGroup.registerBlockGroup();
        ToolItemGroup.registerToolItemGroups();
        BankTerminalScreenHandlers.registerScreenHandlers();
        registerCommands();

        // Отладочная информация
        LOGGER.info("=== ДЕБАГ ИНФОРМАЦИЯ ===");
        LOGGER.info("Проверка регистрации предметов:");
        LOGGER.info("bronze_ore_material: " +
                Registries.ITEM.containsId(Identifier.of(MOD_ID, "bronze_ore_material")));
        LOGGER.info("bronze_coin: " +
                Registries.ITEM.containsId(Identifier.of(MOD_ID, "bronze_coin")));

        // Простая проверка рецептов
        LOGGER.info("Проверка рецептов...");
        LOGGER.info("Рецепты загружены (детальная проверка требует другого API)");

        LOGGER.info("Мод MechaRP загружен!");
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("activatecard")
                    .then(CommandManager.argument("pin", StringArgumentType.string())
                            .executes(context -> {
                                ServerCommandSource source = context.getSource();
                                if (!source.isExecutedByPlayer()) {
                                    source.sendError(Text.literal("This command can only be executed by a player"));
                                    return 0;
                                }

                                var player = source.getPlayer();
                                var stack = player.getMainHandStack();

                                if (stack.getItem() instanceof BankCardItem) {
                                    String pin = StringArgumentType.getString(context, "pin");

                                    // Проверка что PIN состоит из 4 цифр
                                    if (pin.length() != 4 || !pin.matches("\\d+")) {
                                        source.sendError(Text.literal("PIN must be 4 digits"));
                                        return 0;
                                    }

                                    BankCardItem.setPinCode(stack, pin);
                                    player.sendMessage(Text.translatable("message.mecharp.card_activated"), false);
                                    return 1;
                                } else {
                                    source.sendError(Text.literal("Hold a bank card in your main hand"));
                                    return 0;
                                }
                            })
                    )
            );
        });
    }


}