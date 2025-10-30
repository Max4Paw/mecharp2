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
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/gui/container/crafting_table.png");

    private ButtonWidget depositButton;
    private ButtonWidget withdrawButton;
    private int balance = -1;

    public BankTerminalScreen(BankTerminalScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init(); // Здесь вычисляются x и y

        // Кнопка "Внести" - позиционируем в левой части интерфейса
        this.depositButton = ButtonWidget.builder(
                Text.literal("Внести"),
                button -> this.onDepositButtonClick()
        ).dimensions(this.x + 30, this.y + 50, 60, 20).build();

        // Кнопка "Вывести" - под кнопкой "Внести"
        this.withdrawButton = ButtonWidget.builder(
                Text.literal("Вывести"),
                button -> this.onWithdrawButtonClick()
        ).dimensions(this.x + 30, this.y + 75, 60, 20).build();

        this.addDrawableChild(this.depositButton);
        this.addDrawableChild(this.withdrawButton);

        updateButtonStates();
    }

    private void onDepositButtonClick() {
        if (this.client != null && this.client.interactionManager != null && this.handler != null) {
            this.client.interactionManager.clickButton(this.handler.syncId, 0);
        }
    }

    private void onWithdrawButtonClick() {
        if (this.client != null && this.client.interactionManager != null && this.handler != null) {
            this.client.interactionManager.clickButton(this.handler.syncId, 1);
        }
    }

    private void updateBalance() {
        if (this.handler != null) {
            ItemStack cardStack = this.handler.getSlot(0).getStack();
            if (!cardStack.isEmpty() && cardStack.getItem() instanceof BankCardItem) {
                this.balance = BankCardItem.getBalance(cardStack);
            } else {
                this.balance = -1;
            }
        }
    }

    private void updateButtonStates() {
        if (this.handler == null) return;

        ItemStack cardStack = this.handler.getSlot(0).getStack();
        ItemStack coinsStack = this.handler.getSlot(1).getStack();

        boolean hasCard = !cardStack.isEmpty() && cardStack.getItem() instanceof BankCardItem;
        boolean hasCoins = !coinsStack.isEmpty();
        boolean hasBalance = this.balance > 0;

        this.depositButton.active = hasCard && hasCoins;
        this.withdrawButton.active = hasCard && hasBalance;

        // Отладочный вывод в консоль
        System.out.println("Card: " + hasCard + ", Coins: " + hasCoins + ", Balance: " + hasBalance);
        System.out.println("Deposit button active: " + this.depositButton.active + ", Withdraw button active: " + this.withdrawButton.active);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        // Основной фон
        int backgroundColor = 0xFFC0C0C0;
        context.fill(this.x, this.y, this.x + this.backgroundWidth, this.y + this.backgroundHeight, backgroundColor);

        // Рамка
        int borderColor = 0xFF000000;
        context.fill(this.x, this.y, this.x + this.backgroundWidth, this.y + 1, borderColor); // верх
        context.fill(this.x, this.y + this.backgroundHeight - 1, this.x + this.backgroundWidth, this.y + this.backgroundHeight, borderColor); // низ
        context.fill(this.x, this.y, this.x + 1, this.y + this.backgroundHeight, borderColor); // лево
        context.fill(this.x + this.backgroundWidth - 1, this.y, this.x + this.backgroundWidth, this.y + this.backgroundHeight, borderColor); // право

        // Области для слотов
        int slotColor = 0xFF808080;
        // Слот для карты (левый верхний)
        context.fill(this.x + 8, this.y + 20, this.x + 26, this.y + 38, slotColor);
        // Слот для монет (под картой)
        context.fill(this.x + 8, this.y + 45, this.x + 26, this.y + 63, slotColor);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        // Заголовок
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 0x404040, false);

        // Обновляем и отображаем баланс
        updateBalance();
        if (this.balance >= 0) {
            Text balanceText = Text.literal("Баланс: " + this.balance);
            context.drawText(this.textRenderer, balanceText, 30, 22, 0x404040, false);
        } else {
            Text noCardText = Text.literal("Вставьте карту");
            context.drawText(this.textRenderer, noCardText, 30, 22, 0x404040, false);
        }

        // Подсказки для слотов
        Text cardHint = Text.literal("Карта");
        Text coinsHint = Text.literal("Монеты");
        context.drawText(this.textRenderer, cardHint, 30, 35, 0x404040, false);
        context.drawText(this.textRenderer, coinsHint, 30, 50, 0x404040, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta); // Стандартный фон (полупрозрачный)
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    public void handledScreenTick() {
        super.handledScreenTick();
        updateButtonStates();
    }
}