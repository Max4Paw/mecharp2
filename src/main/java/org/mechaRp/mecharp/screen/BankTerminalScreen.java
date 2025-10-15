package org.mechaRp.mecharp.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mechaRp.mecharp.item.BankCardItem;

public class BankTerminalScreen extends HandledScreen<BankTerminalScreenHandler> {
    // Используем стандартную текстуру верстака для тестирования
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/gui/container/crafting_table.png");
    private ButtonWidget depositButton;

    public BankTerminalScreen(BankTerminalScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    protected void init() {
        super.init();

        // Кнопка "Внести" - позиционируем относительно центра
        this.depositButton = ButtonWidget.builder(
                Text.literal("Внести"),
                button -> this.onDepositButtonClick()
        ).dimensions(this.x + 60, this.y + 70, 56, 20).build();

        this.addDrawableChild(this.depositButton);

        // Обновляем состояние кнопки
        updateButtonState();
    }

    private void onDepositButtonClick() {
        if (this.client != null && this.client.interactionManager != null && this.handler != null) {
            this.client.interactionManager.clickButton(this.handler.syncId, 0);
        }
    }

    private void updateButtonState() {
        // Активируем кнопку только если есть и карта и монеты
        boolean hasCard = !this.handler.getTerminalInventory().getStack(0).isEmpty();
        boolean hasCoins = !this.handler.getTerminalInventory().getStack(1).isEmpty();
        this.depositButton.active = hasCard && hasCoins;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        // Простой серый фон для тестирования
        context.fill(this.x, this.y, this.x + this.backgroundWidth, this.y + this.backgroundHeight, 0xFFC0C0C0);

        // Рамка
        context.fill(this.x, this.y, this.x + this.backgroundWidth, this.y + 1, 0xFF000000); // верх
        context.fill(this.x, this.y + this.backgroundHeight - 1, this.x + this.backgroundWidth, this.y + this.backgroundHeight, 0xFF000000); // низ
        context.fill(this.x, this.y, this.x + 1, this.y + this.backgroundHeight, 0xFF000000); // лево
        context.fill(this.x + this.backgroundWidth - 1, this.y, this.x + this.backgroundWidth, this.y + this.backgroundHeight, 0xFF000000); // право
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        // Отображаем заголовок
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 0x404040, false);

        // Отображаем баланс если есть карта
        ItemStack cardStack = this.handler.getTerminalInventory().getStack(0);
        if (!cardStack.isEmpty()) {
            int balance = BankCardItem.getBalance(cardStack);
            Text balanceText = Text.literal("Баланс: " + balance);
            context.drawText(this.textRenderer, balanceText, 8, 20, 0x404040, false);
        }

        // Отображаем подсказки
        Text cardHint = Text.literal("← Карта");
        Text coinsHint = Text.literal("← Монеты");
        context.drawText(this.textRenderer, cardHint, 100, 24, 0x404040, false);
        context.drawText(this.textRenderer, coinsHint, 100, 54, 0x404040, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);

        // Обновляем состояние кнопки каждый кадр
        updateButtonState();
    }
}