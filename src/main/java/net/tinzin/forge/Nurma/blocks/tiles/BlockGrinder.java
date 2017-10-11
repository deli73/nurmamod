package net.tinzin.forge.Nurma.blocks.tiles;

import jline.internal.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tinzin.forge.Nurma.blocks.BlockTileEntity;

public class BlockGrinder extends BlockTileEntity<TileEntityGrinder> {
    public BlockGrinder () {
        super(Material.ROCK, "grinder");
        this.fullBlock = false;
    }
    @Override
    public Class<TileEntityGrinder> getTileEntityClass() {
        return TileEntityGrinder.class;
    }

    @Nullable
    @Override
    public TileEntityGrinder createTileEntity(World world, IBlockState state) {
        return new TileEntityGrinder();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
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
}
