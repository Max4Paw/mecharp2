// BankTerminalScreenHandler.java
package org.mechaRp.init.entity.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class BankTerminalScreenHandler extends ScreenHandler {

    public BankTerminalScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(BankTerminalScreenHandlers.BANK_TERMINAL_SCREEN_HANDLER, syncId);

        // Добавляем слоты игрока
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Добавляем горячие слоты
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true; // Или ваша логика проверки доступа
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }
}