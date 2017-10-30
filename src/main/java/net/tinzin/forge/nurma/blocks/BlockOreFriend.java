package net.tinzin.forge.nurma.blocks;

import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockOreFriend extends BlockRock {
    public BlockOreFriend(){
        super("friendly_ore");
        setHardness(3.0F);
        setResistance(5.0F);
    }

    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player){
        world.playSound(player, pos, SoundEvents.ENTITY_RABBIT_HURT ,SoundCategory.BLOCKS, 1f,2f);
        if(!world.isRemote){world.setBlockState(pos, Blocks.STONE.getDefaultState());}
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        worldIn.playSound(playerIn, pos, SoundEvents.ENTITY_WITCH_AMBIENT ,SoundCategory.BLOCKS, 1f,2f);
        if(!worldIn.isRemote){worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());}
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        if (state.getBlock() instanceof BlockStone){
            System.err.println("whAT THE fUCK");
        }
        else{
            System.err.println("???");
        }
        return 0;
    }
}
