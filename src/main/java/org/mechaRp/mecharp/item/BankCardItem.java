package org.mechaRp.mecharp.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;

import java.util.Optional;

public class BankCardItem extends Item {
    public BankCardItem(Settings settings) {
        super(settings);
    }

    // Устанавливает PIN-код, сохраняя существующий баланс
    public static void setPinCode(ItemStack stack, String pin) {
        NbtCompound nbt = getOrCreateNbt(stack);
        nbt.putString("PinCode", pin);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    // Получает PIN-код
    public static String getPinCode(ItemStack stack) {
        NbtCompound nbt = getNbt(stack);
        if (nbt != null && nbt.contains("PinCode")) {
            Optional<String> pinOptional = nbt.getString("PinCode");
            return pinOptional.orElse("");
        }
        return "";
    }

    // Устанавливает баланс, сохраняя существующий PIN-код
    public static void setBalance(ItemStack stack, int balance) {
        NbtCompound nbt = getOrCreateNbt(stack);
        nbt.putInt("Balance", balance);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    // Получает баланс
    public static int getBalance(ItemStack stack) {
        NbtCompound nbt = getNbt(stack);
        if (nbt != null && nbt.contains("Balance")) {
            Optional<Integer> balanceOptional = nbt.getInt("Balance");
            return balanceOptional.orElse(0);
        }
        return 0;
    }

    // Вспомогательный метод для получения NBT (или null, если его нет)
    private static NbtCompound getNbt(ItemStack stack) {
        NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
        return nbtComponent != null ? nbtComponent.getNbt() : null;
    }

    // Вспомогательный метод для получения или создания NBT
    private static NbtCompound getOrCreateNbt(ItemStack stack) {
        NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
        return nbtComponent != null ? nbtComponent.getNbt() : new NbtCompound();
    }

    // Метод для проверки, есть ли данные на карте
    public static boolean hasData(ItemStack stack) {
        return stack.get(DataComponentTypes.CUSTOM_DATA) != null;
    }
}