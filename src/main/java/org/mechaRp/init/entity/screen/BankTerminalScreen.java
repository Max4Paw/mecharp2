package org.mechaRp.init.entity.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class BankTerminalScreen extends HandledScreen<BankTerminalScreenHandler> {
    private String currentPin = "";

    public BankTerminalScreen(BankTerminalScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 200;
        this.backgroundWidth = 300;
    }

    @Override
    protected void init() {
        super.init();
        initPinPadButtons();
    }

    private void initPinPadButtons() {
        int buttonSize = 30;
        int startX = this.x + this.backgroundWidth / 2 - 45;
        int startY = this.y + 100;

        // Кнопки 1-9
        for (int i = 1; i <= 9; i++) {
            final int number = i;
            int x = startX + ((i - 1) % 3) * (buttonSize + 5);
            int y = startY + ((i - 1) / 3) * (buttonSize + 5);

            this.addDrawableChild(ButtonWidget.builder(Text.literal(String.valueOf(i)), button -> {
                if (currentPin.length() < 4) {
                    currentPin += number;
                }
            }).dimensions(x, y, buttonSize, buttonSize).build());
        }

        // Кнопка 0
        this.addDrawableChild(ButtonWidget.builder(Text.literal("0"), button -> {
            if (currentPin.length() < 4) {
                currentPin += "0";
            }
        }).dimensions(startX + buttonSize + 5, startY + (buttonSize + 5) * 3, buttonSize, buttonSize).build());

        // Кнопка очистки
        this.addDrawableChild(ButtonWidget.builder(Text.literal("C"), button -> {
            currentPin = "";
        }).dimensions(startX, startY + (buttonSize + 5) * 3, buttonSize, buttonSize).build());

        // Кнопка подтверждения
        this.addDrawableChild(ButtonWidget.builder(Text.literal("OK"), button -> {
            if (currentPin.length() == 4) {
                handler.onEnterButtonClick(currentPin);
                currentPin = "";
            }
        }).dimensions(startX + (buttonSize + 5) * 2, startY + (buttonSize + 5) * 3, buttonSize, buttonSize).build());
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        context.fill(i, j, i + this.backgroundWidth, j + this.backgroundHeight, 0xFF2D2D2D);
        context.drawBorder(i, j, this.backgroundWidth, this.backgroundHeight, 0xFFFFFFFF);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);

        // Заголовок
        context.drawText(this.textRenderer, "Банковский терминал", this.x + 10, this.y + 10, 0xFFFFFF, false);
        context.drawText(this.textRenderer, "Введите PIN-код:", this.x + 10, this.y + 30, 0xFFFFFF, false);

        // Рисуем поле для PIN-кода
        int fieldX = this.x + 10;
        int fieldY = this.y + 50;
        int fieldWidth = 100;
        int fieldHeight = 20;

        // Фон поля
        context.fill(fieldX, fieldY, fieldX + fieldWidth, fieldY + fieldHeight, 0xFF000000);
        context.drawBorder(fieldX, fieldY, fieldWidth, fieldHeight, 0xFFFFFFFF);

        // 👇 Реалистичное отображение PIN-кода
        String displayText;
        if (currentPin.length() <= 1) {
            displayText = currentPin; // первая цифра видна
        } else {
            // Все цифры скрыты, кроме последней
            displayText = "*".repeat(currentPin.length() - 1) + currentPin.charAt(currentPin.length() - 1);
        }

        int textWidth = textRenderer.getWidth(displayText);
        context.drawText(textRenderer, displayText, fieldX + (fieldWidth - textWidth) / 2, fieldY + 6, 0xFFFFFF, false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode >= GLFW.GLFW_KEY_0 && keyCode <= GLFW.GLFW_KEY_9 && currentPin.length() < 4) {
            currentPin += (keyCode - GLFW.GLFW_KEY_0);
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_BACKSPACE && !currentPin.isEmpty()) {
            currentPin = currentPin.substring(0, currentPin.length() - 1);
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_ENTER && currentPin.length() == 4) {
            handler.onEnterButtonClick(currentPin);
            currentPin = "";
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
