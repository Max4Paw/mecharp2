package org.mechaRp.init.networking;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.server.network.ServerPlayerEntity;
import org.mechaRp.mecharp.Mecharp;

public class BankTerminalPayloads {
    public static final Identifier PIN_UPDATE = Identifier.of(Mecharp.MOD_ID, "bank_terminal_pin_update");
    public static final Identifier PIN_VERIFY = Identifier.of(Mecharp.MOD_ID, "bank_terminal_pin_verify");
    public static final Identifier DEPOSIT = Identifier.of(Mecharp.MOD_ID, "bank_terminal_deposit");
    public static final Identifier WITHDRAW = Identifier.of(Mecharp.MOD_ID, "bank_terminal_withdraw");
    public static final Identifier CANCEL = Identifier.of(Mecharp.MOD_ID, "bank_terminal_cancel");

    public static void sendPinUpdate(ServerPlayerEntity player, String pin, boolean showActual) {
        // Реализация отправки пакета
    }

    public static void sendPinVerification(ServerPlayerEntity player, String pin) {
        // Реализация отправки пакета
    }

    public static void sendDepositAction(ServerPlayerEntity player, String pin, ItemStack moneyStack) {
        // Реализация отправки пакета
    }

    public static void sendWithdrawAction(ServerPlayerEntity player, String pin) {
        // Реализация отправки пакета
    }

    public static void sendCancelAction(ServerPlayerEntity player) {
        // Реализация отправки пакета
    }
}