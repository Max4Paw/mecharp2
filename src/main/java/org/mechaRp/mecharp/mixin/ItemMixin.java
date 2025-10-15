//package org.mechaRp.mecharp.mixin;
//
//import net.minecraft.block.BlockState;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import org.mechaRp.mecharp.registry.ModTags;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(Item.class)
//public class ItemMixin {
//
//    @Inject(method = "getMiningSpeed", at = @At("HEAD"), cancellable = true)
//    private void checkPalladiumTool(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
//        // Если блок требует палладиевый инструмент
//        if (state.isIn(ModTags.Blocks.NEEDS_PALLADIUM_TOOL)) {
//            // Проверяем, является ли инструмент палладиевым
//            if (!stack.isIn(ModTags.Items.PALLADIUM_TOOLS)) {
//                // Если не палладиевый — скорость добычи 0
//                cir.setReturnValue(0.0F);
//            }
//        }
//    }
//}
