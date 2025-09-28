package org.mechaRp.init.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
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

import java.util.UUID;

public class BankTerminalBlockEntity extends BlockEntity {
    private String currentPin = "";
    private boolean pinVerified = false;
    private String cardNumber = "";
    private long balance = 0;
    private UUID currentUserId = null;

    public BankTerminalBlockEntity(BlockPos pos, BlockState state) {
        super(BlockInit.BANK_TERMINAL_BLOCK_ENTITY, pos, state);
    }

    public void setCurrentUser(PlayerEntity player) {
        this.currentUserId = player != null ? player.getUuid() : null;
    }

    public boolean insertCard(PlayerEntity player, ItemStack card) {
        if (currentUserId != null && !currentUserId.equals(player.getUuid())) {
            player.sendMessage(Text.literal("Терминал занят"), false);
            return false;
        }

        if (!BankCardItem.hasPin(card)) {
            player.sendMessage(Text.literal("Карта не активирована"), false);
            return false;
        }

        this.currentUserId = player.getUuid();
        this.cardNumber = generateCardNumber(card);
        this.balance = getCardBalance(card);
        this.pinVerified = false;
        markDirty();
        return true;
    }

    public boolean verifyPin(String pin) {
        if (currentUserId == null) return false;

        PlayerEntity player = getCurrentPlayer();
        if (player == null) return false;

        ItemStack card = findCardInInventory(player);
        if (card.isEmpty()) return false;

        if (BankCardItem.verifyPin(card, pin)) {
            this.pinVerified = true;
            this.currentPin = pin;
            markDirty();
            return true;
        }
        return false;
    }

    public void processDeposit(ItemStack moneyStack) {
        if (!pinVerified || currentUserId == null) return;

        PlayerEntity player = getCurrentPlayer();
        if (player == null) return;

        ItemStack card = findCardInInventory(player);
        if (card.isEmpty()) return;

        int amount = calculateMoneyAmount(moneyStack);
        if (amount > 0) {
            long newBalance = getCardBalance(card) + amount;
            setCardBalance(card, newBalance);
            this.balance = newBalance;

            moneyStack.setCount(0);
            markDirty();
        }
    }

    public void processWithdraw(int amount) {
        if (!pinVerified || currentUserId == null) return;

        PlayerEntity player = getCurrentPlayer();
        if (player == null) return;

        ItemStack card = findCardInInventory(player);
        if (card.isEmpty()) return;

        long currentBalance = getCardBalance(card);
        if (currentBalance >= amount) {
            setCardBalance(card, currentBalance - amount);
            this.balance = currentBalance - amount;
            giveMoneyToPlayer(player, amount);
            markDirty();
        }
    }

    public void ejectCard() {
        if (currentUserId != null) {
            PlayerEntity player = getCurrentPlayer();
            if (player != null) {
                player.sendMessage(Text.literal("Карта извлечена"), false);
            }
        }
        resetSession();
    }

    private void resetSession() {
        this.currentPin = "";
        this.pinVerified = false;
        this.cardNumber = "";
        this.balance = 0;
        this.currentUserId = null;
        markDirty();
    }

    private PlayerEntity getCurrentPlayer() {
        if (currentUserId == null || world == null) return null;
        return world.getPlayerByUuid(currentUserId);
    }

    private ItemStack findCardInInventory(PlayerEntity player) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.getItem() instanceof BankCardItem &&
                    BankCardItem.hasPin(stack)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    private String generateCardNumber(ItemStack card) {
        UUID ownerUuid = BankCardItem.getOwnerUuid(card);
        return ownerUuid != null ? ownerUuid.toString().substring(0, 8) : "unknown";
    }

    private long getCardBalance(ItemStack card) {
        NbtComponent nbtComponent = card.get(DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent != null) {
            NbtCompound nbt = nbtComponent.getNbt();
            return nbt.getLong("Balance").orElse(0L);
        }
        return 0;
    }

    private void setCardBalance(ItemStack card, long balance) {
        NbtComponent existingNbt = card.get(DataComponentTypes.CUSTOM_DATA);
        NbtCompound nbt = existingNbt != null ? existingNbt.getNbt() : new NbtCompound();
        nbt.putLong("Balance", balance);
        card.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    private int calculateMoneyAmount(ItemStack moneyStack) {
        if (moneyStack.getItem() == ModItems.BRONZE_COIN) {
            return moneyStack.getCount();
        } else if (moneyStack.getItem() == ModItems.SILVER_COIN) {
            return moneyStack.getCount() * 100;
        } else if (moneyStack.getItem() == ModItems.PLATINUM_COIN) {
            return moneyStack.getCount() * 10000;
        }
        return 0;
    }

    private void giveMoneyToPlayer(PlayerEntity player, int amount) {
        int platinumCoins = amount / 10000;
        if (platinumCoins > 0) {
            player.getInventory().offerOrDrop(new ItemStack(ModItems.PLATINUM_COIN, platinumCoins));
            amount %= 10000;
        }

        int silverCoins = amount / 100;
        if (silverCoins > 0) {
            player.getInventory().offerOrDrop(new ItemStack(ModItems.SILVER_COIN, silverCoins));
            amount %= 100;
        }

        if (amount > 0) {
            player.getInventory().offerOrDrop(new ItemStack(ModItems.BRONZE_COIN, amount));
        }
    }

    @Override
    public void writeData(WriteView view) {
        super.writeData(view);

        view.putString("CurrentPin", this.currentPin);
        view.putBoolean("PinVerified", this.pinVerified);
        view.putString("CardNumber", this.cardNumber);
        view.putLong("Balance", this.balance);
        if (this.currentUserId != null) {
            view.putString("CurrentUserId", this.currentUserId.toString());
        }
    }

    @Override
    public void readData(ReadView view) {
        super.readData(view);

        this.currentPin = view.getString("CurrentPin", "");
        this.pinVerified = view.getBoolean("PinVerified", false);
        this.cardNumber = view.getString("CardNumber", "");
        this.balance = view.getLong("Balance", 0L);

        String userIdString = view.getString("CurrentUserId", "");
        if (!userIdString.isEmpty()) {
            try {
                this.currentUserId = UUID.fromString(userIdString);
            } catch (IllegalArgumentException e) {
                this.currentUserId = null;
            }
        } else {
            this.currentUserId = null;
        }
    }

    public String getCurrentPin() { return currentPin; }
    public boolean isPinVerified() { return pinVerified; }
    public String getCardNumber() { return cardNumber; }
    public long getBalance() { return balance; }
    public boolean hasUser() { return currentUserId != null; }
}