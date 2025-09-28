package org.mechaRp.init.entity.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import org.mechaRp.init.networking.BankTerminalPayloads;
import org.mechaRp.mecharp.item.BankCardItem;
import org.mechaRp.mecharp.item.ModItems;
import org.mechaRp.mecharp.util.ModTags;

public class BankTerminalScreenHandler extends ScreenHandler {
    public static final int BANK_PANEL_WIDTH = 120;
    public static final int INVENTORY_WIDTH = 176;
    public static final int TOTAL_WIDTH = BANK_PANEL_WIDTH + INVENTORY_WIDTH;
    public static final int HEIGHT = 230;
    public static final int CARD_SLOT_INDEX = 0;
    public static final int MONEY_SLOT_INDEX = 1;

    private final Inventory terminalInventory;
    private final PlayerEntity player;
    private String currentPin = "";
    private long balance = 0;

    public BankTerminalScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(BankTerminalScreenHandlers.BANK_TERMINAL_SCREEN_HANDLER, syncId);

        this.player = playerInventory.player;
        this.terminalInventory = new SimpleInventory(2); // 0 - карта, 1 - деньги

        // Слот для карты
        this.addSlot(new Slot(terminalInventory, CARD_SLOT_INDEX, 15, 195) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == ModItems.BANK_CARD_GREEN||
                        stack.isIn(ModTags.BANK_CARDS)||
                        isBankCard(stack);

            }

        });

        // Слот для денег
        this.addSlot(new Slot(terminalInventory, MONEY_SLOT_INDEX, 65, 195) {
            @Override
            public boolean canInsert(ItemStack stack) {
                // Замените на проверку вашего денежного предмета
                return stack.getItem() == ModItems.BRONZE_COIN || // если есть конкретный предмет
                        stack.isIn(ModTags.MONEY_ITEMS) || // или тег для денег
                        isMoneyItem(stack); // или кастомная проверка
            }
        });

        // Инвентарь игрока
        int invStartX = BANK_PANEL_WIDTH + 8;
        int invStartY = 18;

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, column + row * 9 + 9,
                        invStartX + column * 18, invStartY + row * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, invStartX + i * 18, invStartY + 58));
        }
    }

    // Метод для проверки денежного предмета (настройте под вашу систему)
    private boolean isMoneyItem(ItemStack stack) {
        // Пример: проверка по имени предмета
        String itemName = stack.getItem().toString().toLowerCase();
        return itemName.contains("coin") ||
                itemName.contains("money") ||
                itemName.contains("currency");

        // Или проверка по NBT тегам
        // return stack.hasNbt() && stack.getNbt().contains("MoneyValue");
    }
    private boolean isBankCard(ItemStack stack) {
        return stack.getItem() instanceof BankCardItem;
    }



    @Override
    public boolean canUse(PlayerEntity player) {
        return this.terminalInventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();

            if (index < 2) {
                if (!this.insertItem(itemStack2, 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Определяем в какой слот перемещать
                if (itemStack2.getItem() instanceof BankCardItem) {
                    if (!this.insertItem(itemStack2, CARD_SLOT_INDEX, CARD_SLOT_INDEX + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (isMoneyItem(itemStack2)) {
                    if (!this.insertItem(itemStack2, MONEY_SLOT_INDEX, MONEY_SLOT_INDEX + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    return ItemStack.EMPTY;
                }
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }

    // Методы для связи с Screen
    public void sendPinUpdate(String pin, boolean showActual) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            BankTerminalPayloads.sendPinUpdate(serverPlayer, pin, showActual);
        }
    }

    public void onCancelButtonClick() {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            BankTerminalPayloads.sendCancelAction(serverPlayer);
        }
    }

    public void onEnterButtonClick(String pin) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            BankTerminalPayloads.sendPinVerification(serverPlayer, pin);
        }
    }

    public void onDepositButtonClick(String pin) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            ItemStack moneyStack = terminalInventory.getStack(MONEY_SLOT_INDEX);
            BankTerminalPayloads.sendDepositAction(serverPlayer, pin, moneyStack);
        }
    }

    public void onWithdrawButtonClick(String pin) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            BankTerminalPayloads.sendWithdrawAction(serverPlayer, pin);
        }
    }

    // Методы для обновления UI
    public void updatePinDisplay(String pin, boolean showActual) {
        this.currentPin = pin;
    }

    public void updateBalance(long newBalance) {
        this.balance = newBalance;
    }

    public long getBalance() {
        return balance;
    }

    public ItemStack getCardSlotStack() {
        return terminalInventory.getStack(CARD_SLOT_INDEX);
    }

    public ItemStack getMoneySlotStack() {
        return terminalInventory.getStack(MONEY_SLOT_INDEX);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        if (!player.getWorld().isClient) {
            returnItemsToPlayer(player);
        }
    }

    private void returnItemsToPlayer(PlayerEntity player) {
        // Возвращаем карту
        ItemStack card = terminalInventory.getStack(CARD_SLOT_INDEX);
        if (!card.isEmpty()) {
            player.getInventory().offerOrDrop(card);
            terminalInventory.setStack(CARD_SLOT_INDEX, ItemStack.EMPTY);
        }

        // Возвращаем деньги
        ItemStack money = terminalInventory.getStack(MONEY_SLOT_INDEX);
        if (!money.isEmpty()) {
            player.getInventory().offerOrDrop(money);
            terminalInventory.setStack(MONEY_SLOT_INDEX, ItemStack.EMPTY);
        }
    }
}