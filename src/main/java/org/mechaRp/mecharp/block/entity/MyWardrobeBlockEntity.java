package org.mechaRp.mecharp.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.mechaRp.mecharp.models.WardrobeModel;
import org.mechaRp.mecharp.registry.ModBlockEntities;



public class MyWardrobeBlockEntity extends BlockEntity implements Inventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(63, ItemStack.EMPTY); // Изменено с 81 на 63 ячейки

    public MyWardrobeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WARDROBE_BLOCK_ENTITY, pos, state);
    }

    // Inventory implementation
    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(inventory, slot, amount);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = Inventories.removeStack(inventory, slot);
        markDirty();
        return result;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world == null || this.world.getBlockEntity(this.pos) != this) {
            return false;
        }
        return player.squaredDistanceTo((double)this.pos.getX() + 0.5,
                (double)this.pos.getY() + 0.5,
                (double)this.pos.getZ() + 0.5) <= 64.0;
    }

    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }

    @Override
    protected void writeData(WriteView view) {
        Inventories.writeData(view, inventory);
    }

    @Override
    protected void readData(ReadView view) {
        Inventories.readData(view, inventory);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    private int numPlayersOpen;

    @Override
    public void onOpen(PlayerEntity player){
        Inventory.super.onOpen(player);
        MyWardrobeBlockEntity.this.numPlayersOpen++;
    }

    @Override
    public void onClose(PlayerEntity player){
        Inventory.super.onOpen(player);
        MyWardrobeBlockEntity.this.numPlayersOpen--;
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        NbtCompound nbt = super.toInitialChunkDataNbt(registryLookup);
        // Записываем только данные, необходимые для клиента

        nbt.putInt("numPlayersOpen", numPlayersOpen);
        return nbt;
    }
}