package net.tinzin.forge.nurma.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.tinzin.forge.nurma.Nurma;

public class BlockWood extends BlockRotatedPillar implements IBlockBase {
    protected String name;

    private EnumFacing NO = EnumFacing.DOWN; // this represents the inability to grow a branch, obviously

    private int maxHeight = 11;
    private int minBranchHeight = 4;
    private int maxBranchHeight = 10;

    public BlockWood(String name){
        super(Material.IRON);

        this.name = name;

        setUnlocalizedName(Nurma.modId+"."+name);
        setRegistryName(name);

        this.setLightLevel(3/15f);
        this.setLightOpacity(6);
        this.setHardness(3f);
        this.setResistance(25f);
        this.setSoundType(SoundType.GLASS);
        this.setCreativeTab(Nurma.creativeTab);
    }

    public void registerItemModel(Item itemBlock) {
        Nurma.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    public Block toBlock(){
        return this;
    }

    public boolean canGrowUp(IBlockState state, IBlockAccess world, BlockPos pos){
        if (state.getValue(AXIS).isHorizontal()){ return false; }

        boolean reachedBottom = false;
        BlockPos looking = pos;
        int height = 0;
        while (!reachedBottom){
            height++;
            looking = looking.down();
            if(world.getBlockState(looking).getBlock() != ModBlocks.crystalwood){
                looking = looking.up();
                reachedBottom = true;
            }
        }

        BlockPos[] surrounding = {
                looking.north(),
                looking.north().east(),
                looking.east(),
                looking.east().south(),
                looking.south(),
                looking.south().west(),
                looking.west(),
                looking.west().north()
        };
        for(BlockPos p : surrounding) {
            if(world.getBlockState(p).getBlock() != ModBlocks.crystalwater){
                return false;
            }
        }

        if(height < maxHeight){ return true; }
        return false;
    }

    public EnumFacing branchDir(IBlockState state, IBlockAccess world, BlockPos pos){
        return NO;
    }
}
