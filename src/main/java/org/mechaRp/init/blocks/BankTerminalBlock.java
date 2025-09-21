package org.mechaRp.init.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.mechaRp.init.entity.screen.BankTerminalScreenHandler;

public class BankTerminalBlock extends Block {

    public BankTerminalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        System.out.println("Block used at " + pos);

        if (!world.isClient) {
            System.out.println("Opening screen on server side");
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
                System.out.println("Screen opened successfully");
            } else {
                System.out.println("ScreenHandlerFactory is null!");
            }
        } else {
            System.out.println("Client side block use");
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory(
                (syncId, playerInventory, player) -> new BankTerminalScreenHandler(syncId, playerInventory),
                Text.translatable("container.mecharp.bank_terminal")
        );
    }
}