package org.mechaRp.init.entity.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class BankTerminalScreenHandler extends ScreenHandler {
    private final SimpleInventory terminalInventory;

    public BankTerminalScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(BankTerminalScreenHandlers.BANK_TERMINAL_SCREEN_HANDLER, syncId);

        // создаём свой инвентарь на 1 слот (для карты)
        this.terminalInventory = new SimpleInventory(1);

        // слот для карты (X=80, Y=35 — координаты относительно левого верхнего угла GUI)
        this.addSlot(new Slot(terminalInventory, 0, 80, 35));

        // инвентарь игрока
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9,
                        176 + 8 + j * 18, 84 + i * 18));
            }
        }

        // хотбар
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i,
                    176 + 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack original = slot.getStack();
            newStack = original.copy();

            // если это слот карты (0)
            if (index == 0) {
                if (!this.insertItem(original, 1, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.insertItem(original, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (original.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }
    public void onDepositButtonClick() {
        // TODO: реализовать логику пополнения счета
        System.out.println("Deposit button clicked");
    }

    public void onWithdrawButtonClick() {
        // TODO: реализовать логику снятия денег
        System.out.println("Withdraw button clicked");
    }

    public SimpleInventory getTerminalInventory() {
        return terminalInventory;
    }
}
