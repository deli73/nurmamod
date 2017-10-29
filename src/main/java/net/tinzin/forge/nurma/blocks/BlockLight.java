package net.tinzin.forge.nurma.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockLight extends BlockBase {
    public BlockLight(){
        super(Material.GLASS, "lightsource");
        this.fullBlock = false;
        this.lightValue = 15;
        this.setHardness(0.4f);
        this.blockSoundType = (SoundType.GLASS);
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

    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_){
        return BlockFaceShape.MIDDLE_POLE;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.625D, 0.875D);
    }

}
