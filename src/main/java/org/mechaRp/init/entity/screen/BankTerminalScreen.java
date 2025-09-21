package org.mechaRp.init.entity.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BankTerminalScreen extends HandledScreen<BankTerminalScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of("mecharp", "textures/gui/bank_terminal.png");
    private String inputPin = "";
    private boolean waitingForPin = false;

    public BankTerminalScreen(BankTerminalScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        // Кнопки цифрового PIN-пада
        int startX = (width - backgroundWidth) / 2 + 30;
        int startY = (height - backgroundHeight) / 2 + 50;

        for (int i = 0; i < 10; i++) {
            int number = i;
            this.addDrawableChild(ButtonWidget.builder(Text.literal(String.valueOf(i)), button -> {
                if (inputPin.length() < 4) {
                    inputPin += number;
                }
            }).dimensions(startX + (i % 3) * 20, startY + (i / 3) * 20, 18, 18).build());
        }

        // Кнопка подтверждения
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.mecharp.enter"), button -> {
            // Отправка PIN-кода на сервер
        }).dimensions(startX + 60, startY + 60, 40, 20).build());

        // Кнопка отмены
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.mecharp.cancel"), button -> {
            inputPin = "";
        }).dimensions(startX, startY + 60, 40, 20).build());
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        // Правильный вызов для Minecraft 1.21.8
        //context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        context.fill(x, y, x + backgroundWidth, y + backgroundHeight, 0xFF888888);

        // Отображение введенного PIN-кода (звездочки)
        String displayPin = "•".repeat(inputPin.length());
        context.drawText(textRenderer, displayPin, x + 80, y + 30, 0x000000, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        if (waitingForPin) {
            context.drawText(textRenderer, "§6Enter PIN Code", x + 50, y + 15, 0x000000, false);
        } else {
            context.drawText(textRenderer, "§6Insert Bank Card", x + 45, y + 15, 0x000000, false);
        }
    }
}