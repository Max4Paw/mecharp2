package org.mechaRp.init.entity;
import java.lang.Exception;
import java.lang.Throwable;
public class InsufficientCoinsException extends Exception {

    // 1. Конструктор без параметров
    public InsufficientCoinsException() {
        super();
    }

    // 2. Конструктор с сообщением об ошибке
    public InsufficientCoinsException(String message) {
        super(message);
    }

    // 3. Конструктор с сообщением и внутренним исключением
    public InsufficientCoinsException(String message, Throwable cause) {
        super(message, cause);
    }

    // 4. Конструктор для сохранения цепочки исключений
    public InsufficientCoinsException(Throwable cause) {
        super(cause);
    }

    // 5. Конструктор с сообщением, причиной и информацией о стеке
    public InsufficientCoinsException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    // 6. Можно добавить дополнительные поля для специфической информации
    private String coinType;
    private int requiredAmount;
    private int availableAmount;

    // 7. Геттеры для дополнительных полей
    public String getCoinType() {
        return coinType;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    // 8. Конструктор с дополнительной информацией о монетах
    public InsufficientCoinsException(
            String message,
            String coinType,
            int requiredAmount,
            int availableAmount
    ) {
        super(message);
        this.coinType = coinType;
        this.requiredAmount = requiredAmount;
        this.availableAmount = availableAmount;
    }

    // 9. Переопределение метода toString() для удобного вывода информации
    @Override
    public String toString() {
        return super.toString() +
                "\nCoin Type: " + coinType +
                "\nRequired Amount: " + requiredAmount +
                "\nAvailable Amount: " + availableAmount;
    }
}