package org.mechaRp.mecharp.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.mechaRp.mecharp.registry.ModScreenHandlers;

public class MyWardrobeScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final int rows = 7; // 7 строк
    private final int columns = 9; // 9 столбцов

    public MyWardrobeScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(63)); // 63 слота (7×9)
    }

    public MyWardrobeScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.WARDROBE, syncId);
        this.inventory = inventory;

        checkSize(inventory, 63);
        inventory.onOpen(playerInventory.player);

        // Расчет смещения для инвентаря игрока
        int inventoryOffset = (rows - 4) * 18;

        // Добавляем слоты шкафа (7 строк × 9 столбцов = 63 слота)
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                this.addSlot(new Slot(inventory, col + row * columns, 8 + col * 18, 18 + row * 18));
            }
        }

        // Инвентарь игрока (3 строки × 9 столбцов)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 103 + row * 18 + inventoryOffset));
            }
        }

        // Хотбар игрока
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 161 + inventoryOffset));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slotObject = this.slots.get(slot);

        if (slotObject != null && slotObject.hasStack()) {
            ItemStack itemStack2 = slotObject.getStack();
            itemStack = itemStack2.copy();

            int wardrobeSlots = rows * columns; // 63 слота

            if (slot < wardrobeSlots) {
                // Из шкафа в инвентарь игрока
                if (!this.insertItem(itemStack2, wardrobeSlots, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Из инвентаря игрока в шкаф
                if (!this.insertItem(itemStack2, 0, wardrobeSlots, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemStack2.isEmpty()) {
                slotObject.setStack(ItemStack.EMPTY);
            } else {
                slotObject.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slotObject.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}