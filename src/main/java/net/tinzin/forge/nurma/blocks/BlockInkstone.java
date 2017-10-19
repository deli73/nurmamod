package net.tinzin.forge.nurma.blocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tinzin.forge.nurma.Nurma;
import net.tinzin.forge.nurma.items.ModItems;
import net.tinzin.forge.nurma.sound.SoundRegistrator;

import java.util.Random;

public class BlockInkstone extends BlockRock {
    public static PropertyDirection FACING = BlockHorizontal.FACING;
    public static PropertyBool WATER = PropertyBool.create("water");
    public static PropertyBool INK = PropertyBool.create("ink");

    public static int WET =   (1<<2);
    public static int INKED = (1<<3);
    public static int FACE = 3;

    public static int WATERDRYTICKS = 3;
    public static int INKDRYTICKS = 1;

    public int timeSinceWet = 0;
    public int timeSinceInk = 0;

    public int tickTime = 1;

    public BlockInkstone(){
        super("inkstone");
        this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(WATER, false).withProperty(INK,false));
        this.setCreativeTab(Nurma.toolsTab);
        this.setTickRandomly(true);
    }

    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, FACING, INK, WATER);
    }

    public int getMetaFromState(IBlockState state){
        int meta = 0;
        meta |= state.getValue(FACING).getHorizontalIndex();
        if(state.getValue(WATER)){meta |= WET;}
        if(state.getValue(INK  )){meta |= INKED;}
        return meta;
    }

    public IBlockState getStateFromMeta(int meta){
        int facebits = meta & FACE;
        EnumFacing facing = EnumFacing.getHorizontal(facebits);
        boolean water = (meta & WET) != 0;
        boolean ink = (meta & INKED) != 0;
        return blockState.getBaseState().withProperty(FACING, facing).withProperty(WATER, water).withProperty(INK,ink);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack held = player.getHeldItem(EnumHand.MAIN_HAND);
        if(!state.getValue(WATER) && held.getItem() == Items.POTIONITEM && PotionUtils.getPotionFromItem(held) == PotionTypes.WATER){ // put water in the inkstone
            if(!player.isCreative()){player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.GLASS_BOTTLE));}//empty the bottle but only if not in creative
            world.setBlockState(pos, state.withProperty(WATER,true));
            timeSinceWet = 0;
            //SOUND
            world.playSound(hitX,hitY,hitZ, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.PLAYERS,1f,1f,true);
        }
        if(!state.getValue(INK) && state.getValue(WATER) && held.getItem() == ModItems.inkstick){ // make ink
            player.getHeldItem(EnumHand.MAIN_HAND).damageItem(1,player);
            world.setBlockState(pos, state.withProperty(INK,true));
            timeSinceInk = 0;
            //SOUND
            world.playSound(hitX,hitY,hitZ, SoundRegistrator.INK, SoundCategory.PLAYERS,1f,1f,true);
        }
        return true;
    }

    public int tickRate(World worldIn)
    {
        return tickTime;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if(state.getValue(WATER)){
            timeSinceWet++;
            if(timeSinceWet >= WATERDRYTICKS/tickTime){
                world.setBlockState(pos, state.withProperty(WATER,false));
            }
        }
        if(state.getValue(INK)){
            timeSinceInk++;
            if(timeSinceInk >= INKDRYTICKS/tickTime){
                world.setBlockState(pos, state.withProperty(INK,false));
            }
        }
    }

    //etc

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        AxisAlignedBB n = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.75D,   0.15625, 0.8125D);
        AxisAlignedBB s = new AxisAlignedBB(0.25D,   0.0D, 0.1875D, 0.8125D, 0.15625, 0.8125D);
        AxisAlignedBB e = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.15625, 0.75D);
        AxisAlignedBB w = new AxisAlignedBB(0.1875D, 0.0D, 0.25D,   0.8125D, 0.15625, 0.8125D);
        if(state.getValue(FACING) == EnumFacing.NORTH){return n;}
        if(state.getValue(FACING) == EnumFacing.SOUTH){return s;}
        if(state.getValue(FACING) == EnumFacing.EAST){return e;}
        if(state.getValue(FACING) == EnumFacing.WEST){return w;}
        return null; //this should NEVER happen
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side){
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_){
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
}
