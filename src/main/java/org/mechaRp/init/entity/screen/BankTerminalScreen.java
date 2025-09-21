package org.mechaRp.init.entity.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BankTerminalScreen extends HandledScreen<BankTerminalScreenHandler> {
    private static final int BANK_PANEL_WIDTH = 120;  // Ширина банковской панели
    private static final int INVENTORY_WIDTH = 176;   // Ширина инвентаря
    private static final int TOTAL_WIDTH = BANK_PANEL_WIDTH + INVENTORY_WIDTH; // Общая ширина
    private static final int HEIGHT = 166;            // Высота

    private static final Identifier SLOT_TEXTURE = Identifier.of("textures/gui/container/slot.png");

    private String inputPin = "";
    private boolean waitingForPin = false;

    public BankTerminalScreen(BankTerminalScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = TOTAL_WIDTH;
        this.backgroundHeight = HEIGHT;
    }

    @Override
    protected void init() {
        super.init();

        int bankStartX = (width - TOTAL_WIDTH) / 2 + 10;
        int bankStartY = (height - HEIGHT) / 2 + 30;

        // Кнопки PIN-пада (0–9) - только на банковской панели
        for (int i = 0; i < 10; i++) {
            int number = i;
            this.addDrawableChild(ButtonWidget.builder(
                            Text.literal(String.valueOf(i)),
                            button -> {
                                if (inputPin.length() < 4) {
                                    inputPin += number;
                                }
                            })
                    .dimensions(
                            bankStartX + (i % 3) * 22,
                            bankStartY + (i / 3) * 22,
                            20,
                            20
                    )
                    .build()
            );
        }

        // Кнопка Enter
        this.addDrawableChild(ButtonWidget.builder(
                                Text.translatable("gui.mecharp.enter"),
                                button -> {
                                    this.waitingForPin = false;
                                    // TODO: обработка ввода PIN и операций с картой
                                }
                        )
                        .dimensions(bankStartX + 70, bankStartY + 70, 60, 20)
                        .build()
        );

        // Кнопка Cancel
        this.addDrawableChild(ButtonWidget.builder(
                                Text.translatable("gui.mecharp.cancel"),
                                button -> inputPin = ""
                        )
                        .dimensions(bankStartX, bankStartY + 70, 60, 20)
                        .build()
        );

        // Кнопка Deposit (положить деньги)
        this.addDrawableChild(ButtonWidget.builder(
                                Text.translatable("gui.mecharp.deposit"),
                                button -> {
                                    if (handler instanceof BankTerminalScreenHandler bankHandler) {
                                        bankHandler.onDepositButtonClick();
                                    }
                                }
                        )
                        .dimensions(bankStartX, bankStartY + 95, 60, 20)
                        .build()
        );

        // Кнопка Withdraw (снять деньги)
        this.addDrawableChild(ButtonWidget.builder(
                                Text.translatable("gui.mecharp.withdraw"),
                                button -> {
                                    if (handler instanceof BankTerminalScreenHandler bankHandler) {
                                        bankHandler.onWithdrawButtonClick();
                                    }
                                }
                        )
                        .dimensions(bankStartX + 70, bankStartY + 95, 60, 20)
                        .build()
        );
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - TOTAL_WIDTH) / 2;
        int y = (height - HEIGHT) / 2;

        // === ЛЕВАЯ ЧАСТЬ: БАНКОВСКИЙ ТЕРМИНАЛ ===
        // Фон банковского терминала (точно по границам)
        context.fill(x, y, x + BANK_PANEL_WIDTH, y + HEIGHT, 0xFF2C2C2D);

        // Рамка банковского терминала
        context.fill(x, y, x + BANK_PANEL_WIDTH, y + 1, 0xFF555555); // Верхняя
        context.fill(x, y + HEIGHT - 1, x + BANK_PANEL_WIDTH, y + HEIGHT, 0xFF555555); // Нижняя
        context.fill(x, y, x + 1, y + HEIGHT, 0xFF555555); // Левая
        context.fill(x + BANK_PANEL_WIDTH - 1, y, x + BANK_PANEL_WIDTH, y + HEIGHT, 0xFF555555); // Правая

        // Заголовок банковского терминала
        context.drawText(textRenderer, Text.translatable("gui.mecharp.bank_terminal"),
                x + 10, y + 5, 0xFFFFFF, false);

        // Слот для банковской карты
        drawCardSlot(context, x + 20, y + 25);

        // Слот для денег (внесение/снятие)
        drawMoneySlot(context, x + 60, y + 25);

        // PIN-дисплей
        context.fill(x + 20, y + 60, x + 100, y + 80, 0xFF000000);
        String displayPin = "•".repeat(inputPin.length());
        context.drawText(textRenderer, displayPin, x + 25, y + 65, 0xFFFFFF, false);

        // Отображаем баланс
        context.drawText(textRenderer, Text.translatable("gui.mecharp.balance", "1,000"),
                x + 20, y + 85, 0xFFFFFF, false);

        // === ПРАВАЯ ЧАСТЬ: ИНВЕНТАРЬ ИГРОКА ===
        // Фон инвентаря (точно по границам)
        context.fill(x + BANK_PANEL_WIDTH, y, x + TOTAL_WIDTH, y + HEIGHT, 0xFFC6C6C6);

        // Рамка инвентаря
        context.fill(x + BANK_PANEL_WIDTH, y, x + TOTAL_WIDTH, y + 1, 0xFF555555); // Верхняя
        context.fill(x + BANK_PANEL_WIDTH, y + HEIGHT - 1, x + TOTAL_WIDTH, y + HEIGHT, 0xFF555555); // Нижняя
        context.fill(x + BANK_PANEL_WIDTH, y, x + BANK_PANEL_WIDTH + 1, y + HEIGHT, 0xFF555555); // Левая
        context.fill(x + TOTAL_WIDTH - 1, y, x + TOTAL_WIDTH, y + HEIGHT, 0xFF555555); // Правая

        // Отрисовываем стандартные слоты инвентаря
        drawPlayerInventorySlots(context, x + BANK_PANEL_WIDTH + 8, y + 8);
    }

    private void drawCardSlot(DrawContext context, int x, int y) {
        // Рисуем слот для карты
        context.fill(x, y, x + 32, y + 32, 0xFF555555);
        context.fill(x - 1, y - 1, x + 33, y + 33, 0xFF000000);
        context.drawText(textRenderer, Text.translatable("gui.mecharp.card_slot"), x, y - 10, 0xFFFFFF, false);

        // TODO: отображать карту, если она вставлена
    }

    private void drawMoneySlot(DrawContext context, int x, int y) {
        // Рисуем слот для денег
        context.fill(x, y, x + 32, y + 32, 0xFF555555);
        context.fill(x - 1, y - 1, x + 33, y + 33, 0xFF000000);
        context.drawText(textRenderer, Text.translatable("gui.mecharp.money_slot"), x, y - 10, 0xFFFFFF, false);

        // Временная заглушка - отображаем золотые монеты
        ItemStack moneyStack = new ItemStack(Items.GOLD_NUGGET, 64);
        context.drawItem(moneyStack, x + 8, y + 8);

        // Отображаем количество предметов
        if (moneyStack.getCount() > 1) {
            String countText = Integer.toString(moneyStack.getCount());
            context.drawText(textRenderer, countText,
                    x + 8 + 16 - textRenderer.getWidth(countText),
                    y + 8 + 16 - 8, 0xFFFFFF, false);
        }
    }

    private void drawPlayerInventorySlots(DrawContext context, int x, int y) {
        // Основной инвентарь (3×9) - стандартное расположение
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                drawInventorySlot(context, x + column * 18, y + row * 18);
            }
        }

        // Панель быстрого доступа (1×9) - правильное расстояние (4 пикселя от основного инвентаря)
        for (int column = 0; column < 9; ++column) {
            drawInventorySlot(context, x + column * 18, y + 58); // 3 ряда * 18 + 4 = 54 + 4 = 58
        }

        // Заголовок инвентаря
        context.drawText(textRenderer, this.playerInventoryTitle, x, y - 10, 0x404040, false);
    }

    private void drawInventorySlot(DrawContext context, int x, int y) {
        // Стандартный слот инвентаря
        context.fill(x, y, x + 16, y + 16, 0xFF8B8B8B);
        context.fill(x - 1, y - 1, x + 17, y + 17, 0xFF000000);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);

        int x = (width - TOTAL_WIDTH) / 2;
        int y = (height - HEIGHT) / 2;

        // Статус терминала
        if (waitingForPin) {
            context.drawText(textRenderer, Text.translatable("gui.mecharp.enter_pin"), x + 20, y + 115, 0xFFFFFF, false);
        } else {
            context.drawText(textRenderer, Text.translatable("gui.mecharp.insert_card"), x + 20, y + 115, 0xFFFFFF, false);
        }
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        // Не отображаем стандартные заголовки
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int x = (width - TOTAL_WIDTH) / 2;
        int y = (height - HEIGHT) / 2;

        // Обработка кликов только по банковской части
        if (mouseX >= x && mouseX <= x + BANK_PANEL_WIDTH) {
            // Клик по слоту для денег
            if (isPointWithinBounds(60, 25, 32, 32, mouseX, mouseY)) {
                // TODO: обработка клика по слоту для денег
                return true;
            }

            // Клик по слоту для карты
            if (isPointWithinBounds(20, 25, 32, 32, mouseX, mouseY)) {
                // TODO: обработка клика по слоту для карты
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}