package net.tinzin.forge.nurma.blocks.tiles;

import jline.internal.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tinzin.forge.nurma.GuiHandler;
import net.tinzin.forge.nurma.Nurma;
import net.tinzin.forge.nurma.blocks.BlockTileEntity;

public class BlockParchment extends BlockTileEntity<TileEntityParchment> {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", facing -> facing != EnumFacing.DOWN && facing != EnumFacing.UP);

    private static final AxisAlignedBB BB_NORTH = new AxisAlignedBB(0.125,.25,0.9375,0.875,1,1);
    private static final AxisAlignedBB BB_WEST = new AxisAlignedBB(0.9375,.25,0.125,1,1,0.875);
    private static final AxisAlignedBB BB_SOUTH = new AxisAlignedBB(0.125,.25,0,0.875,1,0.0625);
    private static final AxisAlignedBB BB_EAST = new AxisAlignedBB(0,.25,0.125,0.0625,1,0.875);

    public BlockParchment(){
        super(Material.LEAVES, "parchment");
        this.fullBlock = false;
        SoundType soundType = new SoundType(1,1, SoundEvents.BLOCK_GRASS_PLACE,SoundEvents.BLOCK_GRASS_STEP,SoundEvents.BLOCK_COMPARATOR_CLICK,SoundEvents.BLOCK_CLOTH_HIT,SoundEvents.BLOCK_GRASS_FALL);
        this.setSoundType(soundType);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING,EnumFacing.NORTH));
        this.setCreativeTab(Nurma.creativeTab);
    }

    public BlockStateContainer createBlockState(){return new BlockStateContainer(this,FACING);}

    @Override
    public Class<TileEntityParchment> getTileEntityClass(){ return TileEntityParchment.class; }

    @Nullable
    @Override
    public TileEntityParchment createTileEntity(World world, IBlockState state) {
        return new TileEntityParchment();
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            player.openGui(Nurma.instance, GuiHandler.PARCHMENT, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (this.canPlaceAt(worldIn, pos, facing))
        {
            return this.getDefaultState().withProperty(FACING, facing);
        }
        else
        {
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                if (this.canPlaceAt(worldIn, pos, enumfacing))
                {
                    return this.getDefaultState().withProperty(FACING, enumfacing);
                }
            }

            return this.getDefaultState();
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : FACING.getAllowedValues())
        {
            if (this.canPlaceAt(worldIn, pos, enumfacing))
            {
                return true;
            }
        }

        return false;
    }

    private boolean canPlaceAt(World worldIn, BlockPos pos, EnumFacing facing) {
        BlockPos blockpos = pos.offset(facing.getOpposite());
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, blockpos, facing);

        return facing != EnumFacing.UP && facing != EnumFacing.DOWN && !isExceptBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta)
        {
            case 1:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST);
                break;
            case 2:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST);
                break;
            case 3:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH);
                break;
            default:
                break;
        }

        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        switch ((EnumFacing)state.getValue(FACING))
        {
            case EAST:
                i = i | 1;
                break;
            case WEST:
                i = i | 2;
                break;
            case SOUTH:
                i = i | 3;
                break;
            case NORTH:
                i = i | 4;
                break;
            default:
                break;
        }

        return i;
    }


    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos){
        if(!this.canPlaceAt(worldIn, pos, state.getValue(FACING))){
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
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

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING))
        {
            case EAST:
                return BB_EAST;
            case WEST:
                return BB_WEST;
            case SOUTH:
                return BB_SOUTH;
            case NORTH:
                return BB_NORTH;
            default:
                return NULL_AABB;
        }
    }

}
