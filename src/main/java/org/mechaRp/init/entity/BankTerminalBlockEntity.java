package org.mechaRp.init.entity;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.mechaRp.init.BlockInit;
import org.mechaRp.mecharp.item.BankCardItem;
import org.mechaRp.mecharp.item.ModItems;

public class BankTerminalBlockEntity extends BlockEntity {
    private String currentCardNumber = "";
    private int failedAttempts = 0;
    private long cooldownUntil = 0;

    public BankTerminalBlockEntity(BlockPos pos, BlockState state) {
        super(BlockInit.BANK_TERMINAL_BLOCK_ENTITY, pos, state);
    }

    public boolean insertCard(PlayerEntity player, ItemStack card) {
        if (isCardBlocked()) {
            player.sendMessage(Text.translatable("message.mecharp.card_blocked"), false);
            return false;
        }

        if (BankCardItem.getPinCode(card).isEmpty()) {
            player.sendMessage(Text.translatable("message.mecharp.card_not_activated"), false);
            return false;
        }

        currentCardNumber = BankCardItem.getCardNumber(card);
        player.sendMessage(Text.translatable("message.mecharp.card_inserted"), false);
        return true;
    }

    public boolean verifyPin(PlayerEntity player, ItemStack card, String pin) {
        if (!BankCardItem.getCardNumber(card).equals(currentCardNumber)) {
            player.sendMessage(Text.translatable("message.mecharp.invalid_card"), false);
            return false;
        }

        if (System.currentTimeMillis() < cooldownUntil) {
            long remaining = (cooldownUntil - System.currentTimeMillis()) / 1000;
            player.sendMessage(Text.translatable("message.mecharp.cooldown", remaining), false);
            return false;
        }

        if (BankCardItem.getPinCode(card).equals(pin)) {
            failedAttempts = 0;
            player.sendMessage(Text.translatable("message.mecharp.pin_correct"), false);
            return true;
        } else {
            failedAttempts++;
            if (failedAttempts >= 3) {
                cooldownUntil = System.currentTimeMillis() + 30_000; // 30 секунд блокировки
                player.sendMessage(Text.translatable("message.mecharp.card_temporarily_blocked"), false);
            }
            player.sendMessage(Text.translatable("message.mecharp.pin_incorrect"), false);
            return false;
        }
    }

    public void checkBalance(PlayerEntity player, ItemStack card) {
        if (!BankCardItem.getCardNumber(card).equals(currentCardNumber)) {
            player.sendMessage(Text.translatable("message.mecharp.invalid_card"), false);
            return;
        }

        long balance = BankCardItem.getBalance(card);
        player.sendMessage(Text.translatable("message.mecharp.balance", balance), false);
    }

    public void deposit(PlayerEntity player, ItemStack card, int amount) {
        if (!BankCardItem.getCardNumber(card).equals(currentCardNumber)) {
            player.sendMessage(Text.translatable("message.mecharp.invalid_card"), false);
            return;
        }

        int bronzeCoins = countPlayerCoins(player, ModItems.BRONZE_COIN);
        int silverCoins = countPlayerCoins(player, ModItems.SILVER_COIN);
        int totalCoins = bronzeCoins + (silverCoins * 100);

        if (totalCoins >= amount) {
            removeCoins(player, amount);
            long newBalance = BankCardItem.getBalance(card) + amount;
            BankCardItem.setBalance(card, newBalance);
            markDirty();
            player.sendMessage(Text.translatable("message.mecharp.deposit_success", amount, newBalance), false);
        } else {
            player.sendMessage(Text.translatable("message.mecharp.insufficient_coins"), false);
        }
    }

    public void withdraw(PlayerEntity player, ItemStack card, int amount) {
        if (!BankCardItem.getCardNumber(card).equals(currentCardNumber)) {
            player.sendMessage(Text.translatable("message.mecharp.invalid_card"), false);
            return;
        }

        long balance = BankCardItem.getBalance(card);

        if (balance >= amount) {
            giveCoins(player, amount);
            long newBalance = balance - amount;
            BankCardItem.setBalance(card, newBalance);
            markDirty();
            player.sendMessage(Text.translatable("message.mecharp.withdraw_success", amount, newBalance), false);
        } else {
            player.sendMessage(Text.translatable("message.mecharp.insufficient_balance"), false);
        }
    }

    public void ejectCard(PlayerEntity player) {
        currentCardNumber = "";
        failedAttempts = 0;
        player.sendMessage(Text.translatable("message.mecharp.card_ejected"), false);
    }

    private boolean isCardBlocked() {
        return System.currentTimeMillis() < cooldownUntil;
    }

    private int countPlayerCoins(PlayerEntity player, net.minecraft.item.Item coinType) {
        int count = 0;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(coinType)) {
                count += stack.getCount();
            }
        }
        return count;
    }

    private void removeCoins(PlayerEntity player, int amount) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(ModItems.SILVER_COIN)) {
                while (amount >= 100 && stack.getCount() > 0) {
                    stack.decrement(1);
                    amount -= 100;
                }
            } else if (stack.isOf(ModItems.BRONZE_COIN)) {
                while (amount > 0 && stack.getCount() > 0) {
                    stack.decrement(1);
                    amount--;
                }
            }
            if (amount <= 0) break;
        }
    }

    private void giveCoins(PlayerEntity player, int amount) {
        while (amount >= 100) {
            player.getInventory().insertStack(new ItemStack(ModItems.SILVER_COIN, 1));
            amount -= 100;
        }
        if (amount > 0) {
            player.getInventory().insertStack(new ItemStack(ModItems.BRONZE_COIN, amount));
        }
    }

    @Override
    protected void writeData(WriteView view) {
        view.put("currentCardNumber", Codec.STRING, currentCardNumber);
        view.put("failedAttempts", Codec.INT, failedAttempts);
        view.put("cooldownUntil", Codec.LONG, cooldownUntil);
    }

    @Override
    protected void readData(ReadView view) {
        currentCardNumber = view.read("currentCardNumber", Codec.STRING).orElse("");
        failedAttempts = view.read("failedAttempts", Codec.INT).orElse(0);
        cooldownUntil = view.read("cooldownUntil", Codec.LONG).orElse(0L);
    }
}
