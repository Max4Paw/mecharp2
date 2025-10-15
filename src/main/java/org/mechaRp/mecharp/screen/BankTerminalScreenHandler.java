package org.mechaRp.mecharp.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.mechaRp.mecharp.item.BankCardItem;
import org.mechaRp.mecharp.item.ModItems;
import org.mechaRp.mecharp.registry.ModScreenHandlers;
import org.mechaRp.mecharp.registry.ModTags;

public class BankTerminalScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PlayerInventory playerInventory;

    public BankTerminalScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ModScreenHandlers.BANK_TERMINAL, syncId);
        this.inventory = new SimpleInventory(2); // 0 - карта, 1 - монеты
        this.playerInventory = playerInventory;

        // Слот для банковской карты
        this.addSlot(new Slot(inventory, 0, 80, 20) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.BANK_CARDS);
            }

            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                return true;
            }
        });

        // Слот для монет
        this.addSlot(new Slot(inventory, 1, 80, 50) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.MONEY_ITEMS);
            }

            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                return true;
            }
        });

        // Инвентарь игрока (3 ряда по 9)
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }

        // Хотбар игрока
        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            // Если кликнули по слотам терминала (0-1)
            if (slotIndex < 2) {
                // Перемещаем из терминала в инвентарь игрока
                if (!this.insertItem(originalStack, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            }
            // Если кликнули по инвентарю игрока (2-38)
            else {
                // Проверяем тип предмета и перемещаем в соответствующий слот терминала
                if (originalStack.isIn(ModTags.BANK_CARDS)) {
                    if (!this.insertItem(originalStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (originalStack.isIn(ModTags.MONEY_ITEMS)) {
                    if (!this.insertItem(originalStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                // Если предмет не подходит для терминала, не перемещаем
                else {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id == 0) { // Кнопка "Внести"
            ItemStack cardStack = inventory.getStack(0);
            ItemStack coinStack = inventory.getStack(1);

            if (!cardStack.isEmpty() && !coinStack.isEmpty()) {
                int coinValue = getCoinValue(coinStack);
                int totalAmount = coinValue * coinStack.getCount();

                int currentBalance = BankCardItem.getBalance(cardStack);
                BankCardItem.setBalance(cardStack, currentBalance + totalAmount);

                // Удаляем монеты после внесения
                inventory.setStack(1, ItemStack.EMPTY);

                // Обновляем интерфейс
                this.sendContentUpdates();

                return true;
            }
        }
        return false;
    }

    private int getCoinValue(ItemStack coinStack) {
        if (coinStack.isOf(ModItems.BRONZE_COIN)) {
            return 1;
        } else if (coinStack.isOf(ModItems.SILVER_COIN)) {
            return 10;
        } else if (coinStack.isOf(ModItems.PLATINUM_COIN)) {
            return 100;
        }
        return 0;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);

        // Возвращаем предметы из терминала в инвентарь игрока при закрытии
        if (!player.getWorld().isClient()) {
            this.dropInventory(player, this.inventory);
        }
    }

    protected void dropInventory(PlayerEntity player, Inventory inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                player.getInventory().offerOrDrop(stack);
                inventory.removeStack(i);
            }
        }
    }

    // Геттеры для доступа к инвентарям извне (например, для GUI)
    public Inventory getTerminalInventory() {
        return inventory;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }
}