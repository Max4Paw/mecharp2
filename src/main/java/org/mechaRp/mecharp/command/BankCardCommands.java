package org.mechaRp.mecharp.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.mechaRp.mecharp.item.BankCardItem;

public class BankCardCommands {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        // Команда активации карты
        dispatcher.register(CommandManager.literal("activatecard")
                .then(CommandManager.argument("pin", StringArgumentType.string())
                        .executes(context -> executeActivateCard(context))
                )
        );

        // Команда проверки баланса (пример)
        dispatcher.register(CommandManager.literal("checkbalance")
                .executes(context -> executeCheckBalance(context))
        );
    }

    private static int executeActivateCard(com.mojang.brigadier.context.CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        if (!source.isExecutedByPlayer()) {
            source.sendError(Text.literal("Эта команда может быть выполнена только игроком"));
            return 0;
        }

        var player = source.getPlayer();
        var stack = player.getMainHandStack();

        if (stack.getItem() instanceof BankCardItem) {
            String pin = StringArgumentType.getString(context, "pin");

            if (pin.length() != 4 || !pin.matches("\\d+")) {
                source.sendError(Text.literal("PIN должен состоять из 4 цифр"));
                return 0;
            }

            BankCardItem.setPinCode(stack, pin);
            player.sendMessage(Text.translatable("message.mecharp.card_activated"), false);
            return 1;
        } else {
            source.sendError(Text.literal("Держите банковскую карту в основной руке"));
            return 0;
        }
    }

    private static int executeCheckBalance(com.mojang.brigadier.context.CommandContext<ServerCommandSource> context) {
        // Реализация проверки баланса
        return 1;
    }
}