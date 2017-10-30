package net.tinzin.forge.nurma.blocks.tiles;

import jline.internal.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.tinzin.forge.nurma.ModConfig;
import net.tinzin.forge.nurma.recipes.Recipes;
import net.tinzin.forge.nurma.sound.SoundRegistrator;

public class TileEntityGrinder extends TileEntity {
    private boolean success;
    protected class OneItemHandler extends ItemStackHandler{
        OneItemHandler(){
            super(1);
        }
        @Override
        public int getSlotLimit(int slot)
        {
            return 1;
        }
    }

    private ItemStackHandler inventory = new OneItemHandler();

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }

    void refine(World world, BlockPos pos, EntityPlayer player) {
        ItemStack in = inventory.getStackInSlot(0);
        if(Recipes.keyCheck(in)){
            ItemStack output = Recipes.getOutputFor(in);
            if(ModConfig.client.sound.tile_sounds_on) {
                world.playSound(player, pos, SoundRegistrator.GRIND, SoundCategory.BLOCKS, 1, 1);
            }
            inventory.setStackInSlot(0,output);
        }
    }
}
