//package org.mechaRp.mecharp.mixin;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.loot.context.LootContext;
//import net.minecraft.loot.context.LootContextParameters;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import org.mechaRp.mecharp.registry.ModTags;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//
//
//import java.util.Collections;
//import java.util.List;
//
//@Mixin(Block.class)
//public class BlockMixin {
//
//    @Inject(method = "afterBreak",
//            at = @At("HEAD"),
//            cancellable = true)
//    private void preventDropIfNotPalladium(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci) {
//        if (!world.isClient && state.isIn(ModTags.Blocks.NEEDS_PALLADIUM_TOOL)) {
//            ItemStack tool = player.getMainHandStack();
//            if (tool.isEmpty() || !tool.isIn(ModTags.Items.PALLADIUM_TOOLS)) {
//                // Убираем блок без дропа
//                world.removeBlock(pos, false);
//                ci.cancel();
//            }
//        }
//    }
//}
