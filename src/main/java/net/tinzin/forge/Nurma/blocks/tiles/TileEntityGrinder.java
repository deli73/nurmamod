package net.tinzin.forge.Nurma.blocks.tiles;

import jline.internal.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.tinzin.forge.Nurma.Nurma;
import net.tinzin.forge.Nurma.blocks.ModBlocks;
import net.tinzin.forge.Nurma.items.ModItems;
import net.tinzin.forge.Nurma.network.PacketResultSound;
import net.tinzin.forge.Nurma.sound.SoundRegistrator;

public class TileEntityGrinder extends TileEntity {
    private boolean success;
    private ItemStackHandler inventory = new ItemStackHandler(1);
    private Item[][] refineMap = {
            //{start,                                           success,                    failed}
            {Items.EMERALD,                                     ModItems.refinedEmerald,    ModItems.shatteredEmerald},
            {Items.DIAMOND,                                     ModItems.refinedDiamond,    ModItems.shatteredDiamond},
            {Items.PRISMARINE_CRYSTALS,                         ModItems.refinedPrismarine, Items.AIR},
            {Items.QUARTZ,                                      ModItems.refinedQuartz,     Items.AIR},
            //{Items.END_CRYSTAL,                                 Items.NETHER_STAR,          ModItems.glassShard},
            {Item.getItemFromBlock(Blocks.GLASS),               ModItems.glassShard,        ModItems.glassShard},
            {Items.GOLD_NUGGET,                                 ModItems.goldDust,          ModItems.goldDust},
            {Item.getItemFromBlock(ModBlocks.stonePolished),    ModItems.tabletStone,       ModItems.tabletStone}
    };

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

    void refine(World world, BlockPos pos, EntityPlayer player){
        if (world.isRemote) {return;}

        success = world.rand.nextBoolean();

        ItemStack stack = inventory.getStackInSlot(0);
        if (stack.getCount() == 1) {
            for (int i = 0; i < refineMap.length; i++) {
                if (stack.getItem() == refineMap[i][0]) {
                    IMessage packet;
                    if (success) {
                        //System.out.println("grind succeeded");
                        inventory.setStackInSlot(0, new ItemStack(refineMap[i][1]));
                        packet = new PacketResultSound(pos,true);
                    } else {
                        inventory.setStackInSlot(0, new ItemStack(refineMap[i][2]));
                        if(refineMap[i][1]==refineMap[i][2]){
                            //System.out.println("grind succeeded");
                            packet = new PacketResultSound(pos,true);
                        } else{
                            //System.out.println("grind failed");
                            packet = new PacketResultSound(pos,false);
                        }
                    }
                    //TODO make sound conditional based on config
                    Nurma.network.sendToAllAround(packet, new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64)); //SENT SOUND PACKET

                }
            }
        }
    }
}
