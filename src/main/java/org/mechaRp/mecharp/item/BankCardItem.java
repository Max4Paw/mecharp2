package org.mechaRp.mecharp.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.UUID;

public class BankCardItem extends Item {
    public BankCardItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient && !hasPin(stack)) {
            setPin(stack, user, "0000");
            user.sendMessage(Text.literal("Карта активирована с PIN: 0000"), false);
        }

        return ActionResult.SUCCESS;
    }
    public static boolean hasPin(ItemStack stack) {
        return stack.get(DataComponentTypes.CUSTOM_DATA) != null;
    }

    public static void setPin(ItemStack stack, PlayerEntity owner, String pin) {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("Pin", pin);
        nbt.putString("Owner", owner.getUuid().toString());
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    public static boolean verifyPin(ItemStack stack, String pin) {
        NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent != null) {
            NbtCompound nbt = nbtComponent.getNbt();
            return pin.equals(nbt.getString("Pin").orElse(""));
        }
        return false;
    }

    public static UUID getOwnerUuid(ItemStack stack) {
        NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent != null) {
            NbtCompound nbt = nbtComponent.getNbt();
            String ownerUuidString = nbt.getString("Owner").orElse("");
            if (!ownerUuidString.isEmpty()) {
                try {
                    return UUID.fromString(ownerUuidString);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }
        }
        return null;
    }
    public static void setPinCode(ItemStack stack, String pin) {
        setPinCode(stack, pin); // Алиас для совместимости
    }
}