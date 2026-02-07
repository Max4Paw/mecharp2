package org.mechaRp.mecharp.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class MyWardrobeScreen extends HandledScreen<MyWardrobeScreenHandler> {
    private final int rows = 9; // 9 рядов

    public MyWardrobeScreen(MyWardrobeScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 114 + this.rows * 18; // Высота для 9 рядов
        this.backgroundWidth = 176; // Стандартная ширина
        this.playerInventoryTitleY = this.backgroundHeight - 94; // Позиция заголовка инвентаря игрока
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        // Рисуем черный фон для тестирования
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        context.fill(x, y, x + this.backgroundWidth, y + this.backgroundHeight, 0xFF000000);

        // Рисуем разделитель между шкафом и инвентарем игрока
        int separatorY = y + this.rows * 18 + 17;
        context.fill(x, separatorY, x + this.backgroundWidth, separatorY + 1, 0xFFFFFFFF);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }
}