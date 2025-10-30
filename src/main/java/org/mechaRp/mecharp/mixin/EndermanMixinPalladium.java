package org.mechaRp.mecharp.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.mechaRp.mecharp.config.PalladiumConfig;
import org.mechaRp.mecharp.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)


public abstract class EndermanMixinPalladium extends Entity {
    protected EndermanMixinPalladium(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), cancellable = true, method = "isPlayerStaring")
    private void isPlayerWearingEnderMask(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = (ItemStack) player.getInventory().getStack(3);
        if (stack.getItem() == ModItems.PALLADIUM_HELMET && PalladiumConfig.endermanWillNotBeAngryWithYouPalladium) {
            cir.setReturnValue(false);
        }
    }
}