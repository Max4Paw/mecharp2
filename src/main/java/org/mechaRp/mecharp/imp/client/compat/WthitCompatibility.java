package org.mechaRp.mecharp.imp.client.compat;

import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.component.ItemComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.mechaRp.mecharp.item.ModItems;
import org.mechaRp.mecharp.registry.ModTags;

public class WthitCompatibility implements IWailaPlugin {

    @Override
    public void register(IRegistrar registrar) {
        registrar.addComponent(ToolRequirementComponent.INSTANCE, TooltipPosition.BODY, Block.class);
    }

    public static class ToolRequirementComponent implements IBlockComponentProvider {
        public static final ToolRequirementComponent INSTANCE = new ToolRequirementComponent();

        @Override
        public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
            BlockState state = accessor.getBlockState();
            PlayerEntity player = accessor.getPlayer();

            ItemStack requiredTool = null;
            String toolName = "";

            if (state.isIn(ModTags.Blocks.NEEDS_PALLADIUM_TOOL)) {
                requiredTool = new ItemStack(ModItems.PALLADIUM_PICKAXE);
                toolName = "Palladium Tool";
            }

            if (requiredTool != null) {
                boolean canHarvest = player != null && canHarvestWithTag(player.getMainHandStack(), state);
                String symbol = canHarvest ? Formatting.GREEN + "✔ " : Formatting.RED + "✖ ";

                tooltip.addLine(new ItemComponent(requiredTool));
                tooltip.addLine(Text.literal(symbol + "Requires " + toolName).formatted(Formatting.GRAY));
            }
        }
    }

    private static boolean canHarvestWithTag(ItemStack held, BlockState state) {
        if (held.isEmpty()) return false;


        if (held.isOf(ModItems.PALLADIUM_PICKAXE) ||
                held.isOf(ModItems.PALLADIUM_HAMMER) ||
                held.isOf(ModItems.PALLADIUM_PAXEL)) {
            return true;
        }

        return false;
    }
}