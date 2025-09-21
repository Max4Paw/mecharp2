package org.mechaRp.init.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Интерфейс для блока обмена монет
 * Определяет контракт для работы с системой обмена монет
 */
public interface ExchangeBlockInterface {

    /**
     * Подсчитывает бронзовые монеты в инвентаре игрока
     * @param player игрок
     * @return количество бронзовых монет
     */
    int countPlayerBronzeCoins(PlayerEntity player);

    /**
     * Подсчитывает серебряные монеты в инвентаре игрока
     * @param player игрок
     * @return количество серебряных монет
     */
    int countPlayerSilverCoins(PlayerEntity player);
    int ExchangeScreenHandler();

    /**
     * Подсчитывает все монеты игрока в эквиваленте бронзовых
     * @param player игрок
     * @return общее количество монет (1 серебряная = 100 бронзовых)
     */
    int countAllPlayerCoins(PlayerEntity player);

    /**
     * Проверяет возможность обмена бронзовых монет на серебряные
     * @param player игрок
     * @return true если обмен возможен
     */
    boolean canExchangeBronzeToSilver(PlayerEntity player);

    /**
     * Проверяет возможность обмена серебряных монет на бронзовые
     * @param player игрок
     * @return true если обмен возможен
     */
    boolean canExchangeSilverToBronze(PlayerEntity player);

    /**
     * Обменивает бронзовые монеты из блока на серебряные для игрока
     * @param player игрок
     */
    void exchangeBronzeToSilver(PlayerEntity player);

    /**
     * Обменивает серебряные монеты игрока на бронзовые в блоке
     * @param player игрок
     */
    void exchangeSilverToBronze(PlayerEntity player);

    /**
     * Возвращает количество бронзовых монет в блоке
     * @return количество бронзовых монет
     */
    int getBronzeCoins();

    /**
     * Добавляет бронзовые монеты в блок
     * @param amount количество монет для добавления
     */
    void addBronzeCoins(int amount);

    /**
     * Статический метод для получения реализации интерфейса из мира
     * @param world мир
     * @param pos позиция блока
     * @return экземпляр ExchangeBlockInterface или null если блок не поддерживает интерфейс
     */
    static ExchangeBlockInterface get(World world, BlockPos pos) {
        if (world != null && pos != null) {
            if (world.getBlockEntity(pos) instanceof ExchangeBlockInterface exchangeBlock) {
                return exchangeBlock;
            }
        }
        return null;
    }
}