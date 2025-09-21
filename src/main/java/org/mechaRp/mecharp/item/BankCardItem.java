package org.mechaRp.mecharp.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class BankCardItem extends Item {
    public BankCardItem(Settings settings) {
        super(settings);
    }

    public static void setPinCode(ItemStack stack, String pin) {
        // Новый способ работы с NBT в современных версиях
        stack.set(MyDataComponents.PIN_CODE, pin);
        stack.set(MyDataComponents.CARD_NUMBER, generateCardNumber());
        stack.set(MyDataComponents.BALANCE, 0L);
    }

    public static String getPinCode(ItemStack stack) {
        return stack.get(MyDataComponents.PIN_CODE);
    }

    public static String getCardNumber(ItemStack stack) {
        String cardNumber = stack.get(MyDataComponents.CARD_NUMBER);
        return cardNumber != null ? cardNumber : "0000-0000-0000-0000";
    }

    public static long getBalance(ItemStack stack) {
        Long balance = stack.get(MyDataComponents.BALANCE);
        return balance != null ? balance : 0L;
    }

    public static void setBalance(ItemStack stack, long balance) {
        stack.set(MyDataComponents.BALANCE, balance);
    }

    private static String generateCardNumber() {
        return String.format("%04d-%04d-%04d-%04d",
                (int)(Math.random() * 10000),
                (int)(Math.random() * 10000),
                (int)(Math.random() * 10000),
                (int)(Math.random() * 10000)
        );
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable("item.mecharp.bank_card")
                .append(Text.literal(" (" + getCardNumber(stack) + ")").formatted(Formatting.GRAY));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}